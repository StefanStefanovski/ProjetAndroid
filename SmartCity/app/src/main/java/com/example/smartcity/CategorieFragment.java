package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CategorieFragment extends ListFragment {
    String categories [] = new String[] {"Méteo", "Trafic", "Ville", "Evenements"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categorie,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,categories);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, final int position, long id) {
        //TO DO BDD
        final DescriptionFragment txt = (DescriptionFragment)getFragmentManager().findFragmentById(R.id.fragment2);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://10.118.144.7:3000/actuality?type=" + URLEncoder.encode(categories[position]); ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray arr = new JSONArray(response);
                            JSONObject jsonObject = arr.getJSONObject(0);

                            String title = jsonObject.getString("title");
                            String description = jsonObject.getString("description");

                            txt.change(title + "\n\n" + description);

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
}
