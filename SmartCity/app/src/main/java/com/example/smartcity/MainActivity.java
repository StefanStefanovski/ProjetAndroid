package com.example.smartcity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText pseudoEditText = (EditText) findViewById(R.id.pseudoEditText);
        final EditText mdpEditText = (EditText) findViewById(R.id.motdepasseEditText);
        TextView inscription = (TextView) findViewById(R.id.inscriptionTextView);
        inscription.setText(Html.fromHtml("<p><u>" + inscription.getText() + "</u></p>"));

        Button connexion = (Button) findViewById(R.id.connexion);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscripptionIntent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(inscripptionIntent);
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pseudo = pseudoEditText.getText().toString();
                final String mdp = mdpEditText.getText().toString();
                /* database check user TO DO*/


                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://10.118.144.7:3000/auth/login";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    String token = jsonObject.getString("access_token");

                                    Context context = MainActivity.this;
                                    SharedPreferences sharedPref = context.getSharedPreferences(
                                            "token", Context.MODE_PRIVATE);

                                    sharedPref.edit().putString("token", token);

                                    Intent AcceuilIntent = new Intent(MainActivity.this,AccueilActivity.class);
                                    startActivity(AcceuilIntent);
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Erreur Login ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Erreur Login ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();

                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", pseudo);
                        params.put("password", mdp);
                        return params;
                    }
                };


                queue.add(stringRequest);


            }
        });
    }
}
