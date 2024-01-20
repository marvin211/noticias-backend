package com.devmarvin.noticias.controllers;

import com.devmarvin.noticias.dto.AuthenticationResponseDTO;
import com.devmarvin.noticias.dto.RegistrarUsuarioRequestDTO;
import com.devmarvin.noticias.dto.UserLoginRequestDTO;
import com.devmarvin.noticias.dto.UsuarioResponseDTO;
import com.devmarvin.noticias.entities.Usuario;
import com.devmarvin.noticias.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    //Registrar
    @PostMapping("/registrar")
    public UsuarioResponseDTO crearUsuario(@RequestBody @Valid RegistrarUsuarioRequestDTO usuarioRequestDTO ){
        Usuario usuario = usuarioService.crearUsuario(usuarioRequestDTO);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        BeanUtils.copyProperties(usuario, usuarioResponseDTO);

        return usuarioResponseDTO;
    }

    @GetMapping("/user")
    public UsuarioResponseDTO getUsuario() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(username);

        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO();

        BeanUtils.copyProperties(usuario, usuarioResponse);

        return usuarioResponse;
    }
}
