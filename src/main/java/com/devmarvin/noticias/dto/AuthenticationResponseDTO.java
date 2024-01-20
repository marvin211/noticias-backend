package com.devmarvin.noticias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AuthenticationResponseDTO {
    private String token;
}
