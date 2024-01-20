package com.devmarvin.noticias.services.impl;

import com.devmarvin.noticias.dto.AuthenticationResponseDTO;
import com.devmarvin.noticias.dto.RegistrarUsuarioRequestDTO;
import com.devmarvin.noticias.dto.UserLoginRequestDTO;
import com.devmarvin.noticias.entities.Role;
import com.devmarvin.noticias.entities.Usuario;
import com.devmarvin.noticias.jwt.JwtService;
import com.devmarvin.noticias.repositories.UserRepository;
import com.devmarvin.noticias.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO login(UserLoginRequestDTO usuario) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));

        //Generar el token
        Usuario usuarioEntidad = userRepository.findByUsername(usuario.getUsername())
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));
        String token = jwtService.obtenerToken(usuarioEntidad);

        return AuthenticationResponseDTO.builder()
                .token(token)
                .build();
    }

    @Override
    public Usuario getUsuario(String email) {
        return null;
    }

    @Override
    public Usuario crearUsuario(RegistrarUsuarioRequestDTO usuario) {

        Usuario usuarioEntidad = new Usuario();

        BeanUtils.copyProperties(usuario, usuarioEntidad);
        //como el password en UserRegisterRequest y en UserEntity se llaman diferente entonces lo que se hace es copiarlo manualmente
        usuarioEntidad.setRole(Role.USER);
        usuarioEntidad.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return userRepository.save(usuarioEntidad);
    }
}
