package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import config.MySQLConnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuNhapDAO {

    private Connection connection;
    private PreparedStatement ps;

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }

    // Phương thức để lấy thời gian hiện tại
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public void insertPhieuNhap(PhieuNhapDTO phieuNhapDTO, long now) throws ParseException {
        try {
            connection = MySQLConnection.getConnection();
            Date currentTime = new Date(now); // Tạo đối tượng Date từ giá trị now
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = dateFormat.format(currentTime);
            System.out.println(formattedDateTime);
            Date parsedDate = dateFormat.parse(formattedDateTime);
            System.out.println("long time" + parsedDate);
            long timestampLong = 0;
            try {
                System.out.println(formattedDateTime);
                timestampLong = dateFormat.parse(formattedDateTime).getTime(); // Chuyển đổi thành số long
                System.out.println("long time" + timestampLong);
            } catch (ParseException ex) {
                Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("long time" + timestampLong);
//        // Tạo đối tượng Timestamp từ số long
//        Timestamp timestamp = new Timestamp(timestampLong);
//        Date parsedDate = dateFormat.parse(formattedDateTime);
//        Timestamp timestamp = new Timestamp(parsedDate.getTime());
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            // Chèn dữ liệu vào cơ sở dữ liệu
            System.out.println("long time" + timestamp);
            String sql = "INSERT INTO phieunhap (maphieunhap, thoigian, manhacungcap, manv, tongtien, trangthai) VALUES (?, ?, ?, ?, ?, 1)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, phieuNhapDTO.getMaphieunhap());
            ps.setTimestamp(2, phieuNhapDTO.getThoigiantao()); // Sử dụng thời gian đã định dạng thành chuỗi
            ps.setInt(3, phieuNhapDTO.getManhacungcap());
            ps.setInt(4, phieuNhapDTO.getMaNV());
            ps.setLong(5, phieuNhapDTO.getTongTien());
            ps.executeUpdate();

            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc thông báo lỗi theo nhu cầu của bạn
        }
    }

    public ArrayList<PhieuNhapDTO> getAllPhieuNhap() {
        ArrayList<PhieuNhapDTO> listPhieuNhap = new ArrayList<>();
        String sql = "SELECT maphieunhap, thoigian, manhacungcap, manv, tongtien FROM phieunhap WHERE trangthai = 1 ORDER BY maphieunhap DESC"; // Lấy những phiếu nhập có trạng thái là 1
        try {
            connection = MySQLConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO();
                phieuNhapDTO.setMaphieunhap(rs.getInt("maphieunhap"));
//                // Lấy thời gian từ ResultSet dưới dạng long
//                long timeInMillis = rs.getTimestamp("thoigian").getTime();
//                // Tạo đối tượng java.sql.Timestamp từ thời gian dưới dạng long
//                Timestamp timestamp = new Timestamp(timeInMillis);
                phieuNhapDTO.setThoigiantao(rs.getTimestamp("thoigian"));
                phieuNhapDTO.setManhacungcap(rs.getInt("manhacungcap"));
                phieuNhapDTO.setMaNV(rs.getInt("manv"));
                phieuNhapDTO.setTongTien(rs.getLong("tongtien"));
                listPhieuNhap.add(phieuNhapDTO);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý hoặc thông báo lỗi theo nhu cầu của bạn
        }
        return listPhieuNhap;
    }

    public PhieuNhapDTO selectById(int mapn) {
        PhieuNhapDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE maphieunhap=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mapn);
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                int maphieunhap = rs.getInt("maphieunhap");
                long timeInMillis = rs.getTimestamp("thoigian").getTime();
                // Tạo đối tượng java.sql.Timestamp từ thời gian dưới dạng long
                Timestamp timestamp = new Timestamp(timeInMillis);
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("manv");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new PhieuNhapDTO(maphieunhap, timestamp, mancc, nguoitao, tongtien, trangthai);
                MySQLConnection.closeConnection(connection);
            }

        } catch (SQLException e) {
        }
        return result;
    }

    //Lẫy mã phiếu nhập
    public int getLatestMaPhieuNhap() {
        connection = MySQLConnection.getConnection();
        // Truy vấn cơ sở dữ liệu để lấy mã phiếu nhập lớn nhất đã được tạo
        String sql = "SELECT MAX(maphieunhap) FROM phieunhap";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public boolean DeletePhieuNhap(int mapn) {
        boolean thanhCong = false;
        Connection connection = null;
        PreparedStatement psDeletePhieuNhap = null;
        PreparedStatement psDeleteChiTietPhieuNhap = null;

        try {
            connection = MySQLConnection.getConnection();

            // Lấy danh sách các sản phẩm trong phiếu nhập
            ArrayList<ChiTietPhieuNhapDTO> listPn = ChiTietPhieuNhapDAO.getInstance().selectAll(Integer.toString(mapn));

            // Xóa chi tiết phiếu nhập
            String sqlDeleteChiTietPhieuNhap = "DELETE FROM ctphieunhap WHERE maphieunhap = ?";
            psDeleteChiTietPhieuNhap = connection.prepareStatement(sqlDeleteChiTietPhieuNhap);
            psDeleteChiTietPhieuNhap.setInt(1, mapn);
            int rowsDeletedChiTiet = psDeleteChiTietPhieuNhap.executeUpdate();

            // Xóa phiếu nhập
            String sqlDeletePhieuNhap = "DELETE FROM phieunhap WHERE maphieunhap = ?";
            psDeletePhieuNhap = connection.prepareStatement(sqlDeletePhieuNhap);
            psDeletePhieuNhap.setInt(1, mapn);
            int rowsDeletedPhieuNhap = psDeletePhieuNhap.executeUpdate();

            if (rowsDeletedPhieuNhap > 0 && rowsDeletedChiTiet > 0) {
                // Cập nhật số lượng tồn của các sản phẩm
                for (ChiTietPhieuNhapDTO chiTiet : listPn) {
                    ChiTietPhieuNhapDAO.getInstance().updateSoluongton(chiTiet.getMasp(), -chiTiet.getSoluong());
                }
                thanhCong = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return thanhCong;
    }

}
