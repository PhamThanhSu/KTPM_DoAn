package GUI;

import BUS.SanPhamBUS;
import DAO.LoaiDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.SanPhamDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NhapExcel {

    public void importDataFromExcel(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            SanPhamBUS sanPhamBUS = new SanPhamBUS();
            LoaiDAO loaiDAO = new LoaiDAO();
            XuatXuDAO xuatXuDAO = new XuatXuDAO();
            ThuongHieuDAO thuongHieuDAO = new ThuongHieuDAO();
            
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                // Đọc dữ liệu từ các ô và tạo đối tượng SanPhamDTO
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setTensp(currentRow.getCell(0).getStringCellValue());
                sanPham.setSoluongton((int) currentRow.getCell(1).getNumericCellValue());
                sanPham.setSize(Integer.parseInt(currentRow.getCell(2).getStringCellValue()));
                // Làm tương tự cho các trường khác

                // Lấy thông tin liên quan từ các DAO và gán vào các trường tương ứng
                int loaiId = (int) currentRow.getCell(3).getNumericCellValue();
                sanPham.setLoai(loaiId);

                int thuongHieuId = (int) currentRow.getCell(4).getNumericCellValue();
                sanPham.setThuonghieu(thuongHieuId);

                int xuatXuId = (int) currentRow.getCell(5).getNumericCellValue();
                sanPham.setXuatxu(xuatXuId);

                int khuVucKhoId = (int) currentRow.getCell(6).getNumericCellValue();
                sanPham.setKhuvuckho(khuVucKhoId);

                // Thêm đối tượng SanPhamDTO vào danh sách hoặc cơ sở dữ liệu của ứng dụng
                sanPhamBUS.themSanPham(sanPham); // Đảm bảo phương thức themSanPham trong sanPhamBUS đã được triển khai
            }

            workbook.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
