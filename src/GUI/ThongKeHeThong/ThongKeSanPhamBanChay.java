/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DTO.ThongKeSanPhamBanChayDTO;
import GUI.XuatExcel;
import java.awt.Dimension;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author phatl
 */
public class ThongKeSanPhamBanChay extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeSanPhamBanChay
     */
    ThongKeBUS thongKeBUS;

    public ThongKeSanPhamBanChay() {
        initComponents();
        hienThiDuLieuTable();
        veBieuDoTronTop5();
    }

    LocalDate currentDate = LocalDate.now();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();

    private void hienThongKeThangNamHienTai() {

        txtThang.setText(String.valueOf(currentMonth)); // Thiết lập giá trị cho trường tháng
        txtNam.setText(String.valueOf(currentYear));  // Thiết lập giá trị cho trường năm

        // Gọi hàm thống kê khi khởi động trang
        hienThiDuLieuTable();
    }

    public void hienThiDuLieuTable() {
        thongKeBUS = new ThongKeBUS();

        ArrayList<ThongKeSanPhamBanChayDTO> listSanPhamBanChay = thongKeBUS.getThongKeSanPhamBanChay(currentMonth, currentYear);

        DefaultTableModel model = (DefaultTableModel) tblThongKeSanPham.getModel();
        model.setRowCount(0);
        int k = 1;
        for (ThongKeSanPhamBanChayDTO thongKe : listSanPhamBanChay) {
            Object[] row = {
                k,
                thongKe.getTenSP(),
                thongKe.getSoLuongDaBan()
            };
            model.addRow(row);
            k++;
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblThongKeSanPham.getColumnCount(); i++) {
            tblThongKeSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void veBieuDoTronTop5() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Lấy danh sách tất cả sản phẩm
        try {

            ArrayList<ThongKeSanPhamBanChayDTO> allSanPham = thongKeBUS.getThongKeSanPhamBanChay(currentMonth, currentYear);
//            ArrayList<ThongKeSanPhamBanChayDTO> allSanPham = thongKeBUS.getThongKeSanPhamBanChay();
            int tongSoLuongTop5 = 0;

            // Lấy top 5 sản phẩm và tính tổng số lượng
            for (int i = 0; i < Math.min(5, allSanPham.size()); i++) {
                ThongKeSanPhamBanChayDTO sanPham = allSanPham.get(i);
                dataset.setValue(sanPham.getTenSP(), sanPham.getSoLuongDaBan());
                tongSoLuongTop5 += sanPham.getSoLuongDaBan();
            }

            // Tính tổng số lượng các sản phẩm nằm ngoài top 5
            int tongSoLuongKhac = 0;
            for (int i = 5; i < allSanPham.size(); i++) {
                ThongKeSanPhamBanChayDTO sanPham = allSanPham.get(i);
                tongSoLuongKhac += sanPham.getSoLuongDaBan();
            }

            // Thêm sản phẩm "Khác" vào biểu đồ nếu tổng số lượng khác 0
            if (tongSoLuongKhac > 0) {
                dataset.setValue("Khác", tongSoLuongKhac);
            }

            // Tạo biểu đồ tròn và cấu hình nó
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Top 5 Sản phẩm bán chạy tháng " + String.valueOf(currentMonth) + "/" + String.valueOf(currentYear), // Tiêu đề biểu đồ
                    dataset, // Dữ liệu
                    true, // Hiển thị giá trị phần trăm
                    true,
                    false
            );

            // Tạo plot và cấu hình hiển thị phần trăm
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({2})", new java.text.DecimalFormat("0"), new java.text.DecimalFormat("0%")));

            // Tạo panel để hiển thị biểu đồ
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(500, 300));

            // Thêm panel vào giao diện của bạn
            pnlChart.removeAll(); // Xóa các component cũ trước khi thêm mới
            pnlChart.add(chartPanel);
            pnlChart.revalidate();
            pnlChart.repaint();
        } catch (Exception ex) {
            ex.printStackTrace(); // In ra lỗi nếu có
            // Xử lý ngoại lệ ở đây nếu cần
        }
    }

    public void veBieuDoTronTop5(ArrayList<ThongKeSanPhamBanChayDTO> listSP) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Lấy danh sách tất cả sản phẩm
        try {

            int tongSoLuongTop5 = 0;

            // Lấy top 5 sản phẩm và tính tổng số lượng
            for (int i = 0; i < Math.min(5, listSP.size()); i++) {
                ThongKeSanPhamBanChayDTO sanPham = listSP.get(i);
                dataset.setValue(sanPham.getTenSP(), sanPham.getSoLuongDaBan());
                tongSoLuongTop5 += sanPham.getSoLuongDaBan();
            }

            // Tính tổng số lượng các sản phẩm nằm ngoài top 5
            int tongSoLuongKhac = 0;
            for (int i = 5; i < listSP.size(); i++) {
                ThongKeSanPhamBanChayDTO sanPham = listSP.get(i);
                tongSoLuongKhac += sanPham.getSoLuongDaBan();
            }

            // Thêm sản phẩm "Khác" vào biểu đồ nếu tổng số lượng khác 0
            if (tongSoLuongKhac > 0) {
                dataset.setValue("Khác", tongSoLuongKhac);
            }

            // Tạo biểu đồ tròn và cấu hình nó
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Top 5 Sản phẩm bán chạy tháng " + txtThang.getText() + "/" + txtNam.getText(), // Tiêu đề biểu đồ
                    dataset, // Dữ liệu
                    true, // Hiển thị giá trị phần trăm
                    true,
                    false
            );

            // Tạo plot và cấu hình hiển thị phần trăm
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({2})", new java.text.DecimalFormat("0"), new java.text.DecimalFormat("0%")));

            // Tạo panel để hiển thị biểu đồ
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(500, 300));

            // Thêm panel vào giao diện của bạn
            pnlChart.removeAll(); // Xóa các component cũ trước khi thêm mới
            pnlChart.add(chartPanel);
            pnlChart.revalidate();
            pnlChart.repaint();
        } catch (Exception ex) {
            ex.printStackTrace(); // In ra lỗi nếu có
            // Xử lý ngoại lệ ở đây nếu cần
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongKe = new javax.swing.JPanel();
        pnlChart = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNam = new javax.swing.JTextField();
        btnThongKe = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(pnlThongKe, java.awt.BorderLayout.CENTER);

        pnlChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));
        add(pnlChart, java.awt.BorderLayout.CENTER);

        tblThongKeSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(tblThongKeSanPham);

        add(jScrollPane1, java.awt.BorderLayout.EAST);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Tháng :");
        jPanel1.add(jLabel1);
        jPanel1.add(txtThang);

        jLabel2.setText("Năm :");
        jPanel1.add(jLabel2);
        jPanel1.add(txtNam);

        btnThongKe.setText("THỐNG KÊ");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKe);

        btnXuatExcel.setText("XUẤT EXCEL");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnXuatExcel);

        add(jPanel1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        try {
            // Lấy giá trị từ các ô nhập liệu
            int thang = Integer.parseInt(txtThang.getText());
            int nam = Integer.parseInt(txtNam.getText());

            // Kiểm tra tính hợp lệ của tháng và năm
            if (thang < 1 || thang > 12 || nam < 0) {
                throw new IllegalArgumentException("Tháng và năm nhập vào không hợp lệ.");
            }

            // Gọi hàm thống kê với thời gian nhập
            thongKeSanPhamBanChay(thang, nam);
        } catch (NumberFormatException e) {
            // Xử lý nếu người dùng nhập không phải số
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại tháng và năm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Xử lý nếu người dùng nhập tháng và năm không hợp lệ
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
        XuatExcel xuatExcel = new XuatExcel();
        try {
            xuatExcel.exportJTableToExcel(tblThongKeSanPham);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeSanPhamBanChay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    public void thongKeSanPhamBanChay(int thang, int nam) {
        // Gọi hàm thống kê sản phẩm bán chạy từ lớp BUS
        ArrayList<ThongKeSanPhamBanChayDTO> listSanPhamBanChay = thongKeBUS.getThongKeSanPhamBanChay(thang, nam);

        // Hiển thị dữ liệu lên bảng
        DefaultTableModel model = (DefaultTableModel) tblThongKeSanPham.getModel();
        model.setRowCount(0);
        int k = 1;
        for (ThongKeSanPhamBanChayDTO thongKe : listSanPhamBanChay) {
            Object[] row = {
                k,
                thongKe.getTenSP(),
                thongKe.getSoLuongDaBan()
            };
            model.addRow(row);
            k++;
        }

        // Vẽ biểu đồ tròn với dữ liệu mới
        veBieuDoTronTop5(listSanPhamBanChay);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JTable tblThongKeSanPham;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}
