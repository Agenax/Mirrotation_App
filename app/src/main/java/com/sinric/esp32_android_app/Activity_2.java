package com.sinric.esp32_android_app;
import android.content.Intent;
import android.os.Bundle;
//import android.support.constraint.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
//import android.support.v7.app.AppCompatActivity;

//private MainActivity mActivity1;


public class Activity_2 extends MainActivity {
    private MainActivity MA = new MainActivity();
    String step = "";
    private ImageView bt1p;
    private ImageView bt1m;
    private ImageView bt2p;
    private ImageView bt2m;
    private ImageView bt3p;
    private ImageView bt3m;
    private ImageView btopt;




    //SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_app);
        //String bla = "bla bla";
        //btSendBytes(bla.getBytes());

        //SeekBar seekBar = findViewById(R.id.seekBar);
        //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        //int progress = seekBar.getProgress();
        LinearLayout linLayout = findViewById(R.id.linearBox);
        ViewGroup.LayoutParams linParams = linLayout.getLayoutParams();
        int Pwitdth = linParams.width;
        int Pheight = linParams.height;
        int largeur = Math.min(Pheight,Pwitdth);


        ConstraintLayout imagePack = findViewById(R.id.mainContainer);

        ViewGroup.LayoutParams layoutParams = imagePack.getLayoutParams();
        layoutParams.width = largeur;  // Largeur en pixels
        layoutParams.height = largeur; // Hauteur en pixels
        imagePack.setLayoutParams(layoutParams);


        this.bt1p = (ImageView) findViewById(R.id.bt1p);

        final EditText editText = (EditText) this.findViewById(R.id.text_input);
        step = editText.getText().toString();

        bt1p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "1 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.bt1m = (ImageView) findViewById(R.id.bt1m);

        bt1m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "-1 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.bt2p = (ImageView) findViewById(R.id.bt2p);

        bt2p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "2 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.bt2m = (ImageView) findViewById(R.id.bt2m);

        bt2m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "-2 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.bt3p = (ImageView) findViewById(R.id.bt3p);

        bt3p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "3 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.bt3m = (ImageView) findViewById(R.id.bt3m);

        bt3m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "-3 " + editText.getText().toString();
                btSendBytes(msg.getBytes());
            }
        });

        this.btopt = (ImageView) findViewById(R.id.btopt);

        btopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Activity_opt.class);
                startActivity(otherActivity);
                //finish();
            }
        });
    }

    public void writestep(String msg){
        MA.btSendBytes(msg.getBytes());
    }

    public void stopAct(){
        finish();
    }

    /*SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            String lum =String.valueOf(progress);
            String msg = "6 " + lum;
            btSendBytes(msg.getBytes());
        }
    };*/

}
