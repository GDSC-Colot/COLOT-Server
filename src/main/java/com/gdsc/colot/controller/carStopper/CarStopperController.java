package com.gdsc.colot.controller.carStopper;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.carStopper.dto.request.CarStopperRequestDto;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.service.carStopper.CarStopperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-stopper")
@RestController
public class CarStopperController {

    private final CarStopperService carStopperService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse createCarStopper(@Valid @RequestBody CarStopperRequestDto requestDto) {
        carStopperService.createCarStopper(requestDto);
        return BaseResponse.success(SuccessCode.CAR_STOPPER_CREATED_SUCCESS);
    }

}
