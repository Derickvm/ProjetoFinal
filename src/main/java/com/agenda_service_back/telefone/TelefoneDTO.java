package com.agenda_service_back.telefone;

import com.agenda_service_back.prestador.PrestadorDTO;
import com.agenda_service_back.usuarios.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long telefone_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(XX) XXXX-XXXX")
    private String telefone_numero;
    private UsuarioDTO usuario;
    private PrestadorDTO prestador;
}