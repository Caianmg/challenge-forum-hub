package com.alura.challenge_forum_hub.service;

import com.alura.challenge_forum_hub.model.Usuario;
import com.alura.challenge_forum_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(), new ArrayList<>());
    }

}
