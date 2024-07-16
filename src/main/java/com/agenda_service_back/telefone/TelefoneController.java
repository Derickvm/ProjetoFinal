package com.agenda_service_back.telefone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> getAllTelefones() {
        List<TelefoneDTO> telefones = telefoneService.getAllTelefones();
        return ResponseEntity.ok(telefones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> getTelefoneById(@PathVariable Long id) {
        TelefoneDTO telefone = telefoneService.getTelefoneById(id);
        return ResponseEntity.ok(telefone);
    }

    @PostMapping
    public ResponseEntity<TelefoneDTO> createTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        TelefoneDTO createdTelefone = telefoneService.createTelefone(telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTelefone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> updateTelefone(@PathVariable Long id, @RequestBody TelefoneDTO telefoneDTO) {
        TelefoneDTO updatedTelefone = telefoneService.updateTelefone(id, telefoneDTO);
        return ResponseEntity.ok(updatedTelefone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefone(@PathVariable Long id) {
        telefoneService.deleteTelefone(id);
        return ResponseEntity.noContent().build();
    }

}