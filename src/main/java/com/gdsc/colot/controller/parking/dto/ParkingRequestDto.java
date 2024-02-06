package com.gdsc.colot.controller.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRequestDto {

    @NotBlank(message = "Please insert car stopper id")
    private String carStopperId;
    @NotBlank(message = "Please insert car plate number")
    private String carPlateNum;
    @NotBlank(message = "Please specify parking status")
    private String status;
    private String time;

}
