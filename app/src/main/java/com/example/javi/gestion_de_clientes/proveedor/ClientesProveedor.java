package com.example.javi.gestion_de_clientes.proveedor;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.example.javi.gestion_de_clientes.pojos.Cliente;

/**
 * Created by Javi on 26/11/2017.
 */

public class ClientesProveedor {
    public static void insert (ContentResolver resolver, Cliente cliente){
        Uri uri = Contrato.Cliente.CONTENT_URI;

        ContentValues values =new ContentValues();
        //values.put(Contrato.Cliente._ID, cliente.getId());
        values.put(Contrato.Cliente.NOMBRE, cliente.getNombre());
        values.put(Contrato.Cliente.APELLIDO, cliente.getApellido());

        values.put(Contrato.Cliente.TELEFONO, cliente.getTelefono());
        values.put(Contrato.Cliente.ID_ZONA, cliente.getIdZona());
        Log.i("tiburcio", "" + cliente.getIdZona());


        resolver.insert(uri, values);

    }

    static  public void delete (ContentResolver resolver, int clienteId) {

        Uri uri = Uri.parse(Contrato.Cliente.CONTENT_URI + "/" + clienteId);

        resolver.delete(uri, null, null);


    }

    static public boolean existeCliente(ContentResolver resolver, String Telefono){
        String columns[] = new String[] {
                Contrato.Cliente._ID,
                Contrato.Cliente.NOMBRE,
                Contrato.Cliente.APELLIDO,
                Contrato.Cliente.TELEFONO,
                Contrato.Cliente.ID_ZONA
        };

        Uri baseUri = Contrato.Cliente.CONTENT_URI;
        String selection = Contrato.Cliente.TELEFONO + " = '" + Telefono + "'";

        Cursor cursor = resolver.query(baseUri, columns, selection, null, null);

        if(cursor.getCount() == 0){
            return false;
        } else {
            return true;
        }
    }
}
