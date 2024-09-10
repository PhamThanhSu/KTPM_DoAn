/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhieuXuatBUS {

    PhieuXuatDAO phieuXuatDAO = new PhieuXuatDAO();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    ChiTietPhieuXuatDAO chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();

    ArrayList<PhieuXuatDTO> selectedPXproducts;

    public PhieuXuatBUS() {
    }

    public ArrayList<PhieuXuatDTO> getAllPhieuXuat() {
        return phieuXuatDAO.getAllPhieuXuat();
    }

    public PhieuXuatDTO selectByID(int mapn) {
        return phieuXuatDAO.selectById(mapn);
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuat(int mapn) {
        return chiTietPhieuXuatDAO.getAllChiTietPhieuXuat(mapn);
    }

    public boolean DeletePhieuXuat(int mapx) {
        return phieuXuatDAO.DeletePhieuXuat(mapx);
    }

    public int getMakh(String tenKH) {
        int makh = -1;
        // Kiểm tra nhaCungCapBUS không null trước khi sử dụng
        if (khachHangBUS != null) {
            this.selectedPXproducts = getAllPhieuXuat();
            for (PhieuXuatDTO checkphieuxuat : this.selectedPXproducts) {
                String tenKhachHang = (String) khachHangBUS.selectByID(checkphieuxuat.getMakh()).getHoten();
                if (tenKhachHang != null && tenKH.equals(tenKhachHang)) {
                    makh = checkphieuxuat.getMakh();
                }
            }
        }
        return makh;
    }
    public int getManv(String tenNv) {
        int manv = -1;
        // Kiểm tra nhaCungCapBUS không null trước khi sử dụng
        if (nhanVienBUS != null) {
            this.selectedPXproducts = getAllPhieuXuat();
            for (PhieuXuatDTO checkphieuxuat : this.selectedPXproducts) {
                String tennhanVien = (String) nhanVienBUS.selectByID(checkphieuxuat.getManv()).getHoten();
                if (tennhanVien != null && tenNv.equals(tennhanVien)) {
                    manv = checkphieuxuat.getManv();
                }
            }
        }
        return manv;
    }

    public ArrayList<PhieuXuatDTO> search(String text, ArrayList<PhieuXuatDTO> listPhieu) {
        text = text.toLowerCase();
        String tenKH = "";
        khachHangBUS = new KhachHangBUS();
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        for (PhieuXuatDTO i : listPhieu) {
            tenKH = khachHangBUS.selectByID(i.getMakh()).getHoten();
            //int manv = nhaCungCapBUS.selectByID(i.getMaNV()). select đến tên nhân viên tại đây
            if (Integer.toString(i.getMaphieuxuat()).toLowerCase().contains(text)
                    || tenKH.toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<PhieuXuatDTO> fillerPhieuXuat(int makh, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        this.selectedPXproducts = getAllPhieuXuat();
        for (PhieuXuatDTO phieuXuat : this.selectedPXproducts) {
            String tongTienStr = String.valueOf(phieuXuat.getTongTien()).replaceAll("[.,đ]", "").trim();
            long tongTien = Long.parseLong(tongTienStr);
            System.out.println("giá tiền" + tongTien);
            if ((makh == 0 || phieuXuat.getMakh() == makh)
                    &&(manv == 0 || phieuXuat.getManv()== manv)
                    && (phieuXuat.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuXuat.getThoigiantao().compareTo(time_end) <= 0)
                    && (tongTien >= price_min && tongTien <= price_max)) {
                result.add(phieuXuat);
            }
        }
        return result;
    }
}
