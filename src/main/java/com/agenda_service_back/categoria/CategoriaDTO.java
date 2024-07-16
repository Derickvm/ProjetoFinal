package com.agenda_service_back.categoria;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long categoria_id;
    @NotNull(message = " o campo NOME é requerido.")
    private String categoria_nome;
    @NotNull(message = "o campo DESCRICAO é requerido.")
    private String categoria_descricao;
    //private List<ServicoDTO> servicos;

     public CategoriaDTO(Categoria categoria) {
        this.categoria_id = categoria.getCategoria_id();
        this.categoria_nome = categoria.getCategoria_nome();
        this.categoria_descricao = categoria.getCategoria_descricao();
    }
}
