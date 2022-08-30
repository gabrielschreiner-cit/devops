package com.gabriel.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.clients.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
