package com.agenda_service_back.prestador;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.endereco.EnderecoDTO;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.telefone.Telefone;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long prestador_id;

    @NotNull(message = "O campo NOME é requerido")
    private  String prestador_nome;

    private  String prestador_cnpj;

    @NotNull(message = "O campo CPF é requerido")
    private  String prestador_cpf;

    @NotNull(message = "O campo RAZAO SOCIAL é requerido")
    private  String prestador_razao_social;

    @NotNull(message = "O campo EMAIL é requerido")
    private  String prestador_email;

    private String prestador_senha;

    private Endereco endereco;

    private List<Servico> servicos;

    private List<Telefone> telefones;

    public PrestadorDTO(Prestador prestador) {
        this.prestador_id = prestador.getPrestador_id();
        this.prestador_nome = prestador.getPrestador_nome();
        this.prestador_cnpj = prestador.getPrestador_cnpj();
        this.prestador_cpf= prestador.getPrestador_cpf();
        this.prestador_razao_social = prestador.getPrestador_razao_social();
        this.prestador_email = prestador.getPrestador_email();
        this.prestador_senha = prestador.getPrestador_senha();
        this.endereco = prestador.getEndereco();
        this.servicos = prestador.getServicos();
        this.telefones = prestador.getTelefones();
    }
}