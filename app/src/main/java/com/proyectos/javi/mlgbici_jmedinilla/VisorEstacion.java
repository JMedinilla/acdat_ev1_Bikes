package com.proyectos.javi.mlgbici_jmedinilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VisorEstacion extends AppCompatActivity {

    TextView txtDireccion;
    TextView txtLatitud;
    TextView txtLongitud;
    TextView txtEstado;
    TextView txtPlazas;
    TextView txtPlazasLibres;
    TextView txtPlazasOcupadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_estacion);

        txtDireccion = (TextView)findViewById(R.id.txtDireccion);
        txtLatitud = (TextView)findViewById(R.id.txtLatitud);
        txtLongitud = (TextView)findViewById(R.id.txtLongitud);
        txtEstado = (TextView)findViewById(R.id.txtEstado);
        txtPlazas = (TextView)findViewById(R.id.txtPlazas);
        txtPlazasLibres = (TextView)findViewById(R.id.txtPlazasLibres);
        txtPlazasOcupadas = (TextView)findViewById(R.id.txtPlazasOcupadas);

        Intent intent = getIntent();
        String dir = intent.getStringExtra("direccion");
        String lat = intent.getStringExtra("latitud");
        String lon = intent.getStringExtra("longitud");
        String est = intent.getStringExtra("estado");
        String pla = intent.getStringExtra("plazas");
        String pll = intent.getStringExtra("plazasLibres");
        String plo = intent.getStringExtra("plazasOcupadas");

        txtDireccion.setText(dir);
        txtLatitud.setText(lat);
        txtLongitud.setText(lon);
        txtEstado.setText(est);
        txtPlazas.setText(pla);
        txtPlazasLibres.setText(pll);
        txtPlazasOcupadas.setText(plo);
    }
}
