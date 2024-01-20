package com.devmarvin.noticias.services;

import com.devmarvin.noticias.dto.AuthenticationResponseDTO;
import com.devmarvin.noticias.dto.RegistrarUsuarioRequestDTO;
import com.devmarvin.noticias.dto.UserLoginRequestDTO;
import com.devmarvin.noticias.entities.Usuario;

public interface UsuarioService {
    AuthenticationResponseDTO login(UserLoginRequestDTO usuario);
    Usuario getUsuario(String email);
    Usuario crearUsuario(RegistrarUsuarioRequestDTO usuario);
}
