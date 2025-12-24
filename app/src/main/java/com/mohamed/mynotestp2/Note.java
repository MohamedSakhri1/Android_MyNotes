package com.mohamed.mynotestp2;

import java.io.Serializable;

public class Note implements Serializable {
    private String nom;
    private String description;
    private String date;
    private String priorite; // "Basse", "Moyenne", "Haute"
    private String imageUri; // URI de l'image sous forme de String

    public Note(String nom, String description, String date, String priorite, String imageUri) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.priorite = priorite;
        this.imageUri = imageUri;
    }


    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getPriorite() { return priorite; }
    public String getImageUri() { return imageUri; }
}
