package BUS;

import DAO.GiamGiaSanPhamDAO;
import DTO.GiamGiaSanPhamDTO;
import java.util.ArrayList;
import java.util.List;

public class GiamGiaSanPhamBUS {

    private final GiamGiaSanPhamDAO giamGiaSanPhamDAO;

    public GiamGiaSanPhamBUS() {
        giamGiaSanPhamDAO = GiamGiaSanPhamDAO.getInstance();
    }

    // Add a new discount-product association
    public boolean insertGiamGiaSanPham(GiamGiaSanPhamDTO giamGiaSanPhamDTO) {
        return giamGiaSanPhamDAO.insertGiamGiaSanPham(giamGiaSanPhamDTO);
    }

    public boolean updateSanPhamApDungGiamGia(int magiamgia, List<GiamGiaSanPhamDTO> giamGiaSanPhamDTOList) {
        return giamGiaSanPhamDAO.updateSanPhamApDungGiamGia(magiamgia, giamGiaSanPhamDTOList);
    }

    // Get all discount-product associations
    public List<GiamGiaSanPhamDTO> getAllGiamGiaSanPham() {
        return giamGiaSanPhamDAO.getAllGiamGiaSanPham();
    }

    public ArrayList<GiamGiaSanPhamDTO> selectGiamGiaSPByID(int magiamgia) {
        return giamGiaSanPhamDAO.selectGiamGiaSPByID(magiamgia);
    }

    // Delete a discount-product association
    public boolean deleteGiamGiaSanPham(int magiamgia, int masp) {
        return giamGiaSanPhamDAO.deleteGiamGiaSanPham(magiamgia, masp);
    }
    
    public boolean deleteGiamGiaSanPham(int magiamgia) {
        return giamGiaSanPhamDAO.deleteGiamGiaSanPham(magiamgia);
    }
}
