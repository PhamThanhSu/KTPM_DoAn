package DAO;

import DTO.GiamGiaDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class GiamGiaDAO {

    private Connection conn;

    // Constructor để khởi tạo kết nối cơ sở dữ liệu
    public GiamGiaDAO() {
        conn = (Connection) MySQLConnection.getConnection();
    }

    // Phương thức thêm một mã giảm giá
    public boolean insertGiamGia(GiamGiaDTO giamGia) {
        String sql = "INSERT INTO giamgia (magiamgia, tengiamgia, phantramgiam, giatrihoadon, ngaybatdau, ngayketthuc, trangthai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Bổ sung magiamgia vào câu lệnh INSERT
            pstmt.setInt(1, giamGia.getMagiamgia()); 
            pstmt.setString(2, giamGia.getTengiamgia());
            pstmt.setInt(3, giamGia.getPhantramgiam());
            pstmt.setLong(4, giamGia.getGiatrihoadon());
            // Chuyển đổi từ java.util.Date sang java.sql.Date
            pstmt.setDate(5, new java.sql.Date(giamGia.getNgaybatdau().getTime()));
            pstmt.setDate(6, new java.sql.Date(giamGia.getNgayketthuc().getTime()));
            pstmt.setString(7, giamGia.getTrangthai());

            return pstmt.executeUpdate() > 0;  // Nếu có ít nhất 1 dòng bị ảnh hưởng, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy tất cả mã giảm giá
    public ArrayList<GiamGiaDTO> getAllGiamGia() throws SQLException {
        ArrayList<GiamGiaDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM giamgia";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                GiamGiaDTO giamGia = new GiamGiaDTO();
                giamGia.setMagiamgia(rs.getInt("magiamgia"));
                giamGia.setTengiamgia(rs.getString("tengiamgia"));
                giamGia.setPhantramgiam(rs.getInt("phantramgiam"));
                giamGia.setGiatrihoadon(rs.getLong("giatrihoadon"));
                giamGia.setNgaybatdau(rs.getDate("ngaybatdau"));
                giamGia.setNgayketthuc(rs.getDate("ngayketthuc"));
                giamGia.setTrangthai(rs.getString("trangthai"));
                list.add(giamGia);
            }
        }
        return list;
    }
    //Lấy ra giảm giá hợp lệ trong khoảng thời gian hiện tại
    public ArrayList<GiamGiaDTO> getGiamGiaTuNgayHienTai() throws SQLException {
    ArrayList<GiamGiaDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM giamgia WHERE ngaybatdau <= CURDATE()"; // CURDATE() lấy ngày hiện tại (chỉ ngày, không giờ)
    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            GiamGiaDTO giamGia = new GiamGiaDTO();
            giamGia.setMagiamgia(rs.getInt("magiamgia"));
            giamGia.setTengiamgia(rs.getString("tengiamgia"));
            giamGia.setPhantramgiam(rs.getInt("phantramgiam"));
            giamGia.setGiatrihoadon(rs.getLong("giatrihoadon"));
            giamGia.setNgaybatdau(rs.getDate("ngaybatdau"));
            giamGia.setNgayketthuc(rs.getDate("ngayketthuc"));
            giamGia.setTrangthai(rs.getString("trangthai"));
            list.add(giamGia);
        }
    }
    return list;
}


    public GiamGiaDTO SelectGiamGiabyID(int magiamgia) throws SQLException {
        String sql = "SELECT * FROM giamgia WHERE magiamgia = ?";
        GiamGiaDTO giamGia = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, magiamgia);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    giamGia = new GiamGiaDTO();
                    giamGia.setMagiamgia(rs.getInt("magiamgia"));
                    giamGia.setTengiamgia(rs.getString("tengiamgia"));
                    giamGia.setPhantramgiam(rs.getInt("phantramgiam"));
                    giamGia.setGiatrihoadon(rs.getLong("giatrihoadon"));
                    giamGia.setNgaybatdau(rs.getDate("ngaybatdau"));
                    giamGia.setNgayketthuc(rs.getDate("ngayketthuc"));
                    giamGia.setTrangthai(rs.getString("trangthai"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giamGia;
    }

    // Phương thức cập nhật mã giảm giá
    public boolean updateGiamGia(GiamGiaDTO giamGia) throws SQLException {
        String sql = "UPDATE giamgia SET tengiamgia = ?, phantramgiam = ?, giatrihoadon = ?, trangthai = ? WHERE magiamgia = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, giamGia.getTengiamgia());
            pstmt.setInt(2, giamGia.getPhantramgiam());
            pstmt.setLong(3, giamGia.getGiatrihoadon());
            // Chuyển đổi từ java.util.Date sang java.sql.Date
            pstmt.setString(4, giamGia.getTrangthai());
            pstmt.setInt(5, giamGia.getMagiamgia());
            return pstmt.executeUpdate() > 0;
        }
    }

    // Phương thức xóa mã giảm giá
    public boolean deleteGiamGia(int magiamgia) throws SQLException {
        String sql = "DELETE FROM giamgia WHERE magiamgia = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, magiamgia);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Hàm lấy tổng số mã giảm giá trong cơ sở dữ liệu
    public int getTotalGiamGia() throws SQLException {
        String query = "SELECT COUNT(*) FROM giamgia";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public int getMaxGiamGiaId() throws SQLException {
    String query = "SELECT MAX(magiamgia) FROM giamgia";
    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            return rs.getInt(1); // Lấy giá trị mã giảm giá lớn nhất
        }
    }
    return 0; // Trả về 0 nếu bảng không có bản ghi
}


    //Kiểm tra mã giảm giá có tồn tại không
    public boolean isValidMagiamgia(int magiamgia) {
        String sql = "SELECT COUNT(*) FROM giamgia WHERE magiamgia = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, magiamgia);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Mã giảm giá hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
