package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreerEvennementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_evennement);

        EditText NomEvennement = (EditText)findViewById(R.id.NomEvennementEditText);
        EditText DescriptionEvennementEditText = (EditText)findViewById(R.id.DescriptionEvennementEditText);

        Button CreerEvennementBtn = (Button)findViewById(R.id.CreerEvennementButton);
        CreerEvennementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreerEvennementActivity.this, "Evennement crée!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
