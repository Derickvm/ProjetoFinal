package com.agenda_service_back.endereco;

import com.agenda_service_back.endereco.EnderecoDTO;
import com.agenda_service_back.endereco.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //habilita o REST
@RequestMapping("/enderecos") //localhost:8080/enderecos
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping //retorna uma lista de enderecos
    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos(){
        List<EnderecoDTO> enderecosDTO = enderecoService.findAll();
        return ResponseEntity.ok(enderecosDTO);
    }
    @GetMapping("/{id}") //localhost:8080/enderecos/1    pegar por id
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id){
        EnderecoDTO enderecoDTO = enderecoService.findById(id);
        return ResponseEntity.ok(enderecoDTO);
    }
    @PostMapping //salvar uma categoria no banco de dados
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO createEnderecoDTO = enderecoService.create(enderecoDTO);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createEnderecoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Long id,
                                                        @RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO updateEnderecoDTO = enderecoService.update(id,enderecoDTO);
        return ResponseEntity.ok(updateEnderecoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id){
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

