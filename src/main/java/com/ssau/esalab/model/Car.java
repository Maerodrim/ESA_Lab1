package com.ssau.esalab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Car")
@Data
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "color")
    private String color;

    @Column(name = "numberD")
    private Integer numberD;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonIgnoreProperties({"cars", "manager"})
    private Model model;
}
