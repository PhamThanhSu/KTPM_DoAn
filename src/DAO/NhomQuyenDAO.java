
package DAO;

import DTO.NhomQuyenDTO;
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
}
