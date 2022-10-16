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

    public String leerArchivo(String Sha1Password1, boolean Cfile, int x) {
        try {

            if(Cfile) {
                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                String lineaTexto = file.readLine();
                file.close();

                Json json = new Json();
                Info datos = json.leerJson(lineaTexto);
                String Sha1Password2 = datos.getPassword();

                if (Sha1Password1.equals(Sha1Password2)) {
                    return "Usuario Encontrado";
                } else {
                    return "Siguiente";
                }
            }else{
                return "Usuario no Encontrado";
            }

        } catch (Exception e) {
            return "Error en el Archivo";
        }
    }
}
