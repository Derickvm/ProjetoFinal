package com.agenda_service_back.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    @Query("SELECT s FROM Servico s WHERE s.servico_nome = :servico_nome")
    List<Servico> findByServico_nome(@Param("servico_nome") String servico_nome);

     @Query("SELECT s FROM Servico s WHERE s.servico_id = :servico_id")
    List<Servico> findByServico_id(@Param("servico_id") BigInteger servico_id);
}