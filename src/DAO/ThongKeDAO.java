package DAO;

import DTO.ThongKe.ThongKeDoanhThuDTO; //tk theo nam
import DTO.ThongKe.ThongKeTheoThangDTO; //tk theo thang
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNccDTO;
import DTO.ThongKeSanPhamBanChayDTO;
import DTO.ThongKeTonKhoDTO;
import DTO.ThongKeTrongThangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import config.MySQLConnection;
import java.time.LocalDate;

public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public ArrayList<ThongKeTrongThangDTO> getThongKe7NgayGanNhat() {
        ArrayList<ThongKeTrongThangDTO> result = new ArrayList<>();
        try (Connection con = MySQLConnection.getConnection()) {
            // Lấy ngày hiện tại
            LocalDate ngayHienTai = LocalDate.now();
            System.out.println(ngayHienTai);
            // Lấy ngày bắt đầu là ngày hiện tại và sau đó lùi lại 6 ngày để lấy tổng cộng 7 ngày
            //LocalDate ngayBatDau = ngayHienTai.minusDays(7);

            // SQL query
            String sql = "WITH RECURSIVE dates AS (\n"
                    + "    SELECT CURDATE() - INTERVAL 7 DAY AS date\n"
                    + "    UNION ALL\n"
                    + "    SELECT date + INTERVAL 1 DAY\n"
                    + "    FROM dates\n"
                    + "    WHERE date + INTERVAL 1 DAY < CURDATE() -- Không bao gồm ngày hiện tại\n"
                    + ")\n"
                    + "SELECT \n"
                    + "    dates.date AS ngay, \n"
                    + "    COALESCE(SUM(ctphieuxuat.giaxuat * ctphieuxuat.soluong), 0) AS doanhthu,\n"
                    + "    COALESCE(SUM(ctphieuxuat.gianhap * ctphieuxuat.soluong), 0) AS chiphi\n"
                    + "FROM dates\n"
                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "GROUP BY dates.date\n"
                    + "HAVING doanhthu > 0 -- Chỉ hiển thị những ngày có doanh thu\n"
                    + "ORDER BY dates.date;";
            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setDate(1, java.sql.Date.valueOf(ngayBatDau));
//            pst.setDate(2, java.sql.Date.valueOf(ngayHienTai));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTrongThangDTO thongKeDTO = new ThongKeTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(thongKeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý exception khi đóng kết nối
        }

        return result;
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getTop5SanPhamBanChay() {
        Connection con = MySQLConnection.getConnection();
        ArrayList<ThongKeSanPhamBanChayDTO> listThongSP = new ArrayList<>();
        String sql = "SELECT sp.masp, sp.tensp, SUM(ct.soluong) AS soluong_ban "
                + "FROM sanpham sp, ctphieuxuat ct "
                + "WHERE sp.masp = ct.masp "
                + "GROUP BY sp.tensp "
                + "ORDER BY soluong_ban DESC ";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String tensp = rs.getString("tensp");
                int soluongBan = rs.getInt("soluong_ban");

                ThongKeSanPhamBanChayDTO thongKeSanPhamBanChayDTO = new ThongKeSanPhamBanChayDTO(tensp, soluongBan);
                listThongSP.add(thongKeSanPhamBanChayDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông báo lỗi
            // Ghi log hoặc xử lý exception theo nhu cầu của bạn
        } finally {
            // Đóng kết nối sau khi sử dụng xong
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý exception khi đóng kết nối
            }
        }
        return listThongSP;
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getListSanPhamBanChay() {
        Connection con = MySQLConnection.getConnection();
        ArrayList<ThongKeSanPhamBanChayDTO> listThongSP = new ArrayList<>();
        String sql = "SELECT sp.masp, sp.tensp, SUM(ct.soluong) AS soluong_ban "
                + "FROM sanpham sp "
                + "JOIN ctphieuxuat ct ON sp.masp = ct.masp "
                + "GROUP BY sp.tensp "
                + "ORDER BY soluong_ban DESC ";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String tensp = rs.getString("tensp");
                int soluongBan = rs.getInt("soluong_ban");

                ThongKeSanPhamBanChayDTO thongKeSanPhamBanChayDTO = new ThongKeSanPhamBanChayDTO(tensp, soluongBan);
                listThongSP.add(thongKeSanPhamBanChayDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông báo lỗi
            // Ghi log hoặc xử lý exception theo nhu cầu của bạn
        } finally {
            // Đóng kết nối sau khi sử dụng xong
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý exception khi đóng kết nối
            }
        }
        return listThongSP;
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getListSanPhamBanChay(int thang, int nam) {
        Connection con = MySQLConnection.getConnection();
        ArrayList<ThongKeSanPhamBanChayDTO> listThongSP = new ArrayList<>();
        String sql = "SELECT sp.masp, sp.tensp, SUM(ct.soluong) AS soluong_ban "
                + "FROM sanpham sp "
                + "JOIN ctphieuxuat ct ON sp.masp = ct.masp "
                + "JOIN phieuxuat px ON ct.maphieuxuat = px.maphieuxuat "
                + "WHERE MONTH(px.thoigian) = ? AND YEAR(px.thoigian) = ? "
                + "GROUP BY sp.tensp "
                + "ORDER BY soluong_ban DESC ";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, thang); // Thiết lập tháng
            pst.setInt(2, nam); // Thiết lập năm
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String tensp = rs.getString("tensp");
                int soluongBan = rs.getInt("soluong_ban");

                ThongKeSanPhamBanChayDTO thongKeSanPhamBanChayDTO = new ThongKeSanPhamBanChayDTO(tensp, soluongBan);
                listThongSP.add(thongKeSanPhamBanChayDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông báo lỗi
            // Ghi log hoặc xử lý exception theo nhu cầu của bạn
        } finally {
            // Đóng kết nối sau khi sử dụng xong
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý exception khi đóng kết nối
            }
        }
        return listThongSP;
    }

    public ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT khachhang.makh, khachhang.tenkhachhang, COUNT(phieuxuat.maphieuxuat) AS soluong, IFNULL(SUM(phieuxuat.tongtien), 0) AS total "
                    + "FROM khachhang, phieuxuat "
                    + "WHERE khachhang.makh = phieuxuat.makh AND phieuxuat.thoigian BETWEEN ? AND ? "
                    + "GROUP BY khachhang.makh, khachhang.tenkhachhang "
                    + "HAVING ( khachhang.tenkhachhang LIKE ? OR khachhang.makh LIKE ? ) AND soluong > 0";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("makh");
                String tenkh = rs.getString("tenkhachhang");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sqlSetStartYear = "SET @start_year = ?;";
            String sqlSetEndYear = "SET @end_year = ?;";
            String sqlSelect = """
                     WITH RECURSIVE years AS (
                         SELECT @start_year AS year -- Giá trị năm bắt đầu
                         UNION ALL
                         SELECT year + 1
                         FROM years
                         WHERE year + 1 <= @end_year -- Điều kiện để chạy đến năm kết thúc
                     )
                     SELECT 
                         years.year AS nam, 
                         COALESCE(SUM(ctphieuxuat.giaxuat * ctphieuxuat.soluong), 0) AS doanhthu,
                         COALESCE(SUM(ctphieuxuat.gianhap * ctphieuxuat.soluong), 0) AS chiphi
                     FROM years
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = years.year -- So sánh chính xác theo năm
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     GROUP BY years.year
                     HAVING doanhthu > 0 -- Chỉ hiển thị những năm có doanh thu
                     ORDER BY years.year;""";
            PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
            PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStartYear.setInt(1, year_start);
            pstEndYear.setInt(1, year_end);

            pstStartYear.execute();
            pstEndYear.execute();
            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                Long chiphi = rs.getLong("chiphi");
                Long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
//        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
//        try {
//            Connection con = MySQLConnection.getConnection();
//            String selected_year = "SET @nam = ?;";
//            String sql = """
//                     WITH RECURSIVE months AS (
//                         SELECT 1 AS month -- Bắt đầu từ tháng 1
//                         UNION ALL
//                         SELECT month + 1
//                         FROM months
//                         WHERE month + 1 <= 12 -- Tăng dần đến tháng 12
//                     )
//                     SELECT 
//                         CONCAT(months.month, '-', @selected_year) AS thang_nam, -- Kết hợp tháng và năm để hiển thị
//                         COALESCE(SUM(ctphieuxuat.giaxuat * ctphieuxuat.soluong), 0) AS doanhthu,
//                         COALESCE(SUM(ctphieuxuat.gianhap * ctphieuxuat.soluong), 0) AS chiphi
//                     FROM months
//                     LEFT JOIN phieuxuat ON MONTH(phieuxuat.thoigian) = months.month AND YEAR(phieuxuat.thoigian) = @selected_year -- So sánh tháng và năm
//                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
//                     GROUP BY months.month
//                     HAVING doanhthu > 0 -- Chỉ hiển thị những tháng có doanh thu
//                     ORDER BY months.month;""";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setInt(1, nam);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                int thang = rs.getInt("thang");
//                int chiphi = rs.getInt("chiphi");
//                int doanhthu = rs.getInt("doanhthu");
//                int loinhuan = doanhthu - chiphi;
//                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
//                result.add(thongke);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();

            String sql = """
                     WITH RECURSIVE months AS (
                         SELECT 1 AS month -- Bắt đầu từ tháng 1
                         UNION ALL
                         SELECT month + 1
                         FROM months
                         WHERE month + 1 <= 12 -- Tăng dần đến tháng 12
                     )
                     SELECT 
                         months.month AS thang, -- Chỉ lấy tháng
                         COALESCE(SUM(ctphieuxuat.giaxuat * ctphieuxuat.soluong), 0) AS doanhthu,
                         COALESCE(SUM(ctphieuxuat.gianhap * ctphieuxuat.soluong), 0) AS chiphi
                     FROM months
                     LEFT JOIN phieuxuat ON MONTH(phieuxuat.thoigian) = months.month AND YEAR(phieuxuat.thoigian) = ? -- Truyền năm vào
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     GROUP BY months.month
                     -- HAVING doanhthu > 0 chỉ những tháng có doanh thu mới hiện
                     ORDER BY months.month;""";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nam); // Truyền giá trị năm vào câu truy vấn
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeNccDTO> getThongKeNhaCungCap(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeNccDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT nhacungcap.manhacungcap, nhacungcap.tennhacungcap, COUNT(phieunhap.maphieunhap) AS soluong, IFNULL(SUM(phieunhap.tongtien), 0) AS total "
                    + "FROM nhacungcap, phieunhap "
                    + "WHERE nhacungcap.manhacungcap = phieunhap.manhacungcap AND phieunhap.thoigian BETWEEN ? AND ? "
                    + "GROUP BY nhacungcap.manhacungcap, nhacungcap.tennhacungcap "
                    + "HAVING ( nhacungcap.tennhacungcap LIKE ? OR nhacungcap.manhacungcap LIKE ? ) AND soluong > 0";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manhacungcap = rs.getInt("manhacungcap");
                String tennhacungcap = rs.getString("tennhacungcap");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeNccDTO x = new ThongKeNccDTO(manhacungcap, tennhacungcap, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String ngayString = nam + "-" + thang + "-" + "01";
            System.err.println("ngày tháng " + ngayString);
            String sql = "WITH RECURSIVE dates AS (\n"
                    + "    SELECT DATE('" + ngayString + "') AS date\n"
                    + "    UNION ALL\n"
                    + "    SELECT date + INTERVAL 1 DAY\n"
                    + "    FROM dates\n"
                    + "    WHERE date + INTERVAL 1 DAY <= LAST_DAY('" + ngayString + "')\n"
                    + ")\n"
                    + "SELECT \n"
                    + "    dates.date AS ngay, \n"
                    + "    COALESCE(SUM(ctphieuxuat.giaxuat * ctphieuxuat.soluong), 0) AS doanhthu,\n"
                    + "    COALESCE(SUM(ctphieuxuat.gianhap * ctphieuxuat.soluong), 0) AS chiphi\n"
                    + "FROM dates\n"
                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTrongThangDTO tn = new ThongKeTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public ArrayList<ThongKeTrongThangDTO> getThongKeTuNgayDenNgay(String star, String end) {
//        ArrayList<ThongKeTrongThangDTO> result = new ArrayList<>();
//        try {
//            Connection con = MySQLConnection.getConnection();
//
//            String setStar = "SET @start_date = '" + star + "'";
//            String setEnd = "SET @end_date = '" + end + "'  ;";
//
//            String dateDiffQuery = "SELECT DATEDIFF('" + end + "', '" + star + "') AS days_difference;";
//            PreparedStatement pstDiff = con.prepareStatement(dateDiffQuery);
//            ResultSet rsDiff = pstDiff.executeQuery();
//            int dateDiff = 0;
//            if (rsDiff.next()) {
//                dateDiff = rsDiff.getInt("days_difference");
//            }
//            rsDiff.close();
//            pstDiff.close();
//
//            String numbersQuery = "";
//            for (int i = 0; i <= dateDiff; i++) {
//                if (i > 0) {
//                    numbersQuery += " UNION ALL ";
//                }
//                numbersQuery += "SELECT DATE_ADD('" + star + "', INTERVAL " + i + " DAY) AS date";
//            }
//
//            String sqlSelect = "WITH RECURSIVE dates AS (\n"
//                    + numbersQuery.toString() + "\n"
//                    + ")\n"
//                    + "SELECT \n"
//                    + "    dates.date AS ngay, \n"
//                    + "    COALESCE(SUM(ctphieuxuat.soluong * ctphieuxuat.giaxuat), 0) AS doanhthu,\n"
//                    + "    COALESCE(SUM(ctphieuxuat.soluong * ctphieuxuat.gianhap), 0) AS chiphi\n"
//                    + "FROM \n"
//                    + "    dates\n"
//                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
//                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
//                    +" HAVING doanhthu > 0 chỉ những ngày có doanh thu mới hiện\n"
//                    + "GROUP BY dates.date\n"
//                    + "ORDER BY dates.date;";
//
//            PreparedStatement pstStart = con.prepareStatement(setStar);
//            PreparedStatement pstEnd = con.prepareStatement(setEnd);
//            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);
//
//            pstStart.execute();
//            pstEnd.execute();
//            ResultSet rs = pstSelect.executeQuery();
//            while (rs.next()) {
//                Date ngay = rs.getDate("ngay");
//                int chiphi = rs.getInt("chiphi");
//                int doanhthu = rs.getInt("doanhthu");
//                int loinhuan = doanhthu - chiphi;
//                ThongKeTrongThangDTO tn = new ThongKeTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
//                result.add(tn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public ArrayList<ThongKeTrongThangDTO> getThongKeTuNgayDenNgay(String start, String end) {
        ArrayList<ThongKeTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();

            // Tạo câu truy vấn SQL sử dụng WITH RECURSIVE để tạo dải ngày từ start đến end
            String sqlSelect = """
                WITH RECURSIVE dates AS (
                    SELECT ? AS date -- Ngày bắt đầu
                    UNION ALL
                    SELECT date + INTERVAL 1 DAY
                    FROM dates
                    WHERE date + INTERVAL 1 DAY <= ?
                )
                SELECT 
                    dates.date AS ngay, 
                    COALESCE(SUM(ctphieuxuat.soluong * ctphieuxuat.giaxuat), 0) AS doanhthu,
                    COALESCE(SUM(ctphieuxuat.soluong * ctphieuxuat.gianhap), 0) AS chiphi
                FROM 
                    dates
                LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date
                LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                GROUP BY dates.date
                HAVING doanhthu > 0 -- Chỉ những ngày có doanh thu mới hiển thị
                ORDER BY dates.date;
                """;

            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);
            pstSelect.setString(1, start);  // Truyền giá trị start vào
            pstSelect.setString(2, end);    // Truyền giá trị end vào

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTrongThangDTO tn = new ThongKeTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }

            rs.close();
            pstSelect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTonKhoDTO> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeTonKhoDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection conn = MySQLConnection.getConnection();
            String query
                    = """
                WITH nhap AS (
                    SELECT 
                        sanpham.masp, 
                        SUM(ctphieunhap.soluong) AS sl_nhap
                    FROM 
                        ctphieunhap
                    LEFT JOIN 
                        sanpham ON sanpham.masp = ctphieunhap.masp
                    LEFT JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                    WHERE 
                        phieunhap.thoigian BETWEEN ? AND ?
                    GROUP BY 
                        sanpham.masp
                ),
                xuat AS (
                    SELECT 
                        sanpham.masp, 
                        SUM(ctphieuxuat.soluong) AS sl_xuat
                    FROM 
                        ctphieuxuat
                    LEFT JOIN 
                        sanpham ON sanpham.masp = ctphieuxuat.masp
                    LEFT JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                    WHERE 
                        phieuxuat.thoigian BETWEEN ? AND ?
                    GROUP BY 
                        sanpham.masp
                ),
                temp_table AS (
                    SELECT 
                        sanpham.masp, 
                        sanpham.tensp, 
                        sanpham.size, 
                        COALESCE(nhap.sl_nhap, 0) AS soluongnhap, 
                        COALESCE(xuat.sl_xuat, 0) AS soluongxuat, 
                        COALESCE(sanpham.soluongton, 0) AS soluongton
                    FROM 
                        sanpham
                    LEFT JOIN nhap ON sanpham.masp = nhap.masp
                    LEFT JOIN xuat ON sanpham.masp = xuat.masp
                )
                SELECT 
                    * 
                FROM 
                    temp_table
                WHERE 
                    masp LIKE ? OR tensp LIKE ?
                ORDER BY 
                    masp;
            """;

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(5, "%" + text + "%");
            pst.setString(6, "%" + text + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int size = rs.getInt("size");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongton = rs.getInt("soluongton");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, tensp, size, soluongnhap, soluongxuat, soluongton);
                result.add(p);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
