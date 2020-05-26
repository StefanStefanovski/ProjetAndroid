package com.example.smartcity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParametresActivity extends AppCompatActivity {

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

        Button inscription = (Button)findViewById(R.id.ModifierBtn);
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String user_id = sharedPref.getString("id", "");

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = pseudoEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String mdp = mdpEditText.getText().toString();
                final String city = villeEditText.getText().toString();
                String cmdp = cmdpEditText.getText().toString();
                final int selectedSexe = radioSexButton.getCheckedRadioButtonId();
                RadioButton radioButtonSexe = (RadioButton)findViewById(selectedSexe);
                //Toast.makeText(ParametresActivity.this,"Sexe: " + radioButtonSexe.getText().toString(),Toast.LENGTH_SHORT ).show();
                if(!(mdp.equals(cmdp))){
                    Toast.makeText(ParametresActivity.this, "Les mots de passe sont differentes",Toast.LENGTH_SHORT).show();
                }else{
                    /*BDD a implementer*/

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ParametresActivity.this);
                    String url = "http://10.118.144.7:3000/auth/update";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(ParametresActivity.this, "Mise  A jour de vos information avec success !", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ParametresActivity.this, "Erreur la mise a jour d'information !", Toast.LENGTH_SHORT).show();
                                }
                            })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            if(email != null && email.length() > 0) {
                                params.put("email", email);
                            }

                            if(mdp != null && mdp.length() > 0) {
                                params.put("password", mdp);
                            }

                            if(city != null && city.length() > 0) {
                                params.put("city", city);
                            }

                            return params;
                        }
                    };


                    queue.add(stringRequest);

                }
            }
        });
    }
}
