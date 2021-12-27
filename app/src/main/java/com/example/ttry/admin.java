package com.example.ttry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    EditText us,pas;
    Button ch;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        us =findViewById(R.id.username);
        pas=findViewById(R.id.password);
        ch = findViewById(R.id.admin);
        db = openOrCreateDatabase("admin_detail",MODE_PRIVATE,null);
        db.execSQL("create table if not exists admin (uname varchar(30) primary key,pass varchar(30))");
        //Cursor c = db.rawQuery("Select * from admin where uname=?",new String[]{us.getText().toString()});
        //if(c.getCount()==0)
        {
            ContentValues c1 = new ContentValues();
            c1.put("uname", "tanisha@08");
            c1.put("pass", "tan@123");
            long res = db.insert("admin", null, c1);
            //db.execSQL("insert into admin values('tanisha@08','tan@123')");
            //db.execSQL("insert into admin values('vishalbus','88677567')");
            //db.execSQL("insert into admin values('reshmabus','bus@4321')");
        }
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(us.getText().toString().equals("") || pas.getText().toString().equals(""))
                {
                    Toast.makeText(admin.this, "Values not entered", Toast.LENGTH_SHORT).show();
                }
                else if(us.getText().toString().equals("") && pas.getText().toString().equals(""))
                {
                    Toast.makeText(admin.this, "Values not entered", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String u = us.getText().toString();
                    String p = pas.getText().toString();
                    Cursor c = db.rawQuery("Select * from admin where uname=?",new String[]{u});
                    if(c.getCount()>0)
                    {
                        c.moveToNext();
                        if(p.equals(c.getString(1)))
                        {
                            Toast.makeText(admin.this, "Access granted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(admin.this,change_bus.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(admin.this, "Incorrect Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(admin.this, "Incorrect Username.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}