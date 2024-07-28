package com.agenda_service_back.agendamento;

import com.agenda_service_back.util.JwtUtil;
import com.agenda_service_back.usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> agendamentoDTO = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long agendamento_id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.findById(agendamento_id);
        return ResponseEntity.ok(agendamentoDTO);
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO, HttpServletRequest request) {
        try {
            // Obtenha o token do cabeçalho Authorization
            String token = request.getHeader("Authorization").substring(7);
            System.out.println("Token recebido: " + token);

            // Extraia o ID do usuário do token
            String userIdStr = jwtUtil.extractUserId(token);
            System.out.println("ID do usuário extraído: " + userIdStr);

            // Converta a string para Long
            Long usuario_id = Long.valueOf(userIdStr);

            // Crie um novo objeto Usuario e atribua o ID
            Usuario usuario = new Usuario();
            usuario.setUsuario_id(usuario_id);

            // Atribua o objeto Usuario ao DTO
            agendamentoDTO.setUsuario(usuario);

            // Crie o agendamento
            AgendamentoDTO createAgendamentoDTO = agendamentoService.create(agendamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createAgendamentoDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@PathVariable Long id, @Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO updateAgendamentoDTO = agendamentoService.update(id, agendamentoDTO);
        return ResponseEntity.ok(updateAgendamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
