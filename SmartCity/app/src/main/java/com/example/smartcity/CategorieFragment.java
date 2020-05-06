package com.example.smartcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

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
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        //TO DO BDD
        DescriptionFragment txt = (DescriptionFragment)getFragmentManager().findFragmentById(R.id.fragment2);
            txt.change("Cliqué: "+categories[position]);
    }
}
