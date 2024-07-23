package com.agenda_service_back.agendamento;

import com.agenda_service_back.agendamento.AgendamentoDTO;
import com.agenda_service_back.agendamento.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController //habilitar o REST
@RequestMapping("/agendamento") // localhost:8080/agendamento
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;
    @GetMapping //retorna uma lista de agendamentos
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos(){
        List<AgendamentoDTO> agendamentoDTO = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentoDTO);
    }
    @GetMapping("/{id}") //localhost:8080/agendamento/1 pegar por id
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long agendamento_id){
        AgendamentoDTO agendamentoDTO = agendamentoService.findById(agendamento_id);
        return ResponseEntity.ok(agendamentoDTO);
    }
    @PostMapping //salvar um agendamento no banco de dados
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO){
        System.out.println(agendamentoDTO);
        AgendamentoDTO createAgendamentoDTO = agendamentoService.create(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAgendamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@PathVariable Long id, @Valid @RequestBody AgendamentoDTO agendamentoDTO){
        AgendamentoDTO updateAgendamentoDTO = agendamentoService.update(id, agendamentoDTO);
        return ResponseEntity.ok(updateAgendamentoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id){
        agendamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}