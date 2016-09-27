package com.example.ziyi.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
MyService myService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                myService = ((MyService.LocalBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        Button button_start = (Button)findViewById(R.id.button_start);
        Button button_add = (Button)findViewById(R.id.button_add);
        Button button_stop = (Button)findViewById(R.id.button_stop);
        final TextView textView = (TextView)findViewById(R.id.text);

        button_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MainActivity.this,MyService.class);
                        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                    }
                }
        );

        button_stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unbindService(serviceConnection);
                    }
                }
        );

        button_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myService != null){
                            textView.setText("1+2的计算结果是"+myService.add(1,2));
                        }
                    }
                }
        );
    }
}
