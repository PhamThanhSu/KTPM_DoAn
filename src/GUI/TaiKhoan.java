package GUI;

import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
//import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Component.CheckAction;
import GUI.TKhoan.ChonNhanVien;
import GUI.TKhoan.ThemTaiKhoan;
import GUI.TKhoan.SuaTaiKhoan;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TaiKhoan extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form TaiKhoan
     */
    DefaultTableModel tblModel;
    TaiKhoanBUS taiKhoanBus = new TaiKhoanBUS();
//    NhomQuyenDAO nhomQuyenDAO;
    TaiKhoanDAO taiKhoanDAO;
    ThemTaiKhoan themTaiKhoan;
    SuaTaiKhoan suaTaiKhoan;
    ChonNhanVien chonNhanVien;
    ArrayList<TaiKhoanDTO> listTaiKhoan = taiKhoanBus.getAllTaiKhoan();
    TaiKhoanDTO taiKhoanDTO;

    public TaiKhoan(TaiKhoanDTO taiKhoanDTO) throws SQLException {
        this.taiKhoanDTO = taiKhoanDTO;
        initComponents();
        addIcon();
        Color BackgroundColor = new Color(240, 247, 250);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);
        tblTaiKhoan.setFocusable(false);
        tblTaiKhoan.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblTaiKhoan.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblTaiKhoan.setFocusable(false);
        tblTaiKhoan.setAutoCreateRowSorter(true);

        btnThemTK.addActionListener(this);
        btnSuaTK.addActionListener(this);
        btnXoaTK.addActionListener(this);
        btnXuatExcelTK.addActionListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);

        String[] action = {"create", "update", "delete", "view"};
        Map<String, JButton> buttonMap = new HashMap<>();
        buttonMap.put("create", btnThemTK);       // Nút thêm
        buttonMap.put("delete", btnXoaTK);        // Nút xóa
        buttonMap.put("update", btnSuaTK);        // Nút sửa
//        buttonMap.put("detail", btnChiTietTK);    // Nút chi tiết
        buttonMap.put("export", btnXuatExcelTK);  // Nút xuất Excel
//        buttonMap.put("import", btnNhapExcelTK);  // Nút nhập Excel

// Tạo đối tượng CheckAction
        CheckAction checkAction = new CheckAction(taiKhoanDTO.getManhomquyen(), "taikhoan", action, buttonMap);

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String timkiem = txtTimKiem.getText().trim();
                timKiemTaiKhoan(timkiem);
            }
        });
        
        hienThiListTaiKhoan(listTaiKhoan);
    }

    private void timKiemTaiKhoan(String keyword) {
        ArrayList<TaiKhoanDTO> ketQuaTimKiem = new ArrayList<>();
        ArrayList<TaiKhoanDTO> allTaiKhoan = taiKhoanBus.getAllTaiKhoan();
        for(TaiKhoanDTO tk : allTaiKhoan){
            String tenTK = tk.getUsername().trim();
            String maNV = String.valueOf(tk.getManv());
            if (tenTK.toLowerCase().contains(keyword.toLowerCase()) || maNV.contains(keyword)) {
                ketQuaTimKiem.add(tk); // Thêm khách hàng vào danh sách kết quả
            }
        }
        hienThiListTaiKhoan(ketQuaTimKiem);
    }

    public void hienThiListTaiKhoan(ArrayList<TaiKhoanDTO> listTaiKhoan) {
        taiKhoanBus = new TaiKhoanBUS();
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        for (TaiKhoanDTO taiKhoan : listTaiKhoan) {
            String trangthai = null;
            if (taiKhoan.getTrangthai() == 1) {
                trangthai = "Hoạt động";
            } else if (taiKhoan.getTrangthai() == 0) {
                trangthai = "Ngưng hoạt động";
            }
            Object[] row = {
                taiKhoan.getManv(),
                taiKhoan.getUsername(),
                taiKhoanBus.selectTenNhomQuyenByMaNQ(taiKhoan.getManhomquyen()),
                trangthai};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblTaiKhoan.getColumnCount(); i++) {
            tblTaiKhoan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void addIcon() {
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên tài khoản...");
        btnThemTK.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaTK.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaTK.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnXuatExcelTK.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
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
        btnThemTK = new javax.swing.JButton();
        btnSuaTK = new javax.swing.JButton();
        btnXoaTK = new javax.swing.JButton();
        btnXuatExcelTK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 800));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemTK.setText("Thêm");
        btnThemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTKActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemTK);

        btnSuaTK.setText("Sửa");
        btnSuaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTKActionPerformed(evt);
            }
        });
        pnlTop.add(btnSuaTK);

        btnXoaTK.setText("Xóa");
        btnXoaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTKActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoaTK);

        btnXuatExcelTK.setText("Xuất excel");
        btnXuatExcelTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelTKActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcelTK);

        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

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
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTop.add(btnLamMoi);

        add(pnlTop, java.awt.BorderLayout.NORTH);

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"1", null, null, null},
                {"1", null, null, null},
                {"1", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, "1", null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {"1", null, null, null},
                {"1", null, null, null},
                {"1", null, null, null},
                {"1", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {"1", null, null, null}
            },
            new String [] {
                "Mã NV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tblTaiKhoan);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaTKActionPerformed

    private void btnXoaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaTKActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnXuatExcelTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelTKActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyword = txtTimKiem.getText().trim();
            timKiemTaiKhoan(keyword);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listTaiKhoan = taiKhoanBus.getAllTaiKhoan();
        hienThiListTaiKhoan(listTaiKhoan);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemTKActionPerformed

    private void xoaTaiKhoan() {
        int selectedRow = tblTaiKhoan.getSelectedRow();
        if (selectedRow != -1) {
            taiKhoanBus = new TaiKhoanBUS();
            int manv = (int) tblTaiKhoan.getValueAt(selectedRow, 0);
//          String tennhomquyen = (String) tblTaiKhoan.getValueAt(selectedRow, 2);
            taiKhoanBus = new TaiKhoanBUS();
            int manhomquyen = taiKhoanBus.selectByID(manv).getManhomquyen();
            //Không thể xóa Quản lý và chính mình
            if ((taiKhoanDTO.getManhomquyen() == manhomquyen || manhomquyen == 5) || (taiKhoanDTO.getManhomquyen() == selectTaiKhoan().getManhomquyen())) {
                JOptionPane.showMessageDialog(this, "Không thể xóa tài khoản này!");
                return; // Ngừng xử lý nếu không được phép cập nhật
            }
            boolean thanhCong = taiKhoanBus.xoaTaiKhoan(manv);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công");
                listTaiKhoan = taiKhoanBus.getAllTaiKhoan();
                hienThiListTaiKhoan(listTaiKhoan);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa tài khoản lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để xóa");
        }
    }

    public TaiKhoanDTO selectTaiKhoan() {
        int selectedRow = tblTaiKhoan.getSelectedRow();
        TaiKhoanDTO result = null;
        if (selectedRow != -1) {
            int manv = (int) tblTaiKhoan.getValueAt(selectedRow, 0);
            taiKhoanBus = new TaiKhoanBUS();
            result = taiKhoanBus.selectByID(manv);
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThemTK) {
            chonNhanVien = new ChonNhanVien();
            chonNhanVien.setLocationRelativeTo(null);
            chonNhanVien.setVisible(true);
        } else if (e.getSource() == btnXoaTK) {
            xoaTaiKhoan();
        } else if (e.getSource() == btnSuaTK) {
            if (selectTaiKhoan() != null) {
                try {
                    int manhomquyenselected = selectTaiKhoan().getManhomquyen();
                    int manhomquyentk = taiKhoanDTO.getManhomquyen();
                    System.out.println("mã nhóm quyền tk trong file tk "+ manhomquyentk);
                    suaTaiKhoan = new SuaTaiKhoan(this, selectTaiKhoan(), manhomquyentk);
                    suaTaiKhoan.setLocationRelativeTo(null);
                    suaTaiKhoan.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản");
            }
        } else if (e.getSource() == btnXuatExcelTK) {
            XuatExcel xuatExcel = new XuatExcel();
            try {
                xuatExcel.exportJTableToExcel(tblTaiKhoan);
                JOptionPane.showMessageDialog(null, "Xuất thành công");
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaTK;
    private javax.swing.JButton btnThemTK;
    private javax.swing.JButton btnXoaTK;
    private javax.swing.JButton btnXuatExcelTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
