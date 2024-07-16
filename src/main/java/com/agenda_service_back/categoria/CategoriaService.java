package com.agenda_service_back.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    private CategoriaConverter categoriaConverter;

    //buscar todas as categorias
    public List<CategoriaDTO> findAll(){
        List<Categoria> categorias =
                categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }
    //buscar uma categoria pelo id
    public CategoriaDTO findById(Long categoria_id){
        Categoria categoria = categoriaRepository.findById(categoria_id)
        .orElseThrow(()->new IllegalArgumentException("Categoria nâo encontrada"));
        return categoriaConverter.convertToDTO(categoria);
    }
    //criando uma nova categoria
    public CategoriaDTO create(CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }
    //update categoria
    public CategoriaDTO update(Long categoria_id,CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaRepository.findById(categoria_id)
                .orElseThrow(()->new IllegalArgumentException("Categoria não"));
        categoriaMapper.updateEntity(categoriaDTO,categoria);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }
    public void deleteById(Long categoria_id){
        categoriaRepository.deleteById(categoria_id);
    }
}
