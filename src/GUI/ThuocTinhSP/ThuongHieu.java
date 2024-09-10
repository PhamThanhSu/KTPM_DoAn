/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThuocTinhSP;

import BUS.ThuongHieuBUS;
import DTO.ThuongHieuDTO;
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
public class ThuongHieu extends javax.swing.JPanel {

    /**
     * Creates new form ThuongHIeu
     */
    ThuongHieuBUS thuongHieuBUS;
    ThuongHieuDTO thuongHieuDTO;
    Color BackgroundColor = new Color(240, 247, 250);

    public ThuongHieu() {
        initComponents();
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(BackgroundColor);
        pnlTop.setBackground(BackgroundColor);
        pnlTop.setBorder(new EmptyBorder(0, 0, 10, 0));
        pnlLeft.setOpaque(false);
        
        lblThuongHieu.setFont(new Font("Tahoma", Font.BOLD, 20));
        hienThiListThuongHieu();

        tblThuongHieu.setFocusable(false);
        tblThuongHieu.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table

        tblThuongHieu.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            chonDongTrongBang();
        });

        btnThemThuongHieu.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaThuongHieu.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaThuongHieu.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        

    }

    private void chonDongTrongBang() {
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
            // Lấy giá trị tên thương hiệu từ cột thứ hai (index 1)
            String tenThuongHieu = tblThuongHieu.getValueAt(selectedRow, 1).toString();
            // Đặt giá trị tên thương hiệu vào ô txtTenThuongHieu
            txtTenThuongHieu.setText(tenThuongHieu);
        }
    }

    private void themThuongHieu() {

        // lấy dữ liệu cần thêm
        String tenThuongHieuThem = txtTenThuongHieu.getText().trim();

        if (!tenThuongHieuThem.isEmpty()) {
            thuongHieuDTO = new ThuongHieuDTO();
            // đối tượng cần thêm
            thuongHieuDTO.setTenthuonghieu(tenThuongHieuThem);

            thuongHieuBUS = new ThuongHieuBUS();
            boolean thanhCong = thuongHieuBUS.themThuongHieu(thuongHieuDTO);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Thêm thương hiệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Cập nhật lại bảng hoặc danh sách thương hiệu
                hienThiListThuongHieu();
                // Xóa dữ liệu trong ô nhập liệu
                txtTenThuongHieu.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên thương hiệu", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
    }

    private void xoaThuongHieu() {
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow != -1) {
            int maThuongHieu = (int) tblThuongHieu.getValueAt(selectedRow, 0);
            thuongHieuBUS = new ThuongHieuBUS();

            boolean thanhCong = thuongHieuBUS.xoaThuongHieu(maThuongHieu);
            if (thanhCong) {
                DefaultTableModel model = (DefaultTableModel) tblThuongHieu.getModel();
                model.removeRow(selectedRow);
                txtTenThuongHieu.setText("");
                JOptionPane.showMessageDialog(null, "Đã xóa thương hiệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thương hiệu thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thương hiệu để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void suaThuongHieu() {
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow != -1) {
            // lấy dữ liệu cần sửa và update tên
            int maThuongHieu = (int) tblThuongHieu.getValueAt(selectedRow, 0);
            String tenThuongHieuMoi = txtTenThuongHieu.getText();
            // tạo đối tượng DTO;
            thuongHieuDTO = new ThuongHieuDTO();
            thuongHieuDTO.setMathuonghieu(maThuongHieu);
            thuongHieuDTO.setTenthuonghieu(tenThuongHieuMoi);

            thuongHieuBUS = new ThuongHieuBUS();
            boolean thanhCong = thuongHieuBUS.suaThuongHieu(thuongHieuDTO);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin thương hiệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListThuongHieu();
            } else {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thương hiệu để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // đổ dữ liệu vào table
    private void hienThiListThuongHieu() {
        thuongHieuBUS = new ThuongHieuBUS();
        ArrayList<ThuongHieuDTO> listThuongHieu = thuongHieuBUS.getAllThuongHieu();
        DefaultTableModel model = (DefaultTableModel) tblThuongHieu.getModel();
        model.setRowCount(0);
        for (ThuongHieuDTO thuongHieu : listThuongHieu) {
            Object[] row = {thuongHieu.getMathuonghieu(), thuongHieu.getTenthuonghieu()};
            model.addRow(row);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblThuongHieu.getColumnCount(); i++) {
            tblThuongHieu.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollThuongHieu = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        pnlTop = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblThuongHieu = new javax.swing.JLabel();
        tenThuongHieu = new javax.swing.JPanel();
        lblTenThuongHieu = new javax.swing.JLabel();
        txtTenThuongHieu = new javax.swing.JTextField();
        pnlLeft = new javax.swing.JPanel();
        btnThemThuongHieu = new javax.swing.JButton();
        btnSuaThuongHieu = new javax.swing.JButton();
        btnXoaThuongHieu = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thương hiệu", "Tên thương hiệu"
            }
        ));
        scrollThuongHieu.setViewportView(tblThuongHieu);

        add(scrollThuongHieu, java.awt.BorderLayout.CENTER);

        title.setBackground(new java.awt.Color(0, 102, 255));
        title.setForeground(new java.awt.Color(255, 255, 255));

        lblThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        lblThuongHieu.setText("THƯƠNG HIỆU SẢN PHẨM");
        title.add(lblThuongHieu);

        tenThuongHieu.setBackground(new java.awt.Color(255, 255, 255));

        lblTenThuongHieu.setText("Tên thương hiệu :");
        tenThuongHieu.add(lblTenThuongHieu);

        txtTenThuongHieu.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTenThuongHieu.setPreferredSize(new java.awt.Dimension(150, 30));
        tenThuongHieu.add(txtTenThuongHieu);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlLeft.setPreferredSize(new java.awt.Dimension(130, 580));

        btnThemThuongHieu.setText("Thêm");
        btnThemThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuongHieuActionPerformed(evt);
            }
        });

        btnSuaThuongHieu.setText("Sửa");
        btnSuaThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuongHieuActionPerformed(evt);
            }
        });

        btnXoaThuongHieu.setText("Xóa");
        btnXoaThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuongHieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThemThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(btnSuaThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoaThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnThemThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        add(pnlLeft, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuongHieuActionPerformed
        // TODO add your handling code here: 
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            xoaThuongHieu();
        }

    }//GEN-LAST:event_btnXoaThuongHieuActionPerformed

    private void btnSuaThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuongHieuActionPerformed
        // TODO add your handling code here:
        suaThuongHieu();
    }//GEN-LAST:event_btnSuaThuongHieuActionPerformed

    private void btnThemThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuongHieuActionPerformed
        // TODO add your handling code here:
        themThuongHieu();
    }//GEN-LAST:event_btnThemThuongHieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaThuongHieu;
    private javax.swing.JButton btnThemThuongHieu;
    private javax.swing.JButton btnXoaThuongHieu;
    private javax.swing.JLabel lblTenThuongHieu;
    private javax.swing.JLabel lblThuongHieu;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrollThuongHieu;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JPanel tenThuongHieu;
    private javax.swing.JPanel title;
    private javax.swing.JTextField txtTenThuongHieu;
    // End of variables declaration//GEN-END:variables
}
