package com.agenda_service_back.prestador;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.telefone.Telefone;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.List;

@Data //getter setter tostring equals hashcode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestador")
public class Prestador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prestador_id")
    private long prestador_id;

    @Column(name = "prestador_nome")
    private String prestador_nome;

    @Column(name = "prestador_cpf",unique = true)
    private String prestador_cpf;

    @Column(name = "prestador_cnpj",unique = true)
    private String prestador_cnpj;

    @Column(name = "prestador_email",unique = true)
    private String prestador_email;

    @Column(name = "prestador_senha")
    private String prestador_senha;

    @Column(name = "prestador_razao_social")
    private String prestador_razao_social;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestador_endereco_id",nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "telefone_id",fetch = FetchType.LAZY)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "servico_id",fetch = FetchType.LAZY)
    private List<Servico> servicos;

}
