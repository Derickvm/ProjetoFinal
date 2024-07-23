package com.agenda_service_back.agendamento;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoRepository;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioRepository;
import com.agenda_service_back.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CurrentUser currentUser;

    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AgendamentoDTO findById(Long agendamento_id) {
        Agendamento agendamento = agendamentoRepository.findById(agendamento_id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return convertToDTO(agendamento);
    }

    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO) {
    try {
        Long currentUserId = currentUser.getCurrentUserId();
        Usuario usuario = usuarioRepository.findById(currentUserId)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        agendamentoDTO.setUsuario(usuario);

        Agendamento agendamento = convertToEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return convertToDTO(agendamento);
    } catch (RuntimeException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao criar agendamento: " + e.getMessage(), e);
    }
}


    public AgendamentoDTO update(Long agendamento_id, AgendamentoDTO agendamentoDTO) {
        Agendamento existingAgendamento = agendamentoRepository.findById(agendamento_id)
            .orElseThrow(() -> new RuntimeException("Agendamento nao encontrado"));

        existingAgendamento.setAgendamento_data(agendamentoDTO.getAgendamento_data());
        existingAgendamento.setAgendamento_hora(agendamentoDTO.getAgendamento_hora());
        existingAgendamento.setAgendamento_observacao(agendamentoDTO.getAgendamento_observacao());
        existingAgendamento.setAgendamento_status(agendamentoDTO.getAgendamento_status());

        Usuario usuario = usuarioRepository.findById(agendamentoDTO.getUsuario().getUsuario_id())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        existingAgendamento.setUsuario(usuario);

        Servico servico = servicoRepository.findById(agendamentoDTO.getServico().getServico_id())
            .orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        existingAgendamento.setServico(servico);

        existingAgendamento = agendamentoRepository.save(existingAgendamento);
        return convertToDTO(existingAgendamento);
    }

    public void deleteById(Long agendamento_id) {
        agendamentoRepository.deleteById(agendamento_id);
    }

    private AgendamentoDTO convertToDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setAgendamento_id(agendamento.getAgendamento_id());
        dto.setAgendamento_data(agendamento.getAgendamento_data());
        dto.setAgendamento_hora(agendamento.getAgendamento_hora());
        dto.setAgendamento_observacao(agendamento.getAgendamento_observacao());
        dto.setAgendamento_status(agendamento.getAgendamento_status());
        dto.setUsuario(agendamento.getUsuario());
        dto.setServico(agendamento.getServico());
        return dto;
    }

    private Agendamento convertToEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setAgendamento_id(dto.getAgendamento_id());
        agendamento.setAgendamento_data(dto.getAgendamento_data());
        agendamento.setAgendamento_hora(dto.getAgendamento_hora());
        agendamento.setAgendamento_observacao(dto.getAgendamento_observacao());
        agendamento.setAgendamento_status(dto.getAgendamento_status());
        agendamento.setUsuario(dto.getUsuario());
        agendamento.setServico(dto.getServico());
        return agendamento;
    }
}
