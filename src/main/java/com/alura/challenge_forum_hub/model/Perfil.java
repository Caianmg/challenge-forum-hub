package com.alura.challenge_forum_hub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfil")
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

}
