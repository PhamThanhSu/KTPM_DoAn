/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import DTO.SanPhamDTO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class BuildTable {
    private DefaultTableModel tblModel;
    public BuildTable(){
        
    }
    
    // Phương thức để cập nhật dữ liệu vào bảng
    public void updateTableProducts(JTable table, ArrayList<SanPhamDTO> list) {
        tblModel = (DefaultTableModel) table.getModel(); // Lấy mô hình dữ liệu từ bảng
        tblModel.setRowCount(0); // Xóa hết dữ liệu trong bảng
        for (SanPhamDTO sp : list) {
            // Thêm từng dòng dữ liệu từ ArrayList vào bảng
            Object[] rowData = {sp.getMasp(), sp.getTensp(), sp.getSoluongton(), sp.getSize()};
            tblModel.addRow(rowData);
            // Tạo renderer để hiển thị nội dung ở giữa ô
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Áp dụng renderer cho từng cột trong bảng
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }
    
    
    
}
