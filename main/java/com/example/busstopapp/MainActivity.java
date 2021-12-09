package com.example.busstopapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.busstopapp.API.getCtyCodeList;
import com.example.busstopapp.API.getRouteInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> cn;
    ArrayList<String> cc;
    ArrayList<String> bn;
    ArrayList<String> bc;
    ArrayList<String> liv;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    Spinner spinner = null;
    Spinner spinner2;
    TextView textView;
    Button Sbutton;
    Button Rbutton;
    ListView liveiw;
    TextView textView2;
    getCtyCodeList gccl;
    getRouteInfo grif;
    String code;
    String a;
    String b;
    String c;


    private class A_Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gccl = new getCtyCodeList();
            spinner = (Spinner) findViewById(R.id.spinner1);
            textView = findViewById(R.id.textView);

        }

        @Override
        protected Void doInBackground(Void ... params) {
            cn = gccl.getCTN();
            cc = gccl.getCTC();


            adapter = new ArrayAdapter<String>(
                    MainActivity.this, android.R.layout.simple_expandable_list_item_1, cn);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            spinner.setAdapter(adapter);
        }

    }//AysnTask 스레드 클래스 종료

    private class B_Task extends AsyncTask<Void, Void, Void>/*<String, Integer, Void>*/{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            grif = new getRouteInfo();
            spinner2 = findViewById(R.id.spinner2);
            textView2 = findViewById(R.id.textView2);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            bn = grif.getBusNo("33010");
            bc = grif.getBusCode("33010");

            adapter2 = new ArrayAdapter<String>(
                    MainActivity.this, android.R.layout.simple_expandable_list_item_1, bn);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            spinner2.setAdapter(adapter2);
        }

    }

    private class liV_Task extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            liv = new ArrayList<String>();
            liv.add("lys    823    2021-12-09");
            liv.add("lys    832    2021-12-09");

            adapter3 = new ArrayAdapter<String>(
                    MainActivity.this, android.R.layout.simple_expandable_list_item_1, liv);
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liveiw = findViewById(R.id.liveiw);

        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            liveiw.setAdapter(adapter3);
        }

    }//AysnTask 스레드 클래스 종료

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sbutton = findViewById(R.id.Sbutton);
        Rbutton = findViewById(R.id.Rbutton);

        try {
            new A_Task().execute();
            new B_Task().execute();
            new liV_Task().execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a = cc.get(position);
                c = cn.get(position);
                textView.setText(c);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("");
            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("전체개수", String.valueOf(bn.size()));
                b = bc.get(position);
                textView2.setText(bn.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("낫띵");
            }
        });

        Sbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                intent.putExtra("ccode", a);
                intent.putExtra("busname", b);
                startActivity(intent);
            }
        });

        Rbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = getIntent();
                Intent intent = new Intent(getApplicationContext(), RouteList.class);
                intent.putExtra("ccode", a);
                intent.putExtra("busname", b);
                intent.putExtra("stname", c);
                intent.putExtra("USERid", gIntent.getStringExtra("USERid"));
                //intent.putExtra("ui", 유저 id);
                //intent.putExtra("busCode", spinner2.getSelectedItem().toString());
                startActivity(intent);
            }
        });


    }
}

