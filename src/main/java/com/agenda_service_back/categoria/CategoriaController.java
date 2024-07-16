package com.agenda_service_back.categoria;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //habilita o REST
@RequestMapping("/categorias") //localhost:8080/categorias
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping //retorna uma lista de categorias
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias(){
        List<CategoriaDTO> categoriasDTO = categoriaService.findAll();
        return ResponseEntity.ok(categoriasDTO);
    }
    @GetMapping("/{id}") //localhost:8080/categorias/1    pegar por id
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id){
        CategoriaDTO categoriaDTO = categoriaService.findById(id);
        return ResponseEntity.ok(categoriaDTO);
    }
    @PostMapping //salvar uma categoria no banco de dados
    public ResponseEntity<CategoriaDTO> createCategoria(@Valid
                                  @RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO createCategoriaDTO = categoriaService.create(categoriaDTO);
        return
              ResponseEntity.status(HttpStatus.CREATED).body(createCategoriaDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
                             @Valid @RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO updateCategoriaDTO = categoriaService.update(id,categoriaDTO);
        return ResponseEntity.ok(updateCategoriaDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
