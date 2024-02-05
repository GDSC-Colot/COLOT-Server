package com.gdsc.colot.controller.carStopper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarStopperRequestDto {

    @NotBlank(message = "Please insert your stopper serial number")
    private String serialNum;
    @NotNull(message = "Please insert your parking lot id")
    private Long parkingLotId;

}
