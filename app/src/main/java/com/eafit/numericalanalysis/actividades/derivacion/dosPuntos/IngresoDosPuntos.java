package com.eafit.numericalanalysis.actividades.derivacion.dosPuntos;

import android.app.Dialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.metodos.derivacion.DerivadaDosPuntos;
import com.eafit.numericalanalysis.metodos.derivacion.DerivadaTresPuntos;
import com.eafit.numericalanalysis.util.Tupla;

public class IngresoDosPuntos extends AppCompatActivity implements View.OnClickListener{

    EditText punto1X;
    EditText punto1Y;
    EditText punto2X;
    EditText punto2Y;
    RadioButton btnEncima;
    RadioButton btnDebajo;
    TextView txtRespuesta;
    LinearLayout layoutRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_dos_puntos);
        findViewById(R.id.btnCalcular).setOnClickListener(this);
        punto1X = (EditText) findViewById(R.id.punto1X);
        punto1Y = (EditText) findViewById(R.id.punto1Y);
        punto2X = (EditText) findViewById(R.id.punto2X);
        punto2Y = (EditText) findViewById(R.id.punto2Y);
        btnEncima = (RadioButton) findViewById(R.id.rbtnEncima);
        btnDebajo = (RadioButton) findViewById(R.id.rbtnDebajo);
        txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);
        layoutRespuesta = (LinearLayout) findViewById(R.id.layoutRespuesta);
        btnEncima.setActivated(true);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnCalcular:
                int tipo=0;
                double x=0;


                double x1,x2,y1,y2;
                String sX1, sX2, sY1, sY2;
                sX1 = punto1X.getText().toString();
                sY1 = punto1Y.getText().toString();
                sX2 = punto2X.getText().toString();
                sY2 = punto2Y.getText().toString();
                try{
                    x1=Double.parseDouble(sX1);
                    x2=Double.parseDouble(sX2);
                    y1=Double.parseDouble(sY1);
                    y2=Double.parseDouble(sY2);

                    if(btnEncima.isChecked()){ tipo = 1; x=x1;}
                    else if(btnDebajo.isChecked()){ tipo = 2; x=x2;}

                    Tupla[] puntos = {new Tupla(x1,y1), new Tupla(x2,y2)};
                    double respuesta = DerivadaDosPuntos.evaluar(puntos, tipo);
                    txtRespuesta.setText(String.format("Derivada en: %.14f = %.14f",x,respuesta));
                    layoutRespuesta.setVisibility(View.VISIBLE);
                }catch (NumberFormatException e){
                    Resources res = getResources();
                    error(res.getString(R.string.error_puntos));
                }
        }
    }

    private void error(String mensaje){
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
        texto.setText(mensaje);

        dialog.show();
    }
}
