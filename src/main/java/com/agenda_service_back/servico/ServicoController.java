package com.agenda_service_back.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servico = servicoService.findAll();
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        ServicoDTO servico = servicoService.findById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody ServicoDTO servicoDTO) {
        ServicoDTO createdServicoDTO = servicoService.create(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        ServicoDTO updatedServicoDTO = servicoService.update(id, servicoDTO);
        return ResponseEntity.ok(updatedServicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
