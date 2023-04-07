package com.example.ap2gmagro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ap2gmagro.R;
import com.example.ap2gmagro.beans.Intervention;
import com.example.ap2gmagro.daos.DaoAll;
import com.example.ap2gmagro.daos.Delegate;

public class InterventionListActivity extends AppCompatActivity {
    public static final int RESULT_OK =1;
    public static final int DETAILINTER =2;
    ArrayAdapter<Intervention> interventionAdaptater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DETAILINTER){
            if(resultCode==RESULT_OK){
                interventionAdaptater.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interventionlist);

        ((TextView)(findViewById(R.id.textViewLoginInterventionList))).setText(DaoAll.getInstance().getLogUtilisateur());

        Button btDeconnexion = (Button) findViewById(R.id.buttonDeconnexionInterventionList);
        btDeconnexion.setOnClickListener(view -> {
            String reqDeco ="deconnexion";
            DaoAll.getInstance().getDeco(reqDeco, new Delegate() {
                @Override
                public Boolean whenWSConnexionIsTerminated(Object result) {
                    if ((boolean) result){
                        retourDe();
                        Toast.makeText(InterventionListActivity.this, "Vous avez été bien déconnecté", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(InterventionListActivity.this, "Une erreur est survenu durant la déconnexion", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });
        });

        interventionAdaptater = new ArrayAdapter<Intervention>(
                this, android.R.layout.simple_list_item_1, DaoAll.getInstance().getListIntervention()
        );
        ((ListView)(findViewById(R.id.ListViewInterNTInterventionList))).setAdapter(interventionAdaptater);
        String req = "getInterventionNonTermine";

        DaoAll.getInstance().getInterventionNT(req, new Delegate() {
            @Override
            public Boolean whenWSConnexionIsTerminated(Object result) {
                if((Boolean) (result)){
                    Log.d("testList", "whenWSConnexionIsTerminated: "+result);
                    interventionAdaptater.notifyDataSetChanged();
                }
                return true;
            }
        });

        ((ListView)(findViewById(R.id.ListViewInterNTInterventionList))).setOnItemClickListener((adapterView, view, i, l) ->{
            Intent intent = new Intent(this,DetailInterventionActivity.class);
            intent.putExtra("index", i);
            startActivityForResult(intent,DETAILINTER);
        } );

        Button btNouvelleIntervention = (Button) findViewById(R.id.buttonNouvelleInterventionInterventionList);
        btNouvelleIntervention.setOnClickListener(view -> {
            Intent intent = new Intent(this, NouvelleInterventionActivity.class);
            startActivity(intent);
        });

    }

    private void retourDe() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}