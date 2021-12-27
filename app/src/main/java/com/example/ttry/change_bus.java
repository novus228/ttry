package com.example.ttry;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class change_bus extends AppCompatActivity {
    EditText id,bn,fr,to,ti;
    Button upd,ins,del;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bus);
        id = findViewById(R.id.busid);
        bn = findViewById(R.id.busname);
        fr = findViewById(R.id.from);
        to = findViewById(R.id.to);
        ti = findViewById(R.id.time);
        upd = findViewById(R.id.update);
        ins = findViewById(R.id.insert);
        del = findViewById(R.id.delete);
        db = openOrCreateDatabase("bus_detail",MODE_PRIVATE,null);
        db.execSQL("create table if not exists bus (bid varchar(30) primary key,bname varchar(30),bfrom varchar(30),bto varchar(30),btime varchar(30))");

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = id.getText().toString();
                String n = bn.getText().toString();
                String f = fr.getText().toString();
                String t = to.getText().toString();
                String tim = ti.getText().toString();
                if(i.equals("")||n.equals("")||f.equals("")||t.equals("")||tim.equals(""))
                {
                    Toast.makeText(change_bus.this, "Value not entered.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cursor c = db.rawQuery("select * from bus where bid=?",new String[]{i});
                    if(c.getCount()==0)
                    {
                        db.execSQL("insert into bus values('"+ i +"','"+ n +"','"+ f +"','"+ t +"','"+ tim +"')");
                        Toast.makeText(change_bus.this, "Values entered.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(change_bus.this, "Already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = id.getText().toString();
                String n = bn.getText().toString();
                String f = fr.getText().toString();
                String t = to.getText().toString();
                String tim = ti.getText().toString();
                if(i.equals("")||n.equals("")||f.equals("")||t.equals("")||tim.equals(""))
                {
                    Toast.makeText(change_bus.this, "Value not entered.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(i.equals(""))
                    {
                        Toast.makeText(change_bus.this, "ID not entered.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        db.execSQL("update bus set bname='"+ n +"',bfrom='"+ f +"',bto='"+ t +"',btime='"+ tim +"' where bid='"+ i +"'");
                        Toast.makeText(change_bus.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = id.getText().toString();
                if(i.equals(""))
                {
                    Toast.makeText(change_bus.this, "ID not entered!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.execSQL("delete from bus where bid='" + i +"'");
                    Toast.makeText(change_bus.this, "Deletion Complete.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}