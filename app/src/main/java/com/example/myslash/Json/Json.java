package com.example.myslash.Json;

import android.graphics.Bitmap;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myslash.Encriptación.Des;
import com.example.myslash.Encriptación.EncripBitMap;
import com.google.gson.Gson;

public class Json extends AppCompatActivity{

    public static String crearJson(String Name , String firstName , String lastName , String userName , String Mail , int Age , long Number , boolean Gender , boolean Type , String Password ) {
        Info datos = new Info();
        Gson gson = new Gson();
        Des myDes = new Des();

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

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static Info leerJson(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        Info datos = gson.fromJson(myDes.desCifrar(textoJson), Info.class);

        return datos;
    }

    public static String crearJsonCuenta(String Name , String Password , Location location , boolean tipo , Bitmap bitmap , int Image) {
        Cuenta datos = new Cuenta();
        Gson gson = new Gson();
        Des myDes = new Des();
        EncripBitMap EBM = new EncripBitMap();

        String imageP;

        if(tipo) {
            imageP = EBM.cifrar(bitmap);
        }else{
            imageP = null;
        }

        datos.setNameCuenta(Name);
        datos.setPassCuenta(Password);
        datos.setLocation(location);
        datos.setTipo(tipo);
        datos.setImageP(imageP);
        datos.setImage(Image);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static Cuenta leerJsonCuenta(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        Cuenta datos = gson.fromJson(myDes.desCifrar(textoJson), Cuenta.class);

        return datos;
    }
}
