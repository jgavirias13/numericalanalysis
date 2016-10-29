package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.newton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.NewtonAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaNewton;
import com.eafit.numericalanalysis.util.Comunicacion;

public class NewtonProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_proceso);

        lista = (ListView) findViewById(R.id.listaNewton);
        SalidaNewton salida = (SalidaNewton) Comunicacion.receive();
        NewtonAdapter adapter = new NewtonAdapter(this,salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
