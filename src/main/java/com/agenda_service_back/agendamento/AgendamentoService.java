package com.agenda_service_back.agendamento;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoRepository;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AgendamentoDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento not found"));
        return convertToDTO(agendamento);
    }

    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = convertToEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return convertToDTO(agendamento);
    }

    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento existingAgendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento nao encontrado"));

        existingAgendamento.setAgendamento_data(agendamentoDTO.getAgendamento_data());
        existingAgendamento.setAgendamento_hora(agendamentoDTO.getAgendamento_hora());
        existingAgendamento.setAgendamento_observacao(agendamentoDTO.getAgendamento_observacao());
        existingAgendamento.setAgendamento_status(agendamentoDTO.getAgendamento_status());

        // Atualiza o cliente
        Usuario usuario = usuarioRepository.findById(agendamentoDTO.getUsuario().getUsuario_id())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        existingAgendamento.setUsuario(usuario);

        // Atualiza o serviço
        Servico servico = servicoRepository.findById(agendamentoDTO.getServico().getServico_id())
            .orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        existingAgendamento.setServico(servico);

        existingAgendamento = agendamentoRepository.save(existingAgendamento);
        return convertToDTO(existingAgendamento);
    }

    public void deleteById(Long id) {
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoDTO convertToDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setAgendamento_id(agendamento.getAgendamento_id());
        dto.setAgendamento_data(agendamento.getAgendamento_data());
        dto.setAgendamento_hora(agendamento.getAgendamento_hora());
        dto.setAgendamento_observacao(agendamento.getAgendamento_observacao());
        dto.setAgendamento_status(agendamento.getAgendamento_status());
        dto.setUsuario(agendamento.getUsuario()); // Obtendo o cliente
        dto.setServico(agendamento.getServico()); // Obtendo o serviço
        return dto;
    }

    private Agendamento convertToEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setAgendamento_data(dto.getAgendamento_data());
        agendamento.setAgendamento_hora(dto.getAgendamento_hora());
        agendamento.setAgendamento_observacao(dto.getAgendamento_observacao());
        agendamento.setAgendamento_status(dto.getAgendamento_status());

        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getUsuario_id())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        agendamento.setUsuario(usuario);

        Servico servico = servicoRepository.findById(dto.getServico().getServico_id())
            .orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        agendamento.setServico(servico);

        return agendamento;
    }

//    private Long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getPrincipal() == null) {
//            throw new RuntimeException("Usuário não autenticado");
//        }
//        // Se você usa o nome de usuário como String, você precisa converter para Long ou usar uma abordagem diferente
//        // Aqui estamos assumindo que o usuário é um objeto com um campo `id`
//        Usuario cliente = (Usuario) authentication.getPrincipal();
//        return cliente.getUsuario_id(); // Supondo que `getCliente_id()` retorna Long
//    }
}
