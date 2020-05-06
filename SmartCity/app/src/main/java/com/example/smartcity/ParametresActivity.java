package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParametresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);


        final EditText pseudoEditText = (EditText)findViewById(R.id.pseudoEditText);
        final EditText villeEditText = (EditText)findViewById(R.id.villeEditText);
        final EditText emailEditText = (EditText)findViewById(R.id.emailEditText);
        final EditText mdpEditText = (EditText)findViewById(R.id.motdepasseEditText);
        final EditText cmdpEditText = (EditText)findViewById(R.id.confirmmdpEditText);
        final RadioGroup radioSexButton = (RadioGroup) findViewById(R.id.sexeRadioGroup);

        /*TO DO recuperer le client connecté et met dans les edit text; */
        String pseudoBDD = new String();
        String villeBDD = new String();
        String emailBDD = new String();
        String mdpBDD = new String();

        pseudoEditText.setText(pseudoBDD);
        villeEditText.setText(villeBDD);
        emailEditText.setText(emailBDD);
        mdpEditText.setText(mdpBDD);

        Button modifier = (Button)findViewById(R.id.ModifierBtn);

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pseudo = pseudoEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String mdp = mdpEditText.getText().toString();
                String cmdp = cmdpEditText.getText().toString();
                final int selectedSexe = radioSexButton.getCheckedRadioButtonId();
                RadioButton radioButtonSexe = (RadioButton)findViewById(selectedSexe);


                if(!(mdp.equals(cmdp))){
                    Toast.makeText(ParametresActivity.this, "Les mots de passe sont differentes",Toast.LENGTH_SHORT).show();
                }else {


                    /*TO DO BDD mise a jour les infos d'utilisateur */


                }
            }
        });
    }
}
