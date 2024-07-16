package com.agenda_service_back.agendamento;

import com.agenda_service_back.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    //cria uma interface para ser implementada
}