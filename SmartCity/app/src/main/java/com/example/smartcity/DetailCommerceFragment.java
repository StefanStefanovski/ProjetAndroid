package com.example.smartcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailCommerceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_commerce,container,false);
        //View v = inflater.inflate(R.layout.detail_commerce,container,false);

        TextView titreTextView = (TextView)v.findViewById(R.id.TitreTextView);
        TextView descriptionTextView = (TextView)v.findViewById(R.id.DescriptionTextView);
        TextView infoTextView = (TextView)v.findViewById(R.id.InfoTextView);
        ImageView imageView = (ImageView)v.findViewById(R.id.ImageCommerceView);

        //TO DO:  recuperer tout les informations de la bdd pour le id
        //et mettre la
        // String titre = getIntent().getStringExtra("id");
        //titreTextView.setText(titre);

/*
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imageView.setBackgroundColor(color);

        //la
        String lien = "https://www.viagogo.fr/Billets-de-sport/";
        infoTextView.setText("Acheter des ticket: \n" + Html.fromHtml(lien));

        //et la

        descriptionTextView.setText("Stadion: Old Trafford");*/
        return v;
    }
}
