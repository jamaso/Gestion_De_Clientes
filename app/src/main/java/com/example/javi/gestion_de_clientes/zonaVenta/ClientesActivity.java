package com.example.javi.gestion_de_clientes.zonaVenta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.javi.gestion_de_clientes.R;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ClienteDetalleActivity.class);
                startActivity(intent);
            }
        });

        int IdZona = getIntent().getExtras().getInt("ID");
        /*TextView textViewIdZona = (TextView) findViewById(R.id.textViewIdZona);
        textViewIdZona.setText(String.valueOf(ID));*/

        ClienteListFragment clienteListFragment = ClienteListFragment.newInstance(IdZona);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentoClientes, clienteListFragment);
        transaction.commit();

    }

}
