package com.agenda_service_back.servico;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.categoria.CategoriaDTO;
import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long servico_id;
    private String servico_nome;
    private String servico_informacoes_extras;
    private String servico_descricao;
    private Double servico_preco;
    private CategoriaDTO categoria;  // Corrigido para CategoriaDTO
    private PrestadorDTO prestador;  // Corrigido para PrestadorDTO


    // Novo construtor que aceita um objeto Servico
    public ServicoDTO(Servico servico) {
        this.servico_id = servico.getServico_id();
        this.servico_nome = servico.getServico_nome();
        this.servico_informacoes_extras = servico.getServico_informacoes_extras();
        this.servico_descricao = servico.getServico_descricao();
        this.servico_preco = servico.getServico_preco();
        this.categoria = new CategoriaDTO(servico.getCategoria());  // Converte Categoria para CategoriaDTO
        this.prestador = new PrestadorDTO(servico.getPrestador());  // Converte Prestador para PrestadorDTO
    }

    @Override
    public String toString() {
        return "ServicoDTO{" +
                "servico_id=" + servico_id +
                ", servico_nome='" + servico_nome + '\'' +
                ", servico_informacoes_extras='" + servico_informacoes_extras + '\'' +
                ", servico_descricao='" + servico_descricao + '\'' +
                ", servico_preco=" + servico_preco +
                ", categoria=" + categoria +
                ", prestador=" + prestador +
                '}';
    }
}
