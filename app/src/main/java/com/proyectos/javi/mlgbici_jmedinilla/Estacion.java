package com.proyectos.javi.mlgbici_jmedinilla;

public class Estacion {
    String direccion;
    String latitud;
    String longitud;
    String estado;
    String plazas;
    String plazasLibres;
    String plazasOcupadas;

    @Override
    public String toString() {
        return direccion;
    }
}
