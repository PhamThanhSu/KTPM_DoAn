/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaiDTO;
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
public class LoaiDAO {

    private Connection connection;
    private PreparedStatement pst;

    public LoaiDAO() {

    }

    public ArrayList<LoaiDTO> getAllLoai() {
        ArrayList<LoaiDTO> listLoai = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM loai WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                LoaiDTO loai = new LoaiDTO();
                loai.setMaloai(resultSet.getInt("maloai"));
                loai.setTenloai(resultSet.getString("tenloai"));

                listLoai.add(loai);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu loại");
        }
        return listLoai;
    }

    public boolean themLoai(LoaiDTO loaiDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO loai (tenloai) VALUES (?)"; // Sửa cú pháp câu lệnh SQL
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, loaiDTO.getTenloai());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm loại: " + e.getMessage());
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

    public boolean suaLoai(LoaiDTO loai) {
        boolean thanhCong = false;
        String query = "UPDATE loai set tenloai = ? WHERE maloai = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, loai.getTenloai());
            pst.setInt(2, loai.getMaloai());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa tên loại");
        }
        return thanhCong;
    }

    public boolean xoaLoai(int maloai) {
        boolean thanhCong = false;
        String query = "UPDATE loai set trangthai = 0 WHERE maloai = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, maloai);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                thanhCong = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "lỗi khi xóa loại");
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
    
    public LoaiDTO selectById(int t) {
        LoaiDTO result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM loai WHERE maloai=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maloai = rs.getInt("maloai");
                String tenloai = rs.getString("tenloai");
                result = new LoaiDTO(maloai, tenloai);
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }
}
