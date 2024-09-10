/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author this pc
 */
public class LocTrongPhieuNhap {

    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    //NhanVienBUS nhanVienBUS = new NhanVienBUS(); chưa import nhanvien
    public LocTrongPhieuNhap() {

    }

    public void CBBNhaCungCap(JComboBox<String> comboBox) {
        // Gọi phương thức getAllNhaCungCap từ nhaCungCapBUS để lấy danh sách nhà cung cấp từ cơ sở dữ liệu
        ArrayList<NhaCungCapDTO> nhaCungCapList = nhaCungCapBUS.getAllNhaCungCap();
        ArrayList<String> tenNhaCungCapList = new ArrayList<>();

        for (NhaCungCapDTO nhaCungCap : nhaCungCapList) {
            tenNhaCungCapList.add(nhaCungCap.getTenncc());
        }
        // Thêm từng tên nhà cung cấp vào ComboBox
        for (String tenNhaCungCap : tenNhaCungCapList) {
            comboBox.addItem(tenNhaCungCap);
        }
    }
    
    //Đợi import nhân viên
//    public void CBBNhanVienNhap(JComboBox<String> comboBox){
//        ArrayList<NhanVienDTO> nhanVienList = nhanVienBUS.getAllNhanVien();
//        ArrayList<String> tenNhanVienList = new ArrayList<>();
//
//        for (NhanVienDTO nhanVien : nhanVienList) {
//            tenNhanVienList.add(nhanVien.getHoten());
//        }
//        // Thêm từng tên nhà cung cấp vào ComboBox
//        for (String tenNhanVien : tenNhanVienList) {
//            comboBox.addItem(tenNhanVien);
//        }
//    }
}
