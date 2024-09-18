
package BUS;

import DAO.ChiTietQuyenDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhomQuyenBUS {
    private final NhomQuyenDAO nhomQuyenDAO = new NhomQuyenDAO();
    private final ChiTietQuyenDAO chiTietQuyenDAO = new ChiTietQuyenDAO();

    public ArrayList<NhomQuyenDTO> getAllNhomQuyen() {
        return nhomQuyenDAO.getAllNhomQuyen();
    }
    
    public String[] getArrTenNhomQuyen() {
        int size = getAllNhomQuyen().size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = getAllNhomQuyen().get(i).getTennhomquyen();
        }
        return result;
    }
    
    public NhomQuyenDTO selectByID(int manhomquyen){
        return nhomQuyenDAO.selectById(manhomquyen);
    }
    
    public ArrayList<ChiTietQuyenDTO> getChiTietQuyen(String manhomquyen) throws SQLException {
        return chiTietQuyenDAO.getChiTietQuyen(manhomquyen);
    }
    
    public boolean checkPermisson(int maquyen, String chucnang, String hanhdong) throws SQLException {
        ArrayList<ChiTietQuyenDTO> ctquyen = this.getChiTietQuyen(Integer.toString(maquyen));
        boolean check = false;
        int i = 0;
        while (i < ctquyen.size() && check==false) {
            if(ctquyen.get(i).getMaChucNang().equals(chucnang) && ctquyen.get(i).getHanhDong().equals(hanhdong)) {
                check = true;
            } else {
                i++;
            }
        }
        return check;
    }
    
}
