package com.example.ap2gmagro.daos;

import android.util.Log;

import com.example.ap2gmagro.beans.Intervenant;
import com.example.ap2gmagro.beans.IntervenantTPS;
import com.example.ap2gmagro.beans.Intervention;
import com.example.ap2gmagro.net.WSConnexionHTTPS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoAll {
    private static DaoAll instance = null;
    private String LogUtilisateur;
    private List<Intervention> listIntervention;
    private List<Intervenant> listIntervenant;

    private DaoAll(){
        listIntervention = new ArrayList<>();
        listIntervenant = new ArrayList<>();
    }

    public String getLogUtilisateur() {return LogUtilisateur;}
    public List<Intervention> getListIntervention() {return listIntervention;}
    public List<Intervenant> getListIntervenant(){return listIntervenant;}

    public static DaoAll getInstance() {
        if (instance == null) {
            instance = new DaoAll();
        }
        return instance;
    }

    public void getCoAdd(String req, Delegate delegate) {
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                Boolean dd = false;
                Log.d("getconnexion", "onPostExecute: ");
                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONObject jolog = jo.getJSONObject("response");
                    LogUtilisateur = jolog.getString("nom");
                    LogUtilisateur = LogUtilisateur + " " + jolog.getString("prenom");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                delegate.whenWSConnexionIsTerminated(dd);
            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getDeco(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                Boolean dd = false;
                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                delegate.whenWSConnexionIsTerminated(dd);
            }
        };
        wsConnexionHTTPS.execute(req);
    }
    public void getIntervenant(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s){
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja;
                    ja = jo.getJSONArray("response");
                    listIntervenant.clear();

                    for(int i = 0; i < ja.length(); i++){
                        jo=ja.getJSONObject(i);

                        int idIntervenant = jo.getInt("id");
                        String nom = jo.getString("nom");
                        String prenom = jo.getString("prenom");
                        String site_uai = jo.getString("site_uai");


                        Intervenant unIntervenant =new Intervenant(nom,prenom,idIntervenant,site_uai);
                        listIntervenant.add(unIntervenant);
                        //Log.d("TAG", "onPostExecute: "+listIntervenant.get(0));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);
            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getInterventionNT(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s){
                System.out.println();
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja;
                    ja = jo.getJSONArray("response");
                    listIntervention.clear();

                    for(int i = 0; i < ja.length(); i++){
                        jo=ja.getJSONObject(i);

                        int idIntervention = jo.getInt("idIntervention");
                        String dhDebut = jo.getString("dh_debut");
                        String dhFin = jo.getString("dh_fin");
                        String commentaire = jo.getString("commentaire");
                        String tempsArret = jo.getString("temps_arret");
                        boolean changementOrgane = jo.getInt("changement_organe") == 1;
                        boolean perte = jo.getInt("perte") == 1;
                        String dhCreation = jo.getString("dh_creation");
                        String dhDerniereMaj = jo.getString("dh_derniere_maj");
                        int intervenantId = jo.getInt("intervenant_id");
                        String machineCode = jo.getString("machine_code");
                        String activiteCode = jo.getString("activite_code");
                        String causeDefautCode = jo.getString("cause_defaut_code");
                        String causeObjetCode = jo.getString("cause_objet_code");
                        String symptomeDefautCode = jo.getString("symptome_defaut_code");
                        String symptomeObjetCode = jo.getString("symptome_objet_code");



                        SimpleDateFormat sdfFRDate = new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat sdfHeure = new SimpleDateFormat("hh:mm");
                        SimpleDateFormat sdfENDate = new SimpleDateFormat("yyyy-MM-dd");

                        Date dhDebutOk = sdfENDate.parse(dhDebut);
                        String dhDebutOki = sdfFRDate.format(dhDebutOk);

                        //Date dhFinOk = sdfENDate.parse(dhFin);
                        //String dhFinOki = sdfFRDate.format(dhFinOk);

                        Date dhCreationOk = sdfENDate.parse(dhCreation);
                        String dhCreationOki = sdfFRDate.format(dhCreationOk);

                        Date dhDerniereMajOk = sdfENDate.parse(dhDerniereMaj);
                        String dhDerniereMajOki = sdfFRDate.format(dhDerniereMajOk);

                        Date tempsArretOk = sdfHeure.parse(tempsArret);

                        Intervention uneInterventionNT =new Intervention(idIntervention,dhDebutOki,dhFin,commentaire,
                                tempsArretOk,changementOrgane,perte,dhCreationOki,dhDerniereMajOki,intervenantId,activiteCode,
                                machineCode,causeDefautCode,causeObjetCode,symptomeDefautCode,symptomeObjetCode);
                        listIntervention.add(uneInterventionNT);
                        Log.d("TAG", "onPostExecute: "+listIntervention.get(0));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);
            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void addIntervention(Intervention reqIntervention, List<IntervenantTPS> reqIntervenant, Delegate delegate){
        try {
//            String resJSON = om.writeValueAsString(reqIntervention) ;
//            String intJSON = om.writeValueAsString(reqIntervenant);
//            Log.v("JSONINTERVENTION",resJSON) ;
//            Log.v("JSONINTERVENANT", intJSON);
            ObjectMapper objectMapper = new ObjectMapper();

// Créez un objet qui contient les données de l'intervention et des intervenants
            Map<String, Object> data = new HashMap<>();
            Map<String, Intervention> interventionMap = new HashMap<>();
            Map<String, List<IntervenantTPS>> intervenantMap = new HashMap<>();
            interventionMap.put("intervention", reqIntervention);
            intervenantMap.put("intervenantTPS", reqIntervenant);
            data.put("data", interventionMap);
            data.put("list", intervenantMap);

// Mappez l'objet à une chaîne de caractères JSON
            String jsonData = objectMapper.writeValueAsString(data);
            Log.v("JSONINTERVENANT", jsonData);
            WSConnexionHTTPS ws = new WSConnexionHTTPS(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                }
            } ;
            ws.execute("addIntervention",jsonData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
