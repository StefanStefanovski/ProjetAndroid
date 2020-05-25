package com.example.smartcity.reseaux;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        ImageButton send = (ImageButton)findViewById(R.id.sendButton);

        getRoomChat();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendTextMessage();

            }
        });
    }


    public void sendTextMessage() {
        final EditText messageEditText = (EditText)findViewById(R.id.messageEditText);
        final ListView chat = (ListView)findViewById(R.id.chatBoxListView);
        final AppCompatActivity self = this;

        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String message = messageEditText.getText().toString();
        final String id = this.getIntent().getExtras().getString("id");
        final String username = sharedPref.getString("name", "");


        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/chatroom/messages", URLEncoder.encode(id)); ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        messageEditText.setText("");
                        getRoomChat();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                params2.put("room", id);
                params2.put("username", username);
                params2.put("message", message);

                return params2;
            }
        };

        queue.add(stringRequest);
    }


    public void getRoomChat() {

        String id = this.getIntent().getExtras().getString("id");

        final AppCompatActivity self = this;

        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        String url = String.format("http://10.118.144.7:3000/chatroom/messages?id=%s", URLEncoder.encode(id)); ;

        Context context = ChatActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "token", Context.MODE_PRIVATE);

        final String token = sharedPref.getString("token", "");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            final ListView chat = (ListView)findViewById(R.id.chatBoxListView);


                            JSONArray arr = new JSONArray(response);
                            List<String> data = new ArrayList<>();

                            if(arr.length() <= 0)
                                return;


                            for(int i = 0; i < arr.length(); ++i) {
                                data.add(String.format("%s : %s", arr.getJSONObject(i).getString("username"), arr.getJSONObject(i).get("message")));
                            }


                            ArrayAdapter arrayAdapter = new ArrayAdapter(self, android.R.layout.simple_list_item_1, data);
                            chat.setAdapter(arrayAdapter);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }
        };


        queue.add(stringRequest);
    }
}
