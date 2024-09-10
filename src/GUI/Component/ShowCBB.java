/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author this pc
 */
public class ShowCBB {

    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    NhanVienBUS nhanVienBUS = new NhanVienBUS(); 
    public ShowCBB() {

    }   
    public void CBBNhanVienNhap(JComboBox<String> comboBox){
        ArrayList<NhanVienDTO> nhanVienList = nhanVienBUS.getAllNhanVien();
        ArrayList<String> tenNhanVienList = new ArrayList<>();

        for (NhanVienDTO nhanVien : nhanVienList) {
            tenNhanVienList.add(nhanVien.getHoten());
        }

        for (String tenNhanVien : tenNhanVienList) {
            comboBox.addItem(tenNhanVien);
        }
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
    
    public void CBBKhachHang(JComboBox<String> comboBox) {
        // Gọi phương thức getAllNhaCungCap từ nhaCungCapBUS để lấy danh sách nhà cung cấp từ cơ sở dữ liệu
        ArrayList<KhachHangDTO> khachHangList = khachHangBUS.getAllKhachHang();
        ArrayList<String> tenKhachHangList = new ArrayList<>();

        for (KhachHangDTO khachHang : khachHangList) {
            tenKhachHangList.add(khachHang.getHoten());
        }
        // Thêm từng tên nhà cung cấp vào ComboBox
        for (String tenKhachHang : tenKhachHangList) {
            comboBox.addItem(tenKhachHang);
        }
    }
    
    
}
