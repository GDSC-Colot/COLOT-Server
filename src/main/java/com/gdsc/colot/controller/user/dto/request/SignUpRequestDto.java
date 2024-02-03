package com.gdsc.colot.controller.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @Size(min = 1, max = 20, message = "Name not entered or name too long")
    private String name;

    @NotBlank(message = "Please enter your e-mail")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "[a-zA-z!@#$%^&*-_]{6,20}", message = "Only 6 to 20 long alphabets, numbers, and special characters are allowed")
    private String password;

}
