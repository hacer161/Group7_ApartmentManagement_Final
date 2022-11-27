package vn.edu.spx.group7_apartmentmanagement_final.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Database.myHelper;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;

public class DAO_Contract {
        public static final String CREATE_TB_CONTRACT = "CREATE TABLE CONTRACT(IDCONTRACT INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,IDTENANT INTEGER NOT NULL, ROOMNUMBER TEXT NOT NULL,TIME DATE NOT NULL, ROOMPRICE INTEGER NOT NULL, WATERBILL INTEGER NOT NULL,ELECTRICBILL INTEGER NOT NULL,SERVICEBILL INTEGER NOT NULL, FOREIGN KEY(IDTENANT) REFERENCES TENANT(IDTENANT));";
        public static final String TB_NAME = "CONTRACT";
        public static final String IDCONTRACT = "IDCONTRACT";
        public static final String IDTENANT = "IDTENANT";
        public static final String ROOMNUMBER = "ROOMNUMBER";
        public static final String TIME ="TIME";
        public static final String ROOMPRICE = "ROOMPRICE";
        public static final String WATERBILL = "WATERBILL";
        public static final String ELECTRICBILL = "ELECTRICBILL";
        public static final String SERVICEBILL = "SERVICEBILL";
        SQLiteDatabase database;
        myHelper MyHelper;

        public DAO_Contract(Context context) {
            MyHelper = new myHelper(context);
        }

        public void opend() {
            database = MyHelper.getWritableDatabase();
        }

        public void close() {
            database.close();
        }
        public ArrayList<Contract> selectAll(){
            ArrayList<Contract> list = new ArrayList<>();
            String select ="SELECT * FROM CONTRACT JOIN TENANT ON CONTRACT.IDTENANT = TENANT. IDTENANT";
            Cursor cursor = database.rawQuery(select, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Contract contract = new Contract();
                contract.setIdContract(cursor.getInt(0));
                contract.setIdTenant(cursor.getInt(1));
                contract.setRoomNumber(cursor.getString(2));
                contract.setTime(cursor.getString(3));
                contract.setRoomPrice(cursor.getInt(4));
                contract.setWaterBill(cursor.getInt(5));
                contract.setElectricBill(cursor.getInt(6));
                contract.setServiceBill(cursor.getInt(7));
                contract.setTenantName(cursor.getString(9));
                list.add(contract);
                cursor.moveToNext();
            }
            cursor.close();
            return list;
        }
        public ArrayList<Contract> selectTenantName1(ArrayList<Tenant> listC){
            ArrayList<Contract> listB = new ArrayList<>();
            String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Tenant.TB_NAME+" ON "+TB_NAME+"."+IDTENANT+"="+DAO_Tenant.TB_NAME+"."+DAO_Tenant.IDTENANT+" WHERE "+TB_NAME+"."+IDTENANT+"="+listC.get(0).getIdTenant();
            Cursor cursor =database.rawQuery(select, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Contract contract = new Contract();
                contract.setIdContract(cursor.getInt(0));
                contract.setIdTenant(cursor.getInt(1));
                contract.setRoomNumber(cursor.getString(2));
                contract.setTime(cursor.getString(3));
                contract.setRoomPrice(cursor.getInt(4));
                contract.setWaterBill(cursor.getInt(5));
                contract.setElectricBill(cursor.getInt(6));
                contract.setServiceBill(cursor.getInt(7));
                contract.setTenantName(cursor.getString(9));
                listB.add(contract);
                cursor.moveToNext();
            }
            cursor.close();
            return listB;
        }
        public ArrayList<Contract> selectTenantName2(ArrayList<Tenant> listC){
            ArrayList<Contract> listB = new ArrayList<>();
            String select ="SELECT * FROM CONTRACT JOIN TENANT ON CONTRACT.IDTENANT = TENANT. IDTENANT WHERE CONTRACT.IDTENANT = "+listC.get(1).getIdTenant();
            Cursor cursor =database.rawQuery(select, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Contract contract = new Contract();
                contract.setIdContract(cursor.getInt(0));
                contract.setIdTenant(cursor.getInt(1));
                contract.setRoomNumber(cursor.getString(2));
                contract.setTime(cursor.getString(3));
                contract.setRoomPrice(cursor.getInt(4));
                contract.setWaterBill(cursor.getInt(5));
                contract.setElectricBill(cursor.getInt(6));
                contract.setServiceBill(cursor.getInt(7));
                contract.setTenantName(cursor.getString(9));
                listB.add(contract);
                cursor.moveToNext();
            }
            cursor.close();
            return listB;
        }
        public ArrayList<Contract> selectTenantName3(ArrayList<Tenant> listC){
            ArrayList<Contract> listB = new ArrayList<>();
            String select ="SELECT * FROM CONTRACT JOIN TENANT ON CONTRACT.IDTENANT = TENANT. IDTENANT WHERE CONTRACT.IDTENANT = "+listC.get(2).getIdTenant();
            Cursor cursor =database.rawQuery(select, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Contract contract = new Contract();
                contract.setIdContract(cursor.getInt(0));
                contract.setIdTenant(cursor.getInt(1));
                contract.setRoomNumber(cursor.getString(2));
                contract.setTime(cursor.getString(3));
                contract.setRoomPrice(cursor.getInt(4));
                contract.setWaterBill(cursor.getInt(5));
                contract.setElectricBill(cursor.getInt(6));
                contract.setServiceBill(cursor.getInt(7));
                contract.setTenantName(cursor.getString(9));
                listB.add(contract);
                cursor.moveToNext();
            }
            cursor.close();
            return listB;
        }
        public ArrayList<Contract> selectTenantName4(ArrayList<Tenant> listC){
            ArrayList<Contract> listB = new ArrayList<>();
            String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_Tenant.TB_NAME+" ON "+TB_NAME+"."+IDTENANT+"="+DAO_Tenant.TB_NAME+"."+DAO_Tenant.IDTENANT+" WHERE CONTRACT."+IDTENANT+" NOT IN ( SELECT IDTENANT FROM CONTRACT WHERE IDTENANT = "+listC.get(0).getIdTenant()+" OR "+" IDTENANT = "+listC.get(1).getIdTenant()+" OR IDCATEGORY = "+listC.get(2).getIdTenant()+" )";
            Cursor cursor =database.rawQuery(select, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Contract contract = new Contract();
                contract.setIdContract(cursor.getInt(0));
                contract.setIdTenant(cursor.getInt(1));
                contract.setRoomNumber(cursor.getString(2));
                contract.setTime(cursor.getString(3));
                contract.setRoomPrice(cursor.getInt(4));
                contract.setWaterBill(cursor.getInt(5));
                contract.setElectricBill(cursor.getInt(6));
                contract.setServiceBill(cursor.getInt(7));
                contract.setTenantName(cursor.getString(9));
                listB.add(contract);
                cursor.moveToNext();
            }
            cursor.close();
            return listB;
        }
        public long insertContract(Contract contract){
            ContentValues contentValues = new ContentValues();
            contentValues.put(IDTENANT, contract.getIdTenant());
            contentValues.put(ROOMNUMBER, contract.getRoomNumber());
            contentValues.put(TIME,contract.getTime());
            contentValues.put(ROOMPRICE, contract.getRoomPrice());
            contentValues.put(WATERBILL, contract.getWaterBill());
            contentValues.put(ELECTRICBILL, contract.getElectricBill());
            contentValues.put(SERVICEBILL, contract.getServiceBill());
            try {
                if(database.insert(TB_NAME, null, contentValues)==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }
        public int updateContract(Contract contract){
            ContentValues contentValues = new ContentValues();
            contentValues.put(IDTENANT, contract.getIdTenant());
            contentValues.put(ROOMNUMBER, contract.getRoomNumber());
            contentValues.put(TIME,contract.getTime());
            contentValues.put(ROOMPRICE, contract.getRoomPrice());
            contentValues.put(WATERBILL, contract.getWaterBill());
            contentValues.put(ELECTRICBILL, contract.getElectricBill());
            contentValues.put(SERVICEBILL, contract.getServiceBill());
            try {
                if(database.update(TB_NAME,contentValues,IDCONTRACT+"=?", new String[]{contract.getIdContract()+""})==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }
        public int deleteContract(Contract contract){
            try {
                if(database.delete(TB_NAME,IDCONTRACT+"=?",  new String[]{contract.getIdContract()+""})==-1){
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }

}
