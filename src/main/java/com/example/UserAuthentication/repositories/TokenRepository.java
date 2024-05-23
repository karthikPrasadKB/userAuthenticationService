package com.example.UserAuthentication.repositories;

import com.example.UserAuthentication.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenValue(String tokenValue);
}
