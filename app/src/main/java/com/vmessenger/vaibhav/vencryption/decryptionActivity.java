package com.vmessenger.vaibhav.vencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.*;
import Data.DatabaseHandler;

import Model.encdata;

public class decryptionActivity extends AppCompatActivity {
    private TextView decytext;
    private Button dnbutton;
    private EditText dtext;
    private static Cipher encryptC;
    private static Cipher decryptC;
    private static SecretKey Skey;
    public DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        decytext=(TextView)findViewById(R.id.decyAns);
        dtext=(EditText)findViewById(R.id.decText);
        dnbutton=(Button)findViewById(R.id.debutton);
        dbHandler = new DatabaseHandler(this);



        dnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (encdata enc: dbHandler.getData()) {
                    if (enc.getEncryptedtext().contains(dtext.getText().toString())) {
                        decytext.setText(enc.getPlaintext());
                    }
                }
            }

        });

    }
}
