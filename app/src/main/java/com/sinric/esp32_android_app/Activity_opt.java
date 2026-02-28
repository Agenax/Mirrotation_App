package com.sinric.esp32_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Button;
import android.os.Handler;


import java.util.StringTokenizer;

public class Activity_opt extends MainActivity implements DRecieved_1{
    private boolean active = false;

    public void onStart() {
        super.onStart();
        active = true;
    }

    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    String msg;
    String freq1 = "";
    String cyc1 = "";
    String rez1 = "";
    String temp1 = "";
    String freq2 = "";
    String cyc2 = "";
    String rez2 = "";
    String temp2 = "";
    int spinState1 = 0;
    int spinState2 = 0;
    int spinState12 = 0;
    int spinState22 = 0;
    private ImageView btback;
    private String array_spinner[];
    private String array_spinner2[];
    private Spinner spin1;
    private Spinner spin2;
    private Spinner spin12;
    private EditText editFreq1;
    private EditText editCyc1;
    private EditText editRez1;
    private EditText editTemp1;
    private TextView TCyc1;
    private TextView TFreq1;
    private TextView TRez1;
    private TextView TTemp1;
    private Button buttonok1;
    private Spinner spin22;
    private EditText editFreq2;
    private EditText editCyc2;
    private EditText editRez2;
    private EditText editTemp2;
    private TextView TCyc2;
    private TextView TFreq2;
    private TextView TRez2;
    private TextView TTemp2;
    private Button buttonok2;

//    Random rand = new Random();
    Handler handler = new Handler();

    Runnable r=new Runnable() {
        public void run() {
            DataRecieved();
            //showMsg("running");
            handler.postDelayed(this, 100);
        }
    };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(r, 500);
        setContentView(R.layout.options);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        
        array_spinner=new String[4];
        array_spinner[0]="Manual";
        array_spinner[1]="Dew heater";
        array_spinner[2]="Ventilation";
        array_spinner[3]="Mirror Cooling";
        this.spin1 = (Spinner) findViewById(R.id.spinner1);
        this.spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spin1.setAdapter(adapter);
        this.spin2.setAdapter(adapter);

        this.spin12 = (Spinner) findViewById(R.id.spinner12);
        this.spin22 = (Spinner) findViewById(R.id.spinner22);
        array_spinner2 = new String[2];
        array_spinner2[0]="Manual";
        array_spinner2[1]="Auto";
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spin12.setAdapter(adapter2);
        this.spin22.setAdapter(adapter2);
        spin12.setVisibility(View.INVISIBLE);
        spin22.setVisibility(View.INVISIBLE);

        this.editCyc1 = (EditText) findViewById(R.id.cyc1_input);
        cyc1 = editCyc1.getText().toString();
        this.editFreq1 = (EditText) findViewById(R.id.freq1_input);
        freq1 = editFreq1.getText().toString();
        this.editRez1 = (EditText) findViewById(R.id.rez1_input);
        rez1 = editRez1.getText().toString();
        this.editTemp1 = (EditText) findViewById(R.id.temp1_input);
        temp1 = editTemp1.getText().toString();
        editFreq1.setVisibility(View.VISIBLE);
        editCyc1.setVisibility(View.VISIBLE);
        editRez1.setVisibility(View.VISIBLE);
        editTemp1.setVisibility(View.INVISIBLE);

        this.editCyc2 = (EditText) findViewById(R.id.cyc2_input);
        cyc2 = editCyc2.getText().toString();
        this.editFreq2 = (EditText) findViewById(R.id.freq2_input);
        freq2 = editFreq2.getText().toString();
        this.editRez2 = (EditText) findViewById(R.id.rez2_input);
        rez2 = editRez2.getText().toString();
        this.editTemp2 = (EditText) findViewById(R.id.temp2_input);
        temp2 = editTemp2.getText().toString();
        editFreq2.setVisibility(View.VISIBLE);
        editCyc2.setVisibility(View.VISIBLE);
        editRez2.setVisibility(View.VISIBLE);
        editTemp2.setVisibility(View.INVISIBLE);

        this.TCyc1 = (TextView) findViewById(R.id.textCyc1);
        TCyc1.setVisibility(View.VISIBLE);
        this.TFreq1 = (TextView) findViewById(R.id.textFreq1);
        TFreq1.setVisibility(View.VISIBLE);
        this.TRez1 = (TextView) findViewById(R.id.textRez1);
        TRez1.setVisibility(View.VISIBLE);
        this.TTemp1 = (TextView) findViewById(R.id.textTemp1);
        TTemp1.setVisibility(View.INVISIBLE);
        this.TCyc2 = (TextView) findViewById(R.id.textCyc2);
        TCyc2.setVisibility(View.VISIBLE);
        this.TFreq2 = (TextView) findViewById(R.id.textFreq2);
        TFreq2.setVisibility(View.VISIBLE);
        this.TRez2 = (TextView) findViewById(R.id.textRez2);
        TRez2.setVisibility(View.VISIBLE);
        this.TTemp2 = (TextView) findViewById(R.id.textTemp2);
        TTemp2.setVisibility(View.INVISIBLE);

        this.spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             switch(i){
                 case 0:
                     Log.i("Options","case 0");
                     spinState1 = 0;
                     spin12.setVisibility(View.INVISIBLE);
                     editFreq1.setVisibility(View.VISIBLE);
                     editCyc1.setVisibility(View.VISIBLE);
                     editRez1.setVisibility(View.VISIBLE);
                     editTemp1.setVisibility(View.INVISIBLE);
                     TCyc1.setVisibility(View.VISIBLE);
                     TFreq1.setVisibility(View.VISIBLE);
                     TRez1.setVisibility(View.VISIBLE);
                     TTemp1.setVisibility(View.INVISIBLE);
                     break;

                 case 1:
                     Log.i("Options","case 1");
                     spinState1 = 1;
                     spin12.setVisibility(View.VISIBLE);
                     editFreq1.setVisibility(View.INVISIBLE);
                     editCyc1.setVisibility(View.INVISIBLE);
                     editRez1.setVisibility(View.INVISIBLE);
                     editTemp1.setVisibility(View.VISIBLE);
                     TCyc1.setVisibility(View.INVISIBLE);
                     TFreq1.setVisibility(View.INVISIBLE);
                     TRez1.setVisibility(View.INVISIBLE);
                     TTemp1.setVisibility(View.VISIBLE);
                     break;

                 case 2:
                     Log.i("Options","case 2");
                     spinState1 = 2;
                     spin12.setVisibility(View.VISIBLE);
                     editFreq1.setVisibility(View.INVISIBLE);
                     editCyc1.setVisibility(View.INVISIBLE);
                     editRez1.setVisibility(View.INVISIBLE);
                     editTemp1.setVisibility(View.VISIBLE);
                     TCyc1.setVisibility(View.INVISIBLE);
                     TFreq1.setVisibility(View.INVISIBLE);
                     TRez1.setVisibility(View.INVISIBLE);
                     TTemp1.setVisibility(View.VISIBLE);
                     break;

                 case 3:
                     Log.i("Options","case 3");
                     spinState1 = 3;
                     spin12.setVisibility(View.INVISIBLE);
                     editFreq1.setVisibility(View.INVISIBLE);
                     editCyc1.setVisibility(View.INVISIBLE);
                     editRez1.setVisibility(View.INVISIBLE);
                     editTemp1.setVisibility(View.INVISIBLE);
                     TCyc1.setVisibility(View.INVISIBLE);
                     TFreq1.setVisibility(View.INVISIBLE);
                     TRez1.setVisibility(View.INVISIBLE);
                     TTemp1.setVisibility(View.INVISIBLE);
                     break;
             }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Log.i("Options","case 0");
                        spinState2 = 0;
                        spin22.setVisibility(View.INVISIBLE);
                        editFreq2.setVisibility(View.VISIBLE);
                        editCyc2.setVisibility(View.VISIBLE);
                        editRez2.setVisibility(View.VISIBLE);
                        editTemp2.setVisibility(View.INVISIBLE);
                        TCyc2.setVisibility(View.VISIBLE);
                        TFreq2.setVisibility(View.VISIBLE);
                        TRez2.setVisibility(View.VISIBLE);
                        TTemp2.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        Log.i("Options","case 1");
                        spinState2 = 1;
                        spin22.setVisibility(View.VISIBLE);
                        editFreq2.setVisibility(View.INVISIBLE);
                        editCyc2.setVisibility(View.INVISIBLE);
                        editRez2.setVisibility(View.INVISIBLE);
                        editTemp2.setVisibility(View.VISIBLE);
                        TCyc2.setVisibility(View.INVISIBLE);
                        TFreq2.setVisibility(View.INVISIBLE);
                        TRez2.setVisibility(View.INVISIBLE);
                        TTemp2.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        Log.i("Options","case 2");
                        spinState2 = 2;
                        spin22.setVisibility(View.VISIBLE);
                        editFreq2.setVisibility(View.INVISIBLE);
                        editCyc2.setVisibility(View.INVISIBLE);
                        editRez2.setVisibility(View.INVISIBLE);
                        editTemp2.setVisibility(View.VISIBLE);
                        TCyc2.setVisibility(View.INVISIBLE);
                        TFreq2.setVisibility(View.INVISIBLE);
                        TRez2.setVisibility(View.INVISIBLE);
                        TTemp2.setVisibility(View.VISIBLE);
                        break;

                    case 3:
                        Log.i("Options","case 3");
                        spinState2 = 3;
                        spin22.setVisibility(View.INVISIBLE);
                        editFreq2.setVisibility(View.INVISIBLE);
                        editCyc2.setVisibility(View.INVISIBLE);
                        editRez2.setVisibility(View.INVISIBLE);
                        editTemp2.setVisibility(View.INVISIBLE);
                        TCyc2.setVisibility(View.INVISIBLE);
                        TFreq2.setVisibility(View.INVISIBLE);
                        TRez2.setVisibility(View.INVISIBLE);
                        TTemp2.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Log.i("Options","case 0");
                        spinState12 = 0;
                        break;

                    case 1:
                        Log.i("Options","case 1");
                        spinState12 = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        this.btback = (ImageView) findViewById(R.id.btback);

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent otherActivity = new Intent(getApplicationContext(), Activity_2.class);
                //startActivity(otherActivity);
                finish();
            }
        });

        buttonok1 = findViewById(R.id.button_ok1);
        buttonok1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (spinState1){
                    case 0:
                        msg = "4 0 " + editCyc1.getText().toString() + " " + editFreq1.getText().toString() + " " + editRez1.getText().toString();
                        btSendBytes(msg.getBytes());
                        break;
                    case 1:
                        switch (spinState12){
                            case 0:
                                msg = "4 1 " + temp1;
                                btSendBytes(msg.getBytes());
                                break;
                            case 1:
                                msg = "4 2 " + temp1;
                                btSendBytes(msg.getBytes());
                                break;
                        }
                        break;
                    case 2:
                        switch (spinState12){
                            case 0:
                                msg = "4 3 " + temp1;
                                btSendBytes(msg.getBytes());
                                break;
                            case 1:
                                msg = "4 4 " + temp1;
                                btSendBytes(msg.getBytes());
                                break;
                        }
                        break;
                    case 3:
                        msg = "4 5";
                        btSendBytes(msg.getBytes());
                        break;
                }
            }
        });

        buttonok2 = findViewById(R.id.button_ok2);
        buttonok2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (spinState2){
                    case 0:
                        msg = "5 0 " + editCyc2.getText().toString() + " " + editFreq2.getText().toString() + " " + editRez2.getText().toString();
                        btSendBytes(msg.getBytes());
                        break;
                    case 1:
                        switch (spinState22){
                            case 0:
                                msg = "5 1 " + temp2;
                                btSendBytes(msg.getBytes());
                                break;
                            case 1:
                                msg = "5 2 " + temp2;
                                btSendBytes(msg.getBytes());
                                break;
                        }
                        break;
                    case 2:
                        switch (spinState22){
                            case 0:
                                msg = "5 3 " + temp2;
                                btSendBytes(msg.getBytes());
                                break;
                            case 1:
                                msg = "5 4 " + temp2;
                                btSendBytes(msg.getBytes());
                                break;
                        }
                        break;
                    case 3:
                        msg = "5 5";
                        btSendBytes(msg.getBytes());
                        break;
                }
            }
        });

    }

    public void DataRecieved(){
        //showMsg("Here");
        //String data = MyApplication.Global.svar1;
        //
        String s = ((MyApplication) MyApplication.context()).getRecieved();
        //String data = MyApplication.Global.svar1;
        //showMsg("Got string in recieved : " + s);
        StringTokenizer stdata = new StringTokenizer(s);

        int index = Integer.parseInt(stdata.nextToken());
        switch (index){
            case 1:
                String stint = stdata.nextToken();
                TextView textTI = findViewById(R.id.texttempint);
                textTI.setText("Temperature inside : " + stint + "°C");
                float tdpf = Float.parseFloat(stint);
                String shint = stdata.nextToken();
                TextView textHI = findViewById(R.id.texthumint);
                textHI.setText("Humidity inside : " + shint + "%");
                float hdpf = Float.parseFloat(shint);
                float dp = tdpf - ((100 - hdpf)/5);
                String dpstr = Float.toString(dp);
                TextView textDP = findViewById(R.id.textdewp);
                textDP.setText("Dew point : " + dpstr + "°C");
                break;
            case 2:
                String stout = stdata.nextToken();
                TextView textTE = findViewById(R.id.texttempext);
                textTE.setText("Temperature outside : " + stout + "°C");
                String shout = stdata.nextToken();
                TextView textHE = findViewById(R.id.texthumext);
                textHE.setText("Humidity outside : " + shout + "%");
                break;
            case 10:
                TextView textTINC = findViewById(R.id.texttempint);
                textTINC.setText("Temperature inside : " + "NC");
                TextView textHINC = findViewById(R.id.texthumint);
                textHINC.setText("Humidity inside : " + "NC");
                TextView textDPNC = findViewById(R.id.textdewp);
                textDPNC.setText("Dew point : " + "NC");
                break;
            case 20:
                TextView textTONC = findViewById(R.id.texttempext);
                textTONC.setText("Temperature inside : " + "NC");
                TextView textHONC = findViewById(R.id.texthumext);
                textHONC.setText("Humidity inside : " + "NC");
                break;
        }
        //Log.i("Options",data);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            TextView textled = findViewById(R.id.textdesc1);
            int progress = seekBar.getProgress();
            String lum = String.valueOf(progress);
            textled.setText("Led brightness : " + lum +"%");
            msg = "6 " + lum;
            btSendBytes(msg.getBytes());
        }
    };

}
