package DAO;

import DTO.NhanVienDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NhanVienDAO {

    

    Connection connection;
    PreparedStatement pst;

    public NhanVienDAO() {
    }
    
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }
    
    
    public ArrayList<NhanVienDTO> getAllNhanVien() {
        ArrayList<NhanVienDTO> listNhanVien = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM nhanvien WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setManv(rs.getInt("manv"));
                nhanVienDTO.setHoten(rs.getString("hoten"));
                nhanVienDTO.setGioitinh(rs.getInt("gioitinh"));
                nhanVienDTO.setNgaysinh(rs.getDate("ngaysinh"));
                nhanVienDTO.setSdt(rs.getString("sdt"));
                nhanVienDTO.setEmail(rs.getString("email"));
                nhanVienDTO.setTrangthai(rs.getInt("trangthai"));
                listNhanVien.add(nhanVienDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu nhân viên");
        }
        return listNhanVien;
    }

    public ArrayList<NhanVienDTO> getNhanVienChuaCoTK() {
        ArrayList<NhanVienDTO> listNhanVien = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM nhanvien WHERE trangthai = 1 AND manv NOT IN (SELECT manv FROM taikhoan)";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setManv(rs.getInt("manv"));
                nhanVienDTO.setHoten(rs.getString("hoten"));
                nhanVienDTO.setGioitinh(rs.getInt("gioitinh"));
                nhanVienDTO.setNgaysinh(rs.getDate("ngaysinh"));
                nhanVienDTO.setSdt(rs.getString("sdt"));
                nhanVienDTO.setEmail(rs.getString("email"));
                nhanVienDTO.setTrangthai(rs.getInt("trangthai"));
                listNhanVien.add(nhanVienDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu nhân viên");
        }
        return listNhanVien;
    }

    public boolean themNhanVien(NhanVienDTO nhanVienDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO nhanvien (hoten, gioitinh, ngaysinh, sdt, email, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            // Chuyển đổi java.util.Date thành chuỗi
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(nhanVienDTO.getNgaysinh());
            // Tạo đối tượng java.sql.Date từ chuỗi đã định dạng
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
            pst = connection.prepareStatement(query);
            System.out.println(nhanVienDTO.getHoten());
            pst.setString(1, nhanVienDTO.getHoten());
            pst.setInt(2, nhanVienDTO.getGioitinh());
            pst.setDate(3, sqlDate);
            pst.setString(4, nhanVienDTO.getSdt());
            pst.setString(5, nhanVienDTO.getEmail());
            pst.setInt(6, nhanVienDTO.getTrangthai());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public boolean xoaNhanVien(int t) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE `nhanvien` SET `trangthai`= 0 WHERE manv = ?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thanhCong;
    }

    public boolean suaNhanVien(NhanVienDTO nhanVienDTO) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String query = "UPDATE nhanvien SET hoten=?, gioitinh=?, ngaysinh=?, sdt=?, email=?, trangthai=? WHERE manv=?";
            // Chuyển đổi java.util.Date thành chuỗi
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(nhanVienDTO.getNgaysinh());
            // Tạo đối tượng java.sql.Date từ chuỗi đã định dạng
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
            pst = connection.prepareStatement(query);
            pst.setString(1, nhanVienDTO.getHoten());
            pst.setInt(2, nhanVienDTO.getGioitinh());
            pst.setDate(3, sqlDate);
            pst.setString(4, nhanVienDTO.getSdt());
            pst.setString(5, nhanVienDTO.getEmail());
            pst.setInt(6, nhanVienDTO.getTrangthai());
            pst.setInt(7, nhanVienDTO.getManv());

            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public NhanVienDTO selectById(int manv) {
        NhanVienDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, manv);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("manv");
                String tennv = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                Date ngaysinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                result = new NhanVienDTO(madm, tennv, gioitinh, ngaysinh, sdt, email, trangthai);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    public NhanVienDTO selectNhanVienByMaNV(int manv) {
        NhanVienDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, manv);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int maNV = rs.getInt("manv");
                String hoten = rs.getString("hoten");
                int gioitinh = rs.getInt("gioitinh");
                Date ngaySinh = rs.getDate("ngaysinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                int trangthai = rs.getInt("trangthai");
                result = new NhanVienDTO(maNV, hoten, gioitinh, ngaySinh, sdt, trangthai, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(connection);
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
