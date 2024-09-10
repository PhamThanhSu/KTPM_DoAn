package DAO;

import DTO.SanPhamDTO;
import DTO.ThuongHieuDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SanPhamDAO {

    Connection connection;
    PreparedStatement pst;

    public SanPhamDAO() {
    }

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    public ArrayList<SanPhamDTO> getAllSanPham() {
        ArrayList<SanPhamDTO> listSanPham = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM sanpham WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setMasp(rs.getInt("masp"));
                sanPhamDTO.setTensp(rs.getString("tensp"));
                sanPhamDTO.setSize(rs.getInt("size"));
                sanPhamDTO.setHinhanh(rs.getString("hinhanh"));
                sanPhamDTO.setXuatxu(rs.getInt("xuatxu"));
                sanPhamDTO.setLoai(rs.getInt("loai"));
                sanPhamDTO.setThuonghieu(rs.getInt("thuonghieu"));
                sanPhamDTO.setKhuvuckho(rs.getInt("khuvuckho"));
                sanPhamDTO.setSoluongton(rs.getInt("soluongton"));
                sanPhamDTO.setGianhap(rs.getInt("gianhap"));
                sanPhamDTO.setGiaxuat(rs.getInt("giaxuat"));
                listSanPham.add(sanPhamDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu sản phẩm");
        }
        return listSanPham;
    }

    public boolean themSanPham(SanPhamDTO sanPhamDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO sanpham (tensp, size, hinhanh, xuatxu, loai, thuonghieu, khuvuckho, gianhap, giaxuat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, sanPhamDTO.getTensp());
            pst.setInt(2, sanPhamDTO.getSize());
            pst.setString(3, sanPhamDTO.getHinhanh());
            pst.setInt(4, sanPhamDTO.getXuatxu());
            pst.setInt(5, sanPhamDTO.getLoai());
            pst.setInt(6, sanPhamDTO.getThuonghieu());
            pst.setInt(7, sanPhamDTO.getKhuvuckho());
            pst.setInt(8, sanPhamDTO.getGianhap());
            pst.setInt(9, sanPhamDTO.getGiaxuat());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public boolean xoaSanPham(int t) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE `sanpham` SET `trangthai`=0 WHERE masp = ?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thanhCong;
    }

    public String selectHinhAnhByMaSP(int masp) {
        String result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT hinhanh FROM sanpham WHERE masp=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, masp);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getString("hinhanh");
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return result;
    }

    public boolean suaSanPham(SanPhamDTO sanPhamDTO) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String query = "UPDATE sanpham SET tensp=?, size=?, hinhanh=?, xuatxu=?, loai=?, thuonghieu=?, khuvuckho=?, gianhap=?, giaxuat=? WHERE masp=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, sanPhamDTO.getTensp());
            pst.setInt(2, sanPhamDTO.getSize());
            pst.setString(3, sanPhamDTO.getHinhanh());
            pst.setInt(4, sanPhamDTO.getXuatxu());
            pst.setInt(5, sanPhamDTO.getLoai());
            pst.setInt(6, sanPhamDTO.getThuonghieu());
            pst.setInt(7, sanPhamDTO.getKhuvuckho());
            pst.setInt(8, sanPhamDTO.getGianhap());
            pst.setInt(9, sanPhamDTO.getGiaxuat());
            pst.setInt(10, sanPhamDTO.getMasp());

            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public SanPhamDTO selectById(int masp) {
        SanPhamDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM sanpham WHERE masp=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, masp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maSP = rs.getInt("masp");
                String tenSP = rs.getString("tensp");
                int size = rs.getInt("size");
                String hinhanh = rs.getString("hinhanh");
                int xuatxu = rs.getInt("xuatxu");
                int loai = rs.getInt("loai");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                result = new SanPhamDTO(maSP, tenSP, size, hinhanh, xuatxu, loai, thuonghieu, khuvuckho, gianhap, giaxuat, soluongton);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

    public boolean checkSize(String tensp, int size) {
        boolean thanhCong = false;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM sanpham WHERE tensp=? AND size=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, tensp);
            pst.setInt(2, size);
            rs = pst.executeQuery();
            thanhCong = !rs.next(); // Trả về true nếu không có bản ghi nào được tìm thấy
        } catch (SQLException e) {
            // Xử lý lỗi, ví dụ: ghi log
            e.printStackTrace();
        } finally {
            // Đảm bảo kết nối được đóng
            MySQLConnection.closeConnection(connection);
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return thanhCong;
    }

}
