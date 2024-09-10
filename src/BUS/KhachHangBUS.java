
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
        


public class KhachHangBUS {
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    public KhachHangBUS() {
    }
    
    public ArrayList<KhachHangDTO> getAllKhachHang(){
        return khachHangDAO.getAllKhachHang();
    }
    
    public boolean themKhachHang(KhachHangDTO khachHangDTO){
        return khachHangDAO.themKhachHang(khachHangDTO);
    }
    public boolean xoaKhachHang(int makh){
        return khachHangDAO.xoaKhachHang(makh);
    }
    
    public boolean suaKhachHang(KhachHangDTO khachHangDTO){
        return khachHangDAO.suaKhachHang(khachHangDTO);
    }
    public KhachHangDTO selectByID(int makh){
        return khachHangDAO.selectById(makh);
    }
}
