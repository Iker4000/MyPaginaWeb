package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myslash.Json.Info;
import com.example.myslash.Json.Json;
import com.example.myslash.MySQLite.DbInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Website extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        textview = findViewById(R.id.textView);

        try {

            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            Json json = new Json();

            DbInfo dbInfo = new DbInfo(Website.this);
            String completoTexto = dbInfo.verInfo(numArchivo);
            Info datos = json.leerJson(completoTexto);

            textview.setText("Welcome " + datos.getFirstName());
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            new Handler( ).postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent intent = new Intent( Website.this, ListMain.class);
                    intent.putExtra("numArchivo", numArchivo);
                    startActivity( intent );
                }
            } , 4000 );
        }catch(Exception e){}
    }
}