package com.agenda_service_back.servico;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    @Mappings({
            @Mapping(source = "servico_id", target = "servico_id"),
            @Mapping(source = "servico_nome", target = "servico_nome"),
            @Mapping(source = "servico_descricao", target = "servico_descricao"),
            @Mapping(source = "servico_informacoes_extras", target = "servico_informacoes_extras"),
            @Mapping(source = "servico_preco", target = "servico_preco"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "prestador", target = "prestador")
    })
    ServicoDTO toDTO(Servico servico);

    @Mapping(source = "servicoDTO.servico_id", target = "servico_id")
    Servico toEntity(ServicoDTO servicoDTO);

    List<ServicoDTO> toDTOList(List<Servico> servicos);

    /*List<Servico> toEntityList(List<ServicoDTO> servicoDTOs);*/
    @Mappings({
            @Mapping(source = "servicoDTO.servico_id", target = "servico_id"),
            @Mapping(source = "servicoDTO.servico_nome", target = "servico_nome"),
            @Mapping(source = "servicoDTO.servico_informacoes_extras", target = "servico_informacoes_extras"),
            @Mapping(source = "servicoDTO.servico_descricao", target = "servico_descricao"),
            @Mapping(source = "servicoDTO.servico_preco", target = "servico_preco"),
            @Mapping(source = "servicoDTO.categoria", target = "categoria"),
            @Mapping(source = "servicoDTO.prestador", target = "prestador")
    })
    Servico updateEntity(ServicoDTO servicoDTO, Servico servico);

}
