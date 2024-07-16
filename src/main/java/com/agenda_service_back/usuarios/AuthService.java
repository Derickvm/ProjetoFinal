package com.agenda_service_back.usuarios;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("MySecretKeyForJwtTokenGeneration".getBytes());
    private static final long EXPIRATION_TIME = 3600000; // 1 hora em milissegundos

    public String authenticate(String usuario_email, String usuario_senha) {
        Usuario usuario = usuarioRepository.findByUsuario_email(usuario_email);
        if (usuario != null && usuario.getUsuario_senha().equals(usuario_senha)) {
           return Jwts.builder()
                   .setSubject(usuario.getUsuario_email())
                  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                 //.signWith(SECRET_KEY)
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                 .compact();
      }
        return null;
    }
}
