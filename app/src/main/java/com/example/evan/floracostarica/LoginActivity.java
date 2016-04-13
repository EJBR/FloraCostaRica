package com.example.evan.floracostarica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener() {  //listener para el click en el text register
            @Override
            public void onClick(View v) {
                Intent SignUpIntent = new Intent(LoginActivity.this, SignUp.class);
                LoginActivity.this.startActivity(SignUpIntent);
            }
        });

    }
    public void onButtonClick(View v){  //botón del Login
        if(v.getId()==R.id.bLogin){
            EditText a = (EditText) findViewById(R.id.etUsername);
            String str = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.etPassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            //prueba
            if (pass.equals("")|str.equals("")){
                Toast temp= Toast.makeText(LoginActivity.this , "Incomplete information!" , Toast.LENGTH_SHORT);
                temp.show();
            }
            else{
                Intent i =new Intent(LoginActivity.this, ListViewActivity.class);
                startActivity(i);

                Toast registered= Toast.makeText(LoginActivity.this, "Welcome to FloraCR!", Toast.LENGTH_SHORT);
                registered.show();
            }


            //Checkeo de username y password  //NO FUNCIONÓ
            //System.out.println(password);
/*
            if(!password.equals("not found")){
                Intent i =new Intent(LoginActivity.this, ListViewActivity.class);
                //i.putExtra("name",str);
                startActivity(i);
            }
            else if (pass.equals("")|str.equals("")){
                Toast temp= Toast.makeText(LoginActivity.this , "Incomplete information!" , Toast.LENGTH_SHORT);
                temp.show();
            }
            else{
                Toast temp= Toast.makeText(LoginActivity.this , "Username and password don´t match" , Toast.LENGTH_SHORT);
                temp.show();
            }*/


        }
        /*if(v.getId() == R.id.Bsingupbutton){
            Intent i =new Intent(LoginActivity.this, SignUp.class);

            startActivity(i);
        }*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
