package DAO;

import DTO.KhachHangDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class KhachHangDAO {

    Connection connection;
    PreparedStatement pst;

    public KhachHangDAO() {
    }

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    public ArrayList<KhachHangDTO> getAllKhachHang() {
        ArrayList<KhachHangDTO> listKhachHang = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM khachhang WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KhachHangDTO khachHangDTO = new KhachHangDTO();
                khachHangDTO.setMaKH(rs.getInt("makh"));
                khachHangDTO.setHoten(rs.getString("tenkhachhang"));
                khachHangDTO.setDiachi(rs.getString("diachi"));
                khachHangDTO.setSdt(rs.getString("sdt"));
                khachHangDTO.setNgaythamgia(rs.getDate("ngaythamgia"));
                listKhachHang.add(khachHangDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu khách hàng");
        }
        return listKhachHang;
    }

    public boolean themKhachHang(KhachHangDTO khachHangDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO khachhang (tenkhachhang, diachi, sdt) VALUES (?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, khachHangDTO.getHoten());
            pst.setString(2, khachHangDTO.getDiachi());
            pst.setString(3, khachHangDTO.getSdt());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public boolean xoaKhachHang(int t) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE `khachhang` SET `trangthai`=0 WHERE makh = ?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thanhCong;
    }

    public boolean suaKhachHang(KhachHangDTO khachHangDTO) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String query = "UPDATE khachhang SET tenkhachhang=?, diachi=? , sdt=? WHERE makh=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, khachHangDTO.getHoten());
            pst.setString(2, khachHangDTO.getDiachi());
            pst.setString(3, khachHangDTO.getSdt());
            pst.setInt(4, khachHangDTO.getMaKH());

            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public KhachHangDTO selectById(int makh) {
        KhachHangDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM khachhang WHERE makh=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, makh);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("makh");
                String ten = rs.getString("tenkhachhang");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                result = new KhachHangDTO(ma, ten, diachi, sdt);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

}
