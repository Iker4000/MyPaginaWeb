package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

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
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        listView = (ListView) findViewById(R.id.listViewId1);
        list = new ArrayList<String>();

        boolean BucleArchivo = true;
        int x = 1;
        while (BucleArchivo) {
            File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
            if(Cfile.exists()) {
                try {
                    BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                    String lineaTexto = file.readLine();
                    file.close();


                    Json json = new Json();
                    Info datos = json.leerJson(lineaTexto);

                    list.add(String.format(datos.getUserName()));
                    x = x + 1;

                }catch(Exception e){
                    BucleArchivo = false;
                }
            }else{
                BucleArchivo = false;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_list_view_actividad,R.id.textViewId, list );
        listView.setAdapter(arrayAdapter);
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
        Toast.makeText(getBaseContext(), list.get(i), Toast.LENGTH_SHORT).show();
    }
}