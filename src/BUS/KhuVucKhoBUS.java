/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuVucKhoDAO;
import DTO.KhuVucKhoDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class KhuVucKhoBUS {
    private final KhuVucKhoDAO khuVucKhoDAO = new KhuVucKhoDAO();
    
    public KhuVucKhoBUS(){
    }
    
    public ArrayList<KhuVucKhoDTO> getAllKho() {         
        return khuVucKhoDAO.gettAllKho();        
    }

    
    public String[] getArrTenKho() {
        int size = getAllKho().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllKho().get(i).getTenkhuvuc();
        }
        return result;
    } 
    
    public KhuVucKhoDTO selectByID(int makhuvuc){
        return khuVucKhoDAO.selectById(makhuvuc);
    }
    
    
    public boolean xoaKhuVucKho(int makhuvuc) {
        return khuVucKhoDAO.xoaKho(makhuvuc);
    }

    public boolean suaKhuVucKho(KhuVucKhoDTO khuVucKhoDTO) {
        if (kiemTraTrungTenKhuVucKho(khuVucKhoDTO.getTenkhuvuc())) {
            JOptionPane.showMessageDialog(null, "Tên khu vực kho đã có");
            return false;
        }
        return khuVucKhoDAO.suaKho(khuVucKhoDTO);
    }

    public boolean themKhuVucKho(KhuVucKhoDTO khuVucKhoDTO) {
        if (kiemTraTrungTenKhuVucKho(khuVucKhoDTO.getTenkhuvuc())) {
            JOptionPane.showMessageDialog(null, "Tên khu vực đã có");
            return false;
        }
        return khuVucKhoDAO.themKho(khuVucKhoDTO);
    }

    public boolean kiemTraTrungTenKhuVucKho(String tenKhoMoi) {
        ArrayList<KhuVucKhoDTO> listKhuVucKho= getAllKho();
        
        for (KhuVucKhoDTO khuvuc : listKhuVucKho) {
            if (khuvuc.getTenkhuvuc().equalsIgnoreCase(tenKhoMoi)) {
                return true; // Tên thương hiệu mới trùng với một thương hiệu khác
            }
        }
        return false; // Tên thương hiệu mới không trùng với bất kỳ thương hiệu nào khác
    }
    
}


