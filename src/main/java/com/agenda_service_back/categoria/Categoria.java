package com.agenda_service_back.categoria;

import com.agenda_service_back.servico.Servico;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data //-Getters -Setters Hash Equals toString
//@Getter
//@Setter
@AllArgsConstructor //usar com cuidado em tabelas com chave estrangeira
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoria_id;

    @Column(name = "categoria_nome")
    private String categoria_nome;

    @Column(name = "categoria_descricao")
    private String categoria_descricao;

    // @OneToMany(mappedBy = "servico_id",fetch = FetchType.LAZY)
    // private List<Servico> servicos;

}
