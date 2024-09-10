/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author phatl
 */
public class ThongKeSanPhamBanChayDTO {

    private String tenSP;
    private int soLuongDaBan;

    public ThongKeSanPhamBanChayDTO() {
    }

    public ThongKeSanPhamBanChayDTO(String tenSP, int soLuongDaBan) {
        this.tenSP = tenSP;
        this.soLuongDaBan = soLuongDaBan;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }

}
