package com.agenda_service_back.usuarios;

import com.agenda_service_back.endereco.EnderecoService;
import com.agenda_service_back.usuarios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.agenda_service_back.usuarios.UsuarioMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    //buscar todas as usuarios
    public List<UsuarioDTO> findAll(){
        List<Usuario> usuario =
                usuarioRepository.findAll();
        System.out.println(usuario);
//        return usuarios.stream()
//                .map(usuarioConverter::toDTO)
//                .collect(Collectors.toList());
        return usuarioMapper.toDTOList(usuario);
    }
    //buscar uma usuario pelo id
    public UsuarioDTO findById(Long usuario_id){
        Usuario usuario = usuarioRepository.findById(usuario_id)
                .orElseThrow(()->new IllegalArgumentException("Usuario nâo encontrada"));
        return usuarioMapper.toDTO(usuario);
    }
    //criando uma nova usuario
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        System.out.println(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        System.out.println(usuario);
        //salvo no banco através do save
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
    //update usuario
    public UsuarioDTO update(Long usuario_id,UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(usuario_id)
                .orElseThrow(()->new IllegalArgumentException("Usuario não encontrado"));
        //usuario recebe os dados do usuarioDTO vindos do frontend
        usuarioDTO.setUsuario_id(usuario.getUsuario_id());
        usuario = usuarioMapper.updateEntity(usuarioDTO,usuario);
        //metodo para salvar o usuario no banco de dados
        usuario = usuarioRepository.save(usuario);
        //retorna o usuario entidade convertido em DTO
        return usuarioMapper.toDTO(usuario);
    }
    public void deleteById(Long usuario_id){
        usuarioRepository.deleteById(usuario_id);
    }
//    private Long getCurrentUserId() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication == null || authentication.getPrincipal() == null) {
//        throw new RuntimeException("Usuário não autenticado");
//    }
//
//    String email = (String) authentication.getPrincipal();
//    Usuario usuario = usuarioRepository.findByUsuario_email(email);
//    return usuario.getUsuario_id();
//}

}


