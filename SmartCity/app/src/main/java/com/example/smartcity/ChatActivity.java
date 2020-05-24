package com.example.smartcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ListView chat = (ListView)findViewById(R.id.chatBoxListView);

        final List<String> personne = new ArrayList<String>();
        final List<String> message = new ArrayList<String>();


        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");
        personne.add("Karim : Salut");
        personne.add("Stefan : Salut");
        personne.add("sss: zaezaea");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, personne);
        chat.setAdapter(arrayAdapter);
        final EditText messageEditText = (EditText)findViewById(R.id.messageEditText);

        ImageButton send = (ImageButton)findViewById(R.id.sendButton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = messageEditText.getText().toString();

                personne.add("user : " + message);
                chat.setAdapter(arrayAdapter);

            }
        });
    }
}
