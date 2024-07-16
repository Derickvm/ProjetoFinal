package com.agenda_service_back.usuarios;

import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "endereco", target = "endereco", ignore = true)
    @Mapping(source = "telefones", target = "telefones", ignore = true)
    @Mapping(source = "agendamentos", target = "agendamentos", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "endereco", target = "endereco", ignore = true)
    @Mapping(source = "telefones", target = "telefones", ignore = true)
    @Mapping(source = "agendamentos", target = "agendamentos", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);


    @Mappings({
            @Mapping(source = "usuarioDTO.usuario_id", target = "usuario_id"),
            @Mapping(source = "usuarioDTO.usuario_nome", target = "usuario_nome"),
            @Mapping(source = "usuarioDTO.usuario_cpf", target = "usuario_cpf"),
            @Mapping(source = "usuarioDTO.usuario_email", target = "usuario_email"),
            @Mapping(source = "usuarioDTO.usuario_data_nascimento", target = "usuario_data_nascimento"),
            @Mapping(source = "usuarioDTO.usuario_senha", target = "usuario_senha"),
            @Mapping(source = "usuarioDTO.endereco", target = "endereco"),
            @Mapping(source = "usuarioDTO.telefones", target = "telefones"),
            @Mapping(source = "usuarioDTO.agendamentos", target = "agendamentos")

    })
    Usuario updateEntity(UsuarioDTO usuarioDTO, Usuario usuario);
}

