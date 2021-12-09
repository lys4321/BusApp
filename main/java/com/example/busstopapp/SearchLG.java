package com.example.busstopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.busstopapp.test.ClientSocket;

import java.io.IOException;

public class SearchLG extends AppCompatActivity {
    TextView textView;
    Button button1;
    Button button2;
    String a;
    ClientSocket CS;
    String re;

    private class T2_Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try {
                re = CS.CSocket2(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = textView.getText().toString();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(re.equals("0")){
                new AlertDialog.Builder(SearchLG.this)
                        .setTitle("알람 팝업")
                        .setMessage("계정 없음\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
            else {
                new AlertDialog.Builder(SearchLG.this)
                        .setTitle("알람 팝업")
                        .setMessage("비밀번호 : " + result + "\n\n")
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
        setContentView(R.layout.activity_search_lg);


        textView = findViewById(R.id.editTextTextPersonName);
        button1 = findViewById(R.id.SLB);
        button2= findViewById(R.id.SBB);

        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new T2_Task().execute();
                }catch (Exception e){
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