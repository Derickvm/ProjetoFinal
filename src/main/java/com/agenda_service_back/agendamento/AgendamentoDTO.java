package com.agenda_service_back.agendamento;

import com.agenda_service_back.enums.StatusEnum;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long agendamento_id;

    @NotNull(message = "O campo DATA DE AGENDAMENTO é requerido")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agendamento_data = LocalDate.now();

    @NotNull(message = "O campo HORA DE AGENDAMENTO é requerido")
    @JsonFormat(pattern ="HH:mm")
    private LocalTime agendamento_hora;


    private String agendamento_observacao;


    private StatusEnum agendamento_status;

    @NotNull(message = "O campo Usuario é requerido")
    private Usuario usuario;


    @NotNull(message = "O campo SERVIÇOS é requerido")
    private Servico servico;
}