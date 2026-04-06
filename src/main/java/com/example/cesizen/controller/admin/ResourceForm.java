package com.example.cesizen.controller.admin;

import com.example.cesizen.model.Type;

//DTO
public class ResourceForm {
    private Long categorieId;
    private String titre;
    private Type type;
    private String contenu;

    public Long getCategorieId() { return categorieId; }
    public void setCategorieId(Long categorieId) { this.categorieId = categorieId; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
}
