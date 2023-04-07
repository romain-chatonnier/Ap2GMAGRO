package com.example.ap2gmagro.beans;

public class Intervenant {
    public String nom;
    public String prenom;
    public int id;
    public String site_uai;

    public Intervenant(String nom, String prenom, int id, String site_uai) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.site_uai = site_uai;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public String getSite_uai() {return site_uai;}

    @Override
    public String toString() {
        return nom + ' ' + prenom;
    }
}
