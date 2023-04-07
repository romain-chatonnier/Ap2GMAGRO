package com.example.ap2gmagro.daos;

import com.example.ap2gmagro.beans.Activite;
import com.example.ap2gmagro.beans.CD;
import com.example.ap2gmagro.beans.CO;
import com.example.ap2gmagro.beans.Machine;
import com.example.ap2gmagro.beans.SD;
import com.example.ap2gmagro.beans.SO;
import com.example.ap2gmagro.net.WSConnexionHTTPS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DAONV {
    private static DAONV instance = null;
    private List<Activite> listActivite;
    private List<Machine> listMachine;
    private List<CD> listCD;
    private List<CO> listCO;
    private List<SD> listSD;
    private List<SO> listSO;

    private DAONV(){
        listActivite = new ArrayList<>();
        listMachine = new ArrayList<>();
        listCO = new ArrayList<>();
        listCD = new ArrayList<>();
        listSO = new ArrayList<>();
        listSD = new ArrayList<>();
    }

    public List<Activite> getListActivite() {return listActivite;}
    public List<Machine> getListMachine() {return listMachine;}
    public List<CD> getListCD() {return listCD;}
    public List<CO> getListCO() {return listCO;}
    public List<SD> getListSD() {return listSD;}
    public List<SO> getListSO() {return listSO;}

    public static DAONV getInstance() {
        if (instance == null) {
            instance = new DAONV();
        }
        return instance;
    }

    public void getActivite(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listActivite.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");
                        String libelle = jo.getString("libelle");

                        Activite uneActivite = new Activite(code,libelle);
                        listActivite.add(uneActivite);
                    }
                }catch(JSONException e){
                        e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

                }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getMachine(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listMachine.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");

                        Machine uneMachine = new Machine(code);
                        listMachine.add(uneMachine);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getCO(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listCO.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");
                        String libelle = jo.getString("libelle");

                        CO unCO = new CO(code,libelle);
                        listCO.add(unCO);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getCD(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listCD.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");
                        String libelle = jo.getString("libelle");

                        CD unCD = new CD(code,libelle);
                        listCD.add(unCD);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getSO(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listSO.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");
                        String libelle = jo.getString("libelle");

                        SO unSO = new SO(code,libelle);
                        listSO.add(unSO);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

            }
        };
        wsConnexionHTTPS.execute(req);
    }

    public void getSD(String req, Delegate delegate){
        WSConnexionHTTPS wsConnexionHTTPS = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                Boolean dd = false;

                try {
                    JSONObject jo = new JSONObject(s);
                    dd = jo.getBoolean("success");
                    JSONArray ja = new JSONArray();
                    ja = jo.getJSONArray("response");
                    listSD.clear();

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        String code = jo.getString("code");
                        String libelle = jo.getString("libelle");

                        SD unSD = new SD(code,libelle);
                        listSD.add(unSD);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                delegate.whenWSConnexionIsTerminated(dd);

            }
        };
        wsConnexionHTTPS.execute(req);
    }
}
