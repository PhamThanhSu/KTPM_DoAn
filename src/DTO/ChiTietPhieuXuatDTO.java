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
    private int dongia;

    public ChiTietPhieuXuatDTO(int maphieuxuat, int masp, int soluong, int dongia) {
        this.maphieuxuat = maphieuxuat;
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public ChiTietPhieuXuatDTO() {
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public int getMasp() {
        return masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

}
