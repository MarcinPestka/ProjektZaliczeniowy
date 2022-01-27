package com.example.projektzaliczeniowy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int STATIC_INTEGER_VALUE = 0;
    public String PUBLIC_STATIC_STRING_IDENTIFIER = "eee";

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    String ktoraLista = data.getStringExtra("RESULT_STRING");
                        Log.i("ale ",ktoraLista);

                    String[] fruits = new String[] {
                            "ess",
                            "ess",
                            "ess",
                            "esese"};

                    switch(ktoraLista) {
                        case "0":
                            fruits = new String[] {
                                    "Bułki",
                                    "Szynka",
                                    "Jogurty",
                                    "Masło"
                            };
                            break;
                        case "1":
                            fruits = new String[] {
                                    "Makaron",
                                    "Mięso",
                                    "Sos pomidorowy",
                                    "Bazylia"
                            };
                            break;
                        case "2":
                            fruits = new String[] {
                                    "Popkorn",
                                    "Piwo",
                                    "Nachosy",
                                    "Dipy"
                            };
                            break;
                        case "3":
                            fruits = new String[] {
                                    "Płatki śniadaniowe",
                                    "Mleko",
                                    "Kawa",
                                    "Bułki"
                            };
                            break;
                    }

                    final ListView ListView = (ListView) findViewById(R.id.ListViewID);
                    final ArrayList<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                            (MainActivity.this, android.R.layout.simple_list_item_1, fruits_list);
                    ListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    TextView editText = (TextView) findViewById(R.id.editText);
                    Button dodajListaBtn = (Button) findViewById(R.id.dodajListaBtn);
                    dodajListaBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fruits_list.add(editText.getText().toString());
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });

            ActivityResultLauncher<Intent> timerActivityResult = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                String ileMinut = data.getStringExtra("RESULT_STRING");
                TextView czasText = (TextView) findViewById(R.id.czasText);
                final Integer[] counter = {60*Integer.parseInt(ileMinut)};
                new CountDownTimer(60000*Integer.parseInt(ileMinut), 1000){
                    public void onTick(long millisUntilFinished){
                        counter[0]--;
                        int minutes = counter[0] / 60;
                        int seconds = counter[0] % 60;
                        czasText.setText(String.format("Zostało %d:%02d!", minutes, seconds));
                    }
                    public  void onFinish(){
                        czasText.setText("Koniec czasu, musisz wychodzić!");
                    }
                        }.start();
                    }

                };
    });

    public void openSomeActivityForResult() {
        Intent intent = new Intent(this, ulubioneListy.class);
        someActivityResultLauncher.launch(intent);
    }

    public void openTimerActivity() {
        Intent intent = new Intent(this, TimerActivity.class);
        timerActivityResult.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get reference of widgets from XML layout
        final ListView ListView = (ListView) findViewById(R.id.ListViewID);
        final Button dodajListaBtn = (Button) findViewById(R.id.dodajListaBtn);
        final Button ulubioneBtn = (Button) findViewById(R.id.ulubioneBtn);
        final Button timerBtn = (Button) findViewById(R.id.timerBtn);


        final TextView editText = (TextView) findViewById(R.id.editText);
        String[] fruits = new String[] {
            "Jajka",
            "Mleko",
            "Kawa",
            "Płatki śniadaniowe"
        };


        final ArrayList<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);
        ListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        dodajListaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruits_list.add(editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });


        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if((parent.getChildAt(position)).getBackground() == null){
                    parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                }

                int test = ((ColorDrawable) parent.getChildAt(position).getBackground()).getColor();

                if (test == Color.GREEN) {
                    parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                }else{
                    parent.getChildAt(position).setBackgroundColor(Color.GREEN);
                }
            }
        });

        ulubioneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSomeActivityForResult();
                }
            });

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimerActivity();
            }
        });
    }


}