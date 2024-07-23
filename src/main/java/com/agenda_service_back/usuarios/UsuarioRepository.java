package com.agenda_service_back.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario_email = :usuario_email")
    Usuario findByUsuario_email(@Param("usuario_email") String usuario_email);

    @Query("SELECT u FROM Usuario u WHERE u.usuario_id = :usuario_id ")
    Usuario findById(@Param("usuario_id ") String usuario_id );






}
