
package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTheoThangDTO;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author duong
 */
public class ThongKeDoanhThuTungThang extends javax.swing.JPanel {

    ThongKeBUS thongkeBUS;
    ArrayList<ThongKeTheoThangDTO> listtkThang;
    Chart  chart;

    public ThongKeDoanhThuTungThang() {
        initComponents();
        thongkeBUS = new ThongKeBUS();
        loadThongKeThang(yearchooser.getYear());
    }
    public class Formater {

        public static String FormatVND(double vnd) {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            return formatter.format(vnd) + "đ";
        }

        public static String FormatTime(Timestamp thoigian) {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
            return formatDate.format(thoigian);
        }
    }
   public void loadThongKeThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> list = thongkeBUS.getThongKeTheoThang(nam);
        DefaultTableModel model = (DefaultTableModel) tblThongKe.getModel();
        pnlChart.removeAll();
        chart = new Chart();
        chart.addLegend("Vốn", new Color(37,150,190));
        chart.addLegend("Doanh thu", new Color(21, 135, 15));
        chart.addLegend("Lợi nhuận", new Color(238, 82, 60));
        for (int i = 0; i < list.size(); i++) {
            chart.addData(new ModelChart("Tháng " + (i + 1), new double[]{list.get(i).getChiphi(), list.get(i).getDoanhthu(), list.get(i).getLoinhuan()}));
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                "Tháng " + (i + 1), Formater.FormatVND(list.get(i).getChiphi()), Formater.FormatVND(list.get(i).getDoanhthu()), Formater.FormatVND(list.get(i).getLoinhuan())
            });
        }


    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblChonNam = new javax.swing.JLabel();
        yearchooser = new com.toedter.calendar.JYearChooser();
        btnexport = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        pnlChart = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout(10, 10));

        lblChonNam.setText("Chọn năm thống kê");
        jPanel1.add(lblChonNam);

        yearchooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearchooserPropertyChange(evt);
            }
        });
        jPanel1.add(yearchooser);

        btnexport.setText("Xuất excel");
        btnexport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportActionPerformed(evt);
            }
        });
        jPanel1.add(btnexport);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pnlBottom.setPreferredSize(new java.awt.Dimension(100, 250));

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Vốn", "Doanh thu", "Lợi nhuận"
            }
        ));
        jScrollPane1.setViewportView(tblThongKe);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        add(pnlBottom, java.awt.BorderLayout.PAGE_END);

        pnlChart.setPreferredSize(new java.awt.Dimension(100, 250));
        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));
        add(pnlChart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnexportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportActionPerformed
        // TODO add your handling code here:
        try {
                JTableExporter.exportJTableToExcel(tblThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungThang.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnexportActionPerformed

    private void yearchooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearchooserPropertyChange
        int nam = yearchooser.getYear();
        this.listtkThang = this.thongkeBUS.getThongKeTheoThang(nam);
        loadThongKeThang(nam);
    }//GEN-LAST:event_yearchooserPropertyChange

    
    public class JTableExporter {

    public static void exportJTableToExcel(JTable table) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn lưu file Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            TableModel model = table.getModel();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(model.getColumnName(i));
            }

            // Create data rows
            for (int i = 0; i < model.getRowCount(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell dataCell = dataRow.createCell(j);
                    Object value = model.getValueAt(i, j);
                    if (value != null) {
                        dataCell.setCellValue(value.toString());
                    }
                }
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            workbook.close();
        }
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexport;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChonNam;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JTable tblThongKe;
    private com.toedter.calendar.JYearChooser yearchooser;
    // End of variables declaration//GEN-END:variables
}
