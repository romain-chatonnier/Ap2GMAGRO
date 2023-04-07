package com.example.ap2gmagro.beans;

import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class Intervention {
    public int idIntervention;
    public String dhDebut;
    public String dhFin;
    public String commentaire;
    public Date tempsArret;
    public boolean changementOrgane;
    public boolean perte;
    public String dhCreation;
    public String dhDerniereMaj;
    public int intervenantId;
    public String machineCode;
    public String activiteCode;
    public String causeDefautCode;
    public String causeObjetCode;
    public String symptomeDefautCode;
    public String symptomeObjetCode;

    public Intervention(int idIntervention, String dhDebut, String dhFin, String commentaire,
                        Date tempsArret, boolean changementOrgane, boolean perte, String dhCreation,
                        String dhDerniereMaj, int intervenantId, String machineCode,
                        String activiteCode, String causeDefautCode, String causeObjetCode,
                        String symptomeDefautCode, String symptomeObjetCode) {
        this.idIntervention = idIntervention;
        this.dhDebut = dhDebut;
        this.dhFin = dhFin;
        this.commentaire = commentaire;
        this.tempsArret = tempsArret;
        this.changementOrgane = changementOrgane;
        this.perte = perte;
        this.dhCreation = dhCreation;
        this.dhDerniereMaj = dhDerniereMaj;
        this.intervenantId = intervenantId;
        this.machineCode = machineCode;
        this.activiteCode = activiteCode;
        this.causeDefautCode = causeDefautCode;
        this.causeObjetCode = causeObjetCode;
        this.symptomeDefautCode = symptomeDefautCode;
        this.symptomeObjetCode = symptomeObjetCode;
    }

    public int getIdIntervention() {
        return idIntervention;
    }

    public String getDhDebut() {
        return dhDebut;
    }

    public String getDhFin() {
        return dhFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getTempsArret() {
        return tempsArret;
    }

    public boolean getChangementOrgane() {
        return changementOrgane;
    }

    public boolean getPerte() {
        return perte;
    }

    public String getDhCreation() {
        return dhCreation;
    }

    public String getDhDerniereMaj() {
        return dhDerniereMaj;
    }

    public int getIntervenantId() {
        return intervenantId;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getActiviteCode() {
        return activiteCode;
    }

    public String getCauseDefautCode() {
        return causeDefautCode;
    }

    public String getCauseObjetCode() {
        return causeObjetCode;
    }

    public String getSymptomeDefautCode() {
        return symptomeDefautCode;
    }

    public String getSymptomeObjetCode() {
        return symptomeObjetCode;
    }

    @Override
    public String toString() {
        return idIntervention + " - " + dhDerniereMaj + " - " + activiteCode + " " +machineCode;
    }
}
