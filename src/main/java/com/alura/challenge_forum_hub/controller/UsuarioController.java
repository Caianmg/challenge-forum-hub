package com.alura.challenge_forum_hub.controller;

import com.alura.challenge_forum_hub.dto.UsuarioRequestDTO;
import com.alura.challenge_forum_hub.model.Usuario;
import com.alura.challenge_forum_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}
