package com.example.assignment2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQliteHelper extends SQLiteOpenHelper {
    public static final String C_NAME = "fname";

    public MySQliteHelper(Context context) {

        super(context, "Reg.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Fees(idnum TEXT,Total_Fees REAL, Fees_Paid REAL, Fees_Balance REAL, Clearance_date TEXT primary key)");
        DB.execSQL("create Table Register(" + C_NAME + " TEXT, lname TEXT, idnum TEXT primary key, major TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Register");
        DB.execSQL("drop Table if exists Fees");
        onCreate(DB);
    }
    public Boolean insertuserdata(String fname, String lname, String idnum, String major)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, fname);
        contentValues.put("lname", lname);
        contentValues.put("idnum", idnum);
        contentValues.put("major", major);
        long result=DB.insert("Register", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdataf(String fname, String lname, String idnum, String major)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, fname);
        contentValues.put("lname", lname);
        contentValues.put("idnum", idnum);
        contentValues.put("major", major);
        Cursor cursor = DB.rawQuery("Select * from Register where " + C_NAME + " = ?", new String[]{fname});
        if (cursor.getCount() > 0) {
            long result = DB.update("Register", contentValues, C_NAME + "=?", new String[]{fname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdatar ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Register", null);
        return cursor;
    }
    public Boolean deletedatar (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Register where " + C_NAME + " = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Register", C_NAME + "=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean insertfees(String idnum, String tfees, String pfees, String bfees, String c_date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idnum", idnum);
        contentValues.put("Total_Fees", tfees);
        contentValues.put("Fees_Paid", pfees);
        contentValues.put("Fees_Balance", bfees);
        contentValues.put("Clearance_date", c_date);
        long result=DB.insert("Fees", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String idnum, String tfees, String pfees, String bfees, String c_date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idnum", idnum);
        contentValues.put("Total_Fees", tfees);
        contentValues.put("Fees_Paid", pfees);
        contentValues.put("Fees_Balance", bfees);
        contentValues.put("Clearance_date", c_date);
        Cursor cursor = DB.rawQuery("Select * from Fees where idnum = ?", new String[]{idnum});
        if (cursor.getCount() > 0) {
            long result = DB.update("Fees", contentValues,  "idnum =?", new String[]{idnum});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Fees where idnum = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Fees",  "idnum =?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdataf()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Fees", null);
        return cursor;
    }




    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT Register.fname,Register.idnum, Register.major, Fees.Total_Fees,Fees.Fees_Balance, Fees.Clearance_date\n" +
                "  FROM Register\n" +
                "  JOIN Fees\n" +
                "  ON Register.idnum = Fees.idnum;", null);
        return cursor;
    }
//    public Cursor getdata ()
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Register", null);
//        return cursor;
//    }
}
