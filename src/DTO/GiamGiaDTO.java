package DTO;

import java.util.Date;

public class GiamGiaDTO {

    private int magiamgia;
    private String tengiamgia;
    private int phantramgiam;
    private long giatrihoadon;
    private Date ngaybatdau;
    private Date ngayketthuc;
    private String trangthai;

    // Constructor mặc định
    public GiamGiaDTO() {
    }

    // Constructor có tham số
    public GiamGiaDTO(int magiamgia, String tengiamgia, int phantramgiam, long giatrihoadon, Date ngaybatdau, Date ngayketthuc, String trangthai) {
        this.magiamgia = magiamgia;
        this.tengiamgia = tengiamgia;
        this.phantramgiam = phantramgiam;
        this.giatrihoadon = giatrihoadon;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.trangthai = trangthai;
    }
    
    public GiamGiaDTO(int magiamgia, String tengiamgia, int phantramgiam, long giatrihoadon, String trangthai) {
        this.magiamgia = magiamgia;
        this.tengiamgia = tengiamgia;
        this.phantramgiam = phantramgiam;
        this.giatrihoadon = giatrihoadon;
        this.trangthai = trangthai;
    }

    // Getter và Setter cho từng thuộc tính
    public int getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(int magiamgia) {
        this.magiamgia = magiamgia;
    }

    public String getTengiamgia() {
        return tengiamgia;
    }

    public void setTengiamgia(String tengiamgia) {
        this.tengiamgia = tengiamgia;
    }

    public int getPhantramgiam() {
        return phantramgiam;
    }

    public void setPhantramgiam(int phantramgiam) {
        this.phantramgiam = phantramgiam;
    }

    public long getGiatrihoadon() {
        return giatrihoadon;
    }

    public void setGiatrihoadon(long giatrihoadon) {
        this.giatrihoadon = giatrihoadon;
    }

    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
