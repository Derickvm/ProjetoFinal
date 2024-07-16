package com.agenda_service_back.prestador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrestadorRepository extends JpaRepository<Prestador,Long> {
    //cria uma interface para ser implementada
//    @Query("SELECT p FROM Prestador p WHERE p.servico.id = :servicoId")
//    List<Prestadores> findByServicoId(@Param("servicoId") Long servicoId);


}