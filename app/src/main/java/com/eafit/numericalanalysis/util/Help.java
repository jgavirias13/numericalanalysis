package com.eafit.numericalanalysis.util;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        int id = getIntent().getIntExtra("id",0);
        Resources res = getResources();
        String help = res.getString(id);
        TextView txt = (TextView) findViewById(R.id.txtHelp);
        txt.setText(help);
    }
}
