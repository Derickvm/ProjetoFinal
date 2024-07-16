package com.agenda_service_back.categoria;

import java.util.List;

public class CategoriaConverter {
    public CategoriaDTO convertToDTO(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoria_id(categoria.getCategoria_id());
        categoriaDTO.setCategoria_nome(categoria.getCategoria_nome());
        categoriaDTO.setCategoria_descricao(categoria.getCategoria_descricao());
        return categoriaDTO;
    }

    public Categoria convertToEntity(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setCategoria_id(categoriaDTO.getCategoria_id());
        categoria.setCategoria_nome(categoriaDTO.getCategoria_nome());
        categoria.setCategoria_descricao(categoriaDTO.getCategoria_descricao());
        return categoria;
    }

}
