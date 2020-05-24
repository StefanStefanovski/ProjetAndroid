package com.example.smartcity.commerce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListMenuCommerceFragment extends ListFragment {
    // TO DO: extrait les données et remplaces par la liste de categorie
    List<String> categories;
    private int type = 1;
    private Request<String> add;

    //String[] location = new String[]{"Hyderabad","Guntur","Hyderabad","Bangalore","Vizag","Nagpur"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.list_menu_commerce, container, false);
        fetchData();
        return view;


    }

    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://10.118.144.7:3000/commerce/categories";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray arr = new JSONArray(response);
                            categories = new ArrayList<>();

                            for(int i = 0; i < arr.length(); ++i) {
                                categories.add(arr.getString(i));
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_list_item_1, categories);
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListDetailFragment txt = (ListDetailFragment) getFragmentManager().findFragmentById(R.id.ListDetailCommerceFragment);

        txt.changeCommerce(categories.get(position), String.valueOf(this.type));
        getListView().setSelector(android.R.color.holo_blue_dark);

    }

    public void onChangeProximity(int type) {
        this.type = type;
        this.onListItemClick(null, null, 0, 0);
    }
}
