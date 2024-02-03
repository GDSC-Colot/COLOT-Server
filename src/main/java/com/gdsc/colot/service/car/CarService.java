package com.gdsc.colot.service.car;

import com.gdsc.colot.controller.car.dto.request.CarCreateRequestDto;
import com.gdsc.colot.domain.car.Car;
import com.gdsc.colot.domain.car.CarRepository;
import com.gdsc.colot.domain.user.User;
import com.gdsc.colot.domain.user.UserRepository;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.DuplicateCarException;
import com.gdsc.colot.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Transactional
    public void createCar(CarCreateRequestDto requestDto, String email) {
        checkDuplicateCar(requestDto.getPlateNum());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Car car = Car.builder()
                .plateNum(requestDto.getPlateNum())
                .nickname(requestDto.getNickName())
                .user(user)
                .build();

        carRepository.save(car);
    }

    private void checkDuplicateCar(String plateNum) {
        if (carRepository.existsByPlateNum(plateNum)) {
            throw new DuplicateCarException(ErrorCode.DUPLICATE_CAR_EXCEPTION, ErrorCode.DUPLICATE_CAR_EXCEPTION.getMessage());
        }
    }

}
