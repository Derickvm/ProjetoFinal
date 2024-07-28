package com.agenda_service_back.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private SecretKey secretKey;

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String usuario_id,String usuario_nome) {
        return Jwts
                .builder()
                .setSubject(usuario_id)
                .claim("usuario_id", usuario_id)
                .claim("usuario_nome", usuario_nome)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 90))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String usuario_id) {
        Claims claims = extractAllClaims(token);
        final String subject = claims.getSubject();
        return usuario_id.equals(subject) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        Integer userIdInt = claims.get("usuario_id", Integer.class);
        return userIdInt != null ? userIdInt.toString() : null;
    }
}
