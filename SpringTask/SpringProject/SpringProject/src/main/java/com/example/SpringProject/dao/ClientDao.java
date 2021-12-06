package com.example.SpringProject.dao;

import com.example.SpringProject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {
    public Client findByName(String username);

}
