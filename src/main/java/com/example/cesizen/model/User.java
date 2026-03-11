package com.example.cesizen.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private @Nullable Integer id;

    private String nom_utilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nom_utilisateur;
    }

    public void setName(String name) {
        this.nom_utilisateur = name;
    }
}

