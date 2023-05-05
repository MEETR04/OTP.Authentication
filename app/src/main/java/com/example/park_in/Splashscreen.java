package com.example.park_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);

                }
                catch (Exception e){
                }
                finally {
                    Intent intent = new Intent(Splashscreen.this,login.class);
                    startActivity(intent);
                }
                }

        };thread.start();





    }
}