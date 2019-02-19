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
import com.example.javi.gestion_de_clientes.pojos.Cliente;
import com.example.javi.gestion_de_clientes.proveedor.ClientesProveedor;

/*import android.widget.Toolbar;*/


public class ClienteDetalleActivity extends AppCompatActivity {
    int idZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalle_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idZona = getIntent().getExtras().getInt("idZona");
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

        EditText etnombre = (EditText) findViewById(R.id.editTextNombrCliente);
        EditText etApellido = (EditText) findViewById(R.id.editTextApellidoCliente);
        EditText editTextTelefonoCliente = (EditText) findViewById(R.id.ediTextTelefonoCliente);


        etnombre.setError(null);
        etApellido.setError(null);
        editTextTelefonoCliente.setError(null);


        String nombre = String.valueOf(etnombre.getText());
        String apellido = String.valueOf(etApellido.getText());
        String telefono = String.valueOf(editTextTelefonoCliente.getText());


        if (TextUtils.isEmpty(nombre)) {

            etnombre.setError(getString(R.string.campo_requerido));
            etnombre.requestFocus();
            return;


        }

        if (TextUtils.isEmpty(apellido)) {
            etApellido.setError(getString(R.string.campo_requerido));
            etApellido.requestFocus();
            return;

        }
        if (TextUtils.isEmpty(telefono)) {
            editTextTelefonoCliente.setError(getString(R.string.campo_requerido));
            editTextTelefonoCliente.requestFocus();
            return;


        }


        Cliente cliente = new Cliente(G.SIN_VALOR_INT, nombre, apellido,telefono, idZona);
        ClientesProveedor.insert( getContentResolver(), cliente);
        finish();


    }
}