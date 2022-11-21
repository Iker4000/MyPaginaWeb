package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListMain extends AppCompatActivity {

    private ListView listView;
    private List<Cuenta> list;
    private int []imagen = { R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        listView = (ListView) findViewById(R.id.listViewId1);
        list = new ArrayList<Cuenta>();

        for (int i = 0; i < 4; i++){
            Cuenta cuenta = new Cuenta();
            cuenta.setPassCuenta(String.format("Contraseña: %d",(int)(Math.random()*10000)));

            if (i == 0){
                cuenta.setNameCuenta(String.format( "AMDGMAAF"));
                cuenta.setImage(imagen[0]);
            }
            if (i == 1){
                cuenta.setNameCuenta(String.format( "IOADK"));
                cuenta.setImage(imagen[1]);
            }
            if (i == 2){
                cuenta.setNameCuenta(String.format( "TDIH" ));
                cuenta.setImage(imagen[2]);
            }
            if (i == 3){
                cuenta.setNameCuenta(String.format( "TGWCT" ));
                cuenta.setImage(imagen[3]);
            }
            list.add(cuenta);
        }

        MyAdapter myAdapter = new MyAdapter(list, getBaseContext());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast( i );
            }
        });
    }

    private void toast( int i )
    {
        Toast.makeText(getBaseContext(), list.get(i).getPassCuenta(), Toast.LENGTH_SHORT).show();
    }
}