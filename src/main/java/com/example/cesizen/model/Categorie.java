package com.example.cesizen.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nom;

    // Relation 1:N → Liste de Ressources
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> ressources = new ArrayList<>();

    public Categorie() {};

    // désérialisation string vers Int
    public Categorie(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId(int id) {
        return id;
    }
}
