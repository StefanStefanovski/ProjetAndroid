package com.example.smartcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class ListMenuCommerceFragment extends ListFragment {
    // TO DO: extrait les données et remplaces par la liste de categorie
    String[] categorie = new String[] { "Football","Handball" };
    //String[] location = new String[]{"Hyderabad","Guntur","Hyderabad","Bangalore","Vizag","Nagpur"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_menu_commerce, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, categorie);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListDetailFragment txt = (ListDetailFragment) getFragmentManager().findFragmentById(R.id.ListDetailCommerceFragment);
        txt.changeCommerce(categorie[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);

    }
}
