package com.example.mangaal.chatapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {
    public static final String Database_Name="Student.db";
    public static final String Table_Name="Student_table";
    public static final String Col_1="Id";
    public static final String Col_2="Name";
    public static final String Col_3="Surname";
    public static final String Col_4="Mark";

    public DataBase( Context context) {
        super(context, Database_Name,null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_Name+"(Id INTEGER PRIMARY KEY ,Name TEXT,Surname TEXT,Mark INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" Drop table if exists "+Table_Name);
        onCreate(db);

    }
   public boolean insertData(String Name ,String Surname, String Mark)
   {
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues contextValue= new ContentValues();

       contextValue.put(Col_2,Name);
       contextValue.put(Col_3,Surname);
       contextValue.put(Col_4,Mark);

       long result=db.insert(Table_Name,null,contextValue);
       if(result==-1)
       {
           return(false);
       }
       else
       {
           return(true);
       }

   }
   public Cursor getAllData()
   {
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor res=db.rawQuery(" select * from "+ Table_Name,null);
       return  res;


   }
   public boolean updatedata(String id,String Name,String surname, String mark){
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues contentValues= new ContentValues();
       contentValues.put(Col_1,id);
       contentValues.put(Col_2,Name);
       contentValues.put(Col_3,surname);
       contentValues.put(Col_4,mark);
       db.update(Table_Name,contentValues,"Id = ?",new String[] {id});

       return  true;
   }

   public Integer Remove(String id)
   {
       SQLiteDatabase db=this.getWritableDatabase();
      return db.delete(Table_Name," Id = ? ",new String[]{id});

   }

}
