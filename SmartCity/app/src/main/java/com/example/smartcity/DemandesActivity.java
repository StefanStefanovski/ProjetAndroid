package com.example.smartcity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DemandesActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutDemandes);
        LayoutParams lparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        //TO DO remplace i par nombre de demandes et les parametres dans les fcts
        for (int i = 0; i<=20; i++){
            setNomReseau("Reseau" + i+"", linearLayout);
            setUser("User" + i+"", linearLayout);
            setSwitch(linearLayout,i);

        }
    }

   public void setNomReseau(String nom, LinearLayout linearLayout){
        TextView NomTextView = new TextView(this);
        NomTextView.setText(nom);
        NomTextView.setTextSize(15);
        linearLayout.addView(NomTextView);
   }
    public void setUser(String user, LinearLayout linearLayout){
        TextView NomTextView = new TextView(this);
        NomTextView.setText(user);
        linearLayout.addView(NomTextView);
    }
   public void setSwitch(LinearLayout linearLayout, final int id){
       Switch privacy = new Switch(this);
       privacy.setId(id);
       privacy.setTextOff("true");
       privacy.setChecked(false);
       linearLayout.addView(privacy);

       privacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   Toast.makeText(DemandesActivity.this, "Accepté id : "+id+"", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(DemandesActivity.this, "Refusé id : "+id+"", Toast.LENGTH_SHORT).show();
               }

           }
       });

   }
}
