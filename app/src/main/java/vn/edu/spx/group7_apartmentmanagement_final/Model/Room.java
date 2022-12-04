package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Room {
    private int idRoom;
    private int idTenant;
    private String TenantName;
    private String RoomNumber1;
    private int RoomPrice1M;
    private double WaterBill1M;
    private double ElectricBill1M;
    private double ServiceBill1M;
    public Room(){

    }


    public Room(int idRoom, int idTenant, String tenantName, String roomNumber1, int roomPrice1M, double waterBill1M, double electricBill1M, double serviceBill1M) {
        this.idRoom = idRoom;
        this.idTenant = idTenant;
        TenantName = tenantName;
        RoomNumber1 = roomNumber1;
        RoomPrice1M = roomPrice1M;
        WaterBill1M = waterBill1M;
        ElectricBill1M = electricBill1M;
        ServiceBill1M = serviceBill1M;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(int idTenant) {
        this.idTenant = idTenant;
    }

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String tenantName) {
        TenantName = tenantName;
    }

    public String getRoomNumber1() {
        return RoomNumber1;
    }

    public void setRoomNumber1(String roomNumber1) {
        RoomNumber1 = roomNumber1;
    }

    public int getRoomPrice1M() {
        return RoomPrice1M;
    }

    public void setRoomPrice1M(int roomPrice1M) {
        RoomPrice1M = roomPrice1M;
    }

    public double getWaterBill1M() {
        return WaterBill1M;
    }

    public void setWaterBill1M(double waterBill1M) {
        WaterBill1M = waterBill1M;
    }

    public double getElectricBill1M() {
        return ElectricBill1M;
    }

    public void setElectricBill1M(double electricBill1M) {
        ElectricBill1M = electricBill1M;
    }

    public double getServiceBill1M() {
        return ServiceBill1M;
    }

    public void setServiceBill1M(double serviceBill1M) {
        ServiceBill1M = serviceBill1M;
    }
}
