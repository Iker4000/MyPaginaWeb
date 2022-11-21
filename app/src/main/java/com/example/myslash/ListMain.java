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

    private ListView listView1;
    private List<Cuenta> list1;
    private ListView listView2;
    private List<Cuenta> list2;
    private ListView listView3;
    private List<Cuenta> list3;
    private int []imagenUser = { R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};
    private int []imagen = { R.drawable.editbutton,R.drawable.removebutton};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        listView1 = (ListView) findViewById(R.id.listViewId1);
        list1 = new ArrayList<Cuenta>();

        listView2 = (ListView) findViewById(R.id.listViewId2);
        list2 = new ArrayList<Cuenta>();

        listView3 = (ListView) findViewById(R.id.listViewId3);
        list3 = new ArrayList<Cuenta>();

        for (int i = 0; i < 4; i++){
            Cuenta cuenta1 = new Cuenta();
            Cuenta cuenta2 = new Cuenta();
            Cuenta cuenta3 = new Cuenta();
            cuenta1.setPassCuenta(String.format("Contraseña: %d",(int)(Math.random()*10000)));
            cuenta2.setImage(imagen[0]);
            cuenta3.setImage(imagen[1]);

            if (i == 0){
                cuenta1.setNameCuenta(String.format( "AMDGMAAF"));
                cuenta1.setImage(imagenUser[0]);
            }
            if (i == 1){
                cuenta1.setNameCuenta(String.format( "IOADK"));
                cuenta1.setImage(imagenUser[1]);
            }
            if (i == 2){
                cuenta1.setNameCuenta(String.format( "TDIH" ));
                cuenta1.setImage(imagenUser[2]);
            }
            if (i == 3){
                cuenta1.setNameCuenta(String.format( "TGWCT" ));
                cuenta1.setImage(imagenUser[3]);
            }
            list1.add(cuenta1);
            list2.add(cuenta2);
            list3.add(cuenta3);
        }

        MyAdapter myAdapter1 = new MyAdapter(list1, getBaseContext());
        listView1.setAdapter(myAdapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast1( i );
            }
        });

        MyAdapterEdit myAdapter2 = new MyAdapterEdit(list2, getBaseContext());
        listView2.setAdapter(myAdapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toast2(i);
            }
        });

        MyAdapterRemove myAdapter3 = new MyAdapterRemove(list3, getBaseContext());
        listView3.setAdapter(myAdapter3);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toast3(i);
            }
        });

    }

    private void toast1( int i )
    {
        Toast.makeText(getBaseContext(), list1.get(i).getPassCuenta(), Toast.LENGTH_SHORT).show();
    }

    private void toast2( int i )
    {
        Toast.makeText(getBaseContext(), ""+i+"", Toast.LENGTH_SHORT).show();
    }

    private void toast3( int i )
    {
        Toast.makeText(getBaseContext(), ""+i+"", Toast.LENGTH_SHORT).show();
    }
}