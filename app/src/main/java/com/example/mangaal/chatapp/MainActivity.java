package com.example.mangaal.chatapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBase mysq;
    EditText editName,editsurname,editMark,editId;
    Button Addon,view,add,remove,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysq = new DataBase(this);
        editMark=(EditText)findViewById(R.id.editText3);
        editName=(EditText)findViewById((R.id.editText));
        editsurname=(EditText)findViewById(R.id.editText2);
        Addon=(Button)findViewById(R.id.button4);
        view=(Button)findViewById(R.id.button);
        update=(Button)findViewById(R.id.button3);
        editId=(EditText)findViewById(R.id.editText4) ;
        remove=(Button)findViewById(R.id.button2) ;
        addButton();
        showdata();
        UpdateData();
        RenoveData();
    }
    public void addButton()
    {
        Addon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Boolean isInsert= mysq.insertData(editName.getText().toString(),editsurname.getText().toString(),editMark.getText().toString());
               if(isInsert==true)
               {
                   Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();

               }
            }
        });
    }
    public void showdata()
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mysq.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Surname:"+res.getString(2)+"\n");
                    buffer.append("Mark:"+res.getString(3)+"\n\n");

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    public void UpdateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isUpdate=mysq.updatedata(editId.getText().toString(),editName.getText().toString(),editsurname.getText().toString(),editMark.getText().toString());
                if(isUpdate==true)
                {
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Data not Found",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void RenoveData()
    {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleatRow=mysq.Remove(editId.getText().toString());
                if(deleatRow > 0)
                {
                    Toast.makeText(MainActivity.this,"Data Remove",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Data not Found",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

}
