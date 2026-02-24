-- V1: initial schema (MySQL 8) based on your class diagram + agreed constraints

CREATE TABLE utilisateur (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nom_utilisateur VARCHAR(100) NOT NULL,
  mot_de_passe VARCHAR(255) NOT NULL,
  role ENUM('ADMIN','USER') NOT NULL DEFAULT 'USER',
  cree_le DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  derniere_connexion DATETIME NULL,
  modifie_le DATETIME NULL,
  deleted_at DATETIME NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_utilisateur_nom_utilisateur (nom_utilisateur)
);

CREATE TABLE categorie (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nom VARCHAR(120) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_categorie_nom (nom)
);

-- Tu pourras faire évoluer cet ENUM via une migration V2 si tu ajoutes des types
-- Exemples init: ARTICLE, VIDEO, AUDIO, EXERCICE
CREATE TABLE ressource (
  id BIGINT NOT NULL AUTO_INCREMENT,
  categorie_id BIGINT NOT NULL,
  titre VARCHAR(255) NOT NULL,
  type ENUM('ARTICLE','VIDEO','AUDIO','EXERCICE') NOT NULL,
  contenu TEXT NOT NULL,
  cree_le DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modifie_le DATETIME NULL,
  PRIMARY KEY (id),
  KEY idx_ressource_categorie_id (categorie_id),
  CONSTRAINT fk_ressource_categorie
    FOREIGN KEY (categorie_id) REFERENCES categorie(id)
    ON DELETE RESTRICT
);

CREATE TABLE referentiel_emotions (
  id BIGINT NOT NULL AUTO_INCREMENT,
  emotion VARCHAR(120) NOT NULL,
  emotion_base VARCHAR(120) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_ref_emotions_emotion (emotion)
);

CREATE TABLE emotion_log (
  id BIGINT NOT NULL AUTO_INCREMENT,
  utilisateur_id BIGINT NOT NULL,
  referentiel_emotion_id BIGINT NOT NULL,
  date_log DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_emotion_log_utilisateur_id (utilisateur_id),
  KEY idx_emotion_log_ref_id (referentiel_emotion_id),
  CONSTRAINT fk_emotion_log_utilisateur
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_emotion_log_referentiel
    FOREIGN KEY (referentiel_emotion_id) REFERENCES referentiel_emotions(id)
    ON DELETE RESTRICT
);

CREATE TABLE configuration_standard_coherence_cardiaque (
  id BIGINT NOT NULL AUTO_INCREMENT,
  utilisateur_admin_id BIGINT NOT NULL,
  titre VARCHAR(255) NOT NULL,
  duree_inspiration INT NOT NULL,
  duree_apnee INT NOT NULL,
  duree_expiration INT NOT NULL,
  cree_le DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modifie_le DATETIME NULL,
  PRIMARY KEY (id),
  KEY idx_cfg_std_admin_id (utilisateur_admin_id),
  CONSTRAINT fk_cfg_std_admin
    FOREIGN KEY (utilisateur_admin_id) REFERENCES utilisateur(id)
    ON DELETE RESTRICT
);

CREATE TABLE exer_perso_coherence_cardiaque (
  id BIGINT NOT NULL AUTO_INCREMENT,
  utilisateur_id BIGINT NOT NULL,
  duree_inspiration INT NOT NULL,
  duree_apnee INT NOT NULL,
  duree_expiration INT NOT NULL,
  cree_le DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modifie_le DATETIME NULL,
  PRIMARY KEY (id),
  
  UNIQUE KEY uk_exer_perso_utilisateur (utilisateur_id),
  CONSTRAINT fk_exer_perso_utilisateur
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
    ON DELETE CASCADE
);

CREATE TABLE favori (
  id BIGINT NOT NULL AUTO_INCREMENT,
  utilisateur_id BIGINT NOT NULL,
  ressource_id BIGINT NOT NULL,
  date_ajout DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_favori_user_ressource (utilisateur_id, ressource_id),
  KEY idx_favori_ressource_id (ressource_id),
  CONSTRAINT fk_favori_utilisateur
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_favori_ressource
    FOREIGN KEY (ressource_id) REFERENCES ressource(id)
    ON DELETE CASCADE
);

