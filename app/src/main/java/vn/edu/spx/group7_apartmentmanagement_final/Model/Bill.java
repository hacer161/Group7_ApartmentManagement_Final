package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Bill {
    private int idBill;
    private int idRoom;
    private String RoomNumber2;
    private int RoomPrice2;
    private double WaterBill2;
    private double ElectricBill2;
    private double ServiceBill2;
    public Bill(){

    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomNumber2() {
        return RoomNumber2;
    }

    public void setRoomNumber2(String roomNumber2) {
        RoomNumber2 = roomNumber2;
    }

    public int getRoomPrice2() {
        return RoomPrice2;
    }

    public void setRoomPrice2(int roomPrice2) {
        RoomPrice2 = roomPrice2;
    }

    public double getWaterBill2() {
        return WaterBill2;
    }

    public void setWaterBill2(double waterBill2) {
        WaterBill2 = waterBill2;
    }

    public double getElectricBill2() {
        return ElectricBill2;
    }

    public void setElectricBill2(double electricBill2) {
        ElectricBill2 = electricBill2;
    }

    public double getServiceBill2() {
        return ServiceBill2;
    }

    public void setServiceBill2(double serviceBill2) {
        ServiceBill2 = serviceBill2;
    }

    public Bill(int idBill, int idRoom, String roomNumber2, int roomPrice2, double waterBill2, double electricBill2, double serviceBill2) {
        this.idBill = idBill;
        this.idRoom = idRoom;
        RoomNumber2 = roomNumber2;
        RoomPrice2 = roomPrice2;
        WaterBill2 = waterBill2;
        ElectricBill2 = electricBill2;
        ServiceBill2 = serviceBill2;
    }
}
