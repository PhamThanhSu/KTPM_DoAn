package DAO;

import DTO.GiamGiaSanPhamDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiamGiaSanPhamDAO {

    private Connection connection;
    private PreparedStatement ps;

    public static GiamGiaSanPhamDAO getInstance() {
        return new GiamGiaSanPhamDAO();
    }

    // Insert a new discount-product association
    public boolean insertGiamGiaSanPham(GiamGiaSanPhamDTO giamGiaSanPhamDTO) {
        String sql = "INSERT INTO giamgiasanpham (magiamgia, masp) VALUES (?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, giamGiaSanPhamDTO.getMagiamgia());
            ps.setInt(2, giamGiaSanPhamDTO.getMasp());
            int rowsAffected = ps.executeUpdate();
            MySQLConnection.closeConnection(connection);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSanPhamApDungGiamGia(int magiamgia, List<GiamGiaSanPhamDTO> giamGiaSanPhamDTOList) {
        String sqlDelete = "DELETE FROM giamgiasanpham WHERE magiamgia = ?";
        String sqlInsert = "INSERT INTO giamgiasanpham (magiamgia, masp) VALUES (?, ?)";

        try {
            connection = MySQLConnection.getConnection();
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Xóa tất cả sản phẩm cũ liên quan đến mã giảm giá
            try (PreparedStatement deleteStmt = connection.prepareStatement(sqlDelete)) {
                deleteStmt.setInt(1, magiamgia);
                deleteStmt.executeUpdate();
            }

            // Thêm các sản phẩm mới vào bảng
            try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsert)) {
                for (GiamGiaSanPhamDTO dto : giamGiaSanPhamDTOList) {
                    insertStmt.setInt(1, magiamgia); // Sử dụng mã giảm giá từ tham số
                    insertStmt.setInt(2, dto.getMasp()); // Mã sản phẩm
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch(); // Thực thi batch các câu lệnh insert
            }

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu xảy ra lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Khôi phục trạng thái auto-commit
                    MySQLConnection.closeConnection(connection); // Đóng kết nối
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    // Retrieve all discount-product associations
    public List<GiamGiaSanPhamDTO> getAllGiamGiaSanPham() {
        List<GiamGiaSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT magiamgia, masp FROM giamgiasanpham";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaSanPhamDTO giamGiaSanPham = new GiamGiaSanPhamDTO(
                        rs.getInt("magiamgia"),
                        rs.getInt("masp")
                );
                list.add(giamGiaSanPham);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<GiamGiaSanPhamDTO> selectGiamGiaSPByID(int magiamgia) {
        ArrayList<GiamGiaSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM giamgiasanpham WHERE magiamgia = ?";

        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, magiamgia); // Gán giá trị cho tham số magiamgia
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GiamGiaSanPhamDTO giamGiaSanPham = new GiamGiaSanPhamDTO(
                        rs.getInt("magiamgia"),
                        rs.getInt("masp")
                );
                list.add(giamGiaSanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    MySQLConnection.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // Delete a discount-product association by discount ID and product ID
    public boolean deleteGiamGiaSanPham(int magiamgia, int masp) {
        String sql = "DELETE FROM giamgiasanpham WHERE magiamgia = ? AND masp = ?";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, magiamgia);
            ps.setInt(2, masp);
            int rowsAffected = ps.executeUpdate();
            MySQLConnection.closeConnection(connection);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteGiamGiaSanPham(int magiamgia) {
        String sql = "DELETE FROM giamgiasanpham WHERE magiamgia = ? ";
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, magiamgia);
            int rowsAffected = ps.executeUpdate();
            MySQLConnection.closeConnection(connection);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
