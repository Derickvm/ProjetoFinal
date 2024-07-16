package com.agenda_service_back.prestador;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PrestadorMapper {
    @Mapping(source = "prestador_id", target = "prestador_id")
    PrestadorDTO toDTO(Prestador prestador);
    @Mapping(source = "prestadorDTO.prestador_id", target = "prestador_id")
    Prestador toEntity(PrestadorDTO prestadorDTO);

    //recebendo lista de prestadores do banco em DTO
    List<PrestadorDTO> toDTO(List<Prestador> prestador);

    @Mappings({
            @Mapping(source = "prestadorDTO.prestador_id", target = "prestador_id"),
            @Mapping(source = "prestadorDTO.prestador_nome", target = "prestador_nome"),
            @Mapping(source = "prestadorDTO.prestador_cnpj", target = "prestador_cnpj"),
            @Mapping(source = "prestadorDTO.prestador_cpf", target = "prestador_cpf"),
            @Mapping(source = "prestadorDTO.prestador_razao_social", target = "prestador_razao_social"),
            @Mapping(source = "prestadorDTO.prestador_email", target = "prestador_email"),
            @Mapping(source = "prestadorDTO.prestador_senha", target = "prestador_senha"),
            @Mapping(source = "prestadorDTO.endereco", target = "endereco"),
            @Mapping(source = "prestadorDTO.servicos", target = "servicos"),
            @Mapping(source = "prestadorDTO.telefones", target = "telefones"),

    })
    Prestador updateEntity(PrestadorDTO prestadorDTO, Prestador prestador);
}