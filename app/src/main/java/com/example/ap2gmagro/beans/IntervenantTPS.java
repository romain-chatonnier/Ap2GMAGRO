package com.example.ap2gmagro.beans;

public class IntervenantTPS {
    public Intervenant intervenant;
    public String temps;

    public IntervenantTPS(Intervenant intervenant, String temps) {
        this.intervenant = intervenant;
        this.temps = temps;
    }

    public Intervenant getIntervenant(){ return intervenant;}

    public String getTemps(){ return temps;}

    @Override
    public String toString() {
        return intervenant + " " + temps;
    }
}
