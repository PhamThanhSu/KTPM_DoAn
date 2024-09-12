package DAO;

import DTO.ChiTietQuyenDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietQuyenDAO {
    private Connection connection;
    private PreparedStatement ps;

    public static ChiTietQuyenDAO getInstance() {
        return new ChiTietQuyenDAO();
    }

    public void addChiTietQuyen(ChiTietQuyenDTO chiTietQuyen) throws SQLException {
        String sql = "INSERT INTO ctquyen (manhomquyen, machucnang, quyen) VALUES (?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, chiTietQuyen.getMaNhomQuyen());
            ps.setString(2, chiTietQuyen.getMaChucNang());
            ps.setString(3, chiTietQuyen.getHanhDong());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public void updateChiTietQuyen(ChiTietQuyenDTO chiTietQuyen) throws SQLException {
        String sql = "UPDATE ctquyen SET quyen = ? WHERE manhomquyen = ? AND machucnang = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, chiTietQuyen.getHanhDong());
            ps.setString(2, chiTietQuyen.getMaNhomQuyen());
            ps.setString(3, chiTietQuyen.getMaChucNang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public void deleteChiTietQuyen(String maNhomQuyen, String maChucNang) throws SQLException {
        String sql = "DELETE FROM ctquyen WHERE manhomquyen = ? AND machucnang = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, maNhomQuyen);
            ps.setString(2, maChucNang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public ArrayList<ChiTietQuyenDTO> getChiTietQuyen(String maNhomQuyen) throws SQLException {
        ArrayList<ChiTietQuyenDTO> dsQuyen = new ArrayList<>();
        String sql = "SELECT * FROM ctquyen WHERE manhomquyen = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNhomQuyen);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ChiTietQuyenDTO quyen = new ChiTietQuyenDTO(
                    rs.getString("manhomquyen"),
                    rs.getString("machucnang"),
                    rs.getString("hanhdong")
                );
                dsQuyen.add(quyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsQuyen;
    }

    public List<ChiTietQuyenDTO> getAllChiTietQuyen() throws SQLException {
        List<ChiTietQuyenDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ctquyen";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ChiTietQuyenDTO(
                        rs.getString("manhomquyen"),
                        rs.getString("machucnang"),
                        rs.getString("hanhdong")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return list;
    }
}
