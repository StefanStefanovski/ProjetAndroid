package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreerReseauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_reseau);


        EditText NomReseauEditText = (EditText)findViewById(R.id.NomReseauEditText);
        EditText DescriptionEditText = (EditText)findViewById(R.id.DescriptionReseauEditText);
        Switch privacy = (Switch)findViewById(R.id.switchPrivacy);

        privacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(CreerReseauActivity.this,"Privé selectioné", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreerReseauActivity.this,"Publique selectioné", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button CreerReseauButton = (Button)findViewById(R.id.CreerReseauButton);
        CreerReseauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreerReseauActivity.this, "Reseau crée!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
