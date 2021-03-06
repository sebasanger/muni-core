package com.sanger.muni.dto.auth;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateUserDto {

    @NotBlank(message = "El token no puede estar vacio")
    private String token;
    @NotBlank
    private String password;
    @NotBlank
    private String password2;

}
