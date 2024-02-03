package com.gdsc.colot.controller.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequestDto {

    @NotBlank(message = "Please enter your e-mail")
    private String username;

    @NotBlank(message = "Please enter your password")
    private String password;

}
