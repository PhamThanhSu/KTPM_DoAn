    package DTO;

import java.util.Date;

public class KhachHangDTO {

    private int maKH;
    private String hoten;
    private String diachi;
    private String sdt;
    private Date ngaythamgia;

    public KhachHangDTO() {
    }
    public KhachHangDTO(int maKH, String hoten, String diachi, String sdt) {
        this.maKH = maKH;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
    }
    public KhachHangDTO( String hoten, String diachi, String sdt) {
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
    }
    
    public KhachHangDTO(int maKH, String hoten, String diachi, String sdt,Date ngaythamgia) {
        this.maKH = maKH;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaythamgia = ngaythamgia;
    }

    public Date getNgaythamgia() {
        return ngaythamgia;
    }

    public void setNgaythamgia(Date ngaythamgia) {
        this.ngaythamgia = ngaythamgia;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }


    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", hoten=" + hoten + ", diachi=" + diachi + ", sdt=" + sdt + '}';
    }

}
