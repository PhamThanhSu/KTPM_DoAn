/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuVucKhoDTO;
import DTO.LoaiDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phatl
 */
public class KhuVucKhoDAO {

    private Connection connection;
    private PreparedStatement pst;

    public KhuVucKhoDAO() {
    }

    public ArrayList<KhuVucKhoDTO> gettAllKho() {
        ArrayList<KhuVucKhoDTO> listKho = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM khuvuckho WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                KhuVucKhoDTO khuVucKhoDTO = new KhuVucKhoDTO();
                khuVucKhoDTO.setMakhuvuc(resultSet.getInt("makhuvuc"));
                khuVucKhoDTO.setTenkhuvuc(resultSet.getString("tenkhuvuc"));
                khuVucKhoDTO.setGhichu(resultSet.getString("ghichu"));
                listKho.add(khuVucKhoDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu loại");
        }
        return listKho;
    }

    public KhuVucKhoDTO selectById(int t) {
        KhuVucKhoDTO result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM khuvuckho WHERE makhuvuc=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int makhuvuc = rs.getInt("makhuvuc");
                String tenkhuvuc = rs.getString("tenkhuvuc");
                String ghiChu = rs.getString("ghichu");
                result = new KhuVucKhoDTO(makhuvuc, tenkhuvuc, ghiChu);
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public boolean xoaKho(int makhuvuc) {
        boolean thanhCong = false;
        String query = "UPDATE khuvuckho set trangthai = 0 WHERE makhuvuc = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, makhuvuc);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                thanhCong = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "lỗi khi xóa kho");
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

    public boolean suaKho(KhuVucKhoDTO khuVucKhoDTO) {
        boolean thanhCong = false;
        String query = "UPDATE khuvuckho set tenkhuvuc = ?,ghichu = ?  WHERE makhuvuc = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, khuVucKhoDTO.getTenkhuvuc());
            pst.setString(2, khuVucKhoDTO.getGhichu());
            pst.setInt(3, khuVucKhoDTO.getMakhuvuc());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin khu vực kho");
        }
        return thanhCong;
    }

    public boolean themKho(KhuVucKhoDTO khuVucKhoDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO khuvuckho (tenkhuvuc,ghichu) VALUES (?,?)"; // Sửa cú pháp câu lệnh SQL
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, khuVucKhoDTO.getTenkhuvuc());
            pst.setString(2, khuVucKhoDTO.getGhichu());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm khu vực kho: " + e.getMessage());
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

}
