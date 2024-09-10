/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.PhieuNhap;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapBUS {

    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    ArrayList<PhieuNhapDTO> selectedPNproducts;

    public PhieuNhapBUS() {
    }

    public ArrayList<PhieuNhapDTO> getAllPhieuNhap() {
        return phieuNhapDAO.getAllPhieuNhap();
    }

    public PhieuNhapDTO selectByID(int mapn) {
        return phieuNhapDAO.selectById(mapn);
    }

    public ArrayList<ChiTietPhieuNhapDTO> getListChiTietPhieuNhap(int mapn) {
        return chiTietPhieuNhapDAO.getAllChiTietPhieuNhap(mapn);
    }

    public boolean DeletePhieuNhap(int mapn) {
        return phieuNhapDAO.DeletePhieuNhap(mapn);
    }

    public int getMancc(String tenNCC) {
        int mancc = -1;
        // Kiểm tra nhaCungCapBUS không null trước khi sử dụng
        if (nhaCungCapBUS != null) {
            this.selectedPNproducts = getAllPhieuNhap();
            for (PhieuNhapDTO checkphieunhap : this.selectedPNproducts) {
                String tenNhaCungCap = (String) nhaCungCapBUS.selectByID(checkphieunhap.getManhacungcap()).getTenncc();
                if (tenNhaCungCap != null && tenNCC.equals(tenNhaCungCap)) {
                    mancc = checkphieunhap.getManhacungcap();
                }
            }
        }
        return mancc;
    }
    public int getManv(String tenNv) {
        int manv = -1;
        // Kiểm tra nhaCungCapBUS không null trước khi sử dụng
        if (nhanVienBUS != null) {
            this.selectedPNproducts = getAllPhieuNhap();
            for (PhieuNhapDTO checkphieunhap : this.selectedPNproducts) {
                String tennhanVien = (String) nhanVienBUS.selectByID(checkphieunhap.getMaNV()).getHoten();
                if (tennhanVien != null && tenNv.equals(tennhanVien)) {
                    manv = checkphieunhap.getMaNV();
                }
            }
        }
        return manv;
    }

    public ArrayList<PhieuNhapDTO> fillerPhieuNhap(int mancc, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
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
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        this.selectedPNproducts = getAllPhieuNhap();
        for (PhieuNhapDTO phieuNhap : this.selectedPNproducts) {

            String tongTienStr = String.valueOf(phieuNhap.getTongTien()).replaceAll("[.,đ]", "").trim();
            long tongTien = Long.parseLong(tongTienStr);
            System.out.println("giá tiền" + tongTien);
            if ((mancc == 0 || phieuNhap.getManhacungcap() == mancc)
                    &&(manv == 0 || phieuNhap.getMaNV()== manv)
                    && (phieuNhap.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuNhap.getThoigiantao().compareTo(time_end) <= 0)
                    && (tongTien >= price_min && tongTien <= price_max)) {
                result.add(phieuNhap);
            }
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> search(String text, ArrayList<PhieuNhapDTO> listPhieuNhap) {
        text = text.toLowerCase();
        String tenNcc = "";
        nhaCungCapBUS = new NhaCungCapBUS();
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO i : listPhieuNhap) {
            tenNcc = nhaCungCapBUS.selectByID(i.getManhacungcap()).getTenncc();
            //int manv = nhaCungCapBUS.selectByID(i.getMaNV()). select đến tên nhân viên tại đây
            if (Integer.toString(i.getMaphieunhap()).toLowerCase().contains(text)
                    || tenNcc.toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

}
