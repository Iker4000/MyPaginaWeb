package com.example.myslash.BreakingAPI;

import java.io.Serializable;

public class BreakingFrases implements Serializable {

    private String frase;
    private String autor;
    private int imagen;

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
