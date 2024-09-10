package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
        


public class NhaCungCapBUS {
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    public NhaCungCapBUS() {
    }
    
    public ArrayList<NhaCungCapDTO> getAllNhaCungCap(){
        return nhaCungCapDAO.getAllNhaCungCap();
    }
    
    public boolean themNhaCungCap(NhaCungCapDTO nhaCungCapDTO){
        return nhaCungCapDAO.themNhaCungCap(nhaCungCapDTO);
    }
    public boolean xoaNhaCungCap(int manhacungcap){
        return nhaCungCapDAO.xoaNhaCungCap(manhacungcap);
    }
    
    public boolean suaNhaCungCap(NhaCungCapDTO nhaCungCapDTO){
        return nhaCungCapDAO.suaNhaCungCap(nhaCungCapDTO);
    }
    public NhaCungCapDTO selectByID(int manhacungcap){
        return nhaCungCapDAO.selectById(manhacungcap);
    }
}