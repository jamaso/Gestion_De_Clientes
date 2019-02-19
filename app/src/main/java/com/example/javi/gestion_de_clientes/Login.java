package com.example.javi.gestion_de_clientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edEmail, edPasword;
    Button btSingIn;
    Button btSignUp;

    private Cursor fila;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (EditText) findViewById(R.id.editTextMail);
        edPasword = (EditText) findViewById(R.id.editTextPass);
        btSingIn = (Button) findViewById(R.id.buttonSingIn);
        btSignUp = (Button) findViewById(R.id.buttonSingUp);

        final UserDataBase mDb = new UserDataBase(this);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!campoVacio()) {
                    User user = mDb.buscaUsuarios(edEmail.getText().toString(), edPasword.getText().toString());

                    if(user !=null) {
                        Toast.makeText(Login.this,"Usuario ya existe",Toast.LENGTH_SHORT).show();

                    }
                    else
                        if(user==null){

                            mDb.addUser(new User(edEmail.getText().toString(), edPasword.getText().toString()));

                            Toast.makeText(Login.this, "Usuario Añadido correctamente", Toast.LENGTH_SHORT).show();
                            edEmail.setText("");
                            edPasword.setText("");
                        }

                } else {

                    Toast.makeText(Login.this, "Campo Vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(!campoVacio()) {
                     User user = mDb.buscaUsuarios(edEmail.getText().toString(), edPasword.getText().toString());
                     if (user != null) {
                         Bundle mBundle = new Bundle();
                         mBundle.putString("user", user.getEmail());
                         Intent intent = new Intent(Login.this, DrawerActivity.class);
                         intent.putExtras(mBundle);
                         startActivity(intent);
                         Toast.makeText(Login.this, "BienVenido " + user.getEmail(), Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(Login.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                         edPasword.setText("");
                     }
                 } else {
                    Toast.makeText(Login.this, "Campo Vacio", Toast.LENGTH_SHORT).show();
                }
            }


        });




    }

    private boolean campoVacio() {

        if (TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edPasword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }





}







