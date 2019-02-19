package com.example.javi.gestion_de_clientes.proveedor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

public class ProveedorDeContenido extends ContentProvider {
    //private static final String LOGTAG = "Tiburcio - ProveedorDeContenido";

    private static final int ZONAVENTA_ONE_REG = 1;
    private static final int ZONAVENTA_ALL_REGS = 2;

    private static final int CLIENTE_ONE_REG = 3;
    private static final int CLIENTE_ALL_REGS = 4;

    private SQLiteDatabase sqlDB;
    public DatabaseHelper dbHelper;
    private static final String DATABASE_NAME = "ZonasDeVenta.db";
    private static final int DATABASE_VERSION = 28;

    private static final String ZONAVENTA_TABLE_NAME = "ZonaVenta";
    private static final String CLIENTE_TABLE_NAME = "Cliente";

    // Indicates an invalid content URI
    public static final int INVALID_URI = -1;

    // Defines a helper object that matches content URIs to table-specific parameters
    private static final UriMatcher sUriMatcher;

    // Stores the MIME types served by this provider
    private static final SparseArray<String> sMimeTypes;

    /*
     * Initializes meta-data used by the content provider:
     * - UriMatcher that maps content URIs to codes
     * - MimeType array that returns the custom MIME type of a table
     */
    static {

        // Creates an object that associates content URIs with numeric codes
        sUriMatcher = new UriMatcher(0);

        /*
         * Sets up an array that maps content URIs to MIME types, via a mapping between the
         * URIs and an integer code. These are custom MIME types that apply to tables and rows
         * in this particular provider.
         */
        sMimeTypes = new SparseArray<String>();

        // Adds a URI "match" entry that maps picture URL content URIs to a numeric code

        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                ZONAVENTA_TABLE_NAME,
                ZONAVENTA_ALL_REGS);
        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                CLIENTE_TABLE_NAME,
                CLIENTE_ALL_REGS);


        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                ZONAVENTA_TABLE_NAME + "/#",
                ZONAVENTA_ONE_REG);
        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                CLIENTE_TABLE_NAME + "/#",
                CLIENTE_ONE_REG);

        // Specifies a custom MIME type for the picture URL table

        sMimeTypes.put(
                ZONAVENTA_ALL_REGS,
                "vnd.android.cursor.dir/vnd." +
                        Contrato.AUTHORITY + "." + ZONAVENTA_TABLE_NAME);

        sMimeTypes.put(
                CLIENTE_ALL_REGS,
                "vnd.android.cursor.dir/vnd." +
                        Contrato.AUTHORITY + "." + CLIENTE_TABLE_NAME);

        sMimeTypes.put(
                ZONAVENTA_ONE_REG,
                "vnd.android.cursor.item/vnd."+
                        Contrato.AUTHORITY + "." + ZONAVENTA_TABLE_NAME);

        sMimeTypes.put(
                CLIENTE_ONE_REG,
                "vnd.android.cursor.item/vnd."+
                        Contrato.AUTHORITY + "." + CLIENTE_TABLE_NAME);


    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            //if (!db.isReadOnly()){
            //Habilitamos la integridad referencial
            db.execSQL("PRAGMA foreign_Keys=ON;");
            //}
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create table to store

            db.execSQL("Create table "
                            + ZONAVENTA_TABLE_NAME
                            + "( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                            + Contrato.ZonaVenta.NUMZONA + " TEXT , "
                            + Contrato.ZonaVenta.LOCALIDAD + " TEXT ); "
            );

            String pregunta = "Create table "
                    + CLIENTE_TABLE_NAME
                    + "( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                    + Contrato.Cliente.NOMBRE + " TEXT ,  "
                    + Contrato.Cliente.APELLIDO + " TEXT, "
                    + Contrato.Cliente.TELEFONO + " TEXT, "
                    + Contrato.Cliente.ID_ZONA + " INTEGER, "
                    + "FOREIGN KEY (" + Contrato.Cliente.ID_ZONA
                    + ") REFERENCES " + ZONAVENTA_TABLE_NAME + "( " + Contrato.ZonaVenta._ID + ")ON DELETE CASCADE); ";

            Log.i("tiburcio", pregunta);

            db.execSQL(pregunta);

            inicializarDatos(db);

        }

        void inicializarDatos(SQLiteDatabase db){

            db.execSQL("INSERT INTO " + ZONAVENTA_TABLE_NAME + " (" +  Contrato.ZonaVenta._ID + "," + Contrato.ZonaVenta.NUMZONA + "," + Contrato.ZonaVenta.LOCALIDAD + ") " +
                    "VALUES (1,1,'Telde')");
            db.execSQL("INSERT INTO " + ZONAVENTA_TABLE_NAME + " (" +  Contrato.ZonaVenta._ID + "," + Contrato.ZonaVenta.NUMZONA + "," + Contrato.ZonaVenta.LOCALIDAD+ ") " +
                    "VALUES (2,2,'Valsequillo')");
            db.execSQL("INSERT INTO " + ZONAVENTA_TABLE_NAME + " (" +  Contrato.ZonaVenta._ID + "," + Contrato.ZonaVenta.NUMZONA + "," + Contrato.ZonaVenta.LOCALIDAD + ") " +
                    "VALUES (3,3,'Las Palmas')");

            db.execSQL("INSERT INTO " + CLIENTE_TABLE_NAME + " (" +  Contrato.Cliente._ID + "," + Contrato.Cliente.NOMBRE + "," + Contrato.Cliente.APELLIDO + ","+ Contrato.Cliente.TELEFONO +"," + Contrato.Cliente.ID_ZONA+") " +
                    "VALUES (1,'Antonio','Martin','928333344',1)");
            db.execSQL("INSERT INTO " + CLIENTE_TABLE_NAME + " (" +  Contrato.Cliente._ID + "," + Contrato.Cliente.NOMBRE + "," + Contrato.Cliente.APELLIDO + ","+ Contrato.Cliente.TELEFONO +"," + Contrato.Cliente.ID_ZONA+") " +
                    "VALUES (2,'Juan','Sanchez','923998877',2)");
            db.execSQL("INSERT INTO " + CLIENTE_TABLE_NAME + " (" +  Contrato.Cliente._ID + "," + Contrato.Cliente.NOMBRE + "," + Contrato.Cliente.APELLIDO + ","+ Contrato.Cliente.TELEFONO +"," + Contrato.Cliente.ID_ZONA +") " +
                    "VALUES (3,'Pepe','Diaz','923998877',3)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + ZONAVENTA_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CLIENTE_TABLE_NAME);

            onCreate(db);
        }

    }

    public ProveedorDeContenido() {
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        Log.i("tiburcio", ((dbHelper == null) ? "false" : "true"));
        return (dbHelper == null) ? false : true;
    }

    public void resetDatabase() {
        dbHelper.close();
        dbHelper = new DatabaseHelper(getContext());
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqlDB = dbHelper.getWritableDatabase();

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case ZONAVENTA_ALL_REGS:
                table = ZONAVENTA_TABLE_NAME;
                break;

            case CLIENTE_ALL_REGS:
                table = CLIENTE_TABLE_NAME;
                break;

        }

        long rowId = sqlDB.insert(table, "", values);
        Log.i("tiburcio", "" +  values.get(Contrato.Cliente.ID_ZONA ));
        if (rowId > 0) {
            Uri rowUri = ContentUris.appendId(
                    uri.buildUpon(), rowId).build();
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();
        // insert record in user table and get the row number of recently inserted record

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case ZONAVENTA_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.ZonaVenta._ID + " = "
                        + uri.getLastPathSegment();
                table = ZONAVENTA_TABLE_NAME;
                break;

            case CLIENTE_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.Cliente._ID + " = "
                        + uri.getLastPathSegment();
                table = CLIENTE_TABLE_NAME;
                break;


            case ZONAVENTA_ALL_REGS:
                table = ZONAVENTA_TABLE_NAME;
                break;

            case CLIENTE_ALL_REGS:
                table = CLIENTE_TABLE_NAME;
                break;
        }
        int rows = sqlDB.delete(table, selection, selectionArgs);
        if (rows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return rows;
        }
        throw new SQLException("Failed to delete row into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /*String query = null;*/

        switch (sUriMatcher.match(uri)) {
            case ZONAVENTA_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.ZonaVenta._ID + " = "
                        + uri.getLastPathSegment();
                qb.setTables(ZONAVENTA_TABLE_NAME);
                break;

            case CLIENTE_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.Cliente._ID + " = "
                        + uri.getLastPathSegment();
                qb.setTables(CLIENTE_TABLE_NAME);
                break;


            case ZONAVENTA_ALL_REGS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder =
                        Contrato.ZonaVenta._ID + " ASC";
                qb.setTables(ZONAVENTA_TABLE_NAME);
                break;

            case CLIENTE_ALL_REGS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder =
                        Contrato.Cliente._ID + " ASC";
                qb.setTables(CLIENTE_TABLE_NAME);
                break;
        }

        Cursor c;
        c = qb.query(db, projection, selection, selectionArgs, null, null,
                        sortOrder);
        /*c.setNotificationUri(getContext().getContentResolver(), uri);*/

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();
        // insert record in user table and get the row number of recently inserted record

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case ZONAVENTA_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.ZonaVenta._ID + " = "
                        + uri.getLastPathSegment();
                table = ZONAVENTA_TABLE_NAME;
                break;

            case CLIENTE_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.Cliente._ID + " = "
                        + uri.getLastPathSegment();
                table = CLIENTE_TABLE_NAME;
                break;


            case ZONAVENTA_ALL_REGS:
                table = ZONAVENTA_TABLE_NAME;
                break;

            case CLIENTE_ALL_REGS:
                table = CLIENTE_TABLE_NAME;
                break;
        }

        int rows = sqlDB.update(table, values, selection, selectionArgs);
        if (rows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);

            return rows;
        }
        throw new SQLException("Failed to update row into " + uri);
    }
}
