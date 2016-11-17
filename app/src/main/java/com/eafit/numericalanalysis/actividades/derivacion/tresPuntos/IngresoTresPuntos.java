package com.eafit.numericalanalysis.actividades.derivacion.tresPuntos;

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
import com.eafit.numericalanalysis.metodos.derivacion.DerivadaTresPuntos;
import com.eafit.numericalanalysis.util.Tupla;

public class IngresoTresPuntos extends AppCompatActivity implements View.OnClickListener{

    EditText punto1X;
    EditText punto1Y;
    EditText punto2X;
    EditText punto2Y;
    EditText punto3X;
    EditText punto3Y;
    RadioButton btnEncima;
    RadioButton btnCentro;
    RadioButton btnDebajo;
    TextView txtRespuesta;
    LinearLayout layoutRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_tres_puntos);
        findViewById(R.id.btnCalcular).setOnClickListener(this);
        punto1X = (EditText) findViewById(R.id.punto1X);
        punto1Y = (EditText) findViewById(R.id.punto1Y);
        punto2X = (EditText) findViewById(R.id.punto2X);
        punto2Y = (EditText) findViewById(R.id.punto2Y);
        punto3X = (EditText) findViewById(R.id.punto3X);
        punto3Y = (EditText) findViewById(R.id.punto3Y);
        btnEncima = (RadioButton) findViewById(R.id.rbtnEncima);
        btnCentro = (RadioButton) findViewById(R.id.rbtnCentro);
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


                double x1,x2,x3,y1,y2,y3;
                String sX1, sX2, sX3, sY1, sY2, sY3;
                sX1 = punto1X.getText().toString();
                sY1 = punto1Y.getText().toString();
                sX2 = punto2X.getText().toString();
                sY2 = punto2Y.getText().toString();
                sX3 = punto3X.getText().toString();
                sY3 = punto3Y.getText().toString();
                try{
                    x1=Double.parseDouble(sX1);
                    x2=Double.parseDouble(sX2);
                    x3=Double.parseDouble(sX3);
                    y1=Double.parseDouble(sY1);
                    y2=Double.parseDouble(sY2);
                    y3=Double.parseDouble(sY3);

                    if(btnEncima.isChecked()){ tipo = 1; x=x1;}
                    else if(btnCentro.isChecked()){ tipo = 2; x=x2;}
                    else if(btnDebajo.isChecked()){ tipo = 3; x=x3;}

                    Tupla[] puntos = {new Tupla(x1,y1), new Tupla(x2,y2), new Tupla(x3,y3)};
                    double respuesta = DerivadaTresPuntos.evaluar(puntos, tipo);
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
