/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class ChiTietPhieuXuatDTO {
    private int maphieuxuat;
    private int masp;
    private int soluong;
    private int giaxuat;

    public ChiTietPhieuXuatDTO() {}

    public ChiTietPhieuXuatDTO(int maphieuxuat, int masp, int soluong, int giaxuat) {
        this.maphieuxuat = maphieuxuat;
        this.masp = masp;
        this.soluong = soluong;
        this.giaxuat = giaxuat;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getMaSp() {
        return masp;
    }

    public void setMaSp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO{" +
                "maphieuxuat=" + maphieuxuat +
                ", masp=" + masp +
                ", soluong=" + soluong +
                ", giaxuat=" + giaxuat +
                '}';
    }
}
