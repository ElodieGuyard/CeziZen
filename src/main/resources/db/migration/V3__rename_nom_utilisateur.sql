ALTER TABLE utilisateur RENAME COLUMN nom_utilisateur TO nom;

ALTER TABLE utilisateur
    RENAME INDEX uk_utilisateur_nom_utilisateur TO uk_utilisateur_nom;