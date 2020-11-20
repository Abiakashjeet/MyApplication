package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class WelcomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
   Spinner spinner,spinnere;
   Button button;
    String[] poiority = {"Canada"};
    String[] poioritys = {"Alberta",
            "British Columbia",
            "New Brunswick",
            "Newfoundland and Labrador",
            "Northwest Territories",
            "Nova Scotia",
            "Ontario",
            "Prince Edward Island",
            "Quebec",
            "Saskatchewan"};
    Context context;
    public static WelcomeFragment newInstance() {
        WelcomeFragment fragmentFirst = new WelcomeFragment();
        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);
        context = getActivity();
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnere = view.findViewById(R.id.spinnere);
        spinnere.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapters = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, poiority);

        // Drop down layout style - list view with radio button
        dataAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapters);
 ArrayAdapter<String> dataAdapterss = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, poioritys);

        // Drop down layout style - list view with radio button
        dataAdapterss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnere.setAdapter(dataAdapterss);

        button = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("position");
                intent.putExtra("value","1");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

