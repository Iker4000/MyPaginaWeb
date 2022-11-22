package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Json extends AppCompatActivity{

    public static String crearJson(String Name , String firstName , String lastName , String userName , String Mail , int Age , int Number , boolean Gender , boolean Type , String Password )
    {

        Info datos = new Info();
        Gson gson = new Gson();

        datos.setName(Name);
        datos.setFirstName(firstName);
        datos.setLastName(lastName);
        datos.setUserName(userName);
        datos.setMail(Mail);
        datos.setAge(Age);
        datos.setNumber(Number);
        datos.setGender(Gender);
        datos.setType(Type);
        datos.setPassword(Password);

        String nuevojson = gson.toJson(datos);

        return nuevojson;
    }

    public static Info leerJson(String textoJson){
        Gson gson = new Gson();
        Info datos = gson.fromJson(textoJson, Info.class);

        return datos;
    }

    public static String crearJsonCuenta(String Name , String Password , int Image)
    {

        Cuenta datos = new Cuenta();
        Gson gson = new Gson();

        datos.setNameCuenta(Name);
        datos.setPassCuenta(Password);
        datos.setImage(Image);

        String nuevojson = gson.toJson(datos);

        return nuevojson;
    }

    public static Cuenta leerJsonCuenta(String textoJson){
        Gson gson = new Gson();
        Cuenta datos = gson.fromJson(textoJson, Cuenta.class);

        return datos;
    }
}
