package com.agenda_service_back.usuarios;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.telefone.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private long usuario_id;

    @Column(name = "usuario_nome",unique = true)
    private String usuario_nome;

    @Column(name = "usuario_cpf",unique = true)
    private String usuario_cpf;

    @Column(name = "usuario_email",unique = true)
    private String usuario_email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "usuario_data_nascimento")
    private LocalDate usuario_data_nascimento;

    @Column(name = "usuario_senha")
    private String usuario_senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "telefone_id",fetch = FetchType.EAGER)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "agendamento_id",fetch = FetchType.EAGER)
    private List<Agendamento> agendamentos;

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_id=" + usuario_id +
                ", usuario_cpf='" + usuario_cpf + '\'' +
                ", usuario_email='" + usuario_email + '\'' +
                ", usuario_data_nascimento=" + usuario_data_nascimento +
                ", usuario_senha='" + usuario_senha + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
