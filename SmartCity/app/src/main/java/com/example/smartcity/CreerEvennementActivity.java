package com.example.smartcity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.smartcity.reseaux.ChatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreerEvennementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_evennement);



        final AppCompatActivity self = this;

        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/commerce/categories"); ;

        Context context = CreerEvennementActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String token = sharedPref.getString("token", "");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{



                            JSONArray arr = new JSONArray(response);
                            List<String> data = new ArrayList<>();

                            if(arr.length() <= 0)
                                return;

                            EditText NomEvennement = (EditText)findViewById(R.id.NomEvennementEditText);
                            RadioGroup categorieRadioGroup = (RadioGroup)findViewById(R.id.RadioGroupCategorie);


//                            for(int i = 0; i<3; i++){
//                                addRadioButton("nom"+i+"", i, );
//                            }

                            for(int i = 0; i < arr.length(); ++i) {
                                data.add(String.format("%s", arr.getJSONObject(i).getString("category")));
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }
        };


        queue.add(stringRequest);


        Button CreerEvennementBtn = (Button)findViewById(R.id.CreerEvennementButton);
        CreerEvennementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = String.format("http://10.118.144.7:3000/commerce/add"); ;


                Context context = CreerEvennementActivity.this;
                SharedPreferences sharedPref = context.getSharedPreferences(
                        "token", Context.MODE_PRIVATE);

                final String token = sharedPref.getString("token", "");

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(CreerEvennementActivity.this, "Evennement crée!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        EditText NomEvennement = (EditText)findViewById(R.id.NomEvennementEditText);
                        RadioGroup categorieRadioGroup = (RadioGroup)findViewById(R.id.RadioGroupCategorie);

                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("name", NomEvennement.getText().toString());
                        params.put("category", String.valueOf(categorieRadioGroup.getCheckedRadioButtonId()));

                        return params;
                    }
                };
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
