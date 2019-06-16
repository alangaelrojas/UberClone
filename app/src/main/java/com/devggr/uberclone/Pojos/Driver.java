package com.devggr.uberclone.Pojos;

public class Driver {

    private String urlFoto;
    private String nombre;
    private String calif;
    private String asientos;
    private String lat;
    private String longi;
    private String placas;
    private String description;

    public Driver(String urlFoto, String nombre, String calif, String asientos, String lat, String longi, String placas, String description) {
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.calif = calif;
        this.asientos = asientos;
        this.lat = lat;
        this.longi = longi;
        this.placas = placas;
        this.description = description;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalif() {
        return calif;
    }

    public void setCalif(String calif) {
        this.calif = calif;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
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

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Driver() {
    }

}
