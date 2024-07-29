package com.agenda_service_back.agendamento;


import com.agenda_service_back.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    //buscando todos aos agendamentos
    public List<AgendamentoDTO> findAll(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(agendamentoMapper::toDTO).collect(Collectors.toList());
    }
    // buscar pelo id
    public AgendamentoDTO findById(Long id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Agendamento não encontrado"));
        return agendamentoMapper.toDTO(agendamento);
    }
    //criando um novo agendamento
    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        // Definindo o status como ESPERA
        agendamento.setAgendamento_status(StatusEnum.ESPERA);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }
    //update agendamento
    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Agendamento não encontrado"));
        agendamentoDTO.setAgendamento_id(id);
        agendamento = agendamentoMapper.updateEntity(agendamentoDTO, agendamento);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    public void deleteById(Long id){
        agendamentoRepository.deleteById(id);
    }
}