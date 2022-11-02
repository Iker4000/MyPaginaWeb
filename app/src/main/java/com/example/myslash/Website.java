package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + ".txt")));
            String lineaTexto = file.readLine();
            Info datos = json.leerJson(lineaTexto);
            file.close();

            textview.setText("Welcome " + datos.getFirstName());
        }catch(Exception e){}
    }

    public void CerrarSesion (View v){
        Intent intent = new Intent (Website.this, Login.class);
        startActivity( intent );
    }
}