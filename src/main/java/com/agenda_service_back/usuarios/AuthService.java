package com.agenda_service_back.usuarios;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecretKey secretKey;

    private static final long EXPIRATION_TIME = 3600000;
    // 1 hora em milissegundos

    public String authenticate(String usuario_email, String usuario_senha) {
        Usuario usuario = usuarioRepository.findByUsuario_email(usuario_email);
        if (usuario != null && usuario.getUsuario_senha().equals(usuario_senha)) {
            return Jwts.builder()
                    .setSubject(usuario.getUsuario_email())
                    .claim("usuario_id", usuario.getUsuario_id())
                    .claim("usuario_nome", usuario.getUsuario_nome())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        }
        return null;
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token inválido ou ausente", e);
        }
    }
}
