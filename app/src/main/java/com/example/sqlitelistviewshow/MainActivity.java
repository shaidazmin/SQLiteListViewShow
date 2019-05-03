package com.example.sqlitelistviewshow;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteClass sqLiteClass;

    EditText id, name;
    Button save, show, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteClass = new SQLiteClass(this);
        SQLiteDatabase sqLiteDatabase = sqLiteClass.getWritableDatabase();

        // edit text find ..
        id = (EditText) findViewById(R.id.idEditText);
        name = (EditText) findViewById(R.id.nameEditText);
        // button .....
        save = (Button) findViewById(R.id.saveButton);
        show = (Button) findViewById(R.id.showButton);
        update = (Button) findViewById(R.id.updateButton);
        delete = (Button) findViewById(R.id.deleteButton);

        // set listenere ........
        save.setOnClickListener(this);
        show.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String userId = id.getText().toString();
        String userName = name.getText().toString();
        if (view.getId() == R.id.saveButton) {
            if(userId.equals("") && userName.equals("")){
                Toast.makeText(this,"Enter ID and Name",Toast.LENGTH_LONG).show();
            }else {
              long rowId = sqLiteClass.saveData(userId,userName);
                if(rowId> -1){
                    Toast.makeText(this,"Data is saved",Toast.LENGTH_LONG).show();
                }else if(rowId == rowId){
                    Toast.makeText(this,"this id used",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this,"Data Save failed ",Toast.LENGTH_LONG).show();
                }
            }

        }
        if (view.getId() == R.id.showButton) {
            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
           startActivity(intent);


        }
        if (view.getId() == R.id.updateButton) {

           boolean value = sqLiteClass.updateData(userId,userName);
           if(value == true){
               Toast.makeText(this,"Data is updated ",Toast.LENGTH_LONG).show();
           }
           else {
               Toast.makeText(this,"Data update failed ",Toast.LENGTH_LONG).show();
           }

        }
        if (view.getId() == R.id.deleteButton) {
            boolean value = sqLiteClass.delteData(userId);
            if(value == true){
                Toast.makeText(this,"Data Deleted",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Data Delete failed ",Toast.LENGTH_LONG).show();
            }

        }
    }


}
