package com.example.javi.gestion_de_clientes.proveedor;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.javi.gestion_de_clientes.pojos.ZonaVenta;

/**
 * Created by Javi on 26/11/2017.
 */

public class ZonaventasProveedor {
    public static void insert (ContentResolver resolver, ZonaVenta zonaVenta){
        Uri uri = Contrato.ZonaVenta.CONTENT_URI;

        ContentValues values =new ContentValues();
        values.put(Contrato.ZonaVenta.NUMZONA, zonaVenta.getNumZona());
        values.put(Contrato.ZonaVenta.LOCALIDAD, zonaVenta.getLocalidad());

        resolver.insert(uri, values);

    }

    static  public void delete (ContentResolver resolver, int zonaId) {

        Uri uri = Uri.parse(Contrato.ZonaVenta.CONTENT_URI + "/" + zonaId);

        resolver.delete(uri, null, null);
    }


    static public void update(ContentResolver resolver , ZonaVenta zonaVenta){
        Uri uri = Uri.parse(Contrato.ZonaVenta.CONTENT_URI + "/" + zonaVenta.getId());
        ContentValues values = new ContentValues();

        values.put(Contrato.ZonaVenta.NUMZONA,zonaVenta.getNumZona());
        values.put(Contrato.ZonaVenta.LOCALIDAD,zonaVenta.getLocalidad());

        resolver.update( uri,values, null,null);
    }


    static  public ZonaVenta readReccords(ContentResolver resolver , int zonaId){

        Uri uri = Uri.parse(Contrato.ZonaVenta.CONTENT_URI + "/" + zonaId);

        String[] projection = {

         Contrato.ZonaVenta.NUMZONA,
         Contrato.ZonaVenta.LOCALIDAD

        };

        Cursor cursor = resolver.query(uri, projection, null,null,null);

        if (cursor.moveToFirst()){

            ZonaVenta zonaVenta = new ZonaVenta();
            zonaVenta.setId(zonaId);
            zonaVenta.setNumZona(cursor.getString(cursor.getColumnIndex(Contrato.ZonaVenta.NUMZONA)));
            zonaVenta.setLocalidad(cursor.getString(cursor.getColumnIndex(Contrato.ZonaVenta.LOCALIDAD)));

            
            return zonaVenta;
        }

        return  null;

    }

    static public boolean existeZona(ContentResolver resolver, String numZona){
        String columns[] = new String[] {
                Contrato.ZonaVenta._ID,
                Contrato.ZonaVenta.NUMZONA,
                Contrato.ZonaVenta.LOCALIDAD
        };

        Uri baseUri = Contrato.ZonaVenta.CONTENT_URI;
        String selection = Contrato.ZonaVenta.NUMZONA + " = '" + numZona + "'";

        Cursor cursor = resolver.query(baseUri, columns, selection, null, null);

        if(cursor.getCount() == 0){
            return false;
        } else {
            return true;
        }
    }
}
