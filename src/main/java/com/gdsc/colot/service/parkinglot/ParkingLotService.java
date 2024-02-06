package com.gdsc.colot.service.parkinglot;

import com.gdsc.colot.controller.parkinglot.dto.request.ParkingLotRequestDto;
import com.gdsc.colot.domain.parkingLot.ParkingLot;
import com.gdsc.colot.domain.parkingLot.ParkingLotRepository;
import com.gdsc.colot.domain.user.User;
import com.gdsc.colot.domain.user.UserRepository;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.NotFoundException;
import com.gdsc.colot.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
