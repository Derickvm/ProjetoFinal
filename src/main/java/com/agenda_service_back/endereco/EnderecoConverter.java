package com.agenda_service_back.endereco;

import java.util.List;

public class EnderecoConverter {
    public EnderecoDTO toDTO(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setEndereco_id(endereco.getEndereco_id());
        enderecoDTO.setEndereco_cep(endereco.getEndereco_cep());
        enderecoDTO.setEndereco_bairro(endereco.getEndereco_bairro());
        enderecoDTO.setEndereco_cidade(endereco.getEndereco_cidade());
        enderecoDTO.setEndereco_estado(endereco.getEndereco_estado());
        enderecoDTO.setEndereco_complemento(endereco.getEndereco_complemento());
        enderecoDTO.setEndereco_rua(endereco.getEndereco_rua());
        return enderecoDTO;
    }

    public Endereco toEntity(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        endereco.setEndereco_id(enderecoDTO.getEndereco_id());
        endereco.setEndereco_cep(enderecoDTO.getEndereco_cep());
        endereco.setEndereco_bairro(enderecoDTO.getEndereco_bairro());
        endereco.setEndereco_cidade(enderecoDTO.getEndereco_cidade());
        endereco.setEndereco_estado(enderecoDTO.getEndereco_estado());
        endereco.setEndereco_complemento(enderecoDTO.getEndereco_complemento());
        endereco.setEndereco_rua(enderecoDTO.getEndereco_rua());
        return endereco;
    }

    public Endereco updateEntity(EnderecoDTO enderecoDTO, Endereco endereco) {
        //pega os dados novos de enderecoDTO
        endereco.setEndereco_cep(enderecoDTO.getEndereco_cep());
        endereco.setEndereco_bairro(enderecoDTO.getEndereco_bairro());
        endereco.setEndereco_estado(enderecoDTO.getEndereco_estado());
        endereco.setEndereco_cidade(enderecoDTO.getEndereco_cidade());
        endereco.setEndereco_complemento(enderecoDTO.getEndereco_complemento());
        endereco.setEndereco_numero(enderecoDTO.getEndereco_numero());
        endereco.setEndereco_rua(enderecoDTO.getEndereco_rua());
        return endereco;
    }

//    public List<EnderecoDTO> toDTOList(List<Endereco> enderecos) {
//
//    }
}
