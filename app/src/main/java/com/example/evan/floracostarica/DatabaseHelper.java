package com.example.evan.floracostarica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Evan on 4/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";

    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_PASSWORD="password";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table contacts(id integer primary key not null , "+
            "name text not null , pass text not null);";


    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String query="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertContact(Contact c){            //método para insertar contactos a la DB

        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query= "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME , c.getName());
        values.put(COLUMN_PASSWORD , c.getPassword());
        db.insert(TABLE_NAME, null, values);             //inserción de los values en la tabla
        db.close();
    }

    public String searchPass(String name){         //método para buscar la contraseña del name enviado por parámetro
        db= this.getReadableDatabase();
        String query="select name, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b="not found";
        if(cursor.moveToFirst()){

            do{
                a = cursor.getString(0);        //0 es el name
                if(a.equals(name)){
                    b=cursor.getString(1);         //1 es el pass
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;                 //devuelve la pass
    }

}
