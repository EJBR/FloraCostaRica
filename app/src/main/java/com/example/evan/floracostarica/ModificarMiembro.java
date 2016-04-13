package com.example.evan.floracostarica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarMiembro extends Activity implements OnClickListener {

    EditText et;
    Button btnActualizar, btnEliminar;

    long member_id;

    SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_miembro);

        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        et = (EditText) findViewById(R.id.et_miembro_id);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");

        member_id = Long.parseLong(memberID);

        et.setText(memberName);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnActualizar:                    //acciones para el boton actualizar
                String memName_upd = et.getText().toString();
                dbcon.actualizarDatos(member_id, memName_upd);
                this.returnHome();
                Toast registered= Toast.makeText(ModificarMiembro.this, "Plant Updated!", Toast.LENGTH_SHORT);
                registered.show();
                break;

            case R.id.btnEliminar:                        //acciones para el boton eliminar
                dbcon.deleteData(member_id);
                this.returnHome();
                Toast registered2= Toast.makeText(ModificarMiembro.this, "Plant Eliminated!", Toast.LENGTH_SHORT);
                registered2.show();
                break;
        }
    }

    public void returnHome() {                    //método para regresar a la listViewActivity

        Intent home_intent = new Intent(getApplicationContext(),
                ListViewActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}