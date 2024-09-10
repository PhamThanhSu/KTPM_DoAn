/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class ThuongHieuBUS {

    private final ThuongHieuDAO thuongHieuDAO = new ThuongHieuDAO();

    public ArrayList<ThuongHieuDTO> getAllThuongHieu() {
        return thuongHieuDAO.getAllThuongHieu();
    }

    public boolean xoaThuongHieu(int mathuonghieu) {
        return thuongHieuDAO.xoaThuongHieu(mathuonghieu);
    }

    public boolean suaThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        if (kiemTraTrungTenThuongHieu(thuongHieuDTO.getTenthuonghieu())) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu đã có");
            return false;
        }
        return thuongHieuDAO.suaThuongHieu(thuongHieuDTO);
    }

    public boolean themThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        if (kiemTraTrungTenThuongHieu(thuongHieuDTO.getTenthuonghieu())) {
            JOptionPane.showMessageDialog(null, "Tên thương hiệu đã có");
            return false;
        }
        return thuongHieuDAO.themThuongHieu(thuongHieuDTO);
    }

    public boolean kiemTraTrungTenThuongHieu(String tenThuongHieuMoi) {
        ArrayList<ThuongHieuDTO> listThuongHieu = getAllThuongHieu();
        
        for (ThuongHieuDTO thuongHieu : listThuongHieu) {
            if (thuongHieu.getTenthuonghieu().equalsIgnoreCase(tenThuongHieuMoi)) {
                return true; // Tên thương hiệu mới trùng với một thương hiệu khác
            }
        }
        return false; // Tên thương hiệu mới không trùng với bất kỳ thương hiệu nào khác
    }
    
    public String[] getArrTenThuongHieu() {
        int size = getAllThuongHieu().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllThuongHieu().get(i).getTenthuonghieu();
        }
        return result;
    } 
    
    public ThuongHieuDTO getSelectByID(int mathuonghieu){
        return thuongHieuDAO.selectById(mathuonghieu);
    }
}
