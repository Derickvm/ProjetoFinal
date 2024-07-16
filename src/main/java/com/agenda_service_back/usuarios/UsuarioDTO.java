package com.agenda_service_back.usuarios;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.telefone.Telefone;
import com.agenda_service_back.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long usuario_id;

    private String usuario_nome;

    private String usuario_cpf;

    private String usuario_email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate usuario_data_nascimento;

    private String usuario_senha;
    @JsonIgnore
    private Endereco endereco;
    @JsonIgnore
    private List<Telefone> telefones;
    @JsonIgnore
    private List<Agendamento> agendamentos;


}
