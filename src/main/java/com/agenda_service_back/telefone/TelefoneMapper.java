package com.agenda_service_back.telefone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    @Mappings({
            @Mapping(source = "telefone_id", target = "telefone_id"),
            @Mapping(source = "telefone_numero", target = "telefone_numero"),
            @Mapping(source = "usuario.usuario_id", target = "usuario.usuario_id"),
            @Mapping(source = "prestador.prestador_id", target = "prestador.prestador_id")
    })
    TelefoneDTO toDTO(Telefone telefone);

    @Mappings({
            @Mapping(source = "telefoneDTO.telefone_id", target = "telefone_id"),
            @Mapping(source = "telefoneDTO.telefone_numero", target = "telefone_numero"),
            @Mapping(source = "telefoneDTO.usuario.usuario_id", target = "usuario.usuario_id"),
            @Mapping(source = "telefoneDTO.prestador.prestador_id", target = "prestador.prestador_id")
    })
    Telefone toEntity(TelefoneDTO telefoneDTO);

    List<TelefoneDTO> toDTOList(List<Telefone> telefone);

    @Mappings({
            @Mapping(source = "telefoneDTO.telefone_id", target = "telefone_id"),
            @Mapping(source = "telefoneDTO.telefone_numero", target = "telefone_numero"),
            @Mapping(source = "telefoneDTO.usuario.usuario_id", target = "usuario.usuario_id"),
            @Mapping(source = "telefoneDTO.prestador.prestador_id", target = "prestador.prestador_id")
    })
    Telefone updateEntity(TelefoneDTO telefoneDTO, Telefone telefone);
}