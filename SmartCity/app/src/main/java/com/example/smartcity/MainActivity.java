package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText utilisateurEditText = (EditText)findViewById(R.id.pseudoEditText);
        final EditText mdpEditText = (EditText)findViewById(R.id.motdepasseEditText);
        TextView inscription = (TextView)findViewById(R.id.inscriptionTextView);
        inscription.setText(Html.fromHtml("<p><u>" + inscription.getText() + "</u></p>"));

        Button connexion = (Button)findViewById(R.id.connexion);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscripptionIntent = new Intent(MainActivity.this,InscriptionActivity.class);
                startActivity(inscripptionIntent);
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utilisateur = utilisateurEditText.getText().toString();
                String mdp = mdpEditText.getText().toString();
                /* database check user TO DO*/

                Intent AcceuilIntent = new Intent(MainActivity.this,AccueilActivity.class);
                startActivity(AcceuilIntent);
            }
        });
    }
}
