
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhanVienBUS {
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    
    public NhanVienBUS() {
    }
    
    public ArrayList<NhanVienDTO> getAllNhanVien(){
        return nhanVienDAO.getAllNhanVien();
    }
    
    public ArrayList<NhanVienDTO> getNhanVienChuaCoTK(){
        return nhanVienDAO.getNhanVienChuaCoTK();
    }
    
    public boolean themNhanVien(NhanVienDTO nhanVienDTO){
        if (kiemTraTrungTen(nhanVienDTO.getHoten())) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản đã có");
            return false;
        }
        return nhanVienDAO.themNhanVien(nhanVienDTO);
    }
    
    public boolean xoaNhanVien(int manv){
        return nhanVienDAO.xoaNhanVien(manv);
    }
    
    public boolean suaNhanVien(NhanVienDTO nhanVienDTO){
        return nhanVienDAO.suaNhanVien(nhanVienDTO);
    }
    
    public boolean kiemTraTrungTen(String tenNhanVienMoi) {
        ArrayList<NhanVienDTO> listNhanVien = getAllNhanVien();
        for (NhanVienDTO NhanVien : listNhanVien) {
            if (NhanVien.getHoten().equalsIgnoreCase(tenNhanVienMoi)) {
                return true;
            }
        }
        return false;
    }
    
    public String[] getArrTenNhanVien() {
        int size = getAllNhanVien().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllNhanVien().get(i).getHoten();
        }
        return result;
    }
    
    public NhanVienDTO selectByID(int manv) {
        return nhanVienDAO.selectById(manv);
    }
}
