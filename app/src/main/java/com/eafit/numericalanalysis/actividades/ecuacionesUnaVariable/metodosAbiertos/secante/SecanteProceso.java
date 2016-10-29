package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.secante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.SalidaSecante;
import com.eafit.numericalanalysis.estructuras.SecanteAdapter;
import com.eafit.numericalanalysis.util.Comunicacion;

public class SecanteProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secante_proceso);

        lista = (ListView) findViewById(R.id.listaSecante);
        SalidaSecante salida = (SalidaSecante) Comunicacion.receive();
        SecanteAdapter adapter = new SecanteAdapter(this, salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
