package com.example.busstopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busstopapp.API.BusRouteList;
import com.example.busstopapp.test.ClientSocket;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Login2 extends AppCompatActivity {
    TextView IDT;
    TextView PWT;
    Button button1;
    Button button2;
    ClientSocket CS;
    int i;
    String a;
    String b;

    private class T_Task extends AsyncTask<Void, Void, Void> {
        /*
        @Override
        protected Void doInBackground(String... strings) {
            CS = new ClientSocket();
            try {
                i = CS.CSocket(a, b);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

         */

        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try {
                i = CS.CSocket(a, b);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = IDT.getText().toString();
            b = PWT.getText().toString();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(i == 1){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USERid", a);
                startActivity(intent);
            }else
            {
                new AlertDialog.Builder(Login2.this)
                        .setTitle("알람 팝업")
                        .setMessage("로그인 실패 \n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        IDT = findViewById(R.id.IDID);
        PWT = findViewById(R.id.PWPW);
        button1 = findViewById(R.id.LG);
        button2 = findViewById(R.id.BB);




        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new T_Task().execute();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}