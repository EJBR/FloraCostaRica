package com.example.evan.floracostarica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Evan on 4/11/2016.
 */
public class SignUp extends Activity{

    DatabaseHelper helper= new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }
    public void onSignUpClick(View v){                    //acciones para el boton SignUp

        if(v.getId()==R.id.Bsingupbutton){
            //paso de variables a String despues de la extracción desde la interfaz
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText pass1 = (EditText)findViewById(R.id.TFPassword);
            EditText pass2 = (EditText)findViewById(R.id.TFPassword2);

            String namestr = name.getText().toString();
            String passwordstr1 = pass1.getText().toString();
            String passwordstr2 = pass2.getText().toString();
            
            if(!passwordstr1.equals(passwordstr2)){            //checkea que las password no sean iguales
                Toast pass= Toast.makeText(SignUp.this, "Passwords don´t match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else if (passwordstr1.equals("")|namestr.equals("") | passwordstr2.equals("")){
                Toast temp= Toast.makeText(SignUp.this , "Incomplete information!" , Toast.LENGTH_SHORT);
                temp.show();
            }
            else {                         //si si lo son procede a la inserción

                    //insert in database
                    Contact c = new Contact();
                    c.setName(namestr);
                    c.setPassword(passwordstr1);
                    helper.insertContact(c);
                    //mensaje de registro exitoso
                    Toast registered = Toast.makeText(SignUp.this, "Registered Succesfully!", Toast.LENGTH_SHORT);
                    registered.show();
                    //se regresa al LoginActivity
                    Intent i = new Intent(SignUp.this, LoginActivity.class);
                    startActivity(i);

            }
        }

    }
}
