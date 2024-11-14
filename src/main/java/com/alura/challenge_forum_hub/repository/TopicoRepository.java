package com.alura.challenge_forum_hub.repository;

import com.alura.challenge_forum_hub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome")
    Page<Topico> findByCursoNome(@Param("cursoNome") String cursoNome, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByAno(@Param("ano") Integer ano, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByCursoNomeAndAno(@Param("cursoNome") String cursoNome, @Param("ano") Integer ano, Pageable pageable);

}
