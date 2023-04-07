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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String loginSav = getIntent().getStringExtra("log");
        String mdpSav = getIntent().getStringExtra("mdp");
        ((TextView) findViewById(R.id.textLoginMainActivity)).setText(loginSav);
        ((TextView) findViewById(R.id.textPasswordMainActivity)).setText(mdpSav);

        Button btIdentifier = (Button) findViewById(R.id.buttonConnexionMainActivity);
        btIdentifier.setOnClickListener(view -> {
            String login = ((TextView) findViewById(R.id.textLoginMainActivity)).getText().toString();

            String mdp = ((TextView) findViewById(R.id.textPasswordMainActivity)).getText().toString();

            if (login.equals("")) {
                Toast.makeText(this, "Veuillez remplir le champ Login", Toast.LENGTH_SHORT).show();
            } else if (mdp.equals("")) {
                Toast.makeText(this, "Veuillez remplir le champ Password", Toast.LENGTH_SHORT).show();
            }

            if (!login.equals("") || !mdp.equals("")) {
                String req = "connexion&login=" + login + "&password=" + mdp;
                DaoAll.getInstance().getCoAdd(req, new Delegate() {
                    @Override
                    public Boolean whenWSConnexionIsTerminated(Object result) {
                        if ((boolean) result) {
                            retourCo();
                        } else {
                            Toast.makeText(MainActivity.this, "Votre login ou votre password est déficiant", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
            }
        });
    }

    private void retourCo() {
        Intent intent = new Intent(this, InterventionListActivity.class);
        finish();
        startActivity(intent);
    }
}

