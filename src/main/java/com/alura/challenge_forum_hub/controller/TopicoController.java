package com.alura.challenge_forum_hub.controller;

import com.alura.challenge_forum_hub.dto.AtualizacaoTopicoRequestDTO;
import com.alura.challenge_forum_hub.dto.TopicoRequestDTO;
import com.alura.challenge_forum_hub.dto.TopicoResponseDTO;
import com.alura.challenge_forum_hub.model.Topico;
import com.alura.challenge_forum_hub.repository.TopicoRepository;
import com.alura.challenge_forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Topico> cadastrar(@RequestBody @Valid TopicoRequestDTO dto, @AuthenticationPrincipal String username) {

        Topico topico = topicoService.criarTopico(dto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);

    }

    @GetMapping
    public Page<TopicoResponseDTO> listarTopicos(
            @RequestParam(required = false) String cursoNome,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao
    ) {
        Page<Topico> topicos;

        if (cursoNome != null && ano != null) {
            topicos = topicoRepository.findByCursoNomeAndAno(cursoNome, ano, paginacao);
        } else if (cursoNome != null) {
            topicos = topicoRepository.findByCursoNome(cursoNome, paginacao);
        } else if (ano != null) {
            topicos = topicoRepository.findByAno(ano, paginacao);
        } else {
            topicos = topicoRepository.findAll(paginacao);
        }

        return topicos.map(TopicoResponseDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> detalharTopico(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new TopicoResponseDTO(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> atualizarTopico(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoRequestDTO atualizacaoRequest) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(atualizacaoRequest.getTitulo());
                    topico.setMensagem(atualizacaoRequest.getMensagem());
                    topico.setStatus(atualizacaoRequest.getStatus());

                    topicoRepository.save(topico);

                    return ResponseEntity.ok(new TopicoResponseDTO(topico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTopico(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topicoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
