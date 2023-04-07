package com.example.ap2gmagro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ap2gmagro.R;
import com.example.ap2gmagro.daos.DaoAll;
import com.example.ap2gmagro.daos.Delegate;

public class DetailInterventionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intervention);

        ((TextView)findViewById(R.id.textViewLoginDetailIntervention)).setText(DaoAll.getInstance().getLogUtilisateur());

        Button btDeconnexion = (Button) findViewById(R.id.buttonDeconnexionDetailIntervention);
        btDeconnexion.setOnClickListener(view -> {
            String reqDeco ="deconnexion";
            DaoAll.getInstance().getDeco(reqDeco, new Delegate() {
                @Override
                public Boolean whenWSConnexionIsTerminated(Object result) {
                    if ((boolean) result){
                        retourDe();
                        Toast.makeText(DetailInterventionActivity.this, "Vous avez été bien déconnecté", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DetailInterventionActivity.this, "Une erreur est survenu durant la déconnexion", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });
        });
    }

    private void retourDe() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}