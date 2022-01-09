package com.ssau.esalab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CarDealer")
@Data
public class CarDealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carDealer_id")
    private Integer ID;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "carDealer", orphanRemoval = true)
    private List<Manager> managers = new ArrayList<>();
}

