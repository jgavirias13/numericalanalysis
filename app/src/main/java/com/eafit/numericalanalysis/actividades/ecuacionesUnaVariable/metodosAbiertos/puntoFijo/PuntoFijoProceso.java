package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.puntoFijo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.PuntoFijoAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaPuntoFijo;
import com.eafit.numericalanalysis.util.Comunicacion;

public class PuntoFijoProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_fijo_proceso);

        lista = (ListView) findViewById(R.id.listaPuntoFijo);
        SalidaPuntoFijo salida = (SalidaPuntoFijo) Comunicacion.receive();
        PuntoFijoAdapter adapter = new PuntoFijoAdapter(this, salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
