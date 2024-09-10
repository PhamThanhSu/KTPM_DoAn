package GUI;

import BUS.KhuVucKhoBUS;
import DAO.KhuVucKhoDAO;
import DTO.KhuVucKhoDTO;
import GUI.KhuVucKhoOpTions.SuaKhuVucKho;
import GUI.KhuVucKhoOpTions.ThemKhuVucKho;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class KhuVucKho extends javax.swing.JPanel {

    /**
     * Creates new form KhuVucKho
     */
    ThemKhuVucKho themKhuVucKho;
    KhuVucKhoDAO khuVucKhoDAO;
    KhuVucKhoBUS khuVucKhoBUS;
    SuaKhuVucKho suaKhuVucKho;
    Color BackgroundColor = new Color(240, 247, 250);

    public KhuVucKho() {
        initComponents();
//        setSize(1200, 800);
        addIcon();
        tblKho.setFocusable(false);
        tblKho.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblKho.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblKho.setFocusable(false);
        tblKho.setAutoCreateRowSorter(true);
        hienThiListKhuVucKho();

        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        pnlCenter.setBackground(BackgroundColor);
    }

    private void addIcon() {
        btnThemKho.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaKho.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaKho.setIcon(new FlatSVGIcon("./icon/delete.svg"));
//            btnNhapExcelKho.setIcon(new FlatSVGIcon("./icon/detail.svg"));
        btnXuatExcelKho.setIcon(new FlatSVGIcon("./icon/import_excel.svg"));

    }

    public ArrayList<DTO.KhuVucKhoDTO> listKhuVucKho;

    public void hienThiListKhuVucKho() {
        khuVucKhoDAO = new KhuVucKhoDAO();
        khuVucKhoBUS = new KhuVucKhoBUS();
        listKhuVucKho = khuVucKhoBUS.getAllKho();
        DefaultTableModel model = (DefaultTableModel) tblKho.getModel();
        model.setRowCount(0);

        for (KhuVucKhoDTO khuvuc : listKhuVucKho) {
            Object[] row = {
                khuvuc.getMakhuvuc(),
                khuvuc.getTenkhuvuc(),
                khuvuc.getGhichu(),};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblKho.getColumnCount(); i++) {
            tblKho.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private KhuVucKhoDTO selectKhuVucKho() {
        int selectedRow = tblKho.getSelectedRow();
        KhuVucKhoDTO result = null;
        if (selectedRow != -1) {
            int makhuvuc = (int) tblKho.getValueAt(selectedRow, 0);
            khuVucKhoBUS = new KhuVucKhoBUS();
            result = khuVucKhoBUS.selectByID(makhuvuc);
        }
        return result;
    }

    private void timKiemKhuVucKho(String keyword) {
        ArrayList<KhuVucKhoDTO> ketQuaTimKiem = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tblKho.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tenKhuVucKho = (String) model.getValueAt(i, 1);
            int maKhuVucKho = (int) model.getValueAt(i, 0);

            if (tenKhuVucKho.toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(khuVucKhoBUS.selectByID(maKhuVucKho));
            }
        }
        hienThiListKhuVucKho(ketQuaTimKiem);
    }

    public void hienThiListKhuVucKho(ArrayList<KhuVucKhoDTO> a) {
        khuVucKhoDAO = new KhuVucKhoDAO();
        khuVucKhoBUS = new KhuVucKhoBUS();
        listKhuVucKho = khuVucKhoBUS.getAllKho();
        DefaultTableModel model = (DefaultTableModel) tblKho.getModel();
        model.setRowCount(0);

        for (KhuVucKhoDTO khuvuc : a) {
            Object[] row = {
                khuvuc.getMakhuvuc(),
                khuvuc.getTenkhuvuc(),
                khuvuc.getGhichu(),};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblKho.getColumnCount(); i++) {
            tblKho.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        btnThemKho = new javax.swing.JButton();
        btnSuaKho = new javax.swing.JButton();
        btnXoaKho = new javax.swing.JButton();
        btnXuatExcelKho = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKho = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemKho.setText("Thêm");
        btnThemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhoActionPerformed(evt);
            }
        });
        panelTop.add(btnThemKho);

        btnSuaKho.setText("Sửa");
        btnSuaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKhoActionPerformed(evt);
            }
        });
        panelTop.add(btnSuaKho);

        btnXoaKho.setText("Xóa");
        btnXoaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoActionPerformed(evt);
            }
        });
        panelTop.add(btnXoaKho);

        btnXuatExcelKho.setText("Xuất excel");
        btnXuatExcelKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelKhoActionPerformed(evt);
            }
        });
        panelTop.add(btnXuatExcelKho);

        jLabel62.setText("Tìm kiếm :");
        panelTop.add(jLabel62);

        txtTimKiem.setPreferredSize(new java.awt.Dimension(200, 30));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });
        panelTop.add(txtTimKiem);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(130, 60));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        panelTop.add(btnLamMoi);

        add(panelTop, java.awt.BorderLayout.NORTH);

        pnlCenter.setPreferredSize(new java.awt.Dimension(1200, 700));

        tblKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã kho", "Tên khu vực", "Địa chỉ"
            }
        ));
        jScrollPane1.setViewportView(tblKho);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhoActionPerformed
        // TODO add your handling code here:
        themKhuVucKho = new ThemKhuVucKho(this);
        themKhuVucKho.setVisible(true);

    }//GEN-LAST:event_btnThemKhoActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnSuaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKhoActionPerformed
        // TODO add your handling code here:
        if (selectKhuVucKho() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khu vực kho để sửa");
        }
        suaKhuVucKho = new SuaKhuVucKho(selectKhuVucKho(), this);
        suaKhuVucKho.setLocationRelativeTo(null);
        suaKhuVucKho.setVisible(true);
    }//GEN-LAST:event_btnSuaKhoActionPerformed

    private void btnXoaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblKho.getSelectedRow();
        if (selectedRow != -1) {
            int maKhuVuc = (int) tblKho.getValueAt(selectedRow, 0);
            khuVucKhoBUS = new KhuVucKhoBUS();
            boolean thanhCong = khuVucKhoBUS.xoaKhuVucKho(maKhuVuc);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Xóa khu vực kho thành công");
                listKhuVucKho = khuVucKhoBUS.getAllKho();
                hienThiListKhuVucKho();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa khu vực kho lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khu vực kho để xóa");
        }
    }//GEN-LAST:event_btnXoaKhoActionPerformed

    private void btnXuatExcelKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelKhoActionPerformed
        try {
            // TODO add your handling code here:
            XuatExcel.exportJTableToExcel(tblKho);
            JOptionPane.showMessageDialog(null, "Xuất thành công");
        } catch (IOException ex) {
            Logger.getLogger(KhuVucKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcelKhoActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyword = txtTimKiem.getText().trim();
            timKiemKhuVucKho(keyword);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listKhuVucKho = khuVucKhoBUS.getAllKho();
        hienThiListKhuVucKho();
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaKho;
    private javax.swing.JButton btnThemKho;
    private javax.swing.JButton btnXoaKho;
    private javax.swing.JButton btnXuatExcelKho;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JTable tblKho;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
