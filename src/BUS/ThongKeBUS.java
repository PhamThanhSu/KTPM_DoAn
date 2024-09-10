package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNccDTO;
//import DTO.ThongKe.ThongKeNhaCungCapDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKeSanPhamBanChayDTO;
import DTO.ThongKeTonKhoDTO;
import DTO.ThongKeTrongThangDTO;
import GUI.ThongKeHeThong.ThongKeSanPhamBanChay;
import GUI.ThongKeHeThong.ThongKeTonKho;
//import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author
 */
public class ThongKeBUS {

    ThongKeDAO thongkeDAO = new ThongKeDAO();

    public ArrayList<ThongKeKhachHangDTO> getAllKhachHang() {
        return thongkeDAO.getThongKeKhachHang("", new Date(0), new Date(System.currentTimeMillis()));
    }

    public ArrayList<ThongKeKhachHangDTO> locKhachHang(String text, Date start, Date end) {
        return thongkeDAO.getThongKeKhachHang(text, start, end);
    }

    public ArrayList<ThongKeTonKhoDTO> getThongKeTonKho() {
        return thongkeDAO.getThongKeTonKho("", new Date(0), new Date(System.currentTimeMillis()));
    }
    
    public ArrayList<ThongKeTonKhoDTO> filterTonKho(String text, Date start, Date end) {
        return thongkeDAO.getThongKeTonKho(text, start, end);
    }
    
    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        return this.thongkeDAO.getDoanhThuTheoTungNam(year_start, year_end);
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        return thongkeDAO.getThongKeTheoThang(nam);
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getThongKeSanPhamBanChay() {
        return thongkeDAO.getListSanPhamBanChay();
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getThongKeSanPhamBanChay(int thang, int nam) {
        return thongkeDAO.getListSanPhamBanChay(thang, nam);
    }

    public ArrayList<ThongKeSanPhamBanChayDTO> getTop5SanPhamBanChay() {
        return thongkeDAO.getTop5SanPhamBanChay();
    }

    public ArrayList<ThongKeNccDTO> getAllNhaCungCap() {
        return thongkeDAO.getThongKeNhaCungCap("", new Date(0), new Date(System.currentTimeMillis()));
    }
    
    public ArrayList<ThongKeNccDTO> locNhaCungCap(String text, Date start, Date end) {
        return thongkeDAO.getThongKeNhaCungCap(text, start, end);
    }

    public ArrayList<ThongKeTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        return thongkeDAO.getThongKeTungNgayTrongThang(thang, nam);
    }
    
    public ArrayList<ThongKeTrongThangDTO> getThongKeTuNgayDenNgay(String Start, String End) {
        return thongkeDAO.getThongKeTuNgayDenNgay(Start, End);
    }
    
    public ArrayList<ThongKeTrongThangDTO> getThongKeDoanhThu7NgayGanNhat() {
        return thongkeDAO.getThongKe7NgayGanNhat();
    }
}
