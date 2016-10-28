package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.biseccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.BiseccionAdapter;
import com.eafit.numericalanalysis.estructuras.BusquedasAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaBiseccion;
import com.eafit.numericalanalysis.util.Comunicacion;

public class BiseccionProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biseccion_proceso);

        lista = (ListView) findViewById(R.id.listaBiseccion);
        SalidaBiseccion salida = (SalidaBiseccion) Comunicacion.receive();
        BiseccionAdapter adapter = new BiseccionAdapter(this,salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
