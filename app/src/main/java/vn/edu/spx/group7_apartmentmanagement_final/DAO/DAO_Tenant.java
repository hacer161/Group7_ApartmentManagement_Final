package vn.edu.spx.group7_apartmentmanagement_final.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;

public class DAO_Tenant {
        public static final String CREATE_TB_TENANT = "CREATE TABLE TENANT(IDTENANT INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, TENANTNAME TEXT NOT NULL, IDENTITY TEXT NOT NULL,DOB DATE NOT NULL);";
        public static final String TB_NAME = "TENANT";
        public static final String IDTENANT = "IDTENANT";
        public static final String TENANTNAME = "TENANTNAME";
        public static final String IDENTITY = "IDENTITY";
        public static final String DOB = "DOB";
        SQLiteDatabase database;
        vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper myHelper;
        public DAO_Tenant(Context context) {
            myHelper = new myHelper(context);
        }
        public void opend(){
            database=myHelper.getWritableDatabase();
        }
        public void close(){
            database.close();
        }
        public ArrayList<Tenant> selectAll(){
            ArrayList<Tenant> list = new ArrayList<>();
            String select ="SELECT * FROM TENANT";
            Cursor cursor = database.rawQuery(select, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Tenant tenant = new Tenant();
                tenant.setIdTenant(cursor.getInt(0));
                tenant.setTenantName(cursor.getString(1));
                tenant.setIdentity(cursor.getString(2));
                tenant.setDOB(cursor.getString(3));
                list.add(tenant);
                cursor.moveToNext();
            }
            cursor.close();
            return list;
        }
        public long insertTenant(Tenant tenant){
            ContentValues contentValues = new ContentValues();
            contentValues.put(TENANTNAME, tenant.getTenantName());
            contentValues.put(IDENTITY, tenant.getIdentity());
            contentValues.put(DOB, tenant.getDOB());
            try {
                if(database.insert(TB_NAME, null, contentValues)==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }
        public int updateTenant(Tenant tenant){
            ContentValues contentValues = new ContentValues();
            contentValues.put(TENANTNAME, tenant.getTenantName());
            contentValues.put(IDENTITY, tenant.getIdentity());
            contentValues.put(DOB, tenant.getDOB());
            try {
                if(database.update(TB_NAME,contentValues,IDTENANT+"=?", new String[]{tenant.getIdTenant()+""})==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }
        public int deleteTenant(Tenant tenant){
            try {
                if(database.delete(TB_NAME,IDTENANT+"=?",  new String[]{tenant.getIdTenant()+""})==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }
}
