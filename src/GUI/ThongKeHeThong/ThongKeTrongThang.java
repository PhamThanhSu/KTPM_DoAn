/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeHeThong;

import DTO.ThongKeTrongThangDTO;
import GUI.Component.Formater;
import GUI.Component.Export.JTableExporter;
import java.awt.event.ActionEvent;
import BUS.ThongKeBUS;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ThongKeTrongThang extends javax.swing.JPanel {
    Chart  chart;
    ThongKeBUS thongkeBUS;
    DefaultTableModel tblModel;
    Formater formater = new Formater();
    public ThongKeTrongThang() {
        initComponents();
        thongkeBUS = new ThongKeBUS();
        
        loadThongKeTungNgayTrongThang(chonthang.getMonth() + 1,  chonnam.getYear());
        
        btnthongke.addActionListener((ActionEvent e) -> {
            int thang = chonthang.getMonth() + 1;
            int nam = chonnam.getYear();
            loadThongKeTungNgayTrongThang(thang, nam);
        });
        btnexport.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tblthongke);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ThongKeTrongThang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
    }
    
    public void loadThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTrongThangDTO> list = thongkeBUS.getThongKeTungNgayTrongThang(thang, nam);
        tblModel = (DefaultTableModel) tblthongke.getModel();

        pnlChart.removeAll();
        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        int sum_chiphi = 0;
        int sum_doanhthu = 0;
        int sum_loinhuan = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            sum_chiphi += list.get(i).getChiphi();
            sum_doanhthu += list.get(i).getDoanhthu();
            sum_loinhuan += list.get(i).getLoinhuan();
            if (index % 3 == 0) {
                chart.addData(new ModelChart("Ngày " + (index - 2) + "->" + (index), new double[]{sum_chiphi, sum_doanhthu, sum_loinhuan}));
                sum_chiphi = 0;
                sum_doanhthu = 0;
                sum_loinhuan = 0;
            }
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();

        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            tblModel.addRow(new Object[]{
                list.get(i).getNgay(), formater.FormatVND(list.get(i).getChiphi()), formater.FormatVND(list.get(i).getDoanhthu()), formater.FormatVND(list.get(i).getLoinhuan())
            });
        }
        formater.setColumnAlignment(tblthongke);
    }    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblChonNamBatDau = new javax.swing.JLabel();
        chonthang = new com.toedter.calendar.JMonthChooser();
        lblChonNamKetThuc = new javax.swing.JLabel();
        chonnam = new com.toedter.calendar.JYearChooser();
        btnthongke = new javax.swing.JButton();
        btnexport = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblthongke = new javax.swing.JTable();
        pnlChart = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        lblChonNamBatDau.setText("Chọn tháng");
        jPanel2.add(lblChonNamBatDau);
        jPanel2.add(chonthang);

        lblChonNamKetThuc.setText("Chọn năm");
        jPanel2.add(lblChonNamKetThuc);
        jPanel2.add(chonnam);

        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });
        jPanel2.add(btnthongke);

        btnexport.setText("Xuất excel");
        btnexport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportActionPerformed(evt);
            }
        });
        jPanel2.add(btnexport);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tblthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ));
        jScrollPane1.setViewportView(tblthongke);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        add(pnlBottom, java.awt.BorderLayout.PAGE_END);

        pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));
        add(pnlChart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnexportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportActionPerformed
    }//GEN-LAST:event_btnexportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexport;
    private javax.swing.JButton btnthongke;
    private com.toedter.calendar.JYearChooser chonnam;
    private com.toedter.calendar.JMonthChooser chonthang;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChonNamBatDau;
    private javax.swing.JLabel lblChonNamKetThuc;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JTable tblthongke;
    // End of variables declaration//GEN-END:variables
}
