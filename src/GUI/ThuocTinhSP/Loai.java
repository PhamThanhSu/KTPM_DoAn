/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThuocTinhSP;

import BUS.LoaiBUS;
import BUS.ThuongHieuBUS;
import DTO.LoaiDTO;
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
public class Loai extends javax.swing.JPanel {

    /**
     * Creates new form ThuongHIeu
     */
    LoaiBUS loaiBUS;
    LoaiDTO loaiDTO;
    Color BackgroundColor = new Color(240, 247, 250);

    public Loai() {
        initComponents();
        
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(BackgroundColor);
        pnlTop.setBackground(BackgroundColor);
        pnlTop.setBorder(new EmptyBorder(0, 0, 10, 0));
        pnlLeft.setOpaque(false);
        
        lblLoai.setFont(new Font("Tahoma", Font.BOLD, 20));
        hienThiListLoai();
        tblLoai.setFocusable(false);
        tblLoai.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table

        tblLoai.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            chonDongTrongBang();
        });
        btnThemLoai.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaLoai.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaLoai.setIcon(new FlatSVGIcon("./icon/delete.svg"));

       
    }

    private void chonDongTrongBang() {
        int selectedRow = tblLoai.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
            // Lấy giá trị tên thương hiệu từ cột thứ hai (index 1)
            String tenThuongHieu = tblLoai.getValueAt(selectedRow, 1).toString();
            // Đặt giá trị tên thương hiệu vào ô txtTenThuongHieu
            txtTenLoai.setText(tenThuongHieu);
        }
    }

    private void hienThiListLoai() {
        loaiBUS = new LoaiBUS();
        ArrayList<LoaiDTO> listLoai = loaiBUS.getAllLoai();
        DefaultTableModel model = (DefaultTableModel) tblLoai.getModel();
        model.setRowCount(0);
        for (LoaiDTO loai : listLoai) {
            Object[] row = {loai.getMaloai(), loai.getTenloai()};
            model.addRow(row);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblLoai.getColumnCount(); i++) {
            tblLoai.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void suaLoai() {
        int selectedRow = tblLoai.getSelectedRow();
        if (selectedRow != -1) {
            // lấy dữ liệu cần sửa và update tên
            int maLoai = (int) tblLoai.getValueAt(selectedRow, 0);
            String tenThuongLoaiMoi = txtTenLoai.getText();
            // tạo đối tượng DTO;
            loaiDTO = new LoaiDTO();
            loaiDTO.setMaloai(maLoai);
            loaiDTO.setTenloai(tenThuongLoaiMoi);

            loaiBUS = new LoaiBUS();
            boolean thanhCong = loaiBUS.suaLoai(loaiDTO);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin loại thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListLoai();
                txtTenLoai.setText("");
            } else {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một loại để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void themLoai() {
        String tenLoaiThem = txtTenLoai.getText().trim();
        if (!tenLoaiThem.isEmpty()) {
            loaiDTO = new LoaiDTO();
            loaiDTO.setTenloai(tenLoaiThem);
            loaiBUS = new LoaiBUS();
            boolean thanhCong = loaiBUS.themLoai(loaiDTO);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Thêm thương hiệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Cập nhật lại bảng hoặc danh sách thương hiệu
                hienThiListLoai();
                // Xóa dữ liệu trong ô nhập liệu
                txtTenLoai.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên thương hiệu", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
    }

    private void xoaLoai() {
        int selectedRow = tblLoai.getSelectedRow();
        if (selectedRow != -1) {
            int maLoai = (int) tblLoai.getValueAt(selectedRow, 0);
            loaiBUS = new LoaiBUS();

            boolean thanhCong = loaiBUS.xoaLoai(maLoai);
            if (thanhCong) {
                txtTenLoai.setText("");
                JOptionPane.showMessageDialog(null, "Đã xóa thương hiệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListLoai();
            } else {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thương hiệu để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollThuongHieu = new javax.swing.JScrollPane();
        tblLoai = new javax.swing.JTable();
        pnlTop = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblLoai = new javax.swing.JLabel();
        tenThuongHieu = new javax.swing.JPanel();
        lblTenLoai = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        pnlLeft = new javax.swing.JPanel();
        btnThemLoai = new javax.swing.JButton();
        btnSuaLoai = new javax.swing.JButton();
        btnXoaLoai = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tblLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ));
        scrollThuongHieu.setViewportView(tblLoai);

        add(scrollThuongHieu, java.awt.BorderLayout.CENTER);

        title.setBackground(new java.awt.Color(0, 102, 255));
        title.setForeground(new java.awt.Color(255, 255, 255));

        lblLoai.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblLoai.setForeground(new java.awt.Color(255, 255, 255));
        lblLoai.setText("LOẠI SẢN PHẨM");
        title.add(lblLoai);

        tenThuongHieu.setBackground(new java.awt.Color(255, 255, 255));

        lblTenLoai.setText("Tên loại :");
        tenThuongHieu.add(lblTenLoai);

        txtTenLoai.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTenLoai.setPreferredSize(new java.awt.Dimension(150, 30));
        tenThuongHieu.add(txtTenLoai);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
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

        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        btnSuaLoai.setText("Sửa");
        btnSuaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiActionPerformed(evt);
            }
        });

        btnXoaLoai.setText("Xóa");
        btnXoaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThemLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(btnSuaLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoaLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        add(pnlLeft, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        // TODO add your handling code here:
        themLoai();
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnSuaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiActionPerformed
        // TODO add your handling code here:
        suaLoai();
    }//GEN-LAST:event_btnSuaLoaiActionPerformed

    private void btnXoaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            xoaLoai();
        }

    }//GEN-LAST:event_btnXoaLoaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaLoai;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnXoaLoai;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblTenLoai;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrollThuongHieu;
    private javax.swing.JTable tblLoai;
    private javax.swing.JPanel tenThuongHieu;
    private javax.swing.JPanel title;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
