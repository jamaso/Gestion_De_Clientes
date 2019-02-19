package com.example.javi.gestion_de_clientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.javi.gestion_de_clientes.zonaVenta.ClienteDetalleActivity;
import com.example.javi.gestion_de_clientes.zonaVenta.ZonaVentaActivity;
import com.example.javi.gestion_de_clientes.zonaVenta.ZonaVentaInsertarActivity;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup radioGroupCliente;
    Button butonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        radioGroupCliente = (RadioGroup) findViewById(R.id.radioGroupClientes);
        butonOk = (Button) findViewById(R.id.btnEnter);

        butonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaBtnOk();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void validaBtnOk() {
        radioGroupCliente = (RadioGroup) findViewById(R.id.radioGroupClientes);
        int selected = radioGroupCliente.getCheckedRadioButtonId();
        if (selected>0){
            switch (selected) {
                case R.id.radioButtonClienteNuevo:
                    Intent intent = new Intent(DrawerActivity.this, ZonaVentaInsertarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.radioButtonToursventas:
                    Intent intent2 = new Intent(DrawerActivity.this, ZonaVentaActivity.class);
                    startActivity(intent2);
                    break;

            }
        }if (selected<=0){
            Toast.makeText(DrawerActivity.this,"Elige una opcion",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_zona_ventas) {
            Intent intent = new Intent(DrawerActivity.this, ZonaVentaActivity.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_cliente_nuevo) {
            Intent intent = new Intent(DrawerActivity.this,ClienteDetalleActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuproductos) {
            Intent intent = new Intent(DrawerActivity.this, ProductosActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Cliente_nuevo) {
            Intent intent = new Intent(DrawerActivity.this,ClientesActivity.class);
            startActivity(intent);

        } else if (id == R.id.Zona_Venta) {
            Intent intent = new Intent(DrawerActivity.this,ZonaVentaDetlleActivity.class);
            startActivity(intent);
        }  else if (id == R.id.productos) {
            Intent intent = new Intent(DrawerActivity.this,ProductosActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
