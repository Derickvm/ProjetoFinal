package com.agenda_service_back.agendamento;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

    @Query("SELECT p FROM Agendamento p WHERE p.agendamento_id = :agendamento_id")
    List<Agendamento> findByAgendamento_id(@Param("agendamento_id") Long agendamento_id);
}