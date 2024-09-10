package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoanBUS {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public TaiKhoanDTO selectAccountUser(String username) {
        return taiKhoanDAO.selectByUser(username);
    }

    public String selectTenChucVu(int maquyen) {
        return taiKhoanDAO.selectChucVu(maquyen);
    }

    public ArrayList<TaiKhoanDTO> getAllTaiKhoan() {
        return taiKhoanDAO.getAllTaiKhoan();
    }

    public boolean themTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
        if (kiemTraTrungTen(taiKhoanDTO.getUsername())) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản đã có");
            return false;
        }
        return taiKhoanDAO.themTaiKhoan(taiKhoanDTO);
    }

    public boolean xoaTaiKhoan(int idTaiKhoan) {
        return taiKhoanDAO.xoaTaiKhoan(idTaiKhoan);
    }

    public boolean suaTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
        return taiKhoanDAO.suaTaiKhoan(taiKhoanDTO);
    }

    public TaiKhoanDTO selectByID(int manv) {
        return taiKhoanDAO.selectById(manv);
    }

    public boolean kiemTraTrungTen(String tenTaiKhoanMoi) {
        ArrayList<TaiKhoanDTO> listTaiKhoan = getAllTaiKhoan();
        for (TaiKhoanDTO TaiKhoan : listTaiKhoan) {
            if (TaiKhoan.getUsername().equalsIgnoreCase(tenTaiKhoanMoi)) {
                return true;
            }
        }
        return false;
    }

    public String[] getArrTenTaiKhoan() {
        int size = getAllTaiKhoan().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllTaiKhoan().get(i).getUsername();
        }
        return result;
    }

    public String selectTenNhomQuyenByMaNQ(int manhomquyen) {
        return taiKhoanDAO.selectNhomQuyenByMaNQ(manhomquyen);
    }

}
