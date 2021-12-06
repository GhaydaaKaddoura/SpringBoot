package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.cache.annotation.CacheConfig;
import com.example.SpringProject.model.Client;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
//@Table(name = "sale")
@CacheConfig(cacheNames={"saleVal"})
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Sale implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date creationDate;
    private String seller;


   // @Column(name = "total", nullable = true)
    private Double total;
   // @Column(name = "price", nullable = true)
    private Double price;
   // @Column(name = "quantity", nullable = true)
    private int quantity;


    @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public Sale() {
    }

    public Sale(Date creationDate, String seller, Double total, Double price,int quantity,  Client client, Product product) {
        this.creationDate = creationDate;
        this.seller = seller;
        this.total = total;
        this.price = price;
        this.quantity = quantity;
        this.client = client;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
