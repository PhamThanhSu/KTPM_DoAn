/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.XuatXuDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class XuatXuBUS {

    private final XuatXuDAO xuatXuDAO = new XuatXuDAO();

    public ArrayList<XuatXuDTO> getAllXuatXu() {
        return xuatXuDAO.getAllXuatXu();
    }

    public boolean xoaXuatXu(int maxuatxu) {
        return xuatXuDAO.xoaXuatXu(maxuatxu);
    }

    public boolean suaXuatXu(XuatXuDTO xuatXuDTO) {
        if (kiemTraTrungTenXuatXu(xuatXuDTO.getTenxuatxu())) {
            JOptionPane.showMessageDialog(null, "Tên xuất xứ đã có");
            return false;
        }
        return xuatXuDAO.suaXuatXu(xuatXuDTO);
    }

    public boolean themXuatXu(XuatXuDTO xuatXuDTO) {
        if (kiemTraTrungTenXuatXu(xuatXuDTO.getTenxuatxu())) {
            JOptionPane.showMessageDialog(null, "Tên xuất xứ đã có");
            return false;
        }
        return xuatXuDAO.themXuatXu(xuatXuDTO);
    }

    public boolean kiemTraTrungTenXuatXu(String tenXuatXuMoi) {
        ArrayList<XuatXuDTO> listXuatXu = getAllXuatXu();
        for (XuatXuDTO xuatXu : listXuatXu) {
            if (xuatXu.getTenxuatxu().equalsIgnoreCase(tenXuatXuMoi)) {
                return true; // Tên xuất xứ mới trùng với một xuất xứ khác
            }
        }
        return false; // Tên xuất xứ mới không trùng với bất kỳ xuất xứ nào khác
    }
    
    public String[] getArrTenXuatXu() {
        int size = getAllXuatXu().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllXuatXu().get(i).getTenxuatxu();
        }
        return result;
    } 
    public XuatXuDTO selectByID(int maxuatxu){
        return xuatXuDAO.selectById(maxuatxu);
    }
}
