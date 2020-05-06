package com.example.smartcity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Button demandesButton = (Button)findViewById(R.id.DemandesBtn);
        Button creerReseauButton = (Button)findViewById(R.id.CreerReseauBtn);
        Button creerEvennementButton  = (Button)findViewById(R.id.CreerEvenementBtn);

        demandesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent DemandesIntent = new Intent(ConfigurationActivity.this, DemandesActivity.class);
                startActivity(DemandesIntent);
                */
            }
        });

        creerReseauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent CreerReseauIntent = new Intent(ConfigurationActivity.this, CreerReseauActivity.class);
                startActivity(CreerReseauIntent);
            */
            }
        });

        creerEvennementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent CreerEvennementIntent = new Intent(ConfigurationActivity.this, CreerEvennementActivity.class);
                startActivity(CreerEvennementIntent);
                 */
            }
        });

    }
}
