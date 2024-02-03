package com.gdsc.colot.controller.car.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateRequestDto {

    @NotBlank(message = "Please enter your vehicle number")
    private String plateNum;

    private String nickName;

}
