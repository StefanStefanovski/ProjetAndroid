package com.example.smartcity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Switch;
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
import com.example.smartcity.reseaux.ChatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemandesActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutDemandes);
        final LayoutParams lparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        final AppCompatActivity self = this;


        Context context = DemandesActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String token = sharedPref.getString("token", "");
        final String id = sharedPref.getString("id", "");


        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/chatroom/requests", URLEncoder.encode(id)); ;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        JSONArray arr = null;
                        try {
                            arr = new JSONArray(response);

                            List<String> data = new ArrayList<>();

                            if(arr.length() <= 0)
                                return;


                            for (int i = 0; i<=20; i++){
                                setNomReseau("Reseau" + arr.getJSONObject(i).getString("chatroom.name") , linearLayout );
                                setUser("User" + arr.getJSONObject(i).getString("requestedjoin.name"), linearLayout);
                                setSwitch(linearLayout, i);
                            }
                        } catch (JSONException e) {
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
                params.put("id", String.valueOf(id));

                return params;
            }
        };

    }


    public void changeAccess(final int id) {

        final AppCompatActivity self = this;

        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/chatroom/accept-request"); ;

        Context context = DemandesActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String token = sharedPref.getString("token", "");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            Toast.makeText(DemandesActivity.this, "Access modifie avec success!", Toast.LENGTH_SHORT).show();
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
                params.put("id", String.valueOf(id));

                return params;
            }
        };


        queue.add(stringRequest);
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
               changeAccess(id);
           }
       });

   }
}
