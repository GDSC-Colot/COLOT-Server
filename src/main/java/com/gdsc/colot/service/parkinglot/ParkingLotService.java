package com.gdsc.colot.service.parkinglot;

import com.gdsc.colot.controller.parkinglot.dto.request.ParkingLotRequestDto;
import com.gdsc.colot.controller.parkinglot.dto.response.ParkingLotResponseDto;
import com.gdsc.colot.domain.parkingLot.ParkingLot;
import com.gdsc.colot.domain.parkingLot.ParkingLotRepository;
import com.gdsc.colot.domain.user.User;
import com.gdsc.colot.domain.user.UserRepository;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.BadRequestException;
import com.gdsc.colot.exception.model.NotFoundException;
import com.gdsc.colot.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Transactional
    public void createParkingLot(ParkingLotRequestDto requestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String imageUrl = s3Service.uploadImage(requestDto.getImage(), "parking-lot");

        ParkingLot parkingLot = ParkingLot.builder()
                .address(requestDto.getAddress())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .type(requestDto.getType())
                .operationMethod(requestDto.getOperationMethod())
                .image(imageUrl)
                .capacity(requestDto.getCapacity())
                .chargePerMin(requestDto.getChargePerMin())
                .user(user)
                .build();


        parkingLotRepository.save(parkingLot);
    }


    public List<ParkingLotResponseDto> searchParkingLot(Long level, Long min, Double lat, Double lng) {
        List<ParkingLotResponseDto> result = new ArrayList<>();
        List<Object[]> mapList = new ArrayList<>();

        if (level == 1) {
            mapList = parkingLotRepository.findNearbyParkingLots(lat, lng, 0.5);
        } else if (level == 2) {
            mapList = parkingLotRepository.findNearbyParkingLots(lat, lng, 2.0);
        } else {
            throw new BadRequestException(ErrorCode.MAP_LEVEL_INVALID_EXCEPTION, ErrorCode.MAP_LEVEL_INVALID_EXCEPTION.getMessage());
        }

        for (Object[] parkingLotDoubleMap : mapList) {
            ParkingLot parkingLot = (ParkingLot) parkingLotDoubleMap[0];
            Double distance = (Double) parkingLotDoubleMap[1];
            result.add(
                    ParkingLotResponseDto.of(
                            parkingLot.getId(),
                            parkingLot.getLatitude(),
                            parkingLot.getLongitude(),
                            distance,
                            parkingLot.getChargePerMin() * min,
                            LocalTime.of(0,0),
                            LocalTime.of(23,59)
                    )
            );
        }

        return result;
    }

    @PostConstruct
    public void initTestData() {
        // 중심 위치 좌표
        double centerLatitude = 37.5642135;
        double centerLongitude = 127.0016985;

        // 500m 반경 내에 있는 주차장 생성
        for (int i = 0; i < 10; i++) {
            double randomLatitude = centerLatitude + Math.random() * 0.009;
            double randomLongitude = centerLongitude + Math.random() * 0.009;
            createParkingLot(randomLatitude, randomLongitude, i + 1);
        }

        // 2km 반경 내에 있는 주차장 생성
        for (int i = 0; i < 20; i++) {
            double randomLatitude = centerLatitude + Math.random() * 0.018;
            double randomLongitude = centerLongitude + Math.random() * 0.018;
            createParkingLot(randomLatitude, randomLongitude, i + 1);
        }
    }

    private void createParkingLot(double latitude, double longitude, int index) {
        ParkingLot parkingLot = ParkingLot.builder()
                .address("Test Address" + index)
                .latitude(latitude)
                .longitude(longitude)
                .name("Test Parking Lot" + index)
                .description("Test Description" + index)
                .type(true)
                .operationMethod(true)
                .image("test_image.jpg")
                .capacity(100L)
                .chargePerMin(50L)// 사용자 정보 설정 필요
                .build();
        parkingLotRepository.save(parkingLot);
    }

}
