package com.example.javi.gestion_de_clientes.zonaVenta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.javi.gestion_de_clientes.R;
import com.example.javi.gestion_de_clientes.constantes.G;
import com.example.javi.gestion_de_clientes.pojos.ZonaVenta;
import com.example.javi.gestion_de_clientes.proveedor.ZonaventasProveedor;

/*import android.widget.Toolbar;*/


public class ZonaVentaInsertarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_venta_detalle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalle_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.add(Menu.NONE, G.GUARDAR, Menu.NONE, "Guardar");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.ic_action_guardar);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case G.GUARDAR:
                attempGuardar();
        }
        return super.onOptionsItemSelected(item);
    }

    void attempGuardar() {

        EditText etNumZona = (EditText) findViewById(R.id.editTextNumZona);
        EditText etLocalidad = (EditText) findViewById(R.id.editTextLoclidad);
        /*EditText editTextTelefonoCliente = (EditText) findViewById(R.id.ediTextTelefonoCliente);*/


        etNumZona.setError(null);
        etLocalidad.setError(null);
        /*editTextTelefonoCliente.setError(null);*/


        String numZona = String.valueOf(etNumZona.getText());
        String localidad = String.valueOf(etLocalidad.getText());
        /*String telefono = String.valueOf(editTextTelefonoCliente.getText());*/

        if (TextUtils.isEmpty(numZona)) {
            etNumZona.setError(getString(R.string.campo_requerido));
            etNumZona.requestFocus();
            return;
        }

        if(ZonaventasProveedor.existeZona(getContentResolver(),numZona)){
            etNumZona.setError("La zona ya existe");
            etNumZona.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(localidad)) {
            etLocalidad.setError(getString(R.string.campo_requerido));
            etLocalidad.requestFocus();
            return;
        }
        /*if (TextUtils.isEmpty(telefono)) {
            editTextTelefonoCliente.setError(getString(R.string.campo_requerido));
            editTextTelefonoCliente.requestFocus();
            return;


        }*/


        /*Cliente cliente = new Cliente(G.SIN_VALOR_INT, nombre, apellido,telefono);*/

        ZonaVenta zonaVenta = new ZonaVenta( G.SIN_VALOR_INT, numZona,localidad);
        ZonaventasProveedor.insert( getContentResolver(), zonaVenta);
        finish();


    }
}