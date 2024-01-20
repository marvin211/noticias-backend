package com.devmarvin.noticias.dto;

import com.devmarvin.noticias.annotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrarUsuarioRequestDTO {

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Email
    @UniqueEmail
    private String username;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String password;
}
