
package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DAO.ThongKeDAO;
import DTO.ThongKeTrongThangDTO;
import GUI.Component.Formater;
import GUI.Component.Export.JTableExporter;
import com.itextpdf.text.log.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ThongKeTuNgayDenNgay extends javax.swing.JPanel {
    ThongKeDAO thongKeDAO;
    ThongKeBUS thongKeBUS;
    DefaultTableModel tblModel;
    Formater formater = new Formater();
    public ThongKeTuNgayDenNgay() {
        initComponents();
        
        tblModel = (DefaultTableModel) tblthongkengay.getModel(); 
        thongKeBUS = new ThongKeBUS();
        
        startdate.addPropertyChangeListener("date", e -> {
            Date date = (Date) e.getNewValue();
            try {
                if (validateSelectDate()) {
                }
            } catch (ParseException ex) {
            }
        });
        enddate.addPropertyChangeListener("date", e -> {
            Date date = (Date) e.getNewValue();
            try {
                if (validateSelectDate()) {
                }
            } catch (ParseException ex) {
            }
        });
        
        btnthongke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateSelectDate()) {
                        if (startdate.getDate() != null && enddate.getDate() != null) {
                            thongKeDAO = new ThongKeDAO();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String start = formatter.format(startdate.getDate());
                            String end = formatter.format(enddate.getDate());
                            loadThongKeTungNgayDenNgay(start, end);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
                        }
                    }
                } catch (ParseException ex) {
                }
            }

        });
        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startdate.setDate(null);
                enddate.setDate(null);
                tblModel.setRowCount(0);
            }

        });
        btnExportExcel.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tblthongkengay);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ThongKeTuNgayDenNgay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maincontainer = new javax.swing.JPanel();
        pnltop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startdate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        enddate = new com.toedter.calendar.JDateChooser();
        btnthongke = new javax.swing.JButton();
        btnExportExcel = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        pnlbottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblthongkengay = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        maincontainer.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Từ ngày");
        pnltop.add(jLabel1);
        pnltop.add(startdate);

        jLabel2.setText("Đến ngày");
        pnltop.add(jLabel2);
        pnltop.add(enddate);

        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });
        pnltop.add(btnthongke);

        btnExportExcel.setText("Xuất Excel");
        pnltop.add(btnExportExcel);

        btnreset.setText("Làm mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        pnltop.add(btnreset);

        maincontainer.add(pnltop, java.awt.BorderLayout.PAGE_START);

        pnlbottom.setLayout(new java.awt.BorderLayout());

        tblthongkengay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ));
        jScrollPane1.setViewportView(tblthongkengay);

        pnlbottom.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        maincontainer.add(pnlbottom, java.awt.BorderLayout.CENTER);

        add(maincontainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public boolean validateSelectDate() throws ParseException {
        Date time_start = startdate.getDate();
        Date time_end = enddate.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            startdate.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            enddate.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            enddate.setCalendar(null);
            return false;
        }
        return true;
    }

    public void loadThongKeTungNgayDenNgay(String start, String end) {
        ArrayList<ThongKeTrongThangDTO> list = thongKeDAO.getThongKeTuNgayDenNgay(start, end);
        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            tblModel.addRow(new Object[]{
                list.get(i).getNgay(), formater.FormatVND(list.get(i).getChiphi()), formater.FormatVND(list.get(i).getDoanhthu()), formater.FormatVND(list.get(i).getLoinhuan())
            });
        }
        formater.setColumnAlignment(tblthongkengay);
    }
    
    
    
    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
    }//GEN-LAST:event_btnresetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnthongke;
    private com.toedter.calendar.JDateChooser enddate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel maincontainer;
    private javax.swing.JPanel pnlbottom;
    private javax.swing.JPanel pnltop;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JTable tblthongkengay;
    // End of variables declaration//GEN-END:variables
}
