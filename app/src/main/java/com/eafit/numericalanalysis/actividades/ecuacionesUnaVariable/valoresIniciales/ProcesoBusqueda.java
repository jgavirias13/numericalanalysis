package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.valoresIniciales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.BusquedasAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaBusquedasIncrementales;
import com.eafit.numericalanalysis.util.Comunicacion;

public class ProcesoBusqueda extends AppCompatActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_busqueda);
        SalidaBusquedasIncrementales salida = (SalidaBusquedasIncrementales) Comunicacion.receive();
        lista = (ListView) findViewById(R.id.listaBusqueda);
        BusquedasAdapter adapter = new BusquedasAdapter(this, salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
