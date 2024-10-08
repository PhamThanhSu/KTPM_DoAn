package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.CheckAction;
import GUI.NVien.ChiTietNhanVien;
import GUI.NVien.SuaNhanVien;
import GUI.NVien.ThemNhanVien;
import javax.swing.*;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhanVien extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form NhanVien
     */
    DefaultTableModel tblModel;
    NhanVienBUS nhanVienBus = new NhanVienBUS();
    NhanVienDAO nhanVienDAO;
    ThemNhanVien themNhanVien;
    SuaNhanVien suaNhanVien;
    ChiTietNhanVien chiTietNhanVien;
    TaiKhoanBUS taiKhoanBUS;
    public ArrayList<NhanVienDTO> listNhanVien = nhanVienBus.getAllNhanVien();
    TaiKhoanDTO taiKhoanDTO;
    public NhanVien(TaiKhoanDTO taiKhoanDTO) throws SQLException {
        this.taiKhoanDTO = taiKhoanDTO;
        initComponents();
        addIcon();
        Color BackgroundColor = new Color(240, 247, 250);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);
        tblNhanVien.setFocusable(false);
        tblNhanVien.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblNhanVien.setFocusable(false);
        tblNhanVien.setAutoCreateRowSorter(true);

        btnThemNV.addActionListener(this);
        btnSuaNV.addActionListener(this);
        btnXoaNV.addActionListener(this);
        btnChiTietNV.addActionListener(this);
        btnXuatExcelNV.addActionListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên nhân viên...");
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        
        String[] action = {"create", "update", "delete", "view"};
        Map<String, JButton> buttonMap = new HashMap<>();
        buttonMap.put("create", btnThemNV);       // Nút thêm
        buttonMap.put("delete", btnXoaNV);        // Nút xóa
        buttonMap.put("update", btnSuaNV);        // Nút sửa
        buttonMap.put("detail", btnChiTietNV);    // Nút chi tiết
        buttonMap.put("export", btnXuatExcelNV);  // Nút xuất Excel
//        buttonMap.put("import",btnNhapExcel);  // Nút nhập Excel

// Tạo đối tượng CheckAction
        CheckAction checkAction = new CheckAction(taiKhoanDTO.getManhomquyen(), "nhanvien", action, buttonMap);

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String timkiem = txtTimKiem.getText().trim();
                timKiemNhanVien(timkiem);
            }
        });
        
        hienThiListNhanVien(listNhanVien);
    }

    private void timKiemNhanVien(String keyword) {
        ArrayList<NhanVienDTO> ketQuaTimKiem = new ArrayList<>();
        ArrayList<NhanVienDTO> nhanVienTimKiem = nhanVienBus.getAllNhanVien();
        for(NhanVienDTO timkiem : nhanVienTimKiem){
            String tenNhanVien = timkiem.getHoten().trim();
            if (tenNhanVien.toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(timkiem);
            }
        }
        hienThiListNhanVien(ketQuaTimKiem);
    }

    public void hienThiListNhanVien(ArrayList<NhanVienDTO> listNhanVien) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (NhanVienDTO nhanVien : listNhanVien) {
            String gioitinh;
            if (nhanVien.getGioitinh() == 1) {
                gioitinh = "Nam";
            } else {
                gioitinh = "Nữ";
            }
            String ngaySinhFormatted = sdf.format(nhanVien.getNgaysinh());
            Object[] row = {
                nhanVien.getManv(),
                nhanVien.getHoten(),
                gioitinh,
                ngaySinhFormatted,
                nhanVien.getSdt(),
                nhanVien.getEmail()};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblNhanVien.getColumnCount(); i++) {
            tblNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void addIcon() {
        btnThemNV.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaNV.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaNV.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnChiTietNV.setIcon(new FlatSVGIcon("./icon/detail.svg"));
        btnXuatExcelNV.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));
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
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnChiTietNV = new javax.swing.JButton();
        btnXuatExcelNV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 800));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemNV);

        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });
        pnlTop.add(btnSuaNV);

        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoaNV);

        btnChiTietNV.setText("Chi tiết");
        btnChiTietNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietNVActionPerformed(evt);
            }
        });
        pnlTop.add(btnChiTietNV);

        btnXuatExcelNV.setText("Xuất excel");
        btnXuatExcelNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelNVActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcelNV);

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
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTop.add(btnLamMoi);

        add(pnlTop, java.awt.BorderLayout.NORTH);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnXuatExcelNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelNVActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnChiTietNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietNVActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            String keyword = txtTimKiem.getText().trim();
//            timKiemNhanVien(keyword);
//        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listNhanVien = nhanVienBus.getAllNhanVien();
        hienThiListNhanVien(listNhanVien);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void xoaNhanVien() {
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow != -1) {
            int maNV = (int) tblNhanVien.getValueAt(selectedRow, 0);
            nhanVienBus = new NhanVienBUS();
            taiKhoanBUS = new TaiKhoanBUS();
            int manhomquyen = taiKhoanBUS.selectByID(maNV).getManhomquyen();
            //Không thể xóa Quản lý và chính mình
            if ((taiKhoanDTO.getManhomquyen() == manhomquyen || manhomquyen == 5) || (taiKhoanDTO.getManv()== selectNhanVien().getManv())) {
                JOptionPane.showMessageDialog(this, "Không thể xóa tài khoản này!");
                return; // Ngừng xử lý nếu không được phép cập nhật
            }
            boolean thanhCong = nhanVienBus.xoaNhanVien(maNV);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
                listNhanVien = nhanVienBus.getAllNhanVien();
                hienThiListNhanVien(listNhanVien);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để xóa");
        }
    }

    private NhanVienDTO selectNhanVien() {
        int selectedRow = tblNhanVien.getSelectedRow();
        NhanVienDTO result = null;
        if (selectedRow != -1) {
            int manv = (int) tblNhanVien.getValueAt(selectedRow, 0);
            nhanVienBus = new NhanVienBUS();
            result = nhanVienBus.selectByID(manv);
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThemNV) {
            themNhanVien = new ThemNhanVien();
            themNhanVien.setLocationRelativeTo(null);
            themNhanVien.setVisible(true);
        } else if (e.getSource() == btnXoaNV) {
            xoaNhanVien();
        } else if (e.getSource() == btnSuaNV) {
            if (selectNhanVien() != null) {
                suaNhanVien = new SuaNhanVien(this, selectNhanVien());
                suaNhanVien.setLocationRelativeTo(null);
                suaNhanVien.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
            }
        } else if (e.getSource() == btnXuatExcelNV) {
            XuatExcel xuatExcel = new XuatExcel();
            try {
                xuatExcel.exportJTableToExcel(tblNhanVien);
                JOptionPane.showMessageDialog(null, "Xuất thành công");
            } catch (IOException ex) {
                Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnChiTietNV) {
            if (selectNhanVien() != null) {
                chiTietNhanVien = new ChiTietNhanVien(selectNhanVien());
                chiTietNhanVien.setLocationRelativeTo(null);
                chiTietNhanVien.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietNV;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXuatExcelNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
