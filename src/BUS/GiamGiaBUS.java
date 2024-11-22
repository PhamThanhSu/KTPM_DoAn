/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.GiamGiaDAO;
import DTO.GiamGiaDTO;
import DTO.GiamGiaSanPhamDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.stream.Collectors;

public class GiamGiaBUS {

    GiamGiaDAO giamGiaDAO = new GiamGiaDAO();
    GiamGiaSanPhamBUS giamGiaSanPhamBUS = new GiamGiaSanPhamBUS();

    public GiamGiaBUS() {

    }

    public boolean apDungGiamGiaMotSanPham(int magiamgia, int masp) throws SQLException {
        // Kiểm tra trạng thái giảm giá
        String trangthai = giamGiaDAO.SelectGiamGiabyID(magiamgia).getTrangthai();
        if (!"Có hiệu lực".equals(trangthai)) {
            return false; // Nếu trạng thái không hợp lệ, trả về false ngay
        }

        // Kiểm tra nếu mã sản phẩm có trong danh sách sản phẩm được áp dụng giảm giá
        return giamGiaSanPhamBUS.selectGiamGiaSPByID(magiamgia)
                .stream()
                .anyMatch(giamGiaSP -> giamGiaSP.getMasp() == masp);
    }

    public boolean apDungGiamGiaTongTien(int magiamgia, long tongtien) throws SQLException {
        long giatrihoadon = giamGiaDAO.SelectGiamGiabyID(magiamgia).getGiatrihoadon();
        String trangthai = giamGiaDAO.SelectGiamGiabyID(magiamgia).getTrangthai();
        if (tongtien >= giatrihoadon && trangthai.contains("Có hiệu lực")) {
            return true;
        }
        return false;
    }

    // Phương thức lấy tất cả mã giảm giá
    public GiamGiaDTO SelectGiamGiabyID(int magiamgia) throws SQLException {
        return giamGiaDAO.SelectGiamGiabyID(magiamgia);
    }

    // Phương thức lấy tất cả mã giảm giá
    public ArrayList<GiamGiaDTO> getAllGiamGia() throws SQLException {
        return giamGiaDAO.getAllGiamGia();
    }
    
    public ArrayList<GiamGiaDTO> getGiamGiaTuNgayHienTai() throws SQLException {
        return giamGiaDAO.getGiamGiaTuNgayHienTai();
    }

    // Phương thức thêm mã giảm giá mới
    public boolean insertGiamGia(GiamGiaDTO giamGia) throws SQLException {
        return giamGiaDAO.insertGiamGia(giamGia);
    }

    // Phương thức cập nhật mã giảm giá
    public boolean updateGiamGia(GiamGiaDTO giamGia) throws SQLException {
        // Kiểm tra điều kiện trước khi cập nhật
        if (giamGia.getPhantramgiam() < 0 || giamGia.getPhantramgiam() > 60) {
            System.out.println("Phần trăm giảm không hợp lệ");
            return false;
        }
        return giamGiaDAO.updateGiamGia(giamGia);
    }

    public boolean updateGiamGiaTrangThai(GiamGiaDTO giamGia) throws SQLException {
        return giamGiaDAO.updateGiamGiaTrangThai(giamGia);
    }
    
    // Phương thức xóa mã giảm giá
    public boolean deleteGiamGia(int magiamgia) throws SQLException {
        return giamGiaDAO.deleteGiamGia(magiamgia);
    }

    // Phương thức tìm mã giảm giá theo tên
    public ArrayList<GiamGiaDTO> searchGiamGiaByName(String tengiamgia) throws SQLException {
        // Lấy tất cả danh sách giảm giá
        ArrayList<GiamGiaDTO> allGiamGia = giamGiaDAO.getAllGiamGia();

        // Sử dụng stream để lọc các phần tử có tên giảm giá chứa tên tìm kiếm (không phân biệt chữ hoa, chữ thường)
        ArrayList<GiamGiaDTO> filteredGiamGia = (ArrayList<GiamGiaDTO>) allGiamGia.stream()
                .filter(giamGia -> giamGia.getTengiamgia().toLowerCase().contains(tengiamgia.toLowerCase()))
                .collect(Collectors.toList()); // Thu thập kết quả lọc vào một ArrayList

        return filteredGiamGia; // Trả về ArrayList đã lọc
    }

    // Hàm tạo mã giảm giá mới theo định dạng GG + số mã giảm giá tiếp theo
    public String generateMaGiamGia() throws SQLException {
        int totalGiamGia = giamGiaDAO.getMaxGiamGiaId(); // Lấy mã giảm giá lớn nhất trong database
        String maGiamGiaMoi; // Khai báo biến ở ngoài khối if-else

        if (totalGiamGia < 10) {
            maGiamGiaMoi = "GG0" + (totalGiamGia + 1);
        } else {
            maGiamGiaMoi = "GG" + (totalGiamGia + 1);
        }

        return maGiamGiaMoi;
    }

}
