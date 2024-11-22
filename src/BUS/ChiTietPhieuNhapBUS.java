package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = ChiTietPhieuNhapDAO.getInstance();
    }

    public void insert(ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList) {
        chiTietPhieuNhapDAO.insert(chiTietPhieuNhapList);
    }
    
    public ChiTietPhieuNhapDTO selectSPGiaXuatLonNhatByID(int masp) {
        return chiTietPhieuNhapDAO.selectSPGiaXuatLonNhatByID(masp);
    }
    
    public void updateGiaXuat(int masp, int giaxuat){
        chiTietPhieuNhapDAO.updateGiaXuat(masp, giaxuat);
    }
    
    public void updateSoluongton(int masp, int soluong){
        chiTietPhieuNhapDAO.updateSoluongton(masp, soluong);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t){
        return chiTietPhieuNhapDAO.selectAll(t);
    }
    
    public ChiTietPhieuNhapDTO selectByID(int mapn){
        return chiTietPhieuNhapDAO.selectByID(mapn);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> selectByMASP(int masp){
        return chiTietPhieuNhapDAO.selectByMASP(masp);
    }
    
    public int getGiaXuatByMASP(int masp){
        return chiTietPhieuNhapDAO.getGiaXuatByMASP(masp);
    }
    
    // Lấy danh sách tất cả chi tiết phiếu nhập
    public List<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        return chiTietPhieuNhapDAO.getAllChiTietPhieuNhap();
    }

    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhapByMaPN(int mapn) {
        return chiTietPhieuNhapDAO.getAllChiTietPhieuNhapByMaPN(mapn);
    }
    
    public void updateSoluongCTPN(int masp, int soluong, int maphieunhap) {
        chiTietPhieuNhapDAO.updateSoluongCTPN(masp, soluong, maphieunhap);
    }
}
