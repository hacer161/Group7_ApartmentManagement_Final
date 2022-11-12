package vn.edu.spx.group7_apartmentmanagement_final.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Login;

public class DAO_Login {
    SQLiteDatabase database;
    myHelper myHelper;
    public static final String CREATE_TB_LOGIN = "CREATE TABLE LOGIN(IDAMIN TEXT NOT NULL PRIMARY KEY, FULLNAME TEXT NOT NULL, PHONE TEXT NOT NULL, PASSWORD TEXT NOT NULL);";
    public static final String TB_NAME = "ADMIN";
    public static final String IDAMIN = "IDAMIN";
    public static final String FULLNAME = "FULLNAME";
    public static final String PHONE = "PHONE";
    public static final String PASSWORD = "PASSWORD";

    public DAO_Login(Context context) {
        myHelper = new myHelper(context);
    }

    public void opend() {
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Login> getAll(){
        ArrayList<Login> list_ac = new ArrayList<>();
        String select_tb_user = "SELECT * FROM " + TB_NAME;
        Cursor cursor = database.rawQuery(select_tb_user, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Login obj = new Login();
                obj.setIdAdmin(cursor.getString(0));
                obj.setFullName(cursor.getString(1));
                obj.setPhone(cursor.getString(2));
                obj.setPassword(cursor.getString(3));
                list_ac.add(obj);
                cursor.moveToNext();
            }
        }
        return list_ac;
    }
    public long insertAdmin(Login admin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_Login.IDAMIN, admin.getIdAdmin());
        contentValues.put(DAO_Login.FULLNAME, admin.getFullName());
        contentValues.put(DAO_Login.PHONE, admin.getPhone());
        contentValues.put(DAO_Login.PASSWORD, admin.getPassword());
        try {
            if (database.insert(DAO_Login.TB_NAME, null, contentValues) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
    public int changePass(Login admin){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_Login.PASSWORD, admin.getPassword());
        try {
            if (database.update(DAO_Login.TB_NAME, contentValues,DAO_Login.IDAMIN+"=?",new String[]{DAO_Login.IDAMIN}) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
