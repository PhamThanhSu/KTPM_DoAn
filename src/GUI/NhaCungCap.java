package GUI;

import BUS.NhaCungCapBUS;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.NCCap.ThemNCCap;
import GUI.NCCap.SuaNCCap;
import GUI.NCCap.ChiTietNCCap;
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

public class NhaCungCap extends javax.swing.JPanel implements ActionListener {

    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    ThemNCCap themNCC;
    SuaNCCap suaNCC;
    ChiTietNCCap chiTietNCC;
    ArrayList<NhaCungCapDTO> listNhaCungCap = nhaCungCapBUS.getAllNhaCungCap();

    public NhaCungCap() {
        initComponents();

        Color BackgroundColor = new Color(240, 247, 250);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);
        addIcon();
        tblNhaCC.setFocusable(false);
        tblNhaCC.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblNhaCC.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblNhaCC.setFocusable(false);
        tblNhaCC.setAutoCreateRowSorter(true);

        btnThemNhaCC.addActionListener(this);
        btnSuaNhaCC.addActionListener(this);
        btnXoaNhaCC.addActionListener(this);
        btnChiTietNCC.addActionListener(this);
        btnXuatExcelNCC.addActionListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        hienThiListNhaCungCap(listNhaCungCap);
    }

    private void addIcon() {
        btnThemNhaCC.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaNhaCC.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaNhaCC.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnChiTietNCC.setIcon(new FlatSVGIcon("./icon/detail.svg"));
        btnXuatExcelNCC.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));

    }

    private void timKiemNhaCungCap(String keyword) {
        ArrayList<NhaCungCapDTO> ketQuaTimKiem = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tblNhaCC.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tenNhaCungCap = (String) model.getValueAt(i, 1);
            if (tenNhaCungCap.toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(nhaCungCapBUS.selectByID((int) model.getValueAt(i, 0))); // Thêm ncc vào danh sách kết quả
            }
            String soDienThoai = (String) model.getValueAt(i, 4);
            if (soDienThoai.contains(keyword)) {
                ketQuaTimKiem.add(nhaCungCapBUS.selectByID((int) model.getValueAt(i, 0))); // Thêm nccvào danh sách kết quả
            }
        }
        hienThiListNhaCungCap(ketQuaTimKiem);
    }

    public void hienThiListNhaCungCap(ArrayList<NhaCungCapDTO> listNhaCungCap) {
        nhaCungCapBUS = new NhaCungCapBUS();
        nhaCungCapDAO = new NhaCungCapDAO();
        DefaultTableModel model = (DefaultTableModel) tblNhaCC.getModel();
        model.setRowCount(0);
        for (NhaCungCapDTO nhaCungCap : listNhaCungCap) {
            Object[] row = {
                nhaCungCap.getMancc(),
                nhaCungCap.getTenncc(),
                nhaCungCap.getDiachi(),
                nhaCungCap.getEmail(),
                nhaCungCap.getSdt(),};
            model.addRow(row);
        }

//         Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblNhaCC.getColumnCount(); i++) {
            tblNhaCC.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void hienThiListNhaCungCap() {
        nhaCungCapBUS = new NhaCungCapBUS();
        nhaCungCapDAO = new NhaCungCapDAO();
        listNhaCungCap = nhaCungCapBUS.getAllNhaCungCap();
        DefaultTableModel model = (DefaultTableModel) tblNhaCC.getModel();
        model.setRowCount(0);
        for (NhaCungCapDTO nhaCungCap : listNhaCungCap) {
            Object[] row = {
                nhaCungCap.getMancc(),
                nhaCungCap.getTenncc(),
                nhaCungCap.getDiachi(),
                nhaCungCap.getEmail(),
                nhaCungCap.getSdt(),};
            model.addRow(row);
        }

//         Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblNhaCC.getColumnCount(); i++) {
            tblNhaCC.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

        pnlTop = new javax.swing.JPanel();
        btnThemNhaCC = new javax.swing.JButton();
        btnSuaNhaCC = new javax.swing.JButton();
        btnXoaNhaCC = new javax.swing.JButton();
        btnChiTietNCC = new javax.swing.JButton();
        btnXuatExcelNCC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        asd = new javax.swing.JScrollPane();
        tblNhaCC = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 800));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemNhaCC.setText("Thêm");
        btnThemNhaCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhaCCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemNhaCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemNhaCCMouseExited(evt);
            }
        });
        btnThemNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaCCActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemNhaCC);

        btnSuaNhaCC.setText("Sửa");
        btnSuaNhaCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuaNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNhaCCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaNhaCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaNhaCCMouseExited(evt);
            }
        });
        btnSuaNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhaCCActionPerformed(evt);
            }
        });
        pnlTop.add(btnSuaNhaCC);

        btnXoaNhaCC.setText("Xóa");
        btnXoaNhaCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNhaCCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaNhaCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaNhaCCMouseExited(evt);
            }
        });
        btnXoaNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhaCCActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoaNhaCC);

        btnChiTietNCC.setText("Chi tiết");
        btnChiTietNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChiTietNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChiTietNCCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChiTietNCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChiTietNCCMouseExited(evt);
            }
        });
        btnChiTietNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietNCCActionPerformed(evt);
            }
        });
        pnlTop.add(btnChiTietNCC);

        btnXuatExcelNCC.setText("Xuất excel");
        btnXuatExcelNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuatExcelNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatExcelNCCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXuatExcelNCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXuatExcelNCCMouseExited(evt);
            }
        });
        btnXuatExcelNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelNCCActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcelNCC);

        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

        txtTimKiem.setMinimumSize(new java.awt.Dimension(200, 30));
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

        tblNhaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên nhà cung cấp", "Địa chỉ", "Email", "Điện thoại"
            }
        ));
        asd.setViewportView(tblNhaCC);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(asd, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(asd, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaNhaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhaCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaNhaCCActionPerformed

    private void btnXoaNhaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhaCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaNhaCCActionPerformed

    private void btnXuatExcelNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelNCCActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemNhaCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhaCCMouseEntered
        // TODO add your handling code here:\
        btnThemNhaCC.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnThemNhaCCMouseEntered

    private void btnThemNhaCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhaCCMouseExited
        // TODO add your handling code here:
        btnThemNhaCC.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemNhaCCMouseExited

    private void btnThemNhaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhaCCMouseClicked
        // TODO add your handling code here:
        if (!btnThemNhaCC.isSelected()) {
            btnThemNhaCC.setBackground(Color.WHITE);
        } else {
            btnThemNhaCC.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnThemNhaCCMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listNhaCungCap = nhaCungCapBUS.getAllNhaCungCap();
        hienThiListNhaCungCap(listNhaCungCap);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyword = txtTimKiem.getText().trim();
            timKiemNhaCungCap(keyword);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnThemNhaCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhaCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNhaCCActionPerformed

    private void btnChiTietNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietNCCActionPerformed

    private void btnSuaNhaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNhaCCMouseClicked
        // TODO add your handling code here:
        if (!btnSuaNhaCC.isSelected()) {
            btnSuaNhaCC.setBackground(Color.WHITE);
        } else {
            btnSuaNhaCC.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnSuaNhaCCMouseClicked

    private void btnSuaNhaCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNhaCCMouseEntered
        // TODO add your handling code here:
        btnSuaNhaCC.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnSuaNhaCCMouseEntered

    private void btnSuaNhaCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNhaCCMouseExited
        // TODO add your handling code here:
        btnSuaNhaCC.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnSuaNhaCCMouseExited

    private void btnXoaNhaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNhaCCMouseClicked
        // TODO add your handling code here:
        if (!btnXoaNhaCC.isSelected()) {
            btnXoaNhaCC.setBackground(Color.WHITE);
        } else {
            btnXoaNhaCC.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnXoaNhaCCMouseClicked

    private void btnXoaNhaCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNhaCCMouseEntered
        // TODO add your handling code here:
        btnXoaNhaCC.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnXoaNhaCCMouseEntered

    private void btnXoaNhaCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNhaCCMouseExited
        // TODO add your handling code here:
        btnXoaNhaCC.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnXoaNhaCCMouseExited

    private void btnChiTietNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietNCCMouseClicked
        // TODO add your handling code here:
        if (!btnChiTietNCC.isSelected()) {
            btnChiTietNCC.setBackground(Color.WHITE);
        } else {
            btnChiTietNCC.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnChiTietNCCMouseClicked

    private void btnChiTietNCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietNCCMouseEntered
        // TODO add your handling code here:
        btnChiTietNCC.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnChiTietNCCMouseEntered

    private void btnChiTietNCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietNCCMouseExited
        // TODO add your handling code here:
        btnChiTietNCC.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnChiTietNCCMouseExited

    private void btnXuatExcelNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcelNCCMouseClicked
        // TODO add your handling code here:
        if (!btnXuatExcelNCC.isSelected()) {
            btnXuatExcelNCC.setBackground(Color.WHITE);
        } else {
            btnXuatExcelNCC.setBackground(Color.BLUE);
        }
    }//GEN-LAST:event_btnXuatExcelNCCMouseClicked

    private void btnXuatExcelNCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcelNCCMouseEntered
        // TODO add your handling code here:
        btnXuatExcelNCC.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnXuatExcelNCCMouseEntered

    private void btnXuatExcelNCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatExcelNCCMouseExited
        // TODO add your handling code here:
        btnXuatExcelNCC.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnXuatExcelNCCMouseExited

    private void xoaNhaCungCap() {
        int selectedRow = tblNhaCC.getSelectedRow();
        if (selectedRow != -1) {
            int mancc = (int) tblNhaCC.getValueAt(selectedRow, 0);
            NhaCungCapDTO canXoa = nhaCungCapBUS.selectByID(mancc);
            nhaCungCapBUS = new NhaCungCapBUS();
            boolean thanhCong = nhaCungCapBUS.xoaNhaCungCap(mancc);
            if (thanhCong) {

                JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công");
                listNhaCungCap = nhaCungCapBUS.getAllNhaCungCap();
                hienThiListNhaCungCap(listNhaCungCap);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa");
        }
    }

    private NhaCungCapDTO selectNhaCungCap() {
        int selectedRow = tblNhaCC.getSelectedRow();
        NhaCungCapDTO result = null;
        if (selectedRow != -1) {
            int mancc = (int) tblNhaCC.getValueAt(selectedRow, 0);
            nhaCungCapBUS = new NhaCungCapBUS();
            result = nhaCungCapBUS.selectByID(mancc);
        }
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThemNhaCC) {
            themNCC = new ThemNCCap(this);
            themNCC.setLocationRelativeTo(null);
            themNCC.setVisible(true);
        } else if (e.getSource() == btnXoaNhaCC) {
            xoaNhaCungCap();
        } else if (e.getSource() == btnSuaNhaCC) {
            if (selectNhaCungCap() != null) {
                suaNCC = new SuaNCCap(selectNhaCungCap(), this);
                suaNCC.setLocationRelativeTo(null);
                suaNCC.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            }
        } else if (e.getSource() == btnXuatExcelNCC) {
            XuatExcel xuatExcel = new XuatExcel();
            try {
                xuatExcel.exportJTableToExcel(tblNhaCC);
            } catch (IOException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnChiTietNCC) {
            if (selectNhaCungCap() != null) {
                chiTietNCC = new ChiTietNCCap(selectNhaCungCap());
                chiTietNCC.setLocationRelativeTo(null);
                chiTietNCC.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane asd;
    private javax.swing.JButton btnChiTietNCC;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaNhaCC;
    private javax.swing.JButton btnThemNhaCC;
    private javax.swing.JButton btnXoaNhaCC;
    private javax.swing.JButton btnXuatExcelNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblNhaCC;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
