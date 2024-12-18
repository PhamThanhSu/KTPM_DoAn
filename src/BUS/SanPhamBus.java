/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SanPhamBUS {

    SanPhamDAO sanPhamDAO = new SanPhamDAO();

    public SanPhamBUS() {
    }

    public ArrayList<SanPhamDTO> getAllSanPham() {
        return sanPhamDAO.getAllSanPham();
    }

    public boolean themSanPham(SanPhamDTO sanPhamDTO) {
        return sanPhamDAO.themSanPham(sanPhamDTO);
    }

    public boolean xoaSanPham(int masp) {
        return sanPhamDAO.xoaSanPham(masp);
    }

    public boolean suaSanPham(SanPhamDTO sanPhamDTO) {
        return sanPhamDAO.suaSanPham(sanPhamDTO);
    }

    public String selectAnhByMaSP(int masp) {
        return sanPhamDAO.selectHinhAnhByMaSP(masp);
    }

    public SanPhamDTO selectByID(int masp) {
        return sanPhamDAO.selectById(masp);
    }
    
    public ArrayList<SanPhamDTO> selectByKho(int makho) {
        return sanPhamDAO.selectByKho(makho);
    }
    
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    public boolean add(SanPhamDTO lh) {
        boolean check = sanPhamDAO.themSanPham(lh);
        if (check) {

            this.listSP.add(lh);
        }
        return check;
    }

    public boolean checkSize(String tensp, int size) {
        return sanPhamDAO.checkSize(tensp, size);
    }

    public ArrayList<SanPhamDTO> searchPN(String text) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : getAllSanPham()) {
            String tensp = selectByID(i.getMasp()).getTensp();
            if (Integer.toString(i.getMasp()).toLowerCase().contains(text) || i.getTensp().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> searchPX(String text) {
        text = text.toLowerCase();
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        for (ChiTietPhieuNhapDTO i : chiTietPhieuNhapDAO.getAllChiTietPhieuNhap()) {
            String tensp = selectByID(i.getMasp()).getTensp();
            if (Integer.toString(i.getMasp()).toLowerCase().contains(text) || tensp.toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
