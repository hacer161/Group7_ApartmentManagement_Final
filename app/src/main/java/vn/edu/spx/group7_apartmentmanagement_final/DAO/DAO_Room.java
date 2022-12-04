package vn.edu.spx.group7_apartmentmanagement_final.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;

public class DAO_Room {
    public static final String CREATE_TB_ROOM = "CREATE TABLE ROOM(IDROOM INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,IDTENANT INTEGER, ROOMNUMBER TEXT NOT NULL, ROOMPRICE INTEGER, WATERBILL REAL,ELECTRICBILL REAL,SERVICEBILL REAL, FOREIGN KEY(IDTENANT) REFERENCES TENANT(IDTENANT));";
    public static final String TB_NAME = "ROOM";
    public static final String IDROOM = "IDROOM";
    public static final String IDTENANT = "IDTENANT";
    public static final String ROOMNUMBER = "ROOMNUMBER";
    public static final String ROOMPRICE = "ROOMPRICE";
    public static final String WATERBILL = "WATERBILL";
    public static final String ELECTRICBILL = "ELECTRICBILL";
    public static final String SERVICEBILL = "SERVICEBILL";
    SQLiteDatabase database;
    myHelper MyHelper;

    public DAO_Room(Context context) {
        MyHelper = new myHelper(context);
    }

    public void opend() {
        database = MyHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Room> selectAll(){
        ArrayList<Room> list = new ArrayList<>();
        String select ="SELECT * FROM ROOM JOIN TENANT ON ROOM.IDTENANT = TENANT. IDTENANT";
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Room room = new Room();
            room.setIdRoom(cursor.getInt(0));
            room.setIdTenant(cursor.getInt(1));
            room.setRoomNumber1(cursor.getString(2));
            room.setRoomPrice1M(cursor.getInt(3));
            room.setWaterBill1M(cursor.getDouble(4));
            room.setElectricBill1M(cursor.getDouble(5));
            room.setServiceBill1M(cursor.getDouble(6));
            room.setTenantName(cursor.getString(8));
            list.add(room);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<Room> selectTenantName1(ArrayList<Tenant> listC){
        ArrayList<Room> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Tenant.TB_NAME+" ON "+TB_NAME+"."+IDTENANT+"="+DAO_Tenant.TB_NAME+"."+DAO_Tenant.IDTENANT+" WHERE "+TB_NAME+"."+IDTENANT+"="+listC.get(0).getIdTenant();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Room room = new Room();
            room.setIdRoom(cursor.getInt(0));
            room.setIdTenant(cursor.getInt(1));
            room.setRoomNumber1(cursor.getString(2));
            room.setRoomPrice1M(cursor.getInt(3));
            room.setWaterBill1M(cursor.getDouble(4));
            room.setElectricBill1M(cursor.getDouble(5));
            room.setServiceBill1M(cursor.getDouble(6));
            room.setTenantName(cursor.getString(8));
            listB.add(room);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Room> selectTenantName2(ArrayList<Tenant> listC){
        ArrayList<Room> listB = new ArrayList<>();
        String select ="SELECT * FROM ROOM JOIN TENANT ON ROOM.IDTENANT = TENANT. IDTENANT WHERE ROOM.IDTENANT = "+listC.get(1).getIdTenant();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Room room = new Room();
            room.setIdRoom(cursor.getInt(0));
            room.setIdTenant(cursor.getInt(1));
            room.setRoomNumber1(cursor.getString(2));
            room.setRoomPrice1M(cursor.getInt(3));
            room.setWaterBill1M(cursor.getDouble(4));
            room.setElectricBill1M(cursor.getDouble(5));
            room.setServiceBill1M(cursor.getDouble(6));
            room.setTenantName(cursor.getString(8));
            listB.add(room);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Room> selectTenantName3(ArrayList<Tenant> listC){
        ArrayList<Room> listB = new ArrayList<>();
        String select ="SELECT * FROM ROOM JOIN TENANT ON ROOM.IDTENANT = TENANT. IDTENANT WHERE ROOM.IDTENANT = "+listC.get(2).getIdTenant();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Room room = new Room();
            room.setIdRoom(cursor.getInt(0));
            room.setIdTenant(cursor.getInt(1));
            room.setRoomNumber1(cursor.getString(2));
            room.setRoomPrice1M(cursor.getInt(3));
            room.setWaterBill1M(cursor.getDouble(4));
            room.setElectricBill1M(cursor.getDouble(5));
            room.setServiceBill1M(cursor.getDouble(6));
            room.setTenantName(cursor.getString(8));
            listB.add(room);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Room> selectTenantName4(ArrayList<Tenant> listC){
        ArrayList<Room> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Tenant.TB_NAME+" ON "+TB_NAME+"."+IDTENANT+"="+DAO_Tenant.TB_NAME+"."+DAO_Tenant.IDTENANT+" WHERE ROOM."+IDTENANT+" NOT IN ( SELECT IDTENANT FROM ROOM WHERE IDTENANT = "+listC.get(0).getIdTenant()+" OR "+" IDTENANT = "+listC.get(1).getIdTenant()+" OR IDTENANT = "+listC.get(2).getIdTenant()+" )";
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Room room = new Room();
            room.setIdRoom(cursor.getInt(0));
            room.setIdTenant(cursor.getInt(1));
            room.setRoomNumber1(cursor.getString(2));
            room.setRoomPrice1M(cursor.getInt(3));
            room.setWaterBill1M(cursor.getDouble(4));
            room.setElectricBill1M(cursor.getDouble(5));
            room.setServiceBill1M(cursor.getDouble(6));
            room.setTenantName(cursor.getString(8));
            listB.add(room);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public long insertRoom(Room room){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDTENANT, room.getIdTenant());
        contentValues.put(ROOMNUMBER, room.getRoomNumber1());
        contentValues.put(ROOMPRICE,room.getRoomPrice1M());
        contentValues.put(WATERBILL,room.getWaterBill1M());
        contentValues.put(ELECTRICBILL, room.getElectricBill1M());
        contentValues.put(SERVICEBILL, room.getServiceBill1M());
        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateRoom(Room room){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDTENANT, room.getIdTenant());
        contentValues.put(ROOMNUMBER, room.getRoomNumber1());
        contentValues.put(ROOMPRICE,room.getRoomPrice1M());
        contentValues.put(WATERBILL,room.getWaterBill1M());
        contentValues.put(ELECTRICBILL, room.getElectricBill1M());
        contentValues.put(SERVICEBILL, room.getServiceBill1M());
        try {
            if(database.update(TB_NAME,contentValues,IDROOM+"=?", new String[]{room.getIdRoom()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteContract(Room room){
        try {
            if(database.delete(TB_NAME,IDROOM+"=?",  new String[]{room.getIdRoom()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
