package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SanPhamPhieuNhapDAO {

    public SanPhamPhieuNhapDAO() {

    }

    // Phương thức để lấy danh sách sản phẩm từ cơ sở dữ liệu
    public static ArrayList<SanPhamDTO> getListSanPham() {
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        Connection conn = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection
        String sql = "SELECT masp, tensp, soluongton, size FROM sanpham WHERE trangthai = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng SanPhamDTO từ dữ liệu trả về từ ResultSet
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setSoluongton(rs.getInt("soluongton"));
                sp.setSize(rs.getInt("size"));
                list.add(sp); // Thêm sản phẩm vào danh sách
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm để lấy danh sách sản phẩm với điều kiện masp = masp
    public static ArrayList<SanPhamDTO> getListSanPham(int masp) {
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        Connection conn = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection
        String sql = "SELECT * FROM sanpham WHERE masp = ? AND trangthai = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, masp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setSize(rs.getInt("size"));
                sp.setXuatxu(rs.getInt("xuatxu"));
                sp.setLoai(rs.getInt("loai"));
                sp.setThuonghieu(rs.getInt("thuonghieu"));
                //sp.set
                list.add(sp);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public static ArrayList<ChiTietPhieuNhapDTO> getListSanPhamANDGiaNhapXuat(int masp) {
//        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
//        Connection conn = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection
//        String sql = "SELECT sp.masp, sp.tensp, sp.size, sp.xuatxu, sp.loai, sp.thuonghieu, "
//                + "ctpn.dongia, ctpn.giaxuat "
//                + "FROM sanpham sp "
//                + "LEFT JOIN ctphieunhap ctpn ON sp.masp = ctpn.masp "
//                + "WHERE sp.masp = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, masp);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO();
//                ctphieu.setMasp(rs.getInt("masp"));
//                ctphieu.setTensp(rs.getString("tensp"));
//                ctphieu.setSize(rs.getInt("size"));
//                ctphieu.setXuatxu(rs.getInt("xuatxu"));
//                ctphieu.setLoai(rs.getInt("loai"));
//                ctphieu.setThuonghieu(rs.getInt("thuonghieu"));
//                ctphieu.setGianhap(rs.getInt("dongia"));
//                ctphieu.setGiaxuat(rs.getInt("giaxuat"));
//                list.add(ctphieu);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}
