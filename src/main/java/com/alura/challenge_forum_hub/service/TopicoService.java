package com.alura.challenge_forum_hub.service;

import com.alura.challenge_forum_hub.dto.TopicoRequestDTO;
import com.alura.challenge_forum_hub.model.Topico;
import com.alura.challenge_forum_hub.repository.CursoRepository;
import com.alura.challenge_forum_hub.repository.TopicoRepository;
import com.alura.challenge_forum_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Topico criarTopico(TopicoRequestDTO dto, String nomeUsuario) {

        boolean exists = topicoRepository.existsByTituloAndMensagem(dto.getTitulo(), dto.getMensagem());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tópico duplicado");
        }

        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var autor = usuarioRepository.findByNome(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
        var curso = cursoRepository.findByNome(dto.getNomeCurso()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus("ABERTO");

        return topicoRepository.save(topico);

    }

}
