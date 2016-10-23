package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;

public class IngresoFunciones extends AppCompatActivity implements View.OnClickListener{

    private EditText funcion_f;
    private EditText funcion_g;
    private EditText derivada_f;
    private EditText derivada2_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_funciones);

        this.funcion_f = (EditText) findViewById(R.id.edtF);
        this.funcion_g = (EditText) findViewById(R.id.edtG);
        this.derivada_f = (EditText) findViewById(R.id.edtDF);
        this.derivada2_f = (EditText) findViewById(R.id.edtD2F);

        findViewById(R.id.btnGuardar).setOnClickListener(this);
        findViewById(R.id.btnLimpiar).setOnClickListener(this);

        EstadoFunciones estadoActual = EstadoFunciones.solicitarEstado();
        this.funcion_f.setText(estadoActual.getFuncion_f());
        this.funcion_g.setText(estadoActual.getFuncion_g());
        this.derivada_f.setText(estadoActual.getDerivada_f());
        this.derivada2_f.setText(estadoActual.getDerivada2_f());
    }

    public void onClick(View view){
        switch(view.getId()){
            case(R.id.btnGuardar):
                if(guardarEstado())
                    this.finish();
                break;
            case(R.id.btnLimpiar):
                limpiarCampos();
                break;
        }
    }

    private boolean guardarEstado(){
        EstadoFunciones estadoActual = EstadoFunciones.solicitarEstado();
        if(!estadoActual.setFuncion_f(funcion_f.getText().toString())){
            errorFuncion("f(x)");
            return false;
        }
        if(!estadoActual.setFuncion_g(funcion_g.getText().toString())){
            errorFuncion("g(x)");
            return false;
        }
        if(!estadoActual.setDerivada_f(derivada_f.getText().toString())){
            errorFuncion("f\'(x)");
            return false;
        }
        if(!estadoActual.setDerivada2_f(derivada2_f.getText().toString())){
            errorFuncion("f\'\'(x)");
            return false;
        }
        return true;
    }

    private void errorFuncion(String funcion){
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.error_info);

        Button cancelar = (Button) dialog.findViewById(R.id.btnCancelarInfo);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView texto = (TextView) dialog.findViewById(R.id.texto_error);
        Resources res = getResources();
        texto.setText(res.getText(R.string.error_texto)+" "+funcion);

        dialog.show();
    }

    private void limpiarCampos(){
        this.funcion_f.setText("");
        this.funcion_g.setText("");
        this.derivada_f.setText("");
        this.derivada2_f.setText("");
    }
}
