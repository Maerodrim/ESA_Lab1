package com.ssau.esalab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manager")
@Data
public class Manager implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Integer ID;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "costOfServices")
    private Integer costOfServices;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "manager", orphanRemoval = true)
    @JsonIgnoreProperties({"cars"})
    private List<Model> models = new ArrayList<>();

}
