/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuNhapDTO {

    private int maphieunhap;
    //private Timestamp thoigiantao;
    private Timestamp thoigiantao;
    private int manhacungcap;
    private int manv; //Them manv
    private long tongTien;
    private int trangthai;

    public PhieuNhapDTO(int maphieunhap, Timestamp thoigiantao, int manhacungcap, int manv, long tongTien, int trangthai) {
        this.maphieunhap = maphieunhap;
        this.thoigiantao = thoigiantao;
        this.manhacungcap = manhacungcap;
        this.manv = manv;
        this.tongTien = tongTien;
        this.trangthai = trangthai;
    }

    public PhieuNhapDTO() {
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public int getMaNV() {
        return manv;
    }

    public long getTongTien() {
        return tongTien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public void setMaNV(int manv) {
        this.manv = manv;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.manhacungcap;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.manhacungcap == other.manhacungcap;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "maphieunhap=" + maphieunhap + ", thoigiantao=" + thoigiantao + ", manhacungcap=" + manhacungcap + ", manv =" + manv + ", tongTien=" + tongTien + ", trangthai=" + trangthai + '}';
    }

}
