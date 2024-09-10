/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDoanhThuDTO {
    int thoigian; // nam, thang, ngay
    Long von;
    Long doanhthu;
    Long loinhuan;

    public ThongKeDoanhThuDTO() {
    }
    
    public ThongKeDoanhThuDTO(int thoigian, Long von, Long doanhthu, Long loinhuan) {
        this.thoigian = thoigian;
        this.von = von;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }
    
    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public Long getVon() {
        return von;
    }

    public void setVon(Long von) {
        this.von = von;
    }

    public Long getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(Long doanhthu) {
        this.doanhthu = doanhthu;
    }

    public Long getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(Long loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public String toString() {
        return "ThongKeDoanhThu{" + "thoigian=" + thoigian + ", von=" + von + ", doanhthu=" + doanhthu + ", loinhuan=" + loinhuan + '}';
    }
}
