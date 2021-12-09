package com.example.busstopapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.busstopapp.API.BusRouteList;
import com.example.busstopapp.API.getRouteInfo;
import com.example.busstopapp.test.ClientSocket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RouteList extends AppCompatActivity {
    BusRouteList BrL;
    getRouteInfo GrI;
    ArrayList<String> busnumber;
    ArrayList<String> stopname;
    ArrayList<String> stopname2;
    ArrayAdapter<String> adapter;
    ListView listv;
    Button button;
    Button button2;
    Button button3;
    TextView textView;
    TextView textView3;
    int i;
    String res;
    String uid;
    String busn;
    ClientSocket CS;
    int re;


    private class MTL_Task extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try{
                re = CS.MSocket(uid, busn);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Intent intent = getIntent();
            uid = intent.getStringExtra("USERid");
            busn = textView.getText().toString();

        }





        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(re == 1){
                new AlertDialog.Builder(RouteList.this)
                        .setTitle("알람 팝업")
                        .setMessage("즐겨찾기 성공\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
            else{
                new AlertDialog.Builder(RouteList.this)
                        .setTitle("알람 팝업")
                        .setMessage("즐겨찾기 실패\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
        }

    }//AysnTask 스레드 클래스 종료


    private class MTL2_Task extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            CS = new ClientSocket();
            try{
                re = CS.MSocket(uid, busn);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Intent intent = getIntent();
            uid = intent.getStringExtra("USERid");
            busn = textView.getText().toString();

        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(re == 1){
                new AlertDialog.Builder(RouteList.this)
                        .setTitle("알람 팝업")
                        .setMessage("즐겨찾기 삭제 성공\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
            else{
                new AlertDialog.Builder(RouteList.this)
                        .setTitle("알람 팝업")
                        .setMessage("즐겨찾기 삭제 실패\n\n")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
            }
        }

    }//AysnTask 스레드 클래스 종료

    private class R_Task extends AsyncTask<String, Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            BrL = new BusRouteList();
            listv = findViewById(R.id.listview);
            textView = findViewById(R.id.textttt);

        }

        @Override
        protected Void doInBackground(String... strings) {
            stopname = BrL.stopName(strings[0],strings[1]);
            stopname2 = new ArrayList<String>();
            i = 1;

            for(String n : stopname)
            {
                stopname2.add( String.valueOf(i) + "번째 정거장 : " + n);
                i++;
            }

            adapter = new ArrayAdapter<String>(
                    RouteList.this, android.R.layout.simple_expandable_list_item_1, stopname2);

            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            listv.setAdapter(adapter);
        }

    }//AysnTask 스레드 클래스 종료

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.markinsertB);
        button3 = findViewById(R.id.markdeleteB);
        Intent inintent = getIntent();


        int i = 0;
        textView3 = findViewById(R.id.textttt);
        String q = inintent.getStringExtra("ccode");
        String w = inintent.getStringExtra("busname");
        String s = inintent.getStringExtra("stname");
        //String userid = inintent.getStringExtra("ui");유저 id
        textView3.setText(s);

        try {
            new R_Task().execute(q, w);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try{
                    new MTL_Task().execute();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new MTL2_Task().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}