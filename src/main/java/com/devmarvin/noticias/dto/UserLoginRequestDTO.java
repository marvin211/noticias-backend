package com.devmarvin.noticias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequestDTO {
    @NotEmpty
    @Email
    private String username;

    @NotEmpty
    private String password;
}
