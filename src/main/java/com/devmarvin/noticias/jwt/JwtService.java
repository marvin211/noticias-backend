package com.devmarvin.noticias.jwt;


import com.devmarvin.noticias.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    //Obtener el token
    public String obtenerToken(Usuario usuario) {
        return obtenerToken(new HashMap<>(), usuario);
    }

    private String obtenerToken(Map<String, Object> extraClaims, Usuario usuario) {
        //Generar token
        return Jwts.builder()
                .claims(extraClaims)
                .claim("usuarioId", usuario.getId())
                .claim(("name"), usuario.getNombre())
                .claim("role", usuario.getRole())
                .subject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Obtener el username
    public String obtenerUsernameToken(String token) {
        return obtenerClaim(token, Claims::getSubject);
    }

    //Saber si el token es valido
    public boolean esValidoToken(String token, UserDetails userDetails) {
        final String username = obtenerUsernameToken(token);
        return (username.equals(userDetails.getUsername()) && !expiroToken(token));
    }

    //Obtener todos los claims del token
    private Claims getAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Obtener un Claims en particular
    public <T> T obtenerClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Fecha de expiracion del token
    private Date obtenerFechaExpiracion(String token){
        return obtenerClaim(token, Claims::getExpiration);
    }

    //Token ha expirado
    private boolean expiroToken(String token){
        return obtenerFechaExpiracion(token).before(new Date());
    }

}
