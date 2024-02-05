package com.gdsc.colot.controller.car;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.car.dto.request.CarRequestDto;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.security.UserDetailsImpl;
import com.gdsc.colot.service.car.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CarController {

    private final CarService carService;

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse createCar(@AuthenticationPrincipal UserDetailsImpl loginUser, @RequestBody @Valid CarRequestDto requestDto) {
        carService.createCar(requestDto, loginUser.getEmail());
        return BaseResponse.success(SuccessCode.CAR_CREATED_SUCCESS);
    }

}
