package vn.edu.spx.group7_apartmentmanagement_final.Model;

public class Tenant {
    private int idTenant;
    private String TenantName;
    private String Identity;
    private String DOB;

    public Tenant(){

    }

    public Tenant(int idTenant, String tenantName, String identity, String DOB) {
        this.idTenant = idTenant;
        TenantName = tenantName;
        Identity = identity;
        this.DOB = DOB;
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

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
