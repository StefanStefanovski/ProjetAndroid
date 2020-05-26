package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.actualite.ActualiteActivity;
import com.example.smartcity.commerce.CommerceActivity;
import com.example.smartcity.reseaux.ReseauActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button activiteButton = (Button)findViewById(R.id.actualiteButton);
        Button commerceButton = (Button)findViewById(R.id.commerceButton);
        Button reseauxButton = (Button)findViewById(R.id.reseauButton);
        Button parametresButton = (Button)findViewById(R.id.parametresButton);
        Button configurationButton = (Button)findViewById(R.id.configurationsButton);
        Button deconnexionButton = (Button)findViewById(R.id.DeconexionBtn);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("Ad test", "Ad finishes loading");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d("Ad test", "Ad loading failed");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.d("Ad test", "Ad is visible now");
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.d("Ad test", "User left the app");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d("Ad test", "user came back to app");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });



         final InterstitialAd mInterstitialAd;
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        activiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActualiteIntent = new Intent(AccueilActivity.this, ActualiteActivity.class);
                startActivity(ActualiteIntent);
            }
        });

        commerceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent CommerceIntent = new Intent(AccueilActivity.this, CommerceActivity.class);
                startActivity(CommerceIntent);
            }
        });

        reseauxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ReseauIntent = new Intent(AccueilActivity.this, ReseauActivity.class);
                startActivity(ReseauIntent);

            }
        });
        parametresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent ParametresIntent = new Intent(AccueilActivity.this,ParametresActivity.class);
                startActivity(ParametresIntent);

            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ConfigurationIntent = new Intent(AccueilActivity.this,ConfigurationActivity.class);
                startActivity(ConfigurationIntent);

            }
        });

        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TO DO query serveur pour se deconecter */
                boolean success = true;
                if(success){
                    Toast.makeText(AccueilActivity.this,"A bientôt",Toast.LENGTH_SHORT).show();
                    Intent DeconexionIntent = new Intent(AccueilActivity.this,MainActivity.class);
                    startActivity(DeconexionIntent);
                }else{
                    Toast.makeText(AccueilActivity.this,"Echec",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}