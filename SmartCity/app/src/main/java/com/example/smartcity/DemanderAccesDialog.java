package com.example.smartcity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcity.reseaux.CreerReseauActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.Map;

public class DemanderAccesDialog extends BottomSheetDialogFragment {


    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private String roomid;
    private String userid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.demande_acces_layout, container, false);


        Button demandeAccesButton;
        demandeAccesButton = v.findViewById(R.id.demandeAccesBtn);


        final DemanderAccesDialog self = this;


        demandeAccesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(self.getContext());
                String url = String.format("http://10.118.144.7:3000/chatroom/request"); ;

                Context context = self.getContext();
                SharedPreferences sharedPref = context.getSharedPreferences(
                        "token", Context.MODE_PRIVATE);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(), "Demande envoyé",Toast.LENGTH_SHORT).show();
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
                        params2.put("user_id", self.userid);
                        params2.put("room_id", self.roomid);

                        return params2;
                    }
                };

                queue.add(stringRequest);

            }
        });
        return v;
    }
}
