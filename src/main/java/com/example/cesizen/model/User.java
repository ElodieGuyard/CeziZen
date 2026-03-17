package com.example.cesizen.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private @Nullable Integer id;

    private String nom_utilisateur;
    private Role role;
    private String mot_de_passe;
    private Date cree_le;
    private Date derniere_connexion;
    private Date modifie_le;
    private Date deleted_at;


    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMDP(Integer id){
        return this.mot_de_passe;
    }

    public String getName() {
        return nom_utilisateur;
    }

    public void setName(String name) {
        this.nom_utilisateur = name;
    }

    public Role getRole(Integer id){
        return this.role;
    }

    public Role setRole(Integer id){
        return this.role;
    }

    public Date getCree_le() {
        return cree_le;
    }

    public void setCree_le(Date cree_le) {
        this.cree_le = cree_le;
    }

    public Date getDerniere_connexion() {
        return derniere_connexion;
    }

    public void setDerniere_connexion(Date derniere_connexion) {
        this.derniere_connexion = derniere_connexion;
    }

    public Date getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(Date modifie_le) {
        this.modifie_le = modifie_le;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}

