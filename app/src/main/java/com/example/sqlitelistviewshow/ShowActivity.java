package com.example.sqlitelistviewshow;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView listView;
    SQLiteClass sqLiteClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView = (ListView) findViewById(R.id.listViewId);
       sqLiteClass = new SQLiteClass(this);
       loadAllData();
    }

    public void loadAllData(){
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteClass.showAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(ShowActivity.this,"No data found",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(0)+"  "+arrayList.add(cursor.getString(1)));
            }
        }

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.extra_layout,R.id.textViewId,arrayList);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String string = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(ShowActivity.this,"Selected item is "+string,Toast.LENGTH_LONG).show();
        }
    });


    }
}
