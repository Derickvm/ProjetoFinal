package com.agenda_service_back.servico;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.prestador.Prestador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servico")
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private long servico_id;

    @Column(name = "servico_nome")
    private String servico_nome;

    @Column(name = "servico_informacoes_extras")
    private String servico_informacoes_extras;

    @Column(name = "servico_descricao")
    private String servico_descricao;

    @Column(name = "servico_preco")
    private Double servico_preco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_prestador_id", nullable = false)
    private Prestador prestador;

    @Override
    public String toString() {
        return "Servico{" +
                "servico_id=" + servico_id +
                ", servico_nome='" + servico_nome + '\'' +
                ", servico_informacoes_extras='" + servico_informacoes_extras + '\'' +
                ", servico_descricao='" + servico_descricao + '\'' +
                ", servico_preco=" + servico_preco +
                '}';
    }
}
