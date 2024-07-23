package com.agenda_service_back.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private final HttpServletRequest request;

    public CurrentUser(HttpServletRequest request) {
        this.request = request;
    }

    public Long getCurrentUserId() {
        Long usuario_id = (Long) request.getAttribute("usuario_id");
        if (usuario_id == null) {
            throw new RuntimeException("Token inválido ou ausente");
        }
        return usuario_id;
    }
}
