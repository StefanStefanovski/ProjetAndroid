package com.example.smartcity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.Random;

public class DetailCommerceActivity extends FragmentActivity  {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commerce);

        TextView titreCommerceTextView = (TextView)findViewById(R.id.TitreTextView);
        TextView infosTextView = (TextView)findViewById(R.id.infosTextView);
        ImageView imageView = (ImageView)findViewById(R.id.ImageCommerceView);
        TextView descriptionTextView = (TextView)findViewById(R.id.descriptionCommerceTextView);

        String titre = getIntent().getStringExtra("id");
        titreCommerceTextView.setText(titre);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imageView.setBackgroundColor(color);


        String lien = "https://www.viagogo.fr/Billets-de-sport/";
        infosTextView.setText("Acheter des ticket: \n" + Html.fromHtml(lien));
        infosTextView.setMovementMethod(LinkMovementMethod.getInstance());

        descriptionTextView.setText("Staduim: Old Trafford");
    }

    /*@Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        return super.onCreateView(parent, name, context, attrs);
    }*/
/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commerce);
        //DetailCommerceFragment detailCommerceFragment = (DetailCommerceFragment)getSupportFragmentManager().findFragmentById(R.id.MainDetail);
        //detailCommerceFragment.setArguments(getIntent().getExtras());

    }*/

}
