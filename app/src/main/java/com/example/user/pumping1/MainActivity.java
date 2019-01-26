package com.example.user.pumping1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {
    int timer_min = 0, timer_sec = 0;
    Button btconn, btdis, run, stop, timer;
    EditText f, p1, p2, p3, p4;
    SeekBar f1, s1, s2, s3, s4;
    TextView err;
    BluetoothAdapter btadapt;
    BluetoothSocket bsocket;
    boolean isBTConnected = false;
    private Set<BluetoothDevice> paired_list;
    private ConnectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial_component();
        init_text();
        turnonBT();
        receive_intent(savedInstanceState);
        f1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                f.setText(String.valueOf(seekBar.getProgress()*20+60));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser){
                            String a1 =  String.valueOf(progress*5);
                            p1.setText(a1);
                        }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p2.setText(String.valueOf(seekBar.getProgress()*5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p3.setText(String.valueOf(seekBar.getProgress()*5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p4.setText(String.valueOf(seekBar.getProgress()*5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int progress = round(max(Integer.parseInt(f.getText().toString())-60,0)/20);
                    f1.setProgress(progress);
                    f.setSelection(f.getText().length());
                }catch (Exception e){

                }
            }
        });
        p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int progress = round(max(Integer.parseInt(p1.getText().toString()),0)/5);
                    s1.setProgress(progress);
                    p1.setSelection(p1.getText().length());
                }catch (Exception e){

                }
            }
        });
        p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int progress = round(max(Integer.parseInt(p2.getText().toString()),0)/5);
                    s2.setProgress(progress);
                    p2.setSelection(p2.getText().length());
                }catch (Exception e){

                }

            }
        });
        p3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int progress = round(max(Integer.parseInt(p3.getText().toString()),0)/5);
                    s3.setProgress(progress);
                    p3.setSelection(p3.getText().length());
                }catch (Exception e){

                }
            }
        });
        p4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int progress = round(max(Integer.parseInt(p4.getText().toString()),0)/5);
                    s4.setProgress(progress);
                    p4.setSelection(p4.getText().length());
                }catch (Exception e){

                }
            }
        });
        btconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btlist1 = new Intent(MainActivity.this, btlist.class);
                startActivity(btlist1);
            }
        });
        btdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, timer_setup.class);
                startActivity(intent);
            }
        });
    }

    private void receive_intent(Bundle bundle) {
        Intent intent = getIntent();
        String address = receiveData(intent, bundle, "address");
        String mins = receiveData(intent, bundle, "min");
        String secs = receiveData(intent, bundle, "sec");
        if((mins!=null)&&(secs!=null)){
            timer_min = Integer.parseInt(mins);
            timer_sec = Integer.parseInt(secs);
        }
        if(address!=null){
            final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            try
            {
                if (bsocket == null || !isBTConnected)
                {
                    BluetoothDevice dispositivo = btadapt.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    bsocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    bsocket.connect();//start connection
                    isBTConnected = true;
                    
                }
            }
            catch (IOException e)
            {
                Toast.makeText(getApplicationContext(),"cannot connect", Toast.LENGTH_LONG);
            }
        }

    }

    private String receiveData(Intent intent, Bundle bundle, String key) {
        String str = null;
        if(bundle == null){
            Bundle extras = intent.getExtras();
            if (extras == null){
                str = null;
            }else{
                str = extras.getString(key);
            }
        }else{
            str = (String) bundle.getSerializable(key);
        }
        return  str;
    }

    private void turnonBT() {
        btadapt = BluetoothAdapter.getDefaultAdapter();
        if(btadapt.isEnabled()){

        }else{
            Intent btrequest = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(btrequest);
        }
    }

    private void init_text() {
        f.setText(String.valueOf((f1.getProgress())*20 + 60));
        p1.setText(String.valueOf(s1.getProgress()*5));
        p2.setText(String.valueOf(s2.getProgress()*5));
        p3.setText(String.valueOf(s3.getProgress()*5));
        p4.setText(String.valueOf(s4.getProgress()*5));

        f.setSelection(2);
        p1.setSelection(1);
        p2.setSelection(1);
        p3.setSelection(1);
        p4.setSelection(1);
    }

    private void initial_component() {
        f1 = (SeekBar) findViewById(R.id.Frequency_bar);
        s1 = (SeekBar)findViewById(R.id.Pump1_bar);
        s2 = (SeekBar)findViewById(R.id.Pump2_bar);
        s3 = (SeekBar)findViewById(R.id.Pump3_bar);
        s4 = (SeekBar)findViewById(R.id.Pump4_bar);

        f = (EditText) findViewById(R.id.Frequency_text);
        p1 = (EditText)findViewById(R.id.Pump1_text);
        p2 = (EditText)findViewById(R.id.Pump2_text);
        p3 = (EditText)findViewById(R.id.Pump3_text);
        p4 = (EditText)findViewById(R.id.Pump4_text);

        stop = (Button) findViewById(R.id.stop) ;
        btconn = (Button)findViewById(R.id.bt_connect);
        btdis = (Button)findViewById(R.id.bt_disconnect);
        run = (Button)findViewById(R.id.run);
        timer = (Button)findViewById(R.id.timer);

        err = findViewById(R.id.error);
    }

}
