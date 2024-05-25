package com.example.UserAuthentication.security.repositories;

import java.util.Optional;

import com.example.UserAuthentication.security.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}