package com.vmessenger.vaibhav.vencryption;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import android.content.ClipboardManager;
import android.widget.Toast;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;                                //for implementing DES algorithm
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import Data.DatabaseHandler;
import Model.encdata;

public class encryptionActivity extends AppCompatActivity {
    private TextView encytext;
    private Button enbutton,ctextbutton;
    private EditText etext;
    private static Cipher encryptC;
    private static Cipher decryptC;
    private static SecretKey Skey;
    public DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        encytext=(TextView) findViewById(R.id.encyAns);
        etext=(EditText)findViewById(R.id.encText);
        enbutton=(Button)findViewById(R.id.enbutton);
        ctextbutton=(Button)findViewById(R.id.ctbutton);
        dbHandler = new DatabaseHandler(this);



        enbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toEnc=etext.getText().toString();
                String eAnswer="";
                if(toEnc != null && !toEnc.isEmpty()) {
                    ctextbutton.setVisibility(View.VISIBLE);
                    try {
                        DESKeySpec KSpec = new DESKeySpec("qwertykey".getBytes());
                        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                        SecretKey key = keyFactory.generateSecret(KSpec);
                        byte[] enctext = toEnc.getBytes();
                        Cipher cipher = Cipher.getInstance("DES/CBC/ZeroBytePadding", "BC"); //creating cipher
                        cipher.init(Cipher.ENCRYPT_MODE, key);
                        eAnswer = Base64.encodeToString(cipher.doFinal(enctext), Base64.DEFAULT);

                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    encytext.setText(eAnswer.toString());
                    dbHandler.addData(new encdata(toEnc, eAnswer));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter text!!",Toast.LENGTH_SHORT).show();

                };

            }
        });


        ctextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickCopy(view);
            }
        });



    }
    public void onClickCopy(View v) {                      // User-defined to copy contents to clipboard
        int sdk_Version = android.os.Build.VERSION.SDK_INT;
        if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(encytext.getText().toString());   // Assuming that you are copying the text from a TextView
            Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
        }
        else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", encytext.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
        }
    }
}
