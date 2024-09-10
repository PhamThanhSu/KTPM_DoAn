/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThuocTinhSanPham.XuatXuDTO;
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
public class XuatXuDAO {

    private Connection connection;
    private PreparedStatement pst;
    public XuatXuDAO() {

    }

    public ArrayList<XuatXuDTO> getAllXuatXu() {
        ArrayList<XuatXuDTO> listXuatXu = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM xuatxu WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                XuatXuDTO xuatXuDTO = new XuatXuDTO();
                xuatXuDTO.setMaxuatxu(resultSet.getInt("maxuatxu"));
                xuatXuDTO.setTenxuatxu(resultSet.getString("tenxuatxu"));
                listXuatXu.add(xuatXuDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu thương hiệu");
        }
        return listXuatXu;
    }

    public boolean xoaXuatXu(int maxuatxu) {
    boolean thanhCong = false;
    String query = "UPDATE xuatxu SET trangthai = 0 WHERE maxuatxu = ?";
    try {
        connection = MySQLConnection.getConnection();
        pst = connection.prepareStatement(query);
        pst.setInt(1, maxuatxu);
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            thanhCong = true;
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa xuất xứ");
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return thanhCong;
}

public boolean suaXuatXu(XuatXuDTO xuatXu){
    boolean thanhCong = false;
    String query = "UPDATE xuatxu SET tenxuatxu = ? WHERE maxuatxu = ?";
    try {
        connection = MySQLConnection.getConnection();
        pst = connection.prepareStatement(query);
        pst.setString(1, xuatXu.getTenxuatxu());
        pst.setInt(2, xuatXu.getMaxuatxu());
        int rowAff = pst.executeUpdate();
        if ( rowAff > 0){
            thanhCong = true;
        }     
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin xuất xứ");
    } 
    return thanhCong;
}

public boolean themXuatXu(XuatXuDTO xuatXuDTO){
    boolean thanhCong = false;
    String query = "INSERT INTO xuatxu (tenxuatxu) VALUES (?)";
    try {
        connection = MySQLConnection.getConnection();
        pst = connection.prepareStatement(query);
        pst.setString(1, xuatXuDTO.getTenxuatxu());
        int rowAff = pst.executeUpdate();
        if (rowAff > 0) {
            thanhCong = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm xuất xứ: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return thanhCong;
}
    
    public XuatXuDTO selectById(int t) {
        XuatXuDTO result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM xuatxu WHERE maxuatxu=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maxuatxu = rs.getInt("maxuatxu");
                String tenxuatxu = rs.getString("tenxuatxu");
                result = new XuatXuDTO(maxuatxu, tenxuatxu);
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }
}
