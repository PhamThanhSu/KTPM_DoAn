package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.logging.Level;

public class PhieuXuatDAO {

    private Connection connection;
    private PreparedStatement ps;

    public static PhieuXuatDAO getInstance() {
        return new PhieuXuatDAO();
    }

    public PhieuXuatDAO() {
        connection = MySQLConnection.getConnection();
    }

    // Phương thức để lấy thời gian hiện tại
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public void insertPhieuXuat(PhieuXuatDTO phieuXuatDTO, long now) {
        try {
            Timestamp currentTime = new Timestamp(now); // Tạo đối tượng Timestamp từ giá trị now
            // Chèn dữ liệu vào cơ sở dữ liệu
            String sql = "INSERT INTO phieuxuat (maphieuxuat, thoigian, makh, manv, tongtien, trangthai) VALUES (?, ?, ?, ?, ?, 1)";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, phieuXuatDTO.getMaphieuxuat());
            ps.setTimestamp(2, currentTime); // Sử dụng currentTime thay vì Timestamp.valueOf(currentTime)
            ps.setInt(3, phieuXuatDTO.getMakh());
            ps.setInt(4, phieuXuatDTO.getManv());
            ps.setLong(5, phieuXuatDTO.getTongTien());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc thông báo lỗi theo nhu cầu của bạn
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<PhieuXuatDTO> getAllPhieuXuat() {
        ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
        String sql = "SELECT * FROM phieuxuat WHERE trangthai = 1 ORDER BY maphieuxuat DESC";
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuXuatDTO phieuXuatDTO = new PhieuXuatDTO();
                phieuXuatDTO.setMaphieuxuat(rs.getInt("maphieuxuat"));
                // Lấy thời gian từ ResultSet dưới dạng long
                long timeInMillis = rs.getTimestamp("thoigian").getTime();
                // Tạo đối tượng java.sql.Timestamp từ thời gian dưới dạng long
                Timestamp time = new Timestamp(timeInMillis);
                phieuXuatDTO.setThoigiantao(time);

                phieuXuatDTO.setMakh(rs.getInt("makh"));
                phieuXuatDTO.setManv(rs.getInt("manv"));
                phieuXuatDTO.setTongTien(rs.getLong("tongtien"));
                listPhieuXuat.add(phieuXuatDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc thông báo lỗi theo nhu cầu của bạn
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPhieuXuat;
    }

    public PhieuXuatDTO selectById(int mapx) {
        PhieuXuatDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM phieuxuat WHERE maphieuxuat=? AND trangthai = 1";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mapx);
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                int maphieuxuat = rs.getInt("maphieuxuat");
                long timeInMillis = rs.getTimestamp("thoigian").getTime();
                Timestamp time = new Timestamp(timeInMillis);
                int makh = rs.getInt("makh");
                int manv = rs.getInt("manv");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new PhieuXuatDTO(maphieuxuat, time, tongtien, manv, makh, trangthai);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    //Lẫy mã phiếu nhập
    public int getLatestMaPhieuXuat() {
        // Truy vấn cơ sở dữ liệu để lấy mã phiếu nhập lớn nhất đã được tạo
        String sql = "SELECT MAX(maphieuxuat) FROM phieuxuat WHERE trangthai = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public boolean DeletePhieuXuat(int mapx) {
        boolean thanhCong = false;
        PreparedStatement psUpdatePhieuXuat = null;
        PreparedStatement psUpdateChiTietPhieuXuat = null;

        try {
            connection = MySQLConnection.getConnection();

            // Cập nhật trạng thái chi tiết phiếu xuất thành 0
            String sqlUpdateChiTietPhieuXuat = "UPDATE ctphieuxuat SET trangthai = 0 WHERE maphieuxuat = ?";
            psUpdateChiTietPhieuXuat = connection.prepareStatement(sqlUpdateChiTietPhieuXuat);
            psUpdateChiTietPhieuXuat.setInt(1, mapx);
            int rowsUpdatedChiTiet = psUpdateChiTietPhieuXuat.executeUpdate();

            // Cập nhật trạng thái phiếu xuất thành 0
            String sqlUpdatePhieuXuat = "UPDATE phieuxuat SET trangthai = 0 WHERE maphieuxuat = ?";
            psUpdatePhieuXuat = connection.prepareStatement(sqlUpdatePhieuXuat);
            psUpdatePhieuXuat.setInt(1, mapx);
            int rowsUpdatedPhieuXuat = psUpdatePhieuXuat.executeUpdate();

            if (rowsUpdatedPhieuXuat > 0 && rowsUpdatedChiTiet > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return thanhCong;
    }
}
