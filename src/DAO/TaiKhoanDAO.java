
package DAO;

import DTO.TaiKhoanDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TaiKhoanDAO {
    
    private Connection connection;
    private PreparedStatement pst;
    
    public TaiKhoanDTO selectByUser(String t) {
        TaiKhoanDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                result = tk;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    public String selectChucVu(int maquyen) {
        String result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE manhomquyen=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, maquyen);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                String tennhomquyen = rs.getString("tennhomquyen");
                result = tennhomquyen;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public ArrayList<TaiKhoanDTO> getAllTaiKhoan() {
        ArrayList<TaiKhoanDTO> listTaiKhoan = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM taikhoan WHERE trangthai = 1 or trangthai = 0";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
                taiKhoanDTO.setManv(rs.getInt("manv"));
                taiKhoanDTO.setUsername(rs.getString("tendangnhap"));
                taiKhoanDTO.setMatkhau(rs.getString("matkhau"));
                taiKhoanDTO.setManhomquyen(rs.getInt("manhomquyen"));
                taiKhoanDTO.setTrangthai(rs.getInt("trangthai"));
                listTaiKhoan.add(taiKhoanDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu tài khoản");
        }
        return listTaiKhoan;
    }
    
    public boolean themTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO taikhoan (manv, tendangnhap, matkhau, manhomquyen, trangthai) VALUES (? , ?, ?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, taiKhoanDTO.getManv());
            pst.setString(2, taiKhoanDTO.getUsername());
            pst.setString(3, taiKhoanDTO.getMatkhau());
            pst.setInt(4, taiKhoanDTO.getManhomquyen());
            pst.setInt(5, taiKhoanDTO.getTrangthai());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE); 
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

    public boolean suaTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
        boolean thanhCong = false;
        String query = "UPDATE taikhoan SET tendangnhap=?, matkhau=?, manhomquyen=?, trangthai=? WHERE manv = ?";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, taiKhoanDTO.getUsername());
            pst.setString(2, taiKhoanDTO.getMatkhau());
            pst.setInt(3, taiKhoanDTO.getManhomquyen());
            pst.setInt(4, taiKhoanDTO.getTrangthai());
            pst.setInt(5, taiKhoanDTO.getManv());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa tài khoản" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return thanhCong;
    }

    public boolean xoaTaiKhoan(int t) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE `taikhoan` SET `trangthai`= -1 WHERE manv = ?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public TaiKhoanDTO selectById(int manv){
        TaiKhoanDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE manv=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, manv);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maNV = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                result = new TaiKhoanDTO(maNV, username, matkhau, manhomquyen, trangthai);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public String selectNhomQuyenByMaNQ(int manhomquyen){
        String result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT tennhomquyen FROM nhomquyen WHERE manhomquyen=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, manhomquyen);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                String tenNQ = rs.getString("tennhomquyen");
                result = tenNQ;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }
}
