/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class ChiTietPhieuNhapDTO {

    private int maphieunhap;
    private int masp;
    private int soluong;
    private int dongia;

    public ChiTietPhieuNhapDTO(int maphieunhap, int masp, int soluong, int dongia) {
        this.maphieunhap = maphieunhap;
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public ChiTietPhieuNhapDTO() {
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
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

    public int getMaphieunhap() {
        return maphieunhap;
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

}
