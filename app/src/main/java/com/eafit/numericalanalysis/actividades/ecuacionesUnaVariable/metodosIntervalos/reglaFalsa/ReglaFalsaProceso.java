package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.reglaFalsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.ReglaFalsaAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaReglaFalsa;
import com.eafit.numericalanalysis.util.Comunicacion;

public class ReglaFalsaProceso extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regla_falsa_proceso);

        lista = (ListView) findViewById(R.id.listaReglaFalsa);
        SalidaReglaFalsa salida = (SalidaReglaFalsa) Comunicacion.receive();
        ReglaFalsaAdapter adapter = new ReglaFalsaAdapter(this,salida.getMatriz());
        lista.setAdapter(adapter);
    }
}
