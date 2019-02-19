package com.example.javi.gestion_de_clientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;

public class ZonaVentaDetlleActivity extends AppCompatActivity {


    EditText mEditTextNumZona, mEditTextLoclidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_venta_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validar();


    }

    private void validar() {

        mEditTextNumZona = (EditText) findViewById(R.id.editTextNumZona);
        mEditTextLoclidad = (EditText) findViewById(R.id.editTextLoclidad);

        String numZona = mEditTextNumZona.getText().toString();
        String localidad = mEditTextLoclidad.getText().toString();

        mEditTextNumZona.setError(null);
        mEditTextLoclidad.setError(null);

        if (TextUtils.isEmpty(numZona)) {

            mEditTextNumZona.setError("Campo obligartorio");
            mEditTextNumZona.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(localidad)) {

            mEditTextLoclidad.setError("Campo obligartorio");
            mEditTextLoclidad.requestFocus();
            return;


        }
    }
}