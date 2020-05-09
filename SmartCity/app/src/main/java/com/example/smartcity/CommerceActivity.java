package com.example.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CommerceActivity extends FragmentActivity implements PopupMenu.OnMenuItemClickListener {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);
    }

   public void showPopup (View v){
       PopupMenu popup = new PopupMenu(this,v);
       popup.setOnMenuItemClickListener(this);
       popup.inflate(R.menu.popup_menu);
       popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Proximite:
                Toast.makeText(this, "Proximite", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Annuaire:
                Toast.makeText(this, "Annuaire", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
