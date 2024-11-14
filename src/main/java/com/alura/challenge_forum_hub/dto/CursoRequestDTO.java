package com.alura.challenge_forum_hub.dto;

import jakarta.validation.constraints.NotBlank;

public class CursoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
