package com.example.smartcity.reseaux;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.R;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
        final CreerReseauActivity self = this;
        CreerReseauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText NomReseauEditText = (EditText)findViewById(R.id.NomReseauEditText);
                final EditText DescriptionEditText = (EditText)findViewById(R.id.DescriptionReseauEditText);
                final Switch privacy = (Switch)findViewById(R.id.switchPrivacy);

                Context context = self;
                SharedPreferences sharedPref = context.getSharedPreferences(
                        "token", Context.MODE_PRIVATE);

                final String user_id = sharedPref.getString("id", "");

                RequestQueue queue = Volley.newRequestQueue(self.getApplicationContext());
                String url = String.format("http://10.118.144.7:3000/chatroom"); ;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(CreerReseauActivity.this, "Reseau crée!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params2 = new HashMap<String, String>();
                        params2.put("name", NomReseauEditText.getText().toString());
                        params2.put("owner", user_id);
                        params2.put("public", String.valueOf(privacy.isChecked()));

                        return params2;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }
}
