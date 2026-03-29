INSERT INTO categorie (nom) VALUES
                                ('Respiration'),
                                ('Sommeil'),
                                ('Stress')
    ON DUPLICATE KEY UPDATE nom = VALUES(nom);

-- Ressources de test (3)
INSERT INTO ressource (categorie_id, titre, type, contenu, cree_le, modifie_le) VALUES
                                                                                    (
                                                                                        (SELECT id FROM categorie WHERE nom = 'Respiration' LIMIT 1),
    'Cohérence cardiaque – méthode 365',
    'ARTICLE',
    'La méthode 365 : 3 fois par jour, 6 respirations par minute, pendant 5 minutes. Objectif : apaiser le système nerveux et réduire le stress.',
    NOW(),
    NULL
    ),
(
  (SELECT id FROM categorie WHERE nom = 'Sommeil' LIMIT 1),
  'Routine du soir pour mieux dormir (10 min)',
  'AUDIO',
  'Audio guidé : relâchement progressif du corps + respiration lente. À écouter au coucher, lumière basse, téléphone en mode avion.',
  NOW(),
  NULL
),
(
  (SELECT id FROM categorie WHERE nom = 'Stress' LIMIT 1),
  'Respiration carrée (box breathing)',
  'EXERCICE',
  'Inspire 4s • Retiens 4s • Expire 4s • Retiens 4s. Répéter 5 cycles. Adaptable : 3s/3s/3s/3s si débutant.',
  NOW(),
  NULL
);