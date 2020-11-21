package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class CarFragment extends Fragment {

    private  View view;
    Button textView4,button;
    EditText editextdetails;

    private static final String NUMBER_OF_RECEIVER_NEXT = "nextPage";
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_car, container, false);
        context = getActivity();
        textView4 = view.findViewById(R.id.textView4);
        editextdetails=view.findViewById(R.id.editText4);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("position");
                intent.putExtra("value","2");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


            }
        }); textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirebasedata();
                Intent intent = new Intent("position");
                intent.putExtra("value","4");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });
        return view;
    }
    private void addfirebasedata(){
        String a= editextdetails.getText().toString();
        addTodatabase(a);
    }
    private void addTodatabase(final String a){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("car").child(a).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("car", a);


                    RootRef.child("car").child(a).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}