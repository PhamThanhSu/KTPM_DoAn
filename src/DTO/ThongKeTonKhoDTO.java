/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeTonKhoDTO {

    int masp;
    String tensanpham;
    int size;
    int nhaptrongky;
    int xuattrongky;
    int toncuoiky;

    public ThongKeTonKhoDTO() {
    }

    public ThongKeTonKhoDTO(int masp, String tensanpham, int size, int nhaptrongky, int xuattrongky, int toncuoiky) {
        this.masp = masp;
        this.tensanpham = tensanpham;
        this.size = size;
        this.nhaptrongky = nhaptrongky;
        this.xuattrongky = xuattrongky;
        this.toncuoiky = toncuoiky;
    }
    
    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNhaptrongky() {
        return nhaptrongky;
    }

    public void setNhaptrongky(int nhaptrongky) {
        this.nhaptrongky = nhaptrongky;
    }

    public int getXuattrongky() {
        return xuattrongky;
    }

    public void setXuattrongky(int xuattrongky) {
        this.xuattrongky = xuattrongky;
    }

    public int getToncuoiky() {
        return toncuoiky;
    }

    public void setToncuoiky(int toncuoiky) {
        this.toncuoiky = toncuoiky;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.masp;
        hash = 17 * hash + Objects.hashCode(this.tensanpham);
        hash = 17 * hash + this.size;
        hash = 17 * hash + this.nhaptrongky;
        hash = 17 * hash + this.xuattrongky;
        hash = 17 * hash + this.toncuoiky;
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
        final ThongKeTonKhoDTO other = (ThongKeTonKhoDTO) obj;
        if (this.masp != other.masp) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        if (this.nhaptrongky != other.nhaptrongky) {
            return false;
        }
        if (this.xuattrongky != other.xuattrongky) {
            return false;
        }
        if (this.toncuoiky != other.toncuoiky) {
            return false;
        }
        return Objects.equals(this.tensanpham, other.tensanpham);
    }
 
}
