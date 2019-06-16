package com.devggr.uberclone.Pojos;

public class User {
    private String nombre, email, password, urlFoto, description, lat, longi, calif;

    public User(String nombre, String email, String password, String urlFoto, String description, String lat, String longi, String calif) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.urlFoto = urlFoto;
        this.description = description;
        this.lat = lat;
        this.longi = longi;
        this.calif = calif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getCalif() {
        return calif;
    }

    public void setCalif(String calif) {
        this.calif = calif;
    }

    public User() {
    }

}
