package com.example.sqlitelistviewshow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class SQLiteClass  extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "Student.db";
    public  static  final  String TABLE_NAME = "StudentName";
    public static final int VERSION_NUMBER = 3;
    public static final String ID = "_id";
    public static  final  String NAME = "Name";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY , "+NAME+" VARCHAR (25))";
    public static  final  String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    Context context;


    public SQLiteClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Database is created ",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Database failed ",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context,"Database is UPDATED ",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Database update failed ",Toast.LENGTH_LONG).show();
        }
    }


    // save data ...
     public  long  saveData(String id, String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(ID,id);
         contentValues.put(NAME,name);
         long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
         return rowId;
     }

     // show all data .....

    public  Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
      return  cursor;
    }

    // update data ...
    public  boolean updateData(String id,String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ? ",new String[]{id});
       return true;
    }

    // delete data
    public boolean  delteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID +" = ? ",new String[]{id});
        return true;

    }
}
