
package DTO;

import java.util.Objects;

public class TaiKhoanDTO {
    private int manv;
    private String username;
    private String matkhau;
    private int manhomquyen;
    private int trangthai;

    public TaiKhoanDTO() {
    }
    
    //của GUI.TKhoan
    public TaiKhoanDTO(String username, String matkhau, int maNhomquyen, int trangthai) {
        this.username = username;
        this.matkhau = matkhau;
        this.manhomquyen = maNhomquyen;
        this.trangthai = trangthai;
    }
    
    public TaiKhoanDTO(int manv, String username, String matkhau, int manhomquyen, int trangthai) {
        this.manv = manv;
        this.username = username;
        this.matkhau = matkhau;
        this.manhomquyen = manhomquyen;
        this.trangthai = trangthai;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
    @Override
    public String toString() {
        return "AccountDTO{" + "manv=" + manv + ", username=" + username + ", matkhau=" + matkhau + ", manhomquyen=" + manhomquyen + ", trangthai=" + trangthai + '}';
    }
    
}
