package DAO;

import DTO.ChiTietQuyenDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietQuyenDAO {
    private Connection connection;

    public static ChiTietQuyenDAO getInstance() {
        return new ChiTietQuyenDAO();
    }

    public void addChiTietQuyen(ChiTietQuyenDTO chiTietQuyen) throws SQLException {
        String sql = "INSERT INTO ctquyen (manhomquyen, machucnang, hanhdong) VALUES (?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietQuyen.getMaNhomQuyen());
            ps.setString(2, chiTietQuyen.getMaChucNang());
            ps.setString(3, chiTietQuyen.getHanhDong());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        }
    }

    public void updateChiTietQuyen(ChiTietQuyenDTO chiTietQuyen) throws SQLException {
        String sql = "UPDATE ctquyen SET hanhdong = ? WHERE manhomquyen = ? AND machucnang = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, chiTietQuyen.getHanhDong());
            ps.setInt(2, chiTietQuyen.getMaNhomQuyen());
            ps.setString(3, chiTietQuyen.getMaChucNang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        }
    }

    public void deleteChiTietQuyen(String maNhomQuyen, String maChucNang) throws SQLException {
        String sql = "DELETE FROM ctquyen WHERE manhomquyen = ? AND machucnang = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNhomQuyen);
            ps.setString(2, maChucNang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        }
    }
    
    public void deleteAllChiTietQuyen(int maNhomQuyen) throws SQLException {
        String sql = "DELETE FROM ctquyen WHERE manhomquyen = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, maNhomQuyen);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
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
                    rs.getInt("manhomquyen"),
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

    public ArrayList<ChiTietQuyenDTO> getAllChiTietQuyen() throws SQLException {
        ArrayList<ChiTietQuyenDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ctquyen";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ChiTietQuyenDTO(
                        rs.getInt("manhomquyen"),
                        rs.getString("machucnang"),
                        rs.getString("hanhdong")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi SQL
        }
        return list;
    }

    // Phương thức kiểm tra quyền tồn tại
    public boolean isChiTietQuyenExist(int maNhomQuyen, String maChucNang, String hanhDong) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ctquyen WHERE manhomquyen = ? AND machucnang = ? AND hanhdong = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, maNhomQuyen);
            ps.setString(2, maChucNang);
            ps.setString(3, hanhDong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu quyền tồn tại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu quyền không tồn tại
    }
}
