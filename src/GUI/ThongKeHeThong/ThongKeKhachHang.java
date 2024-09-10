/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeKhachHangDTO;
import GUI.XuatExcel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phatl
 */
public class ThongKeKhachHang extends javax.swing.JPanel implements ActionListener, KeyListener, PropertyChangeListener {

    /**
     * Creates new form ThongKeKhachHang
     */
    ThongKeBUS thongKeBUS;
    ArrayList<ThongKeKhachHangDTO> listkhTH;
    JDateChooser dateStart = new JDateChooser();
    JDateChooser dateEnd = new JDateChooser();

    public ThongKeKhachHang() {
        initComponents();
        thongKeBUS = new ThongKeBUS();
        listkhTH = thongKeBUS.getAllKhachHang();
        themDuLieuVaoBang(listkhTH);
        addSelectDate();

        txtTenKhachHang.putClientProperty("JTextField.showClearButton", true);
        txtTenKhachHang.addKeyListener(this);
        dateStart.addPropertyChangeListener(this);
        dateEnd.addPropertyChangeListener(this);

        tblThongKeKhachHang.setAutoCreateRowSorter(true);
        tblThongKeKhachHang.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tblThongKeKhachHang.columnAtPoint(e.getPoint());
                if (col == 3 || col == 4) { // Số lượt phiếu và Tổng tiền
                    Collections.sort(listkhTH, (a, b) -> {
                        if (col == 3) {
                            return Integer.compare(a.getSoluongphieu(), b.getSoluongphieu());
                        } else {
                            return Long.compare(a.getTongtien(), b.getTongtien());
                        }
                    });
                    themDuLieuVaoBang(listkhTH);
                }
            }
        });

        configureTableColumnSorter(tblThongKeKhachHang, 4, VND_CURRENCY_COMPARATOR);
        btnXuatExcel.setBackground(Color.green);
    }

    public static void configureTableColumnSorter(JTable table, int columnIndex, Comparator<Object> comparator) {
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(tblModel);
            table.setRowSorter(sorter);
        }
        sorter.setComparator(columnIndex, comparator);
    }

    public Comparator<Object> VND_CURRENCY_COMPARATOR = (Object o1, Object o2) -> {
        String s1 = (String) o1;
        String s2 = (String) o2;

        String cleanO1 = s1.replaceAll("[^\\d]", "");
        String cleanO2 = s2.replaceAll("[^\\d]", "");

        if (cleanO1.isEmpty() && cleanO2.isEmpty()) {
            return 0;
        } else if (cleanO1.isEmpty()) {
            return -1;
        } else if (cleanO2.isEmpty()) {
            return 1;
        }

        Long n1 = Long.valueOf(cleanO1);
        Long n2 = Long.valueOf(cleanO2);

        return Long.compare(n1, n2);
    };

    private void addSelectDate() {
        dateStart.setDateFormatString("dd/MM/yyyy");
        dateStart.setBackground(Color.white);
        pnlStartDate.add(dateStart).setVisible(true);

        dateEnd.setDateFormatString("dd/MM/yyyy");
        dateEnd.setBackground(Color.white);
        pnlStartEnd.add(dateEnd).setVisible(true);
    }

    public void loc() throws ParseException {
        if (checkDate()) {
            String input = txtTenKhachHang.getText();
            if (input == null) {
                input = "";
            }

            Date time_start = dateStart.getDate();
            if (time_start == null) {
                time_start = new Date(0);
            }

            Date time_end = dateEnd.getDate();
            if (time_end == null) {
                time_end = new Date(System.currentTimeMillis());
            }

            this.listkhTH = thongKeBUS.locKhachHang(input, new Date(time_start.getTime()), new Date(time_end.getTime()));
            themDuLieuVaoBang(listkhTH);
        }
    }

    public boolean checkDate() throws ParseException {
        Date time_start = dateStart.getDate();
        Date time_end = dateEnd.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateStart.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.setCalendar(null);
            return false;
        }
        return true;
    }

    private void themDuLieuVaoBang(ArrayList<ThongKeKhachHangDTO> list) {
        DefaultTableModel model = (DefaultTableModel) tblThongKeKhachHang.getModel();
        model.setRowCount(0);

        int k = 1;
        for (ThongKeKhachHangDTO i : list) {
            Object[] row
                    = {k,
                        i.getMakh(),
                        i.getTenkh(),
                        i.getSoluongphieu(),
                        formatTien(i.getTongtien())
                    };
            model.addRow(row);
            k++;
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblThongKeKhachHang.getColumnCount(); i++) {
            tblThongKeKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public String formatTien(double tien) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0 VND");
        return decimalFormat.format(tien);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLeft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        pnlStartDate = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlStartEnd = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnXuatExcel = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongKeKhachHang = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLeft.setPreferredSize(new java.awt.Dimension(285, 300));

        jLabel1.setText("Tên khách hàng :");

        txtTenKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhachHangKeyReleased(evt);
            }
        });

        pnlStartDate.setBackground(new java.awt.Color(255, 255, 255));
        pnlStartDate.setLayout(new javax.swing.BoxLayout(pnlStartDate, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Từ ngày :");

        pnlStartEnd.setBackground(new java.awt.Color(255, 255, 255));
        pnlStartEnd.setLayout(new javax.swing.BoxLayout(pnlStartEnd, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Đến ngày :");

        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStartEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlStartEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        add(pnlLeft, java.awt.BorderLayout.WEST);

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyReleased(evt);
            }
        });

        tblThongKeKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Số lượt phiếu", "Tổng tiền"
            }
        ));
        jScrollPane2.setViewportView(tblThongKeKhachHang);
        if (tblThongKeKhachHang.getColumnModel().getColumnCount() > 0) {
            tblThongKeKhachHang.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachHangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangKeyReleased

    private void jScrollPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2KeyReleased

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
        XuatExcel xuatExcel = new XuatExcel();
        try {
            xuatExcel.exportJTableToExcel(tblThongKeKhachHang);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlStartDate;
    private javax.swing.JPanel pnlStartEnd;
    private javax.swing.JTable tblThongKeKhachHang;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            loc();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            loc();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
