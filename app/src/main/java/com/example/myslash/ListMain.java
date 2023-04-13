package com.example.myslash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myslash.Json.Cuenta;
import com.example.myslash.Json.Info;
import com.example.myslash.Json.Json;
import com.example.myslash.List.MyAdapter;
import com.example.myslash.List.MyAdapterEdit;
import com.example.myslash.List.MyAdapterRemove;
import com.example.myslash.MySQLite.DbCuenta;
import com.example.myslash.MySQLite.DbInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ListMain extends AppCompatActivity {

    private TextView textView;
    private ListView listView1, listView2, listView3;
    private List<Cuenta> list1, list2, list3;
    private int []imagenUser = { R.drawable.user,R.drawable.user1,R.drawable.user2,R.drawable.user3 };
    private int []imagen = { R.drawable.editbutton,R.drawable.removebutton };
    private Button btnSiguiente, btnAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numLista = getIntent().getExtras().getInt("numLista");

        textView = (TextView) findViewById(R.id.textViewL1);
        btnAnterior = (Button) findViewById(R.id.buttonL1);
        btnSiguiente = (Button) findViewById(R.id.buttonL2);

        Json json = new Json();

        try {
            DbInfo dbInfo = new DbInfo(ListMain.this);
            DbCuenta dbCuenta = new DbCuenta(ListMain.this);
            String completoTextoU = dbInfo.verInfo(numArchivo);
            Info datosU = json.leerJson(completoTextoU);

            textView.setText("Cuentas de " + datosU.getUserName());

            listView1 = (ListView) findViewById(R.id.listViewId1);
            list1 = new ArrayList<Cuenta>();

            listView2 = (ListView) findViewById(R.id.listViewId2);
            list2 = new ArrayList<Cuenta>();

            listView3 = (ListView) findViewById(R.id.listViewId3);
            list3 = new ArrayList<Cuenta>();

            boolean BucleArchivo = true;
            int x = numLista;
            while (BucleArchivo) {
                if((dbCuenta.comprobarCuenta(numArchivo, x)) && (x < (numLista + 5))){
                    String completoTexto = dbCuenta.verCuenta(numArchivo, x);

                    Cuenta datos = json.leerJsonCuenta(completoTexto);

                    Cuenta cuenta1 = new Cuenta();
                    Cuenta cuenta2 = new Cuenta();
                    Cuenta cuenta3 = new Cuenta();
                    cuenta1.setPassCuenta(datos.getPassCuenta());
                    cuenta1.setNameCuenta(datos.getNameCuenta());
                    cuenta1.setLocation(datos.getLocation());
                    cuenta1.setTipo(datos.isTipo());
                    cuenta1.setImageP(datos.getImageP());
                    cuenta1.setImage(datos.getImage());
                    cuenta2.setImage(imagen[0]);
                    cuenta3.setImage(imagen[1]);

                    list1.add(cuenta1);
                    list2.add(cuenta2);
                    list3.add(cuenta3);
                    x = x + 1;
                }else{
                    BucleArchivo = false;
                }
            }

            if(numLista == 1){
                btnAnterior.setEnabled(false);
            }
            if (!dbCuenta.comprobarCuenta(numArchivo, (numLista + 5))){
                btnSiguiente.setEnabled(false);
            }

            MyAdapter myAdapter1 = new MyAdapter(list1, getBaseContext());
            listView1.setAdapter(myAdapter1);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    toast1(i);
                }
            });

            MyAdapterEdit myAdapter2 = new MyAdapterEdit(list2, getBaseContext());
            listView2.setAdapter(myAdapter2);
            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toast2( i + (numLista - 1));
                }
            });

            MyAdapterRemove myAdapter3 = new MyAdapterRemove(list3, getBaseContext());
            listView3.setAdapter(myAdapter3);
            listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toast3( i + (numLista - 1));
                }
            });

            btnAnterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (ListMain.this, ListMain.class);
                    intent.putExtra("numArchivo", numArchivo);
                    intent.putExtra("numLista", numLista - 5);
                    startActivity( intent );
                }
            });

            btnSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (ListMain.this, ListMain.class);
                    intent.putExtra("numArchivo", numArchivo);
                    intent.putExtra("numLista", numLista + 5);
                    startActivity( intent );
                }
            });

        }catch(Exception e){
            Toast.makeText(getBaseContext(), "Error al Cargar la Lista", Toast.LENGTH_SHORT).show();
        }
    }

    private void toast1( int i )
    {
        Toast.makeText(getBaseContext(), list1.get(i).getPassCuenta(), Toast.LENGTH_SHORT).show();
    }

    private void toast2( int i )
    {
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numLista = getIntent().getExtras().getInt("numLista");
        Intent intent1 = new Intent (ListMain.this, EditList.class);
        intent1.putExtra("numArchivo", numArchivo);
        intent1.putExtra("numContext", 2);
        intent1.putExtra("numLista", numLista);
        intent1.putExtra("numArchivoCuenta", (i + 1));
        startActivity( intent1 );
    }

    private void toast3( int i )
    {
        try {
            DbCuenta dbCuenta = new DbCuenta(ListMain.this);
            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            int numLista = getIntent().getExtras().getInt("numLista");
            if (numLista == (i+1) && numLista > 1 && !dbCuenta.comprobarCuenta(numArchivo, (numLista + 1))){numLista -= 5;}
            boolean BucleArchivo = true;
            int x = (i + 1);
            while (BucleArchivo) {
                if (dbCuenta.comprobarCuenta(numArchivo, x) & dbCuenta.comprobarCuenta(numArchivo, (x + 1))){
                    int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");
                    String completoTexto = dbCuenta.verCuenta(numArchivo, (x + 1));
                    dbCuenta.editarCuenta(numArchivo, x, completoTexto);

                    x = x + 1;
                }
                if (dbCuenta.comprobarCuenta(numArchivo, x) & !dbCuenta.comprobarCuenta(numArchivo, (x + 1))){
                    dbCuenta.eliminarCuenta(numArchivo, x);

                    Intent intent = new Intent (ListMain.this, ListMain.class);
                    intent.putExtra("numArchivo", numArchivo);
                    intent.putExtra("numLista", numLista);
                    startActivity( intent );
                    BucleArchivo = false;
                }
            }
        }catch(Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean flag = false;
        MenuInflater menuInflater = null;
        flag = super.onCreateOptionsMenu(menu);
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mi_menu, menu);
        return flag;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String seleccion = null;
        switch(item.getItemId()){
            case R.id.MenuCerrarSesionrId:
                Intent intent1 = new Intent (ListMain.this, Login.class);
                startActivity( intent1 );
                break;
            case R.id.MenuBreakingApi:
                int numArchivo1 = getIntent().getExtras().getInt("numArchivo");
                int numLista1 = getIntent().getExtras().getInt("numLista");
                Intent intent2 = new Intent (ListMain.this, WebApi.class);
                intent2.putExtra("numArchivo", numArchivo1);
                intent2.putExtra("numLista", numLista1);
                startActivity( intent2 );
                break;
            case R.id.MenuNuevoId:
                int numArchivo2 = getIntent().getExtras().getInt("numArchivo");
                int numLista2 = getIntent().getExtras().getInt("numLista");
                Intent intent3 = new Intent (ListMain.this, EditList.class);
                intent3.putExtra("numArchivo", numArchivo2);
                intent3.putExtra("numContext", 1);
                intent3.putExtra("numLista", numLista2);
                startActivity( intent3 );
                break;
            default:
                seleccion = "sin opcion %s";
                Toast.makeText(getBaseContext(), seleccion, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}