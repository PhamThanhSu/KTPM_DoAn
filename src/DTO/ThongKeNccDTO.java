/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;
import DTO.PhieuNhapDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PC
 */

public class ThongKeNccDTO {
     int mancc;
    String tenncc;
    int soluong;
    long tongtien;

public ThongKeNccDTO() {
    }
    public ThongKeNccDTO(int mancc, String tenncc, int soluong, long tongtien) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.soluong = soluong;
        this.tongtien = tongtien;
}
  

    public int getMancc() {
        return mancc;
    }
 
    public void setMakh(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
 
    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.mancc;
        hash = 29 * hash + Objects.hashCode(this.tenncc);
        hash = 29 * hash + this.soluong;
        hash = 29 * hash + (int) (this.tongtien ^ (this.tongtien >>> 32));
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
        final ThongKeNccDTO other = (ThongKeNccDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ThongKeNccDTO{" + "mancc=" + mancc + ", tenncc=" + tenncc + ", soluong" + soluong+", tongtien=" + tongtien + '}';
    }
}

   