package com.cocktailsguru.app.cocktail.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "coctail")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "volumeTotal")
    private Integer totalVolume;

    @Column(name = "volumeAlko")
    private Integer alcoVolume;

    @Column(name = "volumeAlkoNon")
    private Integer nonAlcoVolume;

    private String instructions;

    private String garnish;

    private String description;

    @Column(name = "imgFileName")
    private String imageName;



}
