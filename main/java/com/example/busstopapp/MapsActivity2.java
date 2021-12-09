package com.example.busstopapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.busstopapp.API.BusNowLocation;
import com.example.busstopapp.API.BusRouteList;
import com.example.busstopapp.API.NowCloseStop;
import com.example.busstopapp.API.getCtyCodeList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.busstopapp.databinding.ActivityMaps2Binding;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    ArrayList<Double> glati;
    ArrayList<Double> glong;
    ArrayList<String> stopCD;
    ArrayList<String> stopNM;
    ArrayList<Double> bglati;
    ArrayList<Double> bglong;
    ArrayList<Double> STglati;
    ArrayList<Double> STglong;
    ArrayList<String> STstopNM;
    BusRouteList BRL;
    BusNowLocation BNL;
    NowCloseStop NCS;
    Double[] a;
    Double[] b;
    String[] c;
    String[] d;
    Double[] ma;
    Double[] mb;
    String[] mc;
    String[] md;
    Double[] STa;
    Double[] STb;
    String[] STn;
    int size = 0;
    int msize = 0;
    int STsize = 0;


    private class ST_Task extends AsyncTask<Double, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            NCS = new NowCloseStop();
        }

        @Override
        protected Void doInBackground(Double... doubles) {
            STglati = NCS.closeStopLA(doubles[0],doubles[1]);
            STglong = NCS.closeStopLO(doubles[0],doubles[1]);
            STstopNM = NCS.closeStopNM(doubles[0],doubles[1]);
            STa = STglati.toArray(new Double[STglati.size()]);
            STb = STglong.toArray(new Double[STglong.size()]);
            STn = STstopNM.toArray(new String[STstopNM.size()]);
            STsize = STglati.size();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            int i = 0;
            Log.i("카운터", String.valueOf(STsize));
            for(i=0;i<5;i++)
            {
                Log.i("태그", String.valueOf(STa[i])+String.valueOf(STb[i]));
                LatLng sydney = new LatLng(STa[i], STb[i]);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(sydney).title(STn[i]);

                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.stop);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }
        }

    }


    private class MB_Task extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            BNL = new BusNowLocation();

        }

        @Override
        protected Void doInBackground(String... strings) {
            bglati = BNL.NowBusLocationLati(strings[0], strings[1]);
            bglong = BNL.NowBusLocationLong(strings[0], strings[1]);

            ma = bglati.toArray(new Double[bglati.size()]);
            mb = bglong.toArray(new Double[bglong.size()]);

            msize = bglati.size();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.i("카운터", String.valueOf(msize));
            for(int i=0;i<msize;i++)
            {
                LatLng sydney = new LatLng(ma[i], mb[i]);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(sydney);

                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.busicon2);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

        }

    }

    private class M_Task extends AsyncTask<String, Void, Void> {

        /*
        @Override
        protected Void doInBackground(Void... voids) {
            glati = BRL.busStopLati("33010","CJB270002000");
            glong = BRL.busStopLong("33010","CJB270002000");
            stopCD = BRL.stopCode("33010","CJB270002000");
            stopNM = BRL.stopName("33010","CJB270002000");
            a = glati.toArray(new Double[glati.size()]);
            b = glong.toArray(new Double[glong.size()]);
            c = stopCD.toArray(new String[stopCD.size()]);
            d = stopNM.toArray(new String[stopNM.size()]);
            size = glati.size();
            return null;
        }

         */



        @Override
        protected Void doInBackground(String... strings) {
            glati = BRL.busStopLati(strings[0],strings[1]);
            glong = BRL.busStopLong(strings[0],strings[1]);
            stopCD = BRL.stopCode(strings[0],strings[1]);
            stopNM = BRL.stopName(strings[0],strings[1]);
            a = glati.toArray(new Double[glati.size()]);
            b = glong.toArray(new Double[glong.size()]);
            c = stopCD.toArray(new String[stopCD.size()]);
            d = stopNM.toArray(new String[stopNM.size()]);
            size = glati.size();
            return null;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            BRL = new BusRouteList();

        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.i("카운터", String.valueOf(size));
            for(int i=0;i<size;i++)
            {
                LatLng sydney = new LatLng(a[i], b[i]);
                mMap.addMarker(new MarkerOptions().position(sydney).title(d[i]));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMaxZoomPreference(10);
        mMap.setMinZoomPreference(14);

        //서원대학교 좌표
        double nowLa = 36.6252;
        double nowLo = 127.4822;

        Intent intent = getIntent();
        String a = intent.getStringExtra("ccode");//"33010";
        String b = intent.getStringExtra("busname");//"CJB270002000";

        Log.i("ccode", a);
        Log.i("busname", b);

        try {
            new M_Task().execute(a,b);
            new MB_Task().execute(a,b);
            new ST_Task().execute(nowLa, nowLo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}