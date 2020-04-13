package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        final EditText pseudoEditText = (EditText)findViewById(R.id.pseudoEditText);
        final EditText villeEditText = (EditText)findViewById(R.id.villeEditText);
        final EditText emailEditText = (EditText)findViewById(R.id.emailEditText);
        final EditText mdpEditText = (EditText)findViewById(R.id.motdepasseEditText);
        final EditText cmdpEditText = (EditText)findViewById(R.id.confirmmdpEditText);
        final RadioGroup radioSexButton = (RadioGroup) findViewById(R.id.sexeRadioGroup);

        Button inscription = (Button)findViewById(R.id.inscriptionBtn);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = pseudoEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String mdp = mdpEditText.getText().toString();
                String cmdp = cmdpEditText.getText().toString();
                int selectedSexe = radioSexButton.getCheckedRadioButtonId();
                RadioButton radioButtonSexe = (RadioButton)findViewById(selectedSexe);
                //Toast.makeText(InscriptionActivity.this,"Sexe: " + radioButtonSexe.getText().toString(),Toast.LENGTH_SHORT ).show();
                if(!(mdp.equals(cmdp))){
                    Toast.makeText(InscriptionActivity.this, "Les mots de passe sont differentes",Toast.LENGTH_SHORT).show();
                }else{
                    /*BDD a implementer*/
                }
            }
        });
    }
}
