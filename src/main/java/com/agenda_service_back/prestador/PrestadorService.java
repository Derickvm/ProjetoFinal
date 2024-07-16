package com.agenda_service_back.prestador;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestadorService {
    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private PrestadorMapper prestadorMapper;
    @Autowired
    private ServicoRepository servicoRepository;

    //buscando todos os prestadores
    @Transactional(readOnly = true)
    public List<PrestadorDTO> findAll() {
        List<Prestador> prestadores = prestadorRepository.findAll();
        return prestadores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PrestadorDTO convertToDTO(Prestador prestador) {
        PrestadorDTO prestadorDTO = new PrestadorDTO();
        prestadorDTO.setPrestador_id(prestador.getPrestador_id());
        prestadorDTO.setPrestador_nome(prestador.getPrestador_nome());
        return prestadorDTO;
    }



    // buscar pelo id
    public PrestadorDTO findById(Long id){
        Prestador prestador = prestadorRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Prestador não encontrado"));
        return prestadorMapper.toDTO(prestador);
    }

    //criando um novo prestador
    public PrestadorDTO create(PrestadorDTO prestadorDTO){
        Prestador prestador = prestadorMapper.toEntity(prestadorDTO);
        prestador = prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }

    //update prestador
    public PrestadorDTO update(Long id, PrestadorDTO prestadorDTO) {
        Prestador prestador = prestadorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prestador não encontrado"));
        prestadorDTO.setPrestador_id(id);
        prestador = prestadorMapper.updateEntity(prestadorDTO, prestador);
        prestador = prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }

    public void deleteById(Long id){
        prestadorRepository.deleteById(id);
    }

    public List<PrestadorDTO> findByServico_nome(String servico_nome) {
        List<Servico> servico = servicoRepository.findByServico_nome(servico_nome);
        List<Prestador> prestador = servico.stream()
                .map(Servico::getPrestador)
                .collect(Collectors.toList());
        return prestador.stream().map(prestadorMapper::toDTO).collect(Collectors.toList());
    }
}