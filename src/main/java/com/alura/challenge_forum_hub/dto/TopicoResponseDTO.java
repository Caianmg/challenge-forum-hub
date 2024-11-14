package com.alura.challenge_forum_hub.dto;

import com.alura.challenge_forum_hub.model.Topico;

import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private String autor;
    private String curso;

    public TopicoResponseDTO(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus().toString();
        this.autor = topico.getAutor().getNome();
        this.curso = topico.getCurso().getNome();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
