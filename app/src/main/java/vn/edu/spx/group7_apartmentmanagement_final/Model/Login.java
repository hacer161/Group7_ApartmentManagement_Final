package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Login {
    private String idAdmin;
    private String fullName;
    private String phone;
    private String password;

    public Login() {
    }

    public Login(String idAdmin, String fullName, String phone, String password) {
        this.idAdmin = idAdmin;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
