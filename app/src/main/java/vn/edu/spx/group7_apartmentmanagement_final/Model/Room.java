package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Room {
        private int idRoom;
        private String thoigian;
        private String sophong;
        private String nguoithue;
        private double tienthue;

        public Room() {

        }

    public Room(int idRoom, String thoigian, String sophong, String nguoithue, double tienthue) {
        this.idRoom = idRoom;
        this.thoigian = thoigian;
        this.sophong = sophong;
        this.nguoithue = nguoithue;
        this.tienthue = tienthue;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getSophong() {
        return sophong;
    }

    public void setSophong(String sophong) {
        this.sophong = sophong;
    }

    public String getNguoithue() {
        return nguoithue;
    }

    public void setNguoithue(String nguoithue) {
        this.nguoithue = nguoithue;
    }

    public double getTienthue() {
        return tienthue;
    }

    public void setTienthue(double tienthue) {
        this.tienthue = tienthue;
    }

   /* public String toString(){
            return "Tên sách: "+titleBook+" Mã sách: "+idBook+"\n"+"Giá Tiền: "+gia+" Tác giả: "+tacGia;
        }*/
}
