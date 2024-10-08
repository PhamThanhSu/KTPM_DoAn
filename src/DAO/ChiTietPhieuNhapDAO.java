package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ChiTietPhieuNhapDAO {

    Connection connection;
    PreparedStatement ps;

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    public ChiTietPhieuNhapDAO() {

    }

    // Hàm thêm chi tiết phiếu nhập bao gồm giá xuất
    // Hàm thêm chi tiết phiếu nhập bao gồm giá xuất
    public void insert(ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList) {
        connection = MySQLConnection.getConnection();
        String sql = "INSERT INTO `ctphieunhap`(`maphieunhap`, `masp`, `soluong`, `gianhap`, `giaxuat`, `soluongconlai`) VALUES (?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            for (ChiTietPhieuNhapDTO chiTiet : chiTietPhieuNhapList) {
                if (chiTiet != null) {
                    ps.setInt(1, chiTiet.getMaphieunhap());
                    ps.setInt(2, chiTiet.getMasp());
                    ps.setInt(3, chiTiet.getSoluong());
                    ps.setInt(4, chiTiet.getGianhap());
                    ps.setInt(5, chiTiet.getGiaxuat());
                    ps.setInt(6, chiTiet.getSoluong()); // Gán số lượng nhập ban đầu bằng số lượng còn lại
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL");
        } finally {
            // Đóng kết nối
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateSoluongton(int masp, int soluong) {
        SanPhamDTO sanpham = new SanPhamDTO();
        int soluongton = sanpham.getSoluongton() + soluong;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE sanpham SET soluongton = soluongton + ? WHERE masp = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, soluongton);
            ps.setInt(2, masp);
            ps.executeUpdate(); // Thực hiện cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, t);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                int masp = rs.getInt("masp");
                int gianhap = rs.getInt("gianhap");
                int soluong = rs.getInt("soluong");
                int giaxuat = rs.getInt("giaxuat");
                int soluongconlai = rs.getInt("soluongconlai"); // Lấy soluongconlai từ kết quả
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp, soluong, gianhap, giaxuat, soluongconlai);
                result.add(ctphieu);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage());
        } finally {
            // Đóng kết nối
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Các hàm khác như selectByID, selectByMASP, getAllChiTietPhieuNhap cũng tương tự
    public ChiTietPhieuNhapDTO selectByID(int mapn) {
        ChiTietPhieuNhapDTO result = null;
        connection = MySQLConnection.getConnection();
        String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mapn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maphieunhap = rs.getInt("maphieunhap");
                int masp = rs.getInt("masp");
                int soluong = rs.getInt("soluong");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongconlai = rs.getInt("soluongconlai"); // Lấy soluongconlai
                result = new ChiTietPhieuNhapDTO(maphieunhap, masp, soluong, gianhap, giaxuat, soluongconlai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ChiTietPhieuNhapDTO selectByMASP(int masp) {
        ChiTietPhieuNhapDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM ctphieunhap WHERE masp=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, masp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maphieunhap = rs.getInt("maphieunhap");
                int masanpham = rs.getInt("masp");
                int soluong = rs.getInt("soluong");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                int soluongconlai = rs.getInt("soluongconlai");
                result = new ChiTietPhieuNhapDTO(maphieunhap, masanpham, soluong, gianhap, giaxuat, soluongconlai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Hàm lấy tất cả chi tiết phiếu nhập theo maphieunhap, bao gồm giá xuất
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap(int mapn) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mapn);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int maphieu = rs.getInt("maphieunhap");
                    int masp = rs.getInt("masp");
                    int gianhap = rs.getInt("gianhap");
                    int soluong = rs.getInt("soluong");
                    int giaxuat = rs.getInt("giaxuat");
                    int soluongconlai = rs.getInt("soluongconlai");

                    // Tạo đối tượng ChiTietPhieuNhapDTO và thêm vào danh sách kết quả
                    ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp, soluong, gianhap, giaxuat, soluongconlai);
                    result.add(ctphieu);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu phiếu nhập: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Đảm bảo kết nối được đóng
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

//    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
//        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
//        connection = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection
//        String sql = "SELECT * FROM ctphieunhap";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int maphieu = rs.getInt("maphieunhap");
//                int masp = rs.getInt("masp");
//                int gianhap = rs.getInt("gianhap");
//                int soluong = rs.getInt("soluong");
//                int giaxuat = rs.getInt("giaxuat"); // Lấy giá xuất từ kết quả
//                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp, soluong, gianhap, giaxuat);
//                result.add(ctphieu);
//                System.out.println(result);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi SQL");
//            ex.printStackTrace(); // In ra lỗi
//        }
//        return result;
//    }
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = MySQLConnection.getConnection(); // Lấy kết nối từ lớp MySQLConnection

            // Truy vấn SQL với điều kiện số lượng còn lại lớn hơn 0
            String sql = "SELECT ct.maphieunhap, ct.masp, ct.gianhap, ct.soluong, ct.giaxuat, ct.soluongconlai "
                    + "FROM ctphieunhap ct "
                    + "JOIN phieunhap pn ON ct.maphieunhap = pn.maphieunhap "
                    + "WHERE ct.soluongconlai > 0 "
                    + "AND ct.maphieunhap = ( "
                    + "    SELECT MIN(ct2.maphieunhap) "
                    + "    FROM ctphieunhap ct2 "
                    + "    JOIN phieunhap pn2 ON ct2.maphieunhap = pn2.maphieunhap "
                    + "    WHERE ct2.masp = ct.masp "
                    + "    AND ct2.soluongconlai > 0 "
                    + ") "
                    + "ORDER BY ct.masp, pn.thoigian";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int maphieu = rs.getInt("maphieunhap");
                    int masp = rs.getInt("masp");
                    int gianhap = rs.getInt("gianhap");
                    int soluong = rs.getInt("soluong");
                    int giaxuat = rs.getInt("giaxuat");
                    int soluongconlai = rs.getInt("soluongconlai");

                    // Tạo đối tượng ChiTietPhieuNhapDTO và thêm vào danh sách kết quả
                    ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp, soluong, gianhap, giaxuat, soluongconlai);
                    result.add(ctphieu);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu phiếu nhập: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Đảm bảo kết nối được đóng sau khi hoàn tất
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public void updateSoluongCTPN(int masp, int soluong, int maphieunhap) {
        connection = MySQLConnection.getConnection();
        try {
            String sql = "UPDATE ctphieunhap SET soluongconlai = soluongconlai + ? WHERE masp = ? AND maphieunhap = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, soluong);
                ps.setInt(2, masp);
                ps.setInt(3, maphieunhap);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
