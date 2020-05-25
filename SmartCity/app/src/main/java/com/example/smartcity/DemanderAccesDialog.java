package com.example.smartcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DemanderAccesDialog extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.demande_acces_layout, container, false);


        Button demandeAccesButton;
        demandeAccesButton = v.findViewById(R.id.demandeAccesBtn);

        demandeAccesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO  send la demande au serveur!
                Toast.makeText(getActivity(), "Demande envoyé",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
