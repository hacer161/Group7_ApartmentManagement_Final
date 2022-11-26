package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Contract {
    private int idContract;
    private int idTenant;
    private String TenantName;
    private String RoomNumber;
    private String Time;
    private int RoomPrice;
    private int WaterBill;
    private int ElectricBill;
    private int ServiceBill;

    public Contract() {

    }

    public Contract(int idContract, int idTenant, String tenantName, String roomNumber, String time, int roomPrice, int waterBill, int electricBill, int serviceBill) {
        this.idContract = idContract;
        this.idTenant = idTenant;
        TenantName = tenantName;
        RoomNumber = roomNumber;
        Time = time;
        RoomPrice = roomPrice;
        WaterBill = waterBill;
        ElectricBill = electricBill;
        ServiceBill = serviceBill;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
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

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        RoomPrice = roomPrice;
    }

    public int getWaterBill() {
        return WaterBill;
    }

    public void setWaterBill(int waterBill) {
        WaterBill = waterBill;
    }

    public int getElectricBill() {
        return ElectricBill;
    }

    public void setElectricBill(int electricBill) {
        ElectricBill = electricBill;
    }

    public int getServiceBill() {
        return ServiceBill;
    }

    public void setServiceBill(int serviceBill) {
        ServiceBill = serviceBill;
    }
}