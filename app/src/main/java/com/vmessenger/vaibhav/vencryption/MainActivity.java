package com.vmessenger.vaibhav.vencryption;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button EMainbutton,DMainbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EMainbutton=(Button)findViewById(R.id.ebutton);
        DMainbutton=(Button)findViewById(R.id.dbutton);







        EMainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,encryptionActivity.class);
                startActivity(i);
            }
        });


        DMainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,decryptionActivity.class);
                startActivity(i);
            }
        });
    }
}
