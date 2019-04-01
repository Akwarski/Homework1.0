package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ChangeSound extends AppCompatActivity {

    Spinner spiner;
    int soundID = 0;
    ArrayAdapter<String> arrayAdapter;
    List<String> list;
    String[] items = {""};
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_sound);

        cancel = findViewById(R.id.buttoncancel);
        spiner = findViewById(R.id.spinner);
        list = new ArrayList<>();
        items = getResources().getStringArray(R.array.sounds);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);

        spiner.setAdapter(arrayAdapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        soundID = R.raw.mario;
                        break;
                    case 1:
                        soundID = R.raw.ring01;
                        break;
                    case 2:
                        soundID = R.raw.ring02;
                        break;
                    case 3:
                        soundID = R.raw.ring03;
                        break;
                    case 4:
                        soundID = R.raw.ring04;
                        break;
                    case 5:
                        soundID = R.raw.ringd;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ok(View view) {
        Intent intent = new Intent();
        intent.putExtra("sound", soundID);
        setResult(RESULT_OK,intent);
        finish();
    }
}
