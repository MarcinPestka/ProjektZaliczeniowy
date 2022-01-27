package com.example.projektzaliczeniowy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ulubioneListy extends AppCompatActivity {

    public String PUBLIC_STATIC_STRING_IDENTIFIER = "elo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulubione_listy);
        ListView listaUluionych = (ListView) findViewById(R.id.listaUlubionych);
        Button listaBtn = (Button) findViewById(R.id.listaBtn);


        String[] listy = new String[] {
                "Lista ogólna",
                "Lista obiadowa",
                "Lista na mecz",
                "Lista śniadaniowa"
        };

        final ArrayList<String> fruits_list = new ArrayList<String>(Arrays.asList(listy));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);

        listaUluionych.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        listaUluionych.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String essa = String.valueOf(position);
                Log.i("data",String.valueOf(position));
                Intent resultIntent = new Intent();
                resultIntent.putExtra("RESULT_STRING", String.valueOf(position));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}