package com.gdsc.colot.controller.parking;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.parking.dto.ParkingRequestDto;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.service.parking.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/parking")
@RestController
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse parking(@Valid @RequestBody ParkingRequestDto requestDto) {
        if (parkingService.parking(requestDto)) {
            return BaseResponse.success(SuccessCode.PARKING_IN_SUCCESS);
        } else {
            return BaseResponse.success(SuccessCode.PARKING_OUT_SUCCESS);
        }
    }

}
