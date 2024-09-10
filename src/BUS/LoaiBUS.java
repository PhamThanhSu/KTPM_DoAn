/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiDAO;
import DTO.LoaiDTO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class LoaiBUS {
    private final LoaiDAO loaiDAO = new LoaiDAO();
    
    public LoaiBUS(){
    }
    
    public ArrayList<LoaiDTO> getAllLoai() {         
        return loaiDAO.getAllLoai();        
    }
    
    public boolean xoaLoai(int maloai) {
        return loaiDAO.xoaLoai(maloai);
    }
    
    public boolean themLoai(LoaiDTO loaiDTO) {
        if (kiemTraTrungTen(loaiDTO.getTenloai())) {
            JOptionPane.showMessageDialog(null, "Tên loại đã có");
            return false;
        }
        return loaiDAO.themLoai(loaiDTO);
    }
    
    public boolean suaLoai(LoaiDTO loaiDTO) {
        if (kiemTraTrungTen(loaiDTO.getTenloai())) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu đã có");
            return false;
        }
        return loaiDAO.suaLoai(loaiDTO);
    }

    public boolean kiemTraTrungTen(String tenLoaiMoi) {
        ArrayList<LoaiDTO> listLoai = getAllLoai();
        for (LoaiDTO loai : listLoai) {
            if (loai.getTenloai().equalsIgnoreCase(tenLoaiMoi)) {
                return true; // Tên thương hiệu mới trùng với một thương hiệu khác
            }
        }
        return false; // Tên thương hiệu mới không trùng với bất kỳ thương hiệu nào khác
    }
    
    public String[] getArrTenLoai() {
        int size = getAllLoai().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllLoai().get(i).getTenloai();
        }
        return result;
    } 
    
    public LoaiDTO selectByID(int maxuatxu){
        return loaiDAO.selectById(maxuatxu);
    }
}


