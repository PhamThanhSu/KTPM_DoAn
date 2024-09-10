package DAO;

import DTO.NhaCungCapDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NhaCungCapDAO {

    Connection connection;
    PreparedStatement pst;

    public NhaCungCapDAO() {
    }

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    public ArrayList<NhaCungCapDTO> getAllNhaCungCap() {
        ArrayList<NhaCungCapDTO> listNhaCungCap = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM nhacungcap WHERE trangthai = 1";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
                nhaCungCapDTO.setMancc(rs.getInt("manhacungcap"));
                nhaCungCapDTO.setTenncc(rs.getString("tennhacungcap"));
                nhaCungCapDTO.setDiachi(rs.getString("diachi"));
                nhaCungCapDTO.setEmail(rs.getString("email"));
                nhaCungCapDTO.setSdt(rs.getString("sdt"));

                listNhaCungCap.add(nhaCungCapDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu nhà cung cấp");
        }
        return listNhaCungCap;
    }

    public boolean themNhaCungCap(NhaCungCapDTO nhaCungCapDTO) {
        boolean thanhCong = false;
        String query = "INSERT INTO nhacungcap (tennhacungcap, diachi, email, sdt) VALUES (?, ?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(query);
            pst.setString(1, nhaCungCapDTO.getTenncc());
            pst.setString(2, nhaCungCapDTO.getDiachi());
            pst.setString(3, nhaCungCapDTO.getSdt());
            pst.setString(4, nhaCungCapDTO.getEmail());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public boolean xoaNhaCungCap(int t) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "UPDATE `nhacungcap` SET `trangthai`=0 WHERE manhacungcap = ?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thanhCong;
    }

    public boolean suaNhaCungCap(NhaCungCapDTO nhaCungCapDTO) {
        boolean thanhCong = false;
        try {
            connection = MySQLConnection.getConnection();
            String query = "UPDATE nhacungcap SET tennhacungcap=?, diachi=?, email=? , sdt=? WHERE manhacungcap=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, nhaCungCapDTO.getTenncc());
            pst.setString(2, nhaCungCapDTO.getDiachi());
            pst.setString(4, nhaCungCapDTO.getSdt());
            pst.setString(3, nhaCungCapDTO.getEmail());
            pst.setInt(5, nhaCungCapDTO.getMancc());
            int rowAff = pst.executeUpdate();
            if (rowAff > 0) {
                thanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    public NhaCungCapDTO selectById(int manhacungcap) {
        NhaCungCapDTO result = null;
        try {
            connection = MySQLConnection.getConnection();
            String sql = "SELECT * FROM nhacungcap WHERE manhacungcap=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, manhacungcap);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("manhacungcap");
                String ten = rs.getString("tennhacungcap");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                result = new NhaCungCapDTO(ma, ten, diachi, email, sdt);
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
        }
        return result;
    }

}
