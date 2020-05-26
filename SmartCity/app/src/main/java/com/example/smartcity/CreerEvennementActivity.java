package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreerEvennementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_evennement);

        EditText NomEvennement = (EditText)findViewById(R.id.NomEvennementEditText);
        RadioGroup categorieRadioGroup = (RadioGroup)findViewById(R.id.RadioGroupCategorie);

        for(int i = 0; i<3; i++){
            addRadioButton("nom"+i+"", i, categorieRadioGroup);
        }
        Button CreerEvennementBtn = (Button)findViewById(R.id.CreerEvennementButton);
        CreerEvennementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreerEvennementActivity.this, "Evennement crée!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addRadioButton(final String nom, final int id, RadioGroup categorieRadioGroup){
        RadioButton rdbtn = new RadioButton(this);
        rdbtn.setId(id);
        rdbtn.setText(nom);
        rdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreerEvennementActivity.this, "button" + nom +"", Toast.LENGTH_SHORT).show();
            }
        });
        categorieRadioGroup.addView(rdbtn);

    }

}
