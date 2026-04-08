package com.example.cesizen.repository;

import com.example.cesizen.model.ExerPersoCoherenceCardiaque;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExerPersoCoherenceCardiaqueRepository
        extends CrudRepository<ExerPersoCoherenceCardiaque, Long> {

    Optional<ExerPersoCoherenceCardiaque> findByUtilisateurId(Integer UtilisateurId);
}
