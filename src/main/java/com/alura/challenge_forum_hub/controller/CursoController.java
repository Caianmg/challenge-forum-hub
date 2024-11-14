package com.alura.challenge_forum_hub.controller;

import com.alura.challenge_forum_hub.dto.CursoRequestDTO;
import com.alura.challenge_forum_hub.model.Curso;
import com.alura.challenge_forum_hub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setCategoria(dto.getCategoria());

        curso = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

}
