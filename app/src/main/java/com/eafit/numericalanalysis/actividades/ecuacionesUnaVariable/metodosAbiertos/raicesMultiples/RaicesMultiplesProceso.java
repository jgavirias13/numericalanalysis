package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.raicesMultiples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.RaicesMultiplesAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaRaicesMultiples;
import com.eafit.numericalanalysis.util.Comunicacion;

public class RaicesMultiplesProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raices_multiples_proceso);
        lista = (ListView) findViewById(R.id.listaRaicesMultiples);
        SalidaRaicesMultiples salida = (SalidaRaicesMultiples) Comunicacion.receive();
        RaicesMultiplesAdapter adapter = new RaicesMultiplesAdapter(this,salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
