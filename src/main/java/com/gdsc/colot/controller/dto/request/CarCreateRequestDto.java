package com.gdsc.colot.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateRequestDto {

    @NotBlank(message = "차량 번호를 입력하세요")
    private String plateNum;

    private String nickName;

}
