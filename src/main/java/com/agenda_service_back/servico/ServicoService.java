package com.agenda_service_back.servico;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.categoria.CategoriaDTO;
import com.agenda_service_back.prestador.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoMapper servicoMapper;

    @Autowired
    private PrestadorService prestadorService;

    // Buscando todos os serviços
    @Transactional(readOnly = true)
    public List<ServicoDTO> findAll() {
         List<Servico> servicos = servicoRepository.findAll();
        return servicos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ServicoDTO convertToDTO(Servico servico) {
        Categoria categoria = servico.getCategoria();
        Prestador prestador = servico.getPrestador();

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoria_id(categoria.getCategoria_id());
        categoriaDTO.setCategoria_nome(categoria.getCategoria_nome());

        PrestadorDTO prestadorDTO = new PrestadorDTO();
        prestadorDTO.setPrestador_id(prestador.getPrestador_id());
        prestadorDTO.setPrestador_nome(prestador.getPrestador_nome());

        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setServico_id(servico.getServico_id());
        servicoDTO.setServico_nome(servico.getServico_nome());
        servicoDTO.setServico_informacoes_extras(servico.getServico_informacoes_extras());
        servicoDTO.setServico_descricao(servico.getServico_descricao());
        servicoDTO.setServico_preco(servico.getServico_preco());
        servicoDTO.setCategoria(categoriaDTO);
        servicoDTO.setPrestador(prestadorDTO);
        return servicoDTO;
    }

    // Buscar pelo ID
    @Transactional(readOnly = true)
    public ServicoDTO findById(Long servicoId) {
        Servico servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        return servicoMapper.toDTO(servico);
    }

    // Criando um novo serviço
    @Transactional
    public ServicoDTO create(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toEntity(servicoDTO);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    // Atualizando serviços
    @Transactional
    public ServicoDTO update(Long servicoId, ServicoDTO servicoDTO) {
        Servico servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        servicoDTO.setServico_id(servicoId);
        servico = servicoMapper.updateEntity(servicoDTO, servico);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public void deleteById(Long id) {
        servicoRepository.deleteById(id);
    }

    // Método para obter prestadores pelo ID do serviço (comentado porque está incompleto)
//    @GetMapping("/{servicoId}/prestadores")
//    public ResponseEntity<List<PrestadorDTO>> getPrestadoresByServicoId(@PathVariable Long servicoId) {
//        Servico servico = servicoRepository.findById(servicoId)
//                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
//        List<PrestadorDTO> prestadores = prestadorService.findPrestadoresByServico(servico);
//        return ResponseEntity.ok(prestadores);
//    }
}
