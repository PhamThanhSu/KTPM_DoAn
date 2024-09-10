/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThuocTinhSP;

import BUS.XuatXuBUS;
import DTO.ThuocTinhSanPham.XuatXuDTO;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phatl
 */
public class XuatXu extends javax.swing.JPanel {

    /**
     * Creates new form XuatXu
     */
    Color BackgroundColor = new Color(240, 247, 250);

    public XuatXu() {
        initComponents();
        hienThiListXuatXu();

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(BackgroundColor);
        pnlTop.setBackground(BackgroundColor);
        pnlTop.setBorder(new EmptyBorder(0, 0, 10, 0));
        pnlLeft.setOpaque(false);

        lblXuatXu.setFont(new Font("Tahoma", Font.BOLD, 20));
        tblXuatXu.setFocusable(false);
        tblXuatXu.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table

        tblXuatXu.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            chonDongTrongBang();
        });

        btnThemXuatXu.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaXuatXu.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaXuatXu.setIcon(new FlatSVGIcon("./icon/delete.svg"));
    }

    private void chonDongTrongBang() {
        int selectedRow = tblXuatXu.getSelectedRow();
        if (selectedRow != -1) {
            String tenXuatXu = tblXuatXu.getValueAt(selectedRow, 1).toString(); // Thay đổi tên biến
            txtTenXuatXu.setText(tenXuatXu); // Thay đổi tên biến
        }
    }

    private void hienThiListXuatXu() { // Thay đổi tên phương thức
        XuatXuBUS xuatXuBUS = new XuatXuBUS(); // Thay đổi tên biến
        ArrayList<XuatXuDTO> listXuatXu = xuatXuBUS.getAllXuatXu(); // Thay đổi tên biến
        DefaultTableModel model = (DefaultTableModel) tblXuatXu.getModel();
        model.setRowCount(0);
        for (XuatXuDTO xuatXu : listXuatXu) { // Thay đổi tên biến
            Object[] row = {xuatXu.getMaxuatxu(), xuatXu.getTenxuatxu()}; // Thay đổi tên biến
            model.addRow(row);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblXuatXu.getColumnCount(); i++) {
            tblXuatXu.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void themXuatXu() {
        String tenXuatXuThem = txtTenXuatXu.getText().trim();
        if (!tenXuatXuThem.isEmpty()) {
            XuatXuDTO xuatXuDTO = new XuatXuDTO();
            xuatXuDTO.setTenxuatxu(tenXuatXuThem);
            XuatXuBUS xuatXuBUS = new XuatXuBUS(); // Thay đổi tên biến và khởi tạo đối tượng
            boolean thanhCong = xuatXuBUS.themXuatXu(xuatXuDTO); // Thay đổi tên phương thức và gọi phương thức thêm xuất xứ
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Thêm xuất xứ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListXuatXu(); // Thay đổi tên phương thức
                txtTenXuatXu.setText(""); // Xóa dữ liệu trong ô nhập liệu
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên xuất xứ", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void suaXuatXu() {
        int selectedRow = tblXuatXu.getSelectedRow();
        if (selectedRow != -1) {
            int maxuatxu = (int) tblXuatXu.getValueAt(selectedRow, 0);
            String tenXuatXuMoi = txtTenXuatXu.getText();
            XuatXuDTO xuatXuDTO = new XuatXuDTO();
            xuatXuDTO.setMaxuatxu(maxuatxu);
            xuatXuDTO.setTenxuatxu(tenXuatXuMoi);
            XuatXuBUS xuatXuBUS = new XuatXuBUS();
            boolean thanhCong = xuatXuBUS.suaXuatXu(xuatXuDTO);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin xuất xứ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListXuatXu();
                txtTenXuatXu.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một xuất xứ để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void xoaXuatXu() {
        int selectedRow = tblXuatXu.getSelectedRow();
        if (selectedRow != -1) {
            int maxuatxu = (int) tblXuatXu.getValueAt(selectedRow, 0);
            XuatXuBUS xuatXuBUS = new XuatXuBUS();
            boolean thanhCong = xuatXuBUS.xoaXuatXu(maxuatxu);
            if (thanhCong) {
                txtTenXuatXu.setText("");
                JOptionPane.showMessageDialog(null, "Đã xóa xuất xứ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListXuatXu();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một xuất xứ để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollThuongHieu = new javax.swing.JScrollPane();
        tblXuatXu = new javax.swing.JTable();
        pnlTop = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblXuatXu = new javax.swing.JLabel();
        tenThuongHieu = new javax.swing.JPanel();
        lblTenXuatXu = new javax.swing.JLabel();
        txtTenXuatXu = new javax.swing.JTextField();
        pnlLeft = new javax.swing.JPanel();
        btnThemXuatXu = new javax.swing.JButton();
        btnSuaXuatXu = new javax.swing.JButton();
        btnXoaXuatXu = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tblXuatXu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã xuất xứ", "Tên xuất xứ"
            }
        ));
        scrollThuongHieu.setViewportView(tblXuatXu);

        add(scrollThuongHieu, java.awt.BorderLayout.CENTER);

        title.setBackground(new java.awt.Color(0, 102, 255));
        title.setForeground(new java.awt.Color(255, 255, 255));

        lblXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblXuatXu.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatXu.setText("XUẤT XỨ SẢN PHẨM");
        title.add(lblXuatXu);

        tenThuongHieu.setBackground(new java.awt.Color(255, 255, 255));

        lblTenXuatXu.setText("Tên xuất xứ:");
        tenThuongHieu.add(lblTenXuatXu);

        txtTenXuatXu.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTenXuatXu.setPreferredSize(new java.awt.Dimension(150, 30));
        tenThuongHieu.add(txtTenXuatXu);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlLeft.setPreferredSize(new java.awt.Dimension(130, 580));

        btnThemXuatXu.setText("Thêm");
        btnThemXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatXuActionPerformed(evt);
            }
        });

        btnSuaXuatXu.setText("Sửa");
        btnSuaXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaXuatXuActionPerformed(evt);
            }
        });

        btnXoaXuatXu.setText("Xóa");
        btnXoaXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaXuatXuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThemXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(btnSuaXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoaXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnThemXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        add(pnlLeft, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatXuActionPerformed
        // TODO add your handling code here:
        themXuatXu();
    }//GEN-LAST:event_btnThemXuatXuActionPerformed

    private void btnSuaXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaXuatXuActionPerformed
        // TODO add your handling code here:
        suaXuatXu();
    }//GEN-LAST:event_btnSuaXuatXuActionPerformed

    private void btnXoaXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaXuatXuActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            xoaXuatXu();
        }

    }//GEN-LAST:event_btnXoaXuatXuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaXuatXu;
    private javax.swing.JButton btnThemXuatXu;
    private javax.swing.JButton btnXoaXuatXu;
    private javax.swing.JLabel lblTenXuatXu;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrollThuongHieu;
    private javax.swing.JTable tblXuatXu;
    private javax.swing.JPanel tenThuongHieu;
    private javax.swing.JPanel title;
    private javax.swing.JTextField txtTenXuatXu;
    // End of variables declaration//GEN-END:variables
}
