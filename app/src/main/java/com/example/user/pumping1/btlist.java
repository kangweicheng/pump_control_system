package com.example.user.pumping1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class btlist extends AppCompatActivity {
    private Set<BluetoothDevice> pairedlist;
    private BluetoothAdapter btadpater;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btlist);
        list = (ListView)findViewById(R.id.BTlist);
        btadpater = BluetoothAdapter.getDefaultAdapter();
        pairedlist = btadpater.getBondedDevices();
        ArrayList<String> lists = new ArrayList<String>();
        if (pairedlist.size()>0)
        {

            for(BluetoothDevice bt : pairedlist)
            {

                lists.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, lists);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = ((TextView) view).getText().toString();
                String address = info.substring(info.length() - 17);

                Intent intent = new Intent(btlist.this, MainActivity.class);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });


    }
}
