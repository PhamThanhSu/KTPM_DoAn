/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuXuatDTO;
import DTO.SanPhamDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ChiTietPhieuXuatDAO {

    private Connection connection;
    private PreparedStatement ps;

    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }

    public ChiTietPhieuXuatDAO() {
        connection = MySQLConnection.getConnection();
    }

    public void insert(ArrayList<ChiTietPhieuXuatDTO> chiTietPhieuXuatList) {
        String sql = "INSERT INTO `ctphieuxuat`(`maphieuxuat`, `masp`, `soluong`, `dongia`) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (ChiTietPhieuXuatDTO chiTiet : chiTietPhieuXuatList) {
                if (chiTiet != null) {
                    ps.setInt(1, chiTiet.getMaphieuxuat());
                    ps.setInt(2, chiTiet.getMasp());
                    ps.setInt(3, chiTiet.getSoluong());
                    ps.setInt(4, chiTiet.getDongia());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL");
        }
    }

    public void updateSoluongton(int masp, int soluong) {
        SanPhamDTO sanpham = new SanPhamDTO();
        int soluongton = sanpham.getSoluongton() - soluong;
        try {
            String sql = "UPDATE sanpham SET soluongton = soluongton + ? WHERE masp = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, soluongton);
                ps.setInt(2, masp);
                System.out.println("check");
                System.out.println(masp);
                System.out.println(soluongton);
                ps.executeUpdate(); // Thực hiện cập nhật
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }

    public ArrayList<ChiTietPhieuXuatDTO> selectAll(String t) {
        connection = MySQLConnection.getConnection();
        ArrayList<ChiTietPhieuXuatDTO> result = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, t);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int maphieu = rs.getInt("maphieuxuat");
                    int masp = rs.getInt("masp");
                    int dongia = rs.getInt("dongia");
                    int soluong = rs.getInt("soluong");
                    ChiTietPhieuXuatDTO ctphieu = new ChiTietPhieuXuatDTO(maphieu, masp, soluong, dongia);
                    result.add(ctphieu);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
        }
        return result;
    }

    public ChiTietPhieuXuatDTO selectByID(int mapx) {
        ChiTietPhieuXuatDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mapx);
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                int maphieuxuat = rs.getInt("maphieuxuat");
                int masp = rs.getInt("masp");
                int soluong = rs.getInt("soluong");
                int dongia = rs.getInt("dongia");
                result = new ChiTietPhieuXuatDTO(maphieuxuat, masp, soluong, dongia);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuat(int mapx) {
        ArrayList<ChiTietPhieuXuatDTO> result = new ArrayList<>();
        Connection conn = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection
        String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
        System.out.println(mapx);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mapx); // Đặt giá trị cho tham số mapn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieuxuat");
                int masp = rs.getInt("masp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                ChiTietPhieuXuatDTO ctphieu = new ChiTietPhieuXuatDTO(maphieu, masp, soluong, dongia);
                result.add(ctphieu);
                System.out.println(result);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL");
            ex.printStackTrace(); // In ra lỗi
        }
        return result;
    }

}
