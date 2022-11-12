package vn.edu.spx.group7_apartmentmanagement_final.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Login;

public class myHelper extends SQLiteOpenHelper {
    static final String db_NAME = "QUANLY.db";
    static final int VERSION = 1;

    public myHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_Login.CREATE_TB_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Login.TB_NAME);

    }
}
