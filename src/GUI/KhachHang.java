package GUI;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.KHang.ThemKHang;
import GUI.KHang.ChiTietKHang;
import GUI.KHang.SuaKHang;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class KhachHang extends javax.swing.JPanel implements ActionListener {

    KhachHangBUS khachHangBUS = new KhachHangBUS();
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    ThemKHang themKH;
    SuaKHang suaKH;
    ChiTietKHang chiTietKH;
    ArrayList<KhachHangDTO> listKhachHang = khachHangBUS.getAllKhachHang();

    public KhachHang() {
        initComponents();
        addIcon();
        Color BackgroundColor = new Color(240, 247, 250);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);

        tblKhachHang.setFocusable(false);
        tblKhachHang.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblKhachHang.setFocusable(false);
        tblKhachHang.setAutoCreateRowSorter(true);

        btnThemKH.addActionListener(this);
        btnSuaKH.addActionListener(this);
        btnXoaKH.addActionListener(this);
        btnChiTietKH.addActionListener(this);
        btnXuatExcel1KH.addActionListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        hienThiListKhachHang(listKhachHang);
    }

    private void addIcon() {
        btnThemKH.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaKH.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaKH.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnChiTietKH.setIcon(new FlatSVGIcon("./icon/detail.svg"));
        btnXuatExcel1KH.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));
    }

    private void timKiemKhachHang(String keyword) {
        ArrayList<KhachHangDTO> ketQuaTimKiem = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tenKhachHang = (String) model.getValueAt(i, 1);
            if (tenKhachHang.toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(khachHangBUS.selectByID((int) model.getValueAt(i, 0))); // Thêm khách hàng vào danh sách kết quả
            }
            String soDienThoai = (String) model.getValueAt(i, 3);
            if (soDienThoai.contains(keyword)) {
                ketQuaTimKiem.add(khachHangBUS.selectByID((int) model.getValueAt(i, 0))); // Thêm khách hàng vào danh sách kết quả
            }
        }
        hienThiListKhachHang(ketQuaTimKiem);
    }

    public void hienThiListKhachHang(ArrayList<KhachHangDTO> listKhachHang) {
        khachHangBUS = new KhachHangBUS();
        khachHangDAO = new KhachHangDAO();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (KhachHangDTO khachHang : listKhachHang) {
            Object[] row = {
                khachHang.getMaKH(),
                khachHang.getHoten(),
                khachHang.getDiachi(),
                khachHang.getSdt(),
                khachHang.getNgaythamgia(),};
            model.addRow(row);
        }

//         Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
            tblKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void hienThiListKhachHang() {
        khachHangBUS = new KhachHangBUS();
        khachHangDAO = new KhachHangDAO();
        listKhachHang = khachHangBUS.getAllKhachHang();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        for (KhachHangDTO khachHang : listKhachHang) {
            Object[] row = {
                khachHang.getMaKH(),
                khachHang.getHoten(),
                khachHang.getDiachi(),
                khachHang.getSdt(),
                khachHang.getNgaythamgia(),};
            model.addRow(row);
        }

//         Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
            tblKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnChiTietKH = new javax.swing.JButton();
        btnXuatExcel1KH = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemKH.setText("Thêm");
        btnThemKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemKHMouseExited(evt);
            }
        });
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemKH);

        btnSuaKH.setText("Sửa");
        btnSuaKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaKHMouseExited(evt);
            }
        });
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });
        pnlTop.add(btnSuaKH);

        btnXoaKH.setText("Xóa");
        btnXoaKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaKHMouseExited(evt);
            }
        });
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoaKH);

        btnChiTietKH.setText("Chi tiết");
        btnChiTietKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChiTietKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChiTietKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChiTietKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChiTietKHMouseExited(evt);
            }
        });
        btnChiTietKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietKHActionPerformed(evt);
            }
        });
        pnlTop.add(btnChiTietKH);

        btnXuatExcel1KH.setText("Xuất excel");
        btnXuatExcel1KH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuatExcel1KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatExcel1KHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuatExcel1KHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuatExcel1KHMouseExited(evt);
            }
        });
        btnXuatExcel1KH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcel1KHActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcel1KH);

        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

        txtTimKiem.setPreferredSize(new java.awt.Dimension(150, 30));
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
        pnlTop.add(txtTimKiem);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTop.add(btnLamMoi);

        add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlCenter.setPreferredSize(new java.awt.Dimension(1200, 700));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Ngày tham gia"
            }
        ));
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnChiTietKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietKHActionPerformed

    private void btnXuatExcel1KHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcel1KHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcel1KHActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKHMouseEntered
        btnThemKH.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnThemKHMouseEntered

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnThemKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKHMouseExited
        // TODO add your handling code here:
        btnThemKH.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemKHMouseExited

    private void btnThemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKHMouseClicked
        // TODO add your handling code here:
        if (!btnThemKH.isSelected()) {
            btnThemKH.setBackground(Color.WHITE);
        } else {
            btnThemKH.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnThemKHMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listKhachHang = khachHangBUS.getAllKhachHang();
        hienThiListKhachHang(listKhachHang);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyword = txtTimKiem.getText().trim();
            timKiemKhachHang(keyword);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnSuaKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaKHMouseEntered
        // TODO add your handling code here:
        btnSuaKH.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnSuaKHMouseEntered

    private void btnSuaKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaKHMouseExited
        // TODO add your handling code here:
        btnSuaKH.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnSuaKHMouseExited

    private void btnSuaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaKHMouseClicked
        // TODO add your handling code here:
        if (!btnSuaKH.isSelected()) {
            btnSuaKH.setBackground(Color.WHITE);
        } else {
            btnSuaKH.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnSuaKHMouseClicked

    private void btnXoaKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKHMouseClicked
        // TODO add your handling code here:
        if (!btnXoaKH.isSelected()) {
            btnXoaKH.setBackground(Color.WHITE);
        } else {
            btnXoaKH.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnXoaKHMouseClicked

    private void btnXoaKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKHMouseEntered
        // TODO add your handling code here:
        btnXoaKH.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnXoaKHMouseEntered

    private void btnXoaKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKHMouseExited
        // TODO add your handling code here:
        btnXoaKH.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnXoaKHMouseExited

    private void btnChiTietKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietKHMouseClicked
        // TODO add your handling code here:
        if (!btnChiTietKH.isSelected()) {
            btnChiTietKH.setBackground(Color.WHITE);
        } else {
            btnChiTietKH.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnChiTietKHMouseClicked

    private void btnChiTietKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietKHMouseEntered
        // TODO add your handling code here:
        btnChiTietKH.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnChiTietKHMouseEntered

    private void btnChiTietKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietKHMouseExited
        // TODO add your handling code here:
        btnChiTietKH.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnChiTietKHMouseExited

    private void btnXuatExcel1KHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcel1KHMouseExited
        // TODO add your handling code here:
        btnXuatExcel1KH.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnXuatExcel1KHMouseExited

    private void btnXuatExcel1KHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcel1KHMouseEntered
        // TODO add your handling code here:
        btnXuatExcel1KH.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnXuatExcel1KHMouseEntered

    private void btnXuatExcel1KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcel1KHMouseClicked
        // TODO add your handling code here:
        if (!btnXuatExcel1KH.isSelected()) {
            btnXuatExcel1KH.setBackground(Color.WHITE);
        } else {
            btnXuatExcel1KH.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnXuatExcel1KHMouseClicked

    private void xoaKhachHang() {
        int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow != -1) {
            int maKH = (int) tblKhachHang.getValueAt(selectedRow, 0);
            KhachHangDTO canXoa = khachHangBUS.selectByID(maKH);
            khachHangBUS = new KhachHangBUS();
            boolean thanhCong = khachHangBUS.xoaKhachHang(maKH);
            if (thanhCong) {

                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
                listKhachHang = khachHangBUS.getAllKhachHang();
                hienThiListKhachHang(listKhachHang);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để xóa");
        }
    }

    private KhachHangDTO selectKhachHang() {
        int selectedRow = tblKhachHang.getSelectedRow();
        KhachHangDTO result = null;
        if (selectedRow != -1) {
            int maKH = (int) tblKhachHang.getValueAt(selectedRow, 0);
            khachHangBUS = new KhachHangBUS();
            result = khachHangBUS.selectByID(maKH);
        }
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThemKH) {
            themKH = new ThemKHang(this);
            themKH.setLocationRelativeTo(null);
            themKH.setVisible(true);
        } else if (e.getSource() == btnXoaKH) {
            xoaKhachHang();
        } else if (e.getSource() == btnSuaKH) {
            if (selectKhachHang() != null) {
                suaKH = new SuaKHang(selectKhachHang(), this);
                suaKH.setLocationRelativeTo(null);
                suaKH.setVisible(true);
                hienThiListKhachHang(listKhachHang);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng");
            }
        } else if (e.getSource() == btnXuatExcel1KH) {
            XuatExcel xuatExcel = new XuatExcel();
            try {
                xuatExcel.exportJTableToExcel(tblKhachHang);

            } catch (IOException ex) {
                Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnChiTietKH) {
            if (selectKhachHang() != null) {
                chiTietKH = new ChiTietKHang(selectKhachHang());
                chiTietKH.setLocationRelativeTo(null);
                chiTietKH.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng");
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietKH;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXuatExcel1KH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
