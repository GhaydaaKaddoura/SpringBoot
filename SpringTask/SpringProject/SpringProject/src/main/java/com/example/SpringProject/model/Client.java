package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
//@Table(name = "client")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Client implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String lastName;
    private String mobile;

    @OneToMany(mappedBy = "client")
    private Set<Sale> saleSet = new HashSet();

    public Client() {
    }

    public Client(String name, String lastName, String mobile, Set<Sale> saleSet) {
        this.name = name;
        this.lastName = lastName;
        this.mobile = mobile;
        this.saleSet = saleSet;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
