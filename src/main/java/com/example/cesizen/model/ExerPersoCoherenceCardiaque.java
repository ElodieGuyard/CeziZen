package com.example.cesizen.model;

import com.example.cesizen.repository.ExerPersoCoherenceCardiaqueRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "exer_perso_coherence_cardiaque")
public class ExerPersoCoherenceCardiaque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer utilisateurId;

    @Column(nullable = false)
    private Integer dureeInspiration = 5;
    @Column(nullable = false)
    private Integer dureeApnee = 0;
    @Column(nullable = false)
    private Integer dureeExpiration = 5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getIdUtilisateur() {
        return utilisateurId;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.utilisateurId = idUtilisateur;
    }

    public Integer getDureeInspiration() {
        return dureeInspiration;
    }

    public void setDureeInspiration(Integer dureeInspiration) {
        this.dureeInspiration = dureeInspiration;
    }

    public Integer getDureeApnee() {
        return dureeApnee;
    }

    public void setDureeApnee(Integer dureeApnee) {
        this.dureeApnee = dureeApnee;
    }

    public Integer getDureeExpiration() {
        return dureeExpiration;
    }

    public void setDureeExpiration(Integer dureeExpiration) {
        this.dureeExpiration = dureeExpiration;
    }

    public void enregistrerExercice(ExerPersoCoherenceCardiaqueRepository repo) {
        repo.save(this);
    }
}
