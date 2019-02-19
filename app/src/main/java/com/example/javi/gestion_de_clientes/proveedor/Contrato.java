package com.example.javi.gestion_de_clientes.proveedor;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contrato {

    public static final String AUTHORITY =
            "com.example.javi.tarea_03.proveedor.ProveedorDeContenido";

    public static final class ZonaVenta implements BaseColumns {

        public static final Uri CONTENT_URI = Uri
                .parse("content://" + AUTHORITY + "/ZonaVenta");

        // Table column

        public static final String NUMZONA = "numZona";
        public static final String LOCALIDAD = "localidad";
    }

    public static final class Cliente implements BaseColumns {

        public static final Uri CONTENT_URI = Uri
                .parse("content://" + AUTHORITY + "/Cliente");

        // Table column
        public static final String id = "_ID";
        public static final String NOMBRE = "Nombre";
        public static final String APELLIDO = "Apellido";
        public static final String TELEFONO = "Telefono";
        public static final String ID_ZONA = "IdZona";
    }

}
