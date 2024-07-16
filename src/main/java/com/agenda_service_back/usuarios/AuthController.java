package com.agenda_service_back.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {

        String usuario_email = credentials.get("usuario_email");
        String usuario_senha = credentials.get("usuario_senha");
        System.out.println("Email recebido: " + usuario_email);
    System.out.println("Senha recebida: " + usuario_senha);

        String token = authService.authenticate(usuario_email, usuario_senha);
        Map<String, String> response = new HashMap<>();
        if (token != null) {
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }
}
