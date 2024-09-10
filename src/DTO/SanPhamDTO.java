/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author 84907
 */
public class SanPhamDTO {

    private int masp;
    private String tensp;
    private int size;
    private String hinhanh;
    private int xuatxu;
    private int loai;
    private int thuonghieu;
    private int khuvuckho;
    private int gianhap;
    private int giaxuat;
    private int soluongton;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String tensp, int size, String hinhanh, int xuatxu, int loai, int thuonghieu, int khuvuckho, int gianhap, int giaxuat) {
        this.tensp = tensp;
        this.size = size;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.loai = loai;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }

    public SanPhamDTO(int masp, String tensp, int size, String hinhanh, int xuatxu, int loai, int thuonghieu, int khuvuckho, int gianhap, int giaxuat, int soluongton) {
        this.masp = masp;
        this.tensp = tensp;
        this.size = size;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.loai = loai;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.soluongton = soluongton;
    }



    public SanPhamDTO(int masp, String tensp, int size, String hinhanh, int xuatxu, int loai, int thuonghieu, int khuvuckho, int soluongton) {
        this.masp = masp;
        this.tensp = tensp;
        this.size = size;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.loai = loai;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.soluongton = soluongton;
    }

    public SanPhamDTO(int masp, String tensp, int size, String hinhanh, int xuatxu, int loai, int thuonghieu, int khuvuckho, int gianhap, int giaxuat) {
        this.masp = masp;
        this.tensp = tensp;
        this.size = size;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.loai = loai;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }

    public int getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public int getSize() {
        return size;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public int getXuatxu() {
        return xuatxu;
    }

    public int getLoai() {
        return loai;
    }

    public int getThuonghieu() {
        return thuonghieu;
    }

    public int getKhuvuckho() {
        return khuvuckho;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public int getGianhap() {
        return gianhap;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    
    public void setMasp(int masp) {
        this.masp = masp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    public void setXuatxu(int xuatxu) {
        this.xuatxu = xuatxu;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public void setThuonghieu(int thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public void setKhuvuckho(int khuvuckho) {
        this.khuvuckho = khuvuckho;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }
    

    
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 53 * hash + this.masp;
//        hash = 53 * hash + Objects.hashCode(this.tensp);
//        hash = 53 * hash + Objects.hashCode(this.hinhanh);
//        hash = 53 * hash + Objects.hashCode(this.xuatxu);
//        hash = 53 * hash + Objects.hashCode(this.chipxuly);
//        hash = 53 * hash + this.dungluongpin;
//        hash = 53 * hash + (int) (Double.doubleToLongBits(this.kichthuocman) ^ (Double.doubleToLongBits(this.kichthuocman) >>> 32));
//        hash = 53 * hash + Objects.hashCode(this.hedieuhanh);
//        hash = 53 * hash + this.phienbanhdh;
//        hash = 53 * hash + Objects.hashCode(this.camerasau);
//        hash = 53 * hash + Objects.hashCode(this.cameratruoc);
//        hash = 53 * hash + this.thoigianbaohanh;
//        hash = 53 * hash + this.thuonghieu;
//        hash = 53 * hash + this.khuvuckho;
//        hash = 53 * hash + this.soluongton;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final SanPhamDTO other = (SanPhamDTO) obj;
//        if (this.masp != other.masp) {
//            return false;
//        }
//        if (this.dungluongpin != other.dungluongpin) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.kichthuocman) != Double.doubleToLongBits(other.kichthuocman)) {
//            return false;
//        }
//        if (this.phienbanhdh != other.phienbanhdh) {
//            return false;
//        }
//        if (this.thoigianbaohanh != other.thoigianbaohanh) {
//            return false;
//        }
//        if (this.thuonghieu != other.thuonghieu) {
//            return false;
//        }
//        if (this.khuvuckho != other.khuvuckho) {
//            return false;
//        }
//        if (this.soluongton != other.soluongton) {
//            return false;
//        }
//        if (!Objects.equals(this.tensp, other.tensp)) {
//            return false;
//        }
//        if (!Objects.equals(this.hinhanh, other.hinhanh)) {
//            return false;
//        }
//        if (!Objects.equals(this.xuatxu, other.xuatxu)) {
//            return false;
//        }
//        if (!Objects.equals(this.chipxuly, other.chipxuly)) {
//            return false;
//        }
//        if (!Objects.equals(this.hedieuhanh, other.hedieuhanh)) {
//            return false;
//        }
//        if (!Objects.equals(this.camerasau, other.camerasau)) {
//            return false;
//        }
//        return Objects.equals(this.cameratruoc, other.cameratruoc);
//    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "masp=" + masp + ", tensp=" + tensp + ", size=" + size + ", hinhanh=" + hinhanh + ", xuatxu=" + xuatxu + ", loai=" + loai + ", thuonghieu=" + thuonghieu + ", khuvuckho=" + khuvuckho + ", soluongton=" + soluongton + '}';
    }

 

}
