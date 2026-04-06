package com.example.cesizen.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ressource")
public class Resource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    // Clé étrangère vers Catégorie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id", nullable = false)  // Colonne FK en base
    private Categorie categorie;
    @Column(nullable = false)
    private String titre;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    private String contenu;
    @Column(nullable = false)
    private java.time.LocalDateTime cree_le;
    private java.time.LocalDateTime modifie_le;

    @PrePersist
    protected void onCreate() {
        if (this.cree_le == null) {
            this.cree_le = java.time.LocalDateTime.now();
        }
    }
    public Resource(Categorie categorie, String titre, Type type, String contenu){
        this.categorie = categorie;
        this.titre = titre;
        this.type = type;
        this.contenu = contenu;
    }

    public Resource(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getCree_le() {
        return cree_le;
    }

    public void setCree_le(LocalDateTime cree_le) {
        this.cree_le = cree_le;
    }

    public LocalDateTime getModifie_le() {
        return modifie_le;
    }

    public void setModifie_le(LocalDateTime modifie_le) {
        this.modifie_le = modifie_le;
    }
}
