package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartcity.actualite.ActualiteActivity;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button activiteButton = (Button)findViewById(R.id.actualiteButton);
        Button commerceButton = (Button)findViewById(R.id.commerceButton);
        Button reseauxButton = (Button)findViewById(R.id.reseauButton);
        Button parametresButton = (Button)findViewById(R.id.parametresButton);
        Button configurationButton = (Button)findViewById(R.id.configurationsButton);
        Button deconnexionButton = (Button)findViewById(R.id.DeconexionBtn);

        activiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActualiteIntent = new Intent(AccueilActivity.this, ActualiteActivity.class);
                startActivity(ActualiteIntent);
            }
        });

        commerceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent CommerceIntent = new Intent(AccueilActivity.this,CommerceActivity.class);
                startActivity(CommerceIntent);
            }
        });

        reseauxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ReseauIntent = new Intent(AccueilActivity.this,ReseauActivity.class);
                startActivity(ReseauIntent);

            }
        });
        parametresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent ParametresIntent = new Intent(AccueilActivity.this,ParametresActivity.class);
                startActivity(ParametresIntent);

            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ConfigurationIntent = new Intent(AccueilActivity.this,ConfigurationActivity.class);
                startActivity(ConfigurationIntent);

            }
        });

        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TO DO query serveur pour se deconecter */
                boolean success = true;
                if(success){
                    Toast.makeText(AccueilActivity.this,"A bientôt",Toast.LENGTH_LONG).show();
                    Intent DeconexionIntent = new Intent(AccueilActivity.this,MainActivity.class);
                    startActivity(DeconexionIntent);
                }else{
                    Toast.makeText(AccueilActivity.this,"Echec",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}