package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        activiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActualiteIntent = new Intent(AccueilActivity.this,ActualiteActivity.class);
                startActivity(ActualiteIntent);
            }
        });

        commerceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent CommerceIntent = new Intent(AccueilActivity.this,CommerceActivity.class);
                startActivity(CommerceIntent);
            */}
        });

        reseauxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent ReseauIntent = new Intent(AccueilActivity.this,ReseauActivity.class);
                startActivity(ReseauIntent);
            */
            }
        });
        parametresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent ParametresIntent = new Intent(AccueilActivity.this,ParametresActivity.class);
                startActivity(ParametresIntent);
            */
            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent ConfigurationIntent = new Intent(AccueilActivity.this,ConfigurationActivity.class);
                startActivity(ConfigurationIntent);
            */
            }
        });
    }
}