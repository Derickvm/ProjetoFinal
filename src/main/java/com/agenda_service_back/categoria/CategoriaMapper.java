package com.agenda_service_back.categoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    //Conversor Entity(Model) para DTO
    @Mapping(source = "categoria_id", target = "categoria_id")
    CategoriaDTO toDTO(Categoria categoria);

    @Mapping(source = "categoriaDTO.categoria_id", target="categoria_id")
    Categoria toEntity(CategoriaDTO categoriaDTO);

    // recebendo uma lista de categorias do banco em DTO
    List<CategoriaDTO> toDTO(List<Categoria> categorias);
    //trocar os dados de CategoriaDTO para Categoria(entidade)
    @Mappings({
            @Mapping(source = "categoriaDTO.categoria_id", target = "categoria_id"),
            @Mapping(source = "categoriaDTO.categoria_nome", target = "categoria_nome"),
            @Mapping(source = "categoriaDTO.categoria_descricao", target = "categoria_descricao")
    })
    Categoria updateEntity(CategoriaDTO categoriaDTO, Categoria categoria);

}
