/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChiTietPhieuXuatBUS {

    private ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;

    public ChiTietPhieuXuatBUS() {
        chiTietPhieuXuatDAO = ChiTietPhieuXuatDAO.getInstance();
    }

    // Thêm chi tiết phiếu xuất
    public void insertChiTietPhieuXuat(ArrayList<ChiTietPhieuXuatDTO> chiTietPhieuXuatList) {
        try {
            chiTietPhieuXuatDAO.insert(chiTietPhieuXuatList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết phiếu xuất: " + e.getMessage());
        }
    }

    // Cập nhật số lượng tồn của sản phẩm
    public void updateSoluongton(int masp, int soluong) {
        try {
            chiTietPhieuXuatDAO.updateSoluongton(masp, soluong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng tồn: " + e.getMessage());
        }
    }

    // Lấy danh sách tất cả chi tiết phiếu xuất theo mã phiếu xuất
    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuat(int mapx) {
        ArrayList<ChiTietPhieuXuatDTO> list = null;
        try {
            list = chiTietPhieuXuatDAO.getAllChiTietPhieuXuat(mapx);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách chi tiết phiếu xuất: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết phiếu xuất theo mã phiếu xuất
    public ChiTietPhieuXuatDTO getChiTietPhieuXuatByID(int mapx) {
        ChiTietPhieuXuatDTO result = null;
        try {
            result = chiTietPhieuXuatDAO.selectByID(mapx);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy chi tiết phiếu xuất theo ID: " + e.getMessage());
        }
        return result;
    }

    // Lấy chi tiết phiếu xuất theo mã sản phẩm
    public ChiTietPhieuXuatDTO getChiTietPhieuXuatByMASP(int masp) {
        ChiTietPhieuXuatDTO result = null;
        try {
            result = chiTietPhieuXuatDAO.selectByMASP(masp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy chi tiết phiếu xuất theo mã sản phẩm: " + e.getMessage());
        }
        return result;
    }

    // Lấy tất cả các chi tiết phiếu xuất theo mã phiếu xuất (bao gồm cả gianhap)
    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuatByMaphieu(int maphieuxuat) {
        ArrayList<ChiTietPhieuXuatDTO> result = new ArrayList<>();
        try {
            result = chiTietPhieuXuatDAO.selectAll(String.valueOf(maphieuxuat));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy tất cả chi tiết phiếu xuất: " + e.getMessage());
        }
        return result;
    }
}
