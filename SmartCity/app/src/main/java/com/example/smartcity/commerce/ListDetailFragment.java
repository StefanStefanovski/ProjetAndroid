package com.example.smartcity.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.R;
import com.example.smartcity.commerce.DetailCommerceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDetailFragment extends ListFragment {

    //TO DO: apres creations de details pour chaque categorie supprimer les arrays de string



    int choice = 0;
    List<HashMap<String, Object>> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_detail_commerce, container, false);
        return view;
    }
    public void changeCommerce(String uname, String type){
        //TO DO: recuperer les evennements de la bdd pour la categorie uname et remplir le listview avec ces données
        //Exemples les evennements de handball

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/commerce?category=%s&type=%s", URLEncoder.encode(uname), URLEncoder.encode(type)); ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{



                            List<JSONObject> objects = new ArrayList<>();
                            List<String> items = new ArrayList<>();

                            setListAdapter(null);

                            JSONArray arr = new JSONArray(response);

                            if(arr.length() <= 0)
                                return;

                            JSONObject jsonObject = arr.getJSONObject(0);

                            data = new ArrayList<>();

                            for(int i = 0; i < arr.length(); ++i) {

                                items.add(arr.getJSONObject(i).getString("title"));

                                HashMap<String, Object> hash = new HashMap<>();
                                hash.put("id", arr.getJSONObject(i).getInt("id"));
                                hash.put("title", arr.getJSONObject(i).getString("title"));
                                hash.put("description", arr.getJSONObject(i).getString("description"));
                                data.add(hash);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_list_item_1, items);
                            setListAdapter(adapter);

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
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {

        Intent i = new Intent(getActivity().getApplicationContext(), DetailCommerceActivity.class);

        i.putExtra("id", this.data.get(position));

        startActivity(i);
    }
}
