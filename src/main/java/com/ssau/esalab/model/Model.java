package com.ssau.esalab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Model")
@Data
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer ID;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "brand")
    private String brand;
    @Column(name = "body")
    private String body;
    @Column(name = "contractNumber")
    private Integer contractNumber;
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "managerId", nullable = false)
    @JsonIgnoreProperties({"models"})
    private Manager manager;

    @OneToMany(mappedBy = "model", orphanRemoval = true)
    @JsonIgnoreProperties({"model"})
    private List<Car> cars = new ArrayList<>();

}
