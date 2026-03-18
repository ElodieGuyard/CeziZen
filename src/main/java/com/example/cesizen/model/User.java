package com.example.cesizen.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nom_utilisateur", nullable = false)
    private String nom_utilisateur;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "mot_de_passe", nullable = false)
    private String mot_de_passe;
    @Column(name = "cree_le", nullable = false)
    private java.time.LocalDateTime cree_le;

    @PrePersist
    protected void onCreate() {
        if (this.cree_le == null) {
            this.cree_le = java.time.LocalDateTime.now();
        }
    }

    private Date derniere_connexion;
    private Date modifie_le;
    private Date deleted_at;
    private boolean enabled;

    public User() {}

    public User(String nom_utilisateur, Role role, String mot_de_passe) {
        this.nom_utilisateur = nom_utilisateur;
        this.role = role;
        this.mot_de_passe = mot_de_passe;
    }

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

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public LocalDateTime getCree_le() {
        return cree_le;
    }

/*    public void setCree_le(LocalDateTime cree_le) { //TODO Si le @prePersiste foncitonne, à priori inutile
        this.cree_le = cree_le;
    }*/

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

    public String getMot_de_passe
            (Integer id){
        return this.mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public boolean setEnabled() { //compte actif ?
        return enabled;
    }
}

