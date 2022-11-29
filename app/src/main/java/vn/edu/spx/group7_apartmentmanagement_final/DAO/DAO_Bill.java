package vn.edu.spx.group7_apartmentmanagement_final.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Bill;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;

public class DAO_Bill {
    public static final String CREATE_TB_BILL = "CREATE TABLE BILL(IDBILL INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,IDROOM INTEGER, FOREIGN KEY(IDROOM) REFERENCES ROOM(IDROOM));";
    public static final String TB_NAME = "BILL";
    public static final String IDBILL = "IDBILL";
    public static final String IDROOM = "IDROOM";

    SQLiteDatabase database;
    myHelper MyHelper;

    public DAO_Bill(Context context) {
        MyHelper = new myHelper(context);
    }

    public void opend() {
        database = MyHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Bill> selectAll(){
        ArrayList<Bill> list = new ArrayList<>();
        String select ="SELECT * FROM BILL JOIN ROOM ON BILL.IDROOM = ROOM. IDROOM";
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getInt(0));
            bill.setIdRoom(cursor.getInt(1));
            bill.setRoomNumber2(cursor.getString(3));
            bill.setRoomPrice2(cursor.getInt(4));
            bill.setWaterBill2(cursor.getDouble(5));
            bill.setElectricBill2(cursor.getDouble(6));
            bill.setServiceBill2(cursor.getDouble(7));
            list.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<Bill> selectTenantName1(ArrayList<Room> listC){
        ArrayList<Bill> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Room.TB_NAME+" ON "+TB_NAME+"."+IDROOM+"="+DAO_Room.TB_NAME+"."+DAO_Room.IDTENANT+" WHERE "+TB_NAME+"."+IDROOM+"="+listC.get(0).getIdRoom();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getInt(0));
            bill.setIdRoom(cursor.getInt(1));
            bill.setRoomNumber2(cursor.getString(3));
            bill.setRoomPrice2(cursor.getInt(4));
            bill.setWaterBill2(cursor.getDouble(5));
            bill.setElectricBill2(cursor.getDouble(6));
            bill.setServiceBill2(cursor.getDouble(7));
            listB.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Bill> selectTenantName2(ArrayList<Room> listC){
        ArrayList<Bill> listB = new ArrayList<>();
        String select ="SELECT * FROM BILL JOIN ROOM ON BILL.IDROOM = ROOM. IDROOM WHERE BILL.IDROOM = "+listC.get(1).getIdRoom();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getInt(0));
            bill.setIdRoom(cursor.getInt(1));
            bill.setRoomNumber2(cursor.getString(3));
            bill.setRoomPrice2(cursor.getInt(4));
            bill.setWaterBill2(cursor.getDouble(5));
            bill.setElectricBill2(cursor.getDouble(6));
            bill.setServiceBill2(cursor.getDouble(7));
            listB.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Bill> selectTenantName3(ArrayList<Room> listC){
        ArrayList<Bill> listB = new ArrayList<>();
        String select ="SELECT * FROM BILL JOIN ROOM ON BILL.IDROOM = ROOM. IDROOM WHERE BILL.IDROOM = "+listC.get(2).getIdRoom();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getInt(0));
            bill.setIdRoom(cursor.getInt(1));
            bill.setRoomNumber2(cursor.getString(3));
            bill.setRoomPrice2(cursor.getInt(4));
            bill.setWaterBill2(cursor.getDouble(5));
            bill.setElectricBill2(cursor.getDouble(6));
            bill.setServiceBill2(cursor.getDouble(7));
            listB.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Bill> selectTenantName4(ArrayList<Room> listC){
        ArrayList<Bill> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Room.TB_NAME+" ON "+TB_NAME+"."+IDROOM+"="+DAO_Room.TB_NAME+"."+DAO_Room.IDROOM+" WHERE BILL."+IDROOM+" NOT IN ( SELECT IDROOM FROM BILL WHERE IDROOM = "+listC.get(0).getIdRoom()+" OR "+" IDROOM = "+listC.get(1).getIdRoom()+" OR IDROOM = "+listC.get(2).getIdRoom()+" )";
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getInt(0));
            bill.setIdRoom(cursor.getInt(1));
            bill.setRoomNumber2(cursor.getString(3));
            bill.setRoomPrice2(cursor.getInt(4));
            bill.setWaterBill2(cursor.getDouble(5));
            bill.setElectricBill2(cursor.getDouble(6));
            bill.setServiceBill2(cursor.getDouble(7));
            listB.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public long insertBILL(Bill bill){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDROOM, bill.getIdRoom());
        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateBill(Bill bill){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDROOM, bill.getIdRoom());

        try {
            if(database.update(TB_NAME,contentValues,IDBILL+"=?", new String[]{bill.getIdBill()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteBill(Bill bill){
        try {
            if(database.delete(TB_NAME,IDBILL+"=?",  new String[]{bill.getIdBill()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
