package br.com.marques.microservice.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marques.microservice.challenge.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}

