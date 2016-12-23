package com.proyectos.javi.mlgbici_jmedinilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String WEB = "http://datosabiertos.malaga.eu/api/action/datastore_search?resource_id=3bb304f9-9de3-4bac-943e-7acce7e8e8f9";

    ListView listaEstaciones;

    ArrayList<Estacion> estaciones;
    ArrayAdapter<Estacion> adapter;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEstaciones = (ListView)findViewById(R.id.listaEstaciones);
        listaEstaciones.setOnItemClickListener(this);

        client = new AsyncHttpClient();

        client.get(WEB, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Toast toast = Toast.makeText(MainActivity.this, "No se ha podido descargar", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    estaciones = analizarEstaciones(response);
                    Toast.makeText(MainActivity.this, "Datos descargados", Toast.LENGTH_SHORT).show();
                    mostrar();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Excepción: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MainActivity.this, VisorEstacion.class);
        i.putExtra("direccion", estaciones.get(position).direccion);
        i.putExtra("latitud", estaciones.get(position).latitud);
        i.putExtra("longitud", estaciones.get(position).longitud);
        i.putExtra("estado", estaciones.get(position).estado);
        i.putExtra("plazas", estaciones.get(position).plazas);
        i.putExtra("plazasLibres", estaciones.get(position).plazasLibres);
        i.putExtra("plazasOcupadas", estaciones.get(position).plazasOcupadas);
        startActivity(i);

    }

    private void mostrar() {
        if (estaciones != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estaciones);
            listaEstaciones.setAdapter(adapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Estacion> analizarEstaciones(JSONObject response) throws JSONException {
        ArrayList<Estacion> ests = new ArrayList<>();

        JSONObject result = response.getJSONObject("result");
        JSONArray records = result.getJSONArray("records");

        for (int i = 0; i< records.length(); i++){
            Estacion est = new Estacion();

            JSONObject numE = records.getJSONObject(i);
            est.direccion = numE.getString("DIRECCION");
            est.latitud = numE.getString("LAT");
            est.longitud = numE.getString("LON");
            est.estado = numE.getString("NOMBRE_ESTADO");
            est.plazas = numE.getString("NUM_DERBIS");
            est.plazasLibres = numE.getString("NUM_LIBRES");
            est.plazasOcupadas = numE.getString("NUM_OCUPADOS");

            ests.add(est);
        }
        return ests;
    }
}
