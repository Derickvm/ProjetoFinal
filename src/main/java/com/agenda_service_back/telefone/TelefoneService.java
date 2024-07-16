package com.agenda_service_back.telefone;

import com.agenda_service_back.prestador.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    public List<TelefoneDTO> getAllTelefones() {
        return telefoneRepository.findAll()
                .stream()
                .map(telefoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TelefoneDTO getTelefoneById(Long id) {
        Optional<Telefone> telefone = telefoneRepository.findById(id);
        if (telefone.isPresent()) {
            return telefoneMapper.toDTO(telefone.get());
        } else {
            throw new RuntimeException("Telefone not found with id: " + id);
        }
    }

    public TelefoneDTO createTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        telefone = telefoneRepository.save(telefone);
        return telefoneMapper.toDTO(telefone);
    }

    public TelefoneDTO updateTelefone(Long id, TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Telefone não encontrado"));
        //telefone recebe os dados do telefoneDTO vindos do frontend
        System.out.println(telefone);
        telefoneDTO.setTelefone_id(telefone.getTelefone_id());
//        Endereco endereco = telefone.getEndereco();
//        telefoneDTO.setEndereco(endereco);
        System.out.println("dto "+telefoneDTO);
        telefone = telefoneMapper.updateEntity(telefoneDTO, telefone);
        telefone = telefoneRepository.save(telefone);
        telefoneDTO = telefoneMapper.toDTO(telefone);
        //metodo para salvar o telefone no banco de dados

        //retorna o telefone entidade convertido em DTO
        return telefoneDTO;
    }

    public void deleteTelefone(Long id) {
        telefoneRepository.deleteById(id);
    }

}