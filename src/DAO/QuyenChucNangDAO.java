package DAO;

import DTO.QuyenChucNangDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuyenChucNangDAO {

    private Connection connection;
    private PreparedStatement ps;

    public static QuyenChucNangDAO getInstance() {
        return new QuyenChucNangDAO();
    }

    public void addQuyenChucNang(QuyenChucNangDTO quyenChucNang) throws SQLException {
        String sql = "INSERT INTO quyenchucnang (machucnang, tenchucnang, trangthai) VALUES (?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, quyenChucNang.getMaChucNang());
            ps.setString(2, quyenChucNang.getTenChucNang());
            ps.setInt(3, quyenChucNang.getTrangThai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            closeResources();
        }
    }

    public void updateQuyenChucNang(QuyenChucNangDTO quyenChucNang) throws SQLException {
        String sql = "UPDATE quyenchucnang SET tenchucnang = ?, trangthai = ? WHERE machucnang = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, quyenChucNang.getTenChucNang());
            ps.setInt(2, quyenChucNang.getTrangThai());
            ps.setString(3, quyenChucNang.getMaChucNang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            closeResources();
        }
    }

    public void deleteQuyenChucNang(String maChucNang) throws SQLException {
        String sql = "DELETE FROM quyenchucnang WHERE machucnang = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, maChucNang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            closeResources();
        }
    }

    public QuyenChucNangDTO getQuyenChucNang(String maChucNang) throws SQLException {
        String sql = "SELECT * FROM quyenchucnang WHERE machucnang = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, maChucNang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new QuyenChucNangDTO(
                        rs.getString("machucnang"),
                        rs.getString("tenchucnang"),
                        rs.getInt("trangthai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            closeResources();
        }
        return null;
    }

    public ArrayList<QuyenChucNangDTO> getAllQuyenChucNang() throws SQLException {
        ArrayList<QuyenChucNangDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM quyenchucnang";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new QuyenChucNangDTO(
                        rs.getString("machucnang"),
                        rs.getString("tenchucnang"),
                        rs.getInt("trangthai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            closeResources();
        }
        return list;
    }

    // Phương thức đóng tài nguyên
    private void closeResources() {
        try {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi đóng tài nguyên
        }
    }
}
