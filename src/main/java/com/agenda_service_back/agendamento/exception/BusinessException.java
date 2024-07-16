package com.agenda_service_back.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Set response status to 400 (Bad Request)
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super("Erro de negócio"); // Default message
    }

    public BusinessException(String message) {
        super(message); // Customized message
    }
}
