package com.example.myslash.Json;

import android.location.Location;

import java.io.Serializable;

public class Cuenta implements Serializable{
    private String nameCuenta;
    private String passCuenta;
    private Location location;
    private int image;

    public Cuenta(){

    }

    public String getNameCuenta() {
        return nameCuenta;
    }

    public void setNameCuenta(String nameCuenta) {
        this.nameCuenta = nameCuenta;
    }

    public String getPassCuenta() {
        return passCuenta;
    }

    public void setPassCuenta(String passCuenta) {
        this.passCuenta = passCuenta;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}