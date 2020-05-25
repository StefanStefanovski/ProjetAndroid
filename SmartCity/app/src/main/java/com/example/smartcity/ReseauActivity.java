package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.smartcity.reseaux.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class ReseauActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reseau_activity);

        ListView ReseauListView = (ListView)findViewById(R.id.Reseaux_ListView);
        List<String> Reseaux = new ArrayList<String>();
        Reseaux.add("FDS"); Reseaux.add("Pharmacie");
        Reseaux.add("Paul Valery"); Reseaux.add("Droit");

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, Reseaux);
        ReseauListView.setAdapter(arrayAdapter);


        ReseauListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //TO DO recuperer l'acces du serveur
                boolean acces = true;
                if(false){
                    Intent ChatIntent = new Intent(ReseauActivity.this, ChatActivity.class);
                    startActivity(ChatIntent);
                }else {
                    DemanderAccesDialog demanderAccesDialog = new DemanderAccesDialog();
                    demanderAccesDialog.show(getSupportFragmentManager(), "Dmander accès!");
                }
            }
        });
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
