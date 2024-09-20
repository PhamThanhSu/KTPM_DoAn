package DAO;

import DTO.NhomQuyenDTO;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.MySQLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhomQuyenDAO {

    private Connection connection;
    private PreparedStatement pst;

    public NhomQuyenDAO() {
    }

    public ArrayList<NhomQuyenDTO> getAllNhomQuyen() {
        ArrayList<NhomQuyenDTO> listNhomQuyen = new ArrayList<>();
        connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM nhomquyen";
        try {
            pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                NhomQuyenDTO nhomQuyenDTO = new NhomQuyenDTO();
                nhomQuyenDTO.setManhomquyen(resultSet.getInt("manhomquyen"));
                nhomQuyenDTO.setTennhomquyen(resultSet.getString("tennhomquyen"));
                listNhomQuyen.add(nhomQuyenDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu");
        }
        return listNhomQuyen;
    }

    public NhomQuyenDTO selectById(int t) {
        NhomQuyenDTO result = null;
        try {
            connection = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE manhomquyen=?";
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                result = new NhomQuyenDTO(manhomquyen, tennhomquyen);
            }
            MySQLConnection.closeConnection(connection);
        } catch (Exception e) {
        }
        return result;
    }

    public boolean themNhomQuyen(NhomQuyenDTO nhomQuyenDTO) throws SQLException {
        String sql = "INSERT INTO nhomquyen (tennhomquyen) VALUES (?)";
        try {
            connection = (Connection) MySQLConnection.getConnection();
            pst = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, nhomQuyenDTO.getTennhomquyen());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                // Lấy ID của nhóm quyền mới thêm để gán vào DTO
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        nhomQuyenDTO.setManhomquyen(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            MySQLConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }

    public boolean xoaNhomQuyen(int maNhomQuyen) throws SQLException {
        String sql = "DELETE FROM nhomquyen WHERE manhomquyen = ?";
        try {
            connection = (Connection) MySQLConnection.getConnection();
            pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.setInt(1, maNhomQuyen);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                // Xóa thành công
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            MySQLConnection.closeConnection(connection);
        }
        return false;
    }

    // Kiểm tra tên nhóm quyền đã tồn tại trong cơ sở dữ liệu
    public boolean isTenNhomQuyenTonTai(String tenNhomQuyen) throws SQLException {
        String sql = "SELECT COUNT(*) FROM nhomquyen WHERE tennhomquyen = ?";
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = MySQLConnection.getConnection();
            pst = connection.prepareStatement(sql);
            pst.setString(1, tenNhomQuyen);
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } finally {
            // Đóng ResultSet
            if (rs != null) {
                rs.close();
            }
            // Đóng PreparedStatement
            if (pst != null) {
                pst.close();
            }
            // Đóng Connection
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

}
