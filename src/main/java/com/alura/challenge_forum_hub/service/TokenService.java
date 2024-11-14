package com.alura.challenge_forum_hub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationTime;

    public String gerarToken(String username) {
        Date dataAgora = new Date();
        Date dataExpiracao = new Date(dataAgora.getTime() + Long.parseLong(expirationTime));

        return JWT.create()
                .withSubject(username)
                .withIssuer("ForumHub")
                .withIssuedAt(dataAgora)
                .withExpiresAt(dataExpiracao)
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean isTokenValido(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }

}
