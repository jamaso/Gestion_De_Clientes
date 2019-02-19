package com.example.javi.gestion_de_clientes;

        import android.app.Activity;
        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.javi.gestion_de_clientes.zonaVenta.ZonaVentaActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppCompatActivity actividad = this;

        Button buton=(Button) findViewById(R.id.buttonIrZonaVenta);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),ZonaVentaActivity.class);
                startActivity(intent);
            }
        });

    }
}
