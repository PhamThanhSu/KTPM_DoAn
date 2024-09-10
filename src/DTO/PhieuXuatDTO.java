/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class PhieuXuatDTO {

    private int maphieuxuat;
    private Timestamp thoigiantao;
    private long tongTien;
    private int manv;
    private int makh;
    private int trangthai;

    public PhieuXuatDTO() {
    }

    public PhieuXuatDTO(int maphieuxuat, Timestamp thoigiantao, long tongTien, int manv, int makh, int trangthai) {
        this.maphieuxuat = maphieuxuat;
        this.thoigiantao = thoigiantao;
        this.tongTien = tongTien;
        this.manv = manv;
        this.makh = makh;
        this.trangthai = trangthai;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public long getTongTien() {
        return tongTien;
    }

    public int getManv() {
        return manv;
    }

    public int getMakh() {
        return makh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.makh;
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
        final PhieuXuatDTO other = (PhieuXuatDTO) obj;
        return this.makh == other.makh;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" + "makh=" + makh + '}';
    }
}
