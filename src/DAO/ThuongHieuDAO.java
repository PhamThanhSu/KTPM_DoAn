/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThuongHieuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.MySQLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class ThuongHieuDAO {

    private Connection connection;
    private PreparedStatement pst;

  

    public ThuongHieuDAO() {

    }

    public ArrayList<ThuongHieuDTO> getAllThuongHieu() {
        ArrayList<ThuongHieuDTO> listThuongHieu = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM thuonghieu WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                ThuongHieuDTO thuongHieu = new ThuongHieuDTO();
                thuongHieu.setMathuonghieu(resultSet.getInt("mathuonghieu"));
                thuongHieu.setTenthuonghieu(resultSet.getString("tenthuonghieu"));
                listThuongHieu.add(thuongHieu);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu thương hiệu");
        }
        return listThuongHieu;
    }

    public boolean xoaThuongHieu(int mathuonghieu) {
        boolean thanhCong = false;
        String query = "UPDATE thuonghieu set trangthai = 0 WHERE mathuonghieu = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, mathuonghieu);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                thanhCong = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "lỗi khi xóa thương hiệu");
        } finally {
            try {
                // Đóng kết nối sau khi hoàn thành
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                // Ghi log lỗi ra console
                ex.printStackTrace();
            }
        }
        return thanhCong;
    }

    public boolean suaThuongHieu(ThuongHieuDTO thuongHieu) {
        boolean thanhCong = false;
        String query = "UPDATE thuonghieu set tenthuonghieu = ? WHERE mathuonghieu = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, thuongHieu.getTenthuonghieu());
            pst.setInt(2, thuongHieu.getMathuonghieu());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin thương hiệu");
        }
        return thanhCong;
    }

    public boolean themThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO thuonghieu (tenthuonghieu) VALUES (?)"; // Sửa cú pháp câu lệnh SQL
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, thuongHieuDTO.getTenthuonghieu());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm thương hiệu: " + e.getMessage());
            e.printStackTrace(); // In ra lỗi chi tiết để debug
        } finally {
            // Đóng các tài nguyên kết nối
            try {
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // In ra lỗi nếu có lỗi khi đóng kết nối
            }
        }
        return thanhCong;
    }

    public ThuongHieuDTO selectById(int t) {
        ThuongHieuDTO result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM thuonghieu WHERE mathuonghieu=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int mathuonghieu = rs.getInt("mathuonghieu");
                String tenthuonghieu = rs.getString("tenthuonghieu");
                result = new ThuongHieuDTO(mathuonghieu, tenthuonghieu);
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

}
