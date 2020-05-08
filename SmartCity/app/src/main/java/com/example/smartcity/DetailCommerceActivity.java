package com.example.smartcity;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailCommerceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commerce);

        TextView titreTextView = (TextView)findViewById(R.id.TitreTextView);
        TextView descriptionTextView = (TextView)findViewById(R.id.DescriptionTextView);
        TextView infoTextView = (TextView)findViewById(R.id.InfoTextView);
        ImageView imageView = (ImageView)findViewById(R.id.ImageCommerceView);

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

        descriptionTextView.setText("Stadion: Old Trafford");
    */}
}
