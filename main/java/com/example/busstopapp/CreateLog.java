package com.example.busstopapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.busstopapp.test.ClientSocket;

import org.w3c.dom.Text;

import java.io.IOException;

public class CreateLog extends AppCompatActivity {
    String ID;
    int back=0;
    String result;
    String IDCH;
    String PWCH;
    int checkN = 0;
    String a;
    String b;
    ClientSocket CS;
    String re;
    TextView tv1;
    TextView tv2;
    Button button1;
    Button button2;
    Button button3;
    TextView TV6;
    int v;
    int h;

    private class T3_Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try {
                re = CS.CSocket2(a);
                if(re != "0"){
                    v = 0;
                }
                else {
                    v = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = tv1.getText().toString();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(v == 1){
                new AlertDialog.Builder(CreateLog.this)
                        .setTitle("알람 팝업")
                        .setMessage("생성 가능\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
            else if(v == 0){
                new AlertDialog.Builder(CreateLog.this)
                        .setTitle("알람 팝업")
                        .setMessage("생성 불가\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }

        }
    }

    private class T4_Task extends AsyncTask<Void, Void, Void> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try {
                re = CS.CSocket3(a, b);
                if(Integer.parseInt(re) >= 1){
                    h = 0;
                }
                else {
                    h = 1;
                }
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
            a = tv1.getText().toString();
            b = tv2.getText().toString();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(h==1){
                new AlertDialog.Builder(CreateLog.this)
                        .setTitle("알람 팝업")
                        .setMessage("생성 성공\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
            else if(h==0){
                new AlertDialog.Builder(CreateLog.this)
                        .setTitle("알람 팝업")
                        .setMessage("생성 실패\n\n")
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
        setContentView(R.layout.activity_create_log);


        tv1 = findViewById(R.id.editTextTextCID);
        tv2 = findViewById(R.id.editTextTextCPW);
        button1 = findViewById(R.id.DBB);
        button2 = findViewById(R.id.CCLG);
        button3 = findViewById(R.id.CCB);
        TV6 = findViewById(R.id.TV6);




        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new T3_Task().execute();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new T4_Task().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }//
        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}