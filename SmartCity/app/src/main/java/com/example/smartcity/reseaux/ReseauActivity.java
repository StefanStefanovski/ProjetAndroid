package com.example.smartcity.reseaux;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.DemanderAccesDialog;
import com.example.smartcity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReseauActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    List<HashMap<String, Object>> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reseau_activity);


        this.getRooms();
    }


    public void getRooms() {


        final ListView ReseauListView = (ListView)findViewById(R.id.Reseaux_ListView);
        final List<String> Reseaux = new ArrayList<String>();

        final AppCompatActivity self = this;


        Context context = self;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String user_id = sharedPref.getString("id", "");

        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/chatroom"); ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            List<JSONObject> objects = new ArrayList<>();
                            List<String> items = new ArrayList<>();

                            ReseauListView.setAdapter(null);

                            JSONArray arr = new JSONArray(response);

                            if(arr.length() <= 0)
                                return;
                            data = new ArrayList<>();

                            for(int i = 0; i < arr.length(); ++i) {
                                HashMap<String, Object> hash = new HashMap<>();
                                hash.put("id", arr.getJSONObject(i).getInt("id"));
                                hash.put("name", arr.getJSONObject(i).getString("name"));
                                hash.put("public", arr.getJSONObject(i).getBoolean("public"));
                                hash.put("owner", arr.getJSONObject(i).getBoolean("owner"));

                                data.add(hash);

                                Reseaux.add(hash.get("name").toString());
                            }


                            arrayAdapter = new ArrayAdapter(self, android.R.layout.simple_list_item_1, Reseaux);
                            ReseauListView.setAdapter(arrayAdapter);

                            ReseauListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id){



                                    RequestQueue queue = Volley.newRequestQueue(self.getApplicationContext());
                                    String url = String.format("http://10.118.144.7:3000/chatroom/hasaccess"); ;

                                    // Request a string response from the provided URL.
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try{

                                                        List<JSONObject> objects = new ArrayList<>();
                                                        List<String> items = new ArrayList<>();

                                                        ReseauListView.setAdapter(null);

                                                        JSONArray arr = new JSONArray(response);

                                                        if(arr.length() <= 0)
                                                            return;
                                                        data = new ArrayList<>();

                                                        for(int i = 0; i < arr.length(); ++i) {
                                                            HashMap<String, Object> hash = new HashMap<>();
                                                            hash.put("id", arr.getJSONObject(i).getInt("id"));
                                                            hash.put("name", arr.getJSONObject(i).getString("name"));
                                                            hash.put("public", arr.getJSONObject(i).getBoolean("public"));
                                                            hash.put("owner", arr.getJSONObject(i).getBoolean("owner"));

                                                            data.add(hash);
                                                        }


                                                        HashMap<String, Object>room = data.get(position);;
                                                        boolean acces = (Boolean) room.get("public");
                                                        String owner = (String) room.get("owner");

                                                        if(acces || owner == user_id){

                                                            Intent ChatIntent = new Intent(ReseauActivity.this, ChatActivity.class);
                                                            ChatIntent.putExtra("id", data.get(position).get("id").toString());
                                                            startActivity(ChatIntent);


                                                        }else {
                                                            DemanderAccesDialog demanderAccesDialog = new DemanderAccesDialog();
                                                            demanderAccesDialog.setRoomid(data.get(position).get("id").toString());
                                                            demanderAccesDialog.setUserid(user_id);
                                                            demanderAccesDialog.show(getSupportFragmentManager(), "Demander accès!");
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
                                            });

                                }
                            });


                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        queue.add(stringRequest);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_reseaux, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Rechercher");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
