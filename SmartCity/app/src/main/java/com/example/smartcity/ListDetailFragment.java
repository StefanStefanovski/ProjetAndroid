package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

public class ListDetailFragment extends ListFragment {

    //TO DO: apres creations de details pour chaque categorie supprimer les arrays de string
    String[] Handball = new String[] { "Montpellier v Vardar","Veszprem v Kielce" };
    String[] Football = new String[] {"PSG v Barcelona","Manchester United v Chelsea"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_detail_commerce, container, false);
        return view;
    }
    public void changeCommerce(String uname){
        //TO DO: recuperer les evennements de la bdd pour la categorie uname et remplir le listview avec ces données
        //Exemples les evennements de handball

        if(uname.equals("Handball")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Handball);
            setListAdapter(adapter);
        }else{

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Football);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent DetailCommerceIntent = new Intent(ListDetailFragment.this.getActivity(),DetailCommerceActivity.class);
        //DetailCommerceIntent.putExtra("id",Football[position]);
        startActivity(DetailCommerceIntent);
    }
}
