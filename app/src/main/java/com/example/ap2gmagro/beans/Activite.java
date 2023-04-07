package com.example.ap2gmagro.beans;

public class Activite {
    public String code;
    public String libelle;

    public Activite(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return  " " + libelle + " ";
    }
}
