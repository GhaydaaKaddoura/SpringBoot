package com.example.SpringProject.dao;

import com.example.SpringProject.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalesDao extends JpaRepository<Sale, Integer> {
    public List<Sale> findByProductId(int productId);
    public List<Sale> findByClientId(int clientId);

}