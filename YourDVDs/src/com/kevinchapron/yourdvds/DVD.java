package com.kevinchapron.yourdvds;

import java.io.Serializable;
import java.lang.reflect.Array;

// class pour la création d'un DVD
public class DVD implements Serializable{

    private String titre;
    private String annee;
    private String realisateur;
    private String image;
    private String dispo;
    private String vu;
    private String acteur1;
    private String acteur2;

    // Constructeur du dvd
    public DVD(String titre, String annee, String realisateur, String image, String dispo, String vu, String acteur1, String acteur2) {
        this.titre = titre;
        this.annee = annee;
        this.realisateur = realisateur;
        this.image = image;
        this.dispo = dispo;
        this.vu = vu;
        this.acteur1 = acteur1;
        this.acteur2 = acteur2;
    }

    /* setter et getter nom du film */
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /* setter et getter année du film */
    public String getAnnee() {
        return annee;
    }
    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    /* setter et getter réalisateur du film */
    public String getRealisateur() {
        return realisateur;
    }
    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }
    
    /* setter et getter affiche du film */
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    /* setter et getter disponibilité du film */
    public String getDispo() {
        return dispo;
    }
    public void setDispo(String dispo) {
        this.dispo = dispo;
    }
    
    /* setter et getter film vu ou pas */
    public String getVu() {
        return vu;
    }
    public void setVu(String vu) {
        this.vu = vu;
    }
    
    /* setter et getter acteurs */
    public String getActeurs() {
        return acteur1+" et "+acteur2;
    }
    public void setActeur1(String acteur) {
        this.acteur1 = acteur;
    }
    public void setActeur2(String acteur) {
        this.acteur2 = acteur;
    }

}
