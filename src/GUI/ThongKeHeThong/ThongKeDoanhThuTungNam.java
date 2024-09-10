package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
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
public class ThongKeDoanhThuTungNam extends javax.swing.JPanel {

    ThongKeBUS thongkeBUS;
    ArrayList<ThongKeDoanhThuDTO> listtkNam;
    public int current_year;

    public ThongKeDoanhThuTungNam() {
        initComponents();
        thongkeBUS = new ThongKeBUS();
        this.current_year = LocalDate.now().getYear();
        this.listtkNam = this.thongkeBUS.getDoanhThuTheoTungNam(current_year - 5, current_year);
        loadDataTalbe(listtkNam);
        loadDataChart(listtkNam);
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
    public void loadDataTalbe(ArrayList<ThongKeDoanhThuDTO> listtkNam) {
    DefaultTableModel model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);

        int k = 0;
        for (ThongKeDoanhThuDTO i : listtkNam) {
            Object[] row
                    = {
                        i.getThoigian(),
                        Formater.FormatVND(i.getVon()),
                        Formater.FormatVND(i.getDoanhthu()),
                        Formater.FormatVND(i.getLoinhuan())
                    };
            model.addRow(row);
            k++;
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblThongKe.getColumnCount(); i++) {
            tblThongKe.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
}
    
    public void loadDataChart(ArrayList<ThongKeDoanhThuDTO> listtkNam) {
        pnlChart.removeAll();
        Chart chart;
        chart = new Chart();
        chart.addLegend("Vốn", new Color(37,150,190));
        chart.addLegend("Doanh thu", new Color(21, 135, 15));
        chart.addLegend("Lợi nhuận", new Color(238, 82, 60));
        for (ThongKeDoanhThuDTO i : listtkNam) {
            chart.addData(new ModelChart("Năm " + i.getThoigian(), new double[]{i.getVon(), i.getDoanhthu(), i.getLoinhuan()}));
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblChonNamBatDau = new javax.swing.JLabel();
        yearchooser_start = new com.toedter.calendar.JYearChooser();
        lblChonNamKetThuc = new javax.swing.JLabel();
        yearchooser_end = new com.toedter.calendar.JYearChooser();
        btnthongke = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnexport = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        pnlChart = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout(10, 10));

        lblChonNamBatDau.setText("Từ năm");
        jPanel1.add(lblChonNamBatDau);
        jPanel1.add(yearchooser_start);

        lblChonNamKetThuc.setText("Đến năm");
        jPanel1.add(lblChonNamKetThuc);
        jPanel1.add(yearchooser_end);

        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });
        jPanel1.add(btnthongke);

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        jPanel1.add(btnreset);

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
                "Năm", "Vốn", "Doanh thu", "Lợi nhuận"
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

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        // TODO add your handling code here:
        System.out.println(yearchooser_start.getYear());
//            if (!Validation.isEmpty(yearchooser_end.getName()) || !Validation.isEmpty(yearchooser_start.getName())) {
                int nambd = yearchooser_start.getYear();
                int namkt = yearchooser_end.getYear();
                if (nambd > current_year || namkt > current_year) {
                    JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
                } else {
                    if (namkt < nambd || namkt <= 2015 || nambd <= 2015) {
                        JOptionPane.showMessageDialog(this, "Năm kết thúc không được bé hơn năm bắt đầu và phải lớn hơn 2015");
                    } else {
                        this.listtkNam = this.thongkeBUS.getDoanhThuTheoTungNam(nambd, namkt);
                        loadDataChart(listtkNam);
                        loadDataTalbe(listtkNam);
                    }
                }
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        yearchooser_start.setName("");
            yearchooser_end.setName("");
            this.listtkNam = this.thongkeBUS.getDoanhThuTheoTungNam(current_year - 5, current_year);
            loadDataChart(listtkNam);
            loadDataTalbe(listtkNam);
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnexportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportActionPerformed
        // TODO add your handling code here:
        try {
                JTableExporter.exportJTableToExcel(tblThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungNam.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnexportActionPerformed

    
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
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnthongke;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChonNamBatDau;
    private javax.swing.JLabel lblChonNamKetThuc;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JTable tblThongKe;
    private com.toedter.calendar.JYearChooser yearchooser_end;
    private com.toedter.calendar.JYearChooser yearchooser_start;
    // End of variables declaration//GEN-END:variables
}
