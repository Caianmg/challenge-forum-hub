package com.alura.challenge_forum_hub.service;

import com.alura.challenge_forum_hub.model.Usuario;
import com.alura.challenge_forum_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean autenticarUsuario(String nome, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            return passwordEncoder.matches(senha, usuario.getSenha());
        }

        return false;
    }

}
