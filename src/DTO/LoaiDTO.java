/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class LoaiDTO {
    private int maloai;
    private String tenloai;

    public LoaiDTO() {
    }

    public LoaiDTO(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    
    
    public int getMaloai() {
        return maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
    
}
