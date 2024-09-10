
package BUS;

import DAO.NhomQuyenDAO;
import DTO.NhomQuyenDTO;
import java.util.ArrayList;

public class NhomQuyenBUS {
    private final NhomQuyenDAO nhomQuyenDAO = new NhomQuyenDAO();

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
}
