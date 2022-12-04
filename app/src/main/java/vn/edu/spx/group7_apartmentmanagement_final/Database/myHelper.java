package vn.edu.spx.group7_apartmentmanagement_final.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Bill;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Login;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;

public class myHelper extends SQLiteOpenHelper {
    static final String db_NAME = "QUANLY.db";
    static final int VERSION = 1;

    public myHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_Login.CREATE_TB_LOGIN);
        db.execSQL(DAO_Contract.CREATE_TB_CONTRACT);
        db.execSQL(DAO_Tenant.CREATE_TB_TENANT);
        db.execSQL(DAO_Room.CREATE_TB_ROOM);
        db.execSQL(DAO_Bill.CREATE_TB_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Login.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Contract.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Bill.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Tenant.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Room.TB_NAME);

    }
}
