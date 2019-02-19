package com.example.javi.gestion_de_clientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class ClientesActivity extends AppCompatActivity {

    EditText mEditTextNombrCliente,mEditTextApellidoCliente,mEdiTextTelefonoCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        validar();



    }

    private void validar() {


        mEditTextNombrCliente =(EditText)findViewById(R.id.editTextNombrCliente);
        mEditTextApellidoCliente = (EditText)findViewById(R.id.editTextApellidoCliente);
        mEdiTextTelefonoCliente = (EditText)findViewById(R.id.ediTextTelefonoCliente);

        mEditTextNombrCliente.setError(null);
        mEditTextApellidoCliente.setError(null);
        mEdiTextTelefonoCliente.setError(null);

        String nombre = mEditTextNombrCliente.getText().toString();
        String apellido = mEditTextApellidoCliente.getText().toString();
        String telefono = mEdiTextTelefonoCliente.getText().toString();


        if(TextUtils.isEmpty(nombre)){

            mEditTextNombrCliente.setError("Campo obligatorio");
        }

        if(TextUtils.isEmpty(apellido)){

            mEditTextApellidoCliente.setError("Campo obligatorio");
        }
        if(TextUtils.isEmpty(telefono)){

            mEdiTextTelefonoCliente.setError("Campo obligatorio");
        }

    }

}
