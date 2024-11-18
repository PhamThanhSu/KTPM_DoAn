package DTO;

import java.sql.Timestamp;

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
    private int magiamgia; // Thêm thuộc tính mã giảm giá

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

    public PhieuXuatDTO(int maphieuxuat, Timestamp thoigiantao, long tongTien, int manv, int makh, int trangthai, int magiamgia) {
        this.maphieuxuat = maphieuxuat;
        this.thoigiantao = thoigiantao;
        this.tongTien = tongTien;
        this.manv = manv;
        this.makh = makh;
        this.trangthai = trangthai;
        this.magiamgia = magiamgia; // Khởi tạo mã giảm giá
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

    public int getMagiamgia() {
        return magiamgia; // Getter cho mã giảm giá
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

    public void setMagiamgia(int magiamgia) {
        this.magiamgia = magiamgia; // Setter cho mã giảm giá
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
        return "PhieuXuatDTO{" + "makh=" + makh + ", magiamgia=" + magiamgia + '}'; // Cập nhật toString() để bao gồm mã giảm giá
    }
}
