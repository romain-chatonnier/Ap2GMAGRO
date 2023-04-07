package com.example.ap2gmagro.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ap2gmagro.R;
import com.example.ap2gmagro.beans.Activite;
import com.example.ap2gmagro.beans.CO;
import com.example.ap2gmagro.beans.CD;
import com.example.ap2gmagro.beans.Intervenant;
import com.example.ap2gmagro.beans.IntervenantTPS;
import com.example.ap2gmagro.beans.Intervention;
import com.example.ap2gmagro.beans.Machine;
import com.example.ap2gmagro.beans.SD;
import com.example.ap2gmagro.beans.SO;
import com.example.ap2gmagro.daos.DAONV;
import com.example.ap2gmagro.daos.DaoAll;
import com.example.ap2gmagro.daos.Delegate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NouvelleInterventionActivity extends AppCompatActivity {
    private Button pickTimeBtn;
    private TextView selectedTimeTV;
    private List<Intervenant> intervenantList;
    private List<IntervenantTPS> intervenantTPSList;
    private List<String> temps;
    private ArrayAdapter<Intervenant> intervenantArrayAdapter;
    private ArrayAdapter<IntervenantTPS> intervenantTPSArrayAdapter;
    private ArrayAdapter<String> tempsArrayAdaptater;
    private boolean interventionTerminer;
    private boolean machineArrete;
    private boolean changeOrgane;
    private boolean perte;
    private TextView selectedTimeTV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_intervention);

        pickTimeBtn = findViewById(R.id.idBtnPickTime);
        selectedTimeTV = findViewById(R.id.idTVSelectedTime);

        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTime();
                openDialogDate();
            }
        });

        ((TextView) findViewById(R.id.textViewLoginNouvelleIntervention)).setText(DaoAll.getInstance().getLogUtilisateur());

        Spinner spinnerActi = (Spinner) findViewById(R.id.spinnerActiviteNouvelleIntervention);
        ArrayAdapter<Activite> actiteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListActivite());
        spinnerActi.setAdapter(actiteAdapter);

        Spinner spinnerMa = (Spinner) findViewById(R.id.spinnerMachineNouvelleIntervention);
        ArrayAdapter<Machine> machineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListMachine());
        spinnerMa.setAdapter(machineAdapter);

        String reqActi = "getActivite";
        DAONV.getInstance().getActivite(reqActi, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    actiteAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        String reqMa = "getMachine";
        DAONV.getInstance().getMachine(reqMa, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    machineAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        Spinner spinnerCD = (Spinner) findViewById(R.id.spinnerCDNouvelleIntervention);
        ArrayAdapter<CD> cdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListCD());
        spinnerCD.setAdapter(cdAdapter);

        String reqCD = "getCauseDefaut";
        DAONV.getInstance().getCD(reqCD, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    cdAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        Spinner spinnerCO = (Spinner) findViewById(R.id.spinnerCONouvelleIntervention);
        ArrayAdapter<CO> coAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListCO());
        spinnerCO.setAdapter(coAdapter);

        String reqCO = "getCauseObjet";
        DAONV.getInstance().getCO(reqCO, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    coAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        Spinner spinnerSD = (Spinner) findViewById(R.id.spinnerSDNouvelleIntervention);
        ArrayAdapter<SD> sdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListSD());
        spinnerSD.setAdapter(sdAdapter);

        String reqSD = "getSymptomeDefaut";
        DAONV.getInstance().getSD(reqSD, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    sdAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        Spinner spinnerSO = (Spinner) findViewById(R.id.spinnerSONouvelleIntervention);
        ArrayAdapter<SO> soAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DAONV.getInstance().getListSO());
        spinnerSO.setAdapter(soAdapter);

        String reqSO = "getSymptomeObjet";
        DAONV.getInstance().getSO(reqSO, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    soAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });

        Button btHeureFin = (Button) findViewById(R.id.idBtnPickTime2);
        selectedTimeTV2 = findViewById(R.id.idTVSelectedTime2);
        btHeureFin.setVisibility(View.INVISIBLE);
        selectedTimeTV2.setVisibility(View.INVISIBLE);

        interventionTerminer = false;
        CheckBox checkBoxIT = (CheckBox) findViewById(R.id.checkboxITNouvelleIntervention);
        checkBoxIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    btHeureFin.setVisibility(View.VISIBLE);
                    selectedTimeTV2.setVisibility(View.VISIBLE);
                    btHeureFin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openDialogTime2();
                            openDialogDate2();
                        }
                    });
                    interventionTerminer = true;
                } else {
                    btHeureFin.setVisibility(View.INVISIBLE);
                    selectedTimeTV2.setVisibility(View.INVISIBLE);
                    interventionTerminer = false;
                }
            }
        });

        Spinner spinnerTA = (Spinner) findViewById(R.id.spinnerTANouvelleIntervention);
        spinnerTA.setVisibility(View.INVISIBLE);
        ArrayList<String> listHeureTA = new ArrayList<>(Arrays.asList("00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00"
                , "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00"
                , "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00"));
        ArrayAdapter<String> staAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listHeureTA);
        spinnerTA.setAdapter(staAdapter);

        machineArrete=false;
        CheckBox checkBoxMA = (CheckBox) findViewById(R.id.checkBoxMANouvelleIntervention);

        checkBoxMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    spinnerTA.setVisibility(View.VISIBLE);
                    machineArrete=true;
                } else {
                    spinnerTA.setVisibility(View.INVISIBLE);
                    machineArrete=false;
                }
            }
        });
        changeOrgane = false;
        CheckBox checkBoxCO=(CheckBox) findViewById(R.id.checkBoxCONouvelleIntervention);

        checkBoxCO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    changeOrgane=true;
                } else {
                    changeOrgane=false;
                }
            }
        });

        perte=false;
        CheckBox checkBoxP=(CheckBox) findViewById(R.id.checkBoxPNouvelleIntervention);

        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    perte=true;
                } else {
                    perte=false;
                }
            }
        });

        intervenantTPSList = new ArrayList<IntervenantTPS>();
        intervenantTPSArrayAdapter = new ArrayAdapter<IntervenantTPS>(
                this, android.R.layout.simple_list_item_1, intervenantTPSList);
        ListView listViewNouvelleIntervention = (ListView) findViewById(R.id.listViewIIHNouvelleIntervenant);
        listViewNouvelleIntervention.setAdapter(intervenantTPSArrayAdapter);
        listViewNouvelleIntervention.setOnItemLongClickListener((adapterView, view, i, l) -> {
            intervenantList.add(intervenantTPSList.get(i).getIntervenant());
            intervenantTPSList.remove(i);
            intervenantTPSArrayAdapter.notifyDataSetChanged();
            return false;
        });

        intervenantList = new ArrayList<>();
        Spinner spinnerIN = (Spinner) findViewById(R.id.spinnerInterNouvelleIntervention);
        ArrayAdapter<Intervenant> siAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, intervenantList);
        spinnerIN.setAdapter(siAdapter);
        String reqIn = "getIntervenant";
        DaoAll.getInstance().getIntervenant(reqIn, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if ((boolean) result) {
                    intervenantList.addAll(DaoAll.getInstance().getListIntervenant());
                    siAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NouvelleInterventionActivity.this, "erreur durant le chargement du spinner", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });


        Spinner spinnerITI = (Spinner) findViewById(R.id.spinnerTINouvelleIntervention);
        temps = new ArrayList<>(Arrays.asList("00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00"
                , "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00"
                , "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00"));
        tempsArrayAdaptater = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temps);
        spinnerITI.setAdapter(tempsArrayAdaptater);

        Button btAjouter = (Button) findViewById(R.id.buttonIINouvelleIntervenant);
        btAjouter.setOnClickListener(view -> {
            Intervenant spinInter = (Intervenant) spinnerIN.getSelectedItem();
            String spinHeureInter = spinnerITI.getSelectedItem().toString();
            intervenantTPSList.add(new IntervenantTPS(spinInter, spinHeureInter));
            intervenantList.remove(spinInter);
            intervenantTPSArrayAdapter.notifyDataSetChanged();
        });

        Button btAnnuler = (Button) findViewById(R.id.buttonAnnulerNouvelleIntervention);
        btAnnuler.setOnClickListener(view -> {
            intervenantList.clear();
            intervenantTPSList.clear();
            temps.clear();
            Intent intent = new Intent(this, InterventionListActivity.class);
            finish();
            startActivity(intent);
        });

        Button btValider = (Button) findViewById(R.id.buttonValiderNouvelleIntervention);
        btValider.setOnClickListener(view -> {
            String resultSpinActi = ((Activite) spinnerActi.getSelectedItem()).getCode();
            String resultTimeDebut = selectedTimeTV.getText().toString();
            String resultSpinMa = ((Machine) spinnerMa.getSelectedItem()).getCode();
            String resultSpinSympO = ((SO) spinnerSO.getSelectedItem()).getCode();
            String resultSpinSympD = ((SD) spinnerSD.getSelectedItem()).getCode();
            String resultSpinCauO = ((CO) spinnerCO.getSelectedItem()).getCode();
            String resultSpinCauD = ((CD) spinnerCD.getSelectedItem()).getCode();
            EditText comment = findViewById(R.id.editTextCommentaireNouvelleIntervention);
            String resultStringComment = (String) comment.getText().toString();
            String resultSpinHeureFin = null;
            String resultatTempsArret = null;
            Date tempsArretOk = null;
            SimpleDateFormat sdfHeure = new SimpleDateFormat("hh:mm");
            if (interventionTerminer == false){
                resultSpinHeureFin = null;
            }
            else{
                resultSpinHeureFin = String.valueOf(selectedTimeTV2);
            }
            if (machineArrete ==false){
                resultatTempsArret = null;
            }
            else{
                resultatTempsArret=String.valueOf(spinnerTA.getSelectedItem());
                try {
                    tempsArretOk = sdfHeure.parse(resultatTempsArret);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            Intervention intervention= new Intervention(0, resultTimeDebut, resultSpinHeureFin, resultStringComment,
                    tempsArretOk, changeOrgane, perte, "", "",0, resultSpinMa,
                    resultSpinActi, resultSpinCauD, resultSpinCauO, resultSpinSympD, resultSpinSympO);

            DaoAll.getInstance().addIntervention(intervention, intervenantTPSList, new Delegate(){
                @Override
                public Boolean whenWSConnexionIsTerminated(Object result) {
                    if ((boolean) result) {
                        Toast.makeText(NouvelleInterventionActivity.this, "Votre nouvelle intervention a bine était crée", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NouvelleInterventionActivity.this, "Une erreur est survenu durant la création de la nouvelle intervention", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });

        });

        Button btDeconnexion = (Button) findViewById(R.id.buttonDeconnexionNouvelleIntervention);
        btDeconnexion.setOnClickListener(view -> {
            String reqDeco = "deconnexion";
            DaoAll.getInstance().getDeco(reqDeco, new Delegate() {
                @Override
                public Boolean whenWSConnexionIsTerminated(Object result) {
                    if ((boolean) result) {
                        retourDe();
                        Toast.makeText(NouvelleInterventionActivity.this, "Vous avez été bien déconnecté", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NouvelleInterventionActivity.this, "Une erreur est survenu durant la déconnexion", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });
        });

    }

    private void openDialogDate(){
        DatePickerDialog dialogDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedTimeTV.setText(String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day));
            }
        }, 2023, 0, 15);
        dialogDate.show();
    }

    private void openDialogTime(){
        TimePickerDialog dialogTime = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                selectedTimeTV.setText(selectedTimeTV.getText().toString()+" "+String.valueOf(hour)+"."+String.valueOf(min));
            }
        }, 0, 0, true);
        dialogTime.show();
    }

    private void openDialogDate2(){
        DatePickerDialog dialogDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedTimeTV2.setText(String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day));
            }
        }, 2023, 0, 15);
        dialogDate.show();
    }

    private void openDialogTime2(){
        TimePickerDialog dialogTime = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                selectedTimeTV2.setText(selectedTimeTV2.getText().toString()+" "+String.valueOf(hour)+"."+String.valueOf(min));
            }
        }, 0, 0, true);
        dialogTime.show();
    }

    private void retourDe() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}