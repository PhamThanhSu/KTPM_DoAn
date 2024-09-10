package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.SanPhamDAO;
import DAO.SanPhamPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamPhieuNhapBUS {
    //SanPhamPhieuNhapDAO sanPhamPhieuNhapDAO = new SanPhamPhieuNhapDAO();
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    public SanPhamPhieuNhapBUS() {
    }
    
    public static ArrayList<SanPhamDTO> getListSanPham() {
        return SanPhamPhieuNhapDAO.getListSanPham();
    }

    public static ArrayList<SanPhamDTO> getListSanPham(int masp) {
        return SanPhamPhieuNhapDAO.getListSanPham(masp);
    }
    
    public ChiTietPhieuNhapDTO selectByID(int mapn){
        return chiTietPhieuNhapDAO.selectByID(mapn);
    }
}
