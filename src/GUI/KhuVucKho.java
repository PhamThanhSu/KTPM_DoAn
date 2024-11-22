package GUI;

import BUS.ChiTietPhieuNhapBUS;
import BUS.ChiTietPhieuXuatBUS;
import BUS.KhuVucKhoBUS;
import BUS.SanPhamBUS;
import DAO.KhuVucKhoDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.KhuVucKhoDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.CheckAction;
import GUI.Component.ImageRenderer;
import GUI.KhuVucKhoOpTions.SuaKhuVucKho;
import GUI.KhuVucKhoOpTions.ThemKhuVucKho;
import GUI.SPham.ChiTietSPTrongKho;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class KhuVucKho extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form KhuVucKho
     */
    ThemKhuVucKho themKhuVucKho;
    KhuVucKhoDAO khuVucKhoDAO;
    KhuVucKhoBUS khuVucKhoBUS;
    SuaKhuVucKho suaKhuVucKho;
    SanPhamBUS sanPhamBUS;
    ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;
    ChiTietSPTrongKho chiTietSPTrongKho;
    Color BackgroundColor = new Color(240, 247, 250);

    public KhuVucKho(TaiKhoanDTO taiKhoanDTO) throws SQLException {
        initComponents();
//        setSize(1200, 800);
        addIcon();
        tblKho.setFocusable(false);
        tblKho.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblKho.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblKho.setFocusable(false);
        tblKho.setAutoCreateRowSorter(true);
        tblsanphamtrongkho.setFocusable(false);
        tblsanphamtrongkho.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblsanphamtrongkho.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblsanphamtrongkho.setFocusable(false);
        tblsanphamtrongkho.setAutoCreateRowSorter(true);

        String[] action = {"create", "update", "delete", "view"};
        Map<String, JButton> buttonMap = new HashMap<>();
        buttonMap.put("create", btnThemKho);       // Nút thêm
        buttonMap.put("delete", btnXoaKho);        // Nút xóa
        buttonMap.put("update", btnSuaKho);        // Nút sửa
//        buttonMap.put("detail", btnChiTietNV);    // Nút chi tiết
        buttonMap.put("export", btnXuatExcelKho);  // Nút xuất Excel
//        buttonMap.put("import",btnNhapExcel);  // Nút nhập Excel

// Tạo đối tượng CheckAction
        CheckAction checkAction = new CheckAction(taiKhoanDTO.getManhomquyen(), "khuvuckho", action, buttonMap);

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String timkiem = txtTimKiem.getText().trim();
                timKiemKhuVucKho(timkiem);
            }
        });

        hienThiListKhuVucKho();
        // Add a MouseListener to the table for row click detection
        tblKho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblKho.getSelectedRow();
                if (selectedRow != -1) {
                    int makhuvuc = (int) tblKho.getValueAt(selectedRow, 0);
                    getAllSanPhamTrongKho(makhuvuc);
                }
            }
        });

        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
        pnlCenter.setBackground(BackgroundColor);
    }

    private void addIcon() {
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên khu vực...");
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
        ArrayList<KhuVucKhoDTO> AllKho = khuVucKhoBUS.getAllKho();
        for (KhuVucKhoDTO kho : AllKho) {
            String tenKhuVucKho = kho.getTenkhuvuc().trim();
            if (tenKhuVucKho.toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(kho);
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

    public void getAllSanPhamTrongKho(int makho) {

        DefaultTableModel model = (DefaultTableModel) tblsanphamtrongkho.getModel();
        model.setRowCount(0);
        sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> listsp = sanPhamBUS.selectByKho(makho);
        for (SanPhamDTO sanpham : listsp) {
            ImageIcon icon = new ImageIcon("./src/img_product/" + sanpham.getHinhanh());
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            Object[] row = {
                sanpham.getMasp(),
                sanpham.getTensp(),
                scaledIcon,
                sanpham.getSize(),
                sanpham.getSoluongton(),};
            model.addRow(row);
        }

        // Set the cell renderer for the column that displays the image
        tblsanphamtrongkho.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer căn giữa cho các cột khác
        for (int i = 0; i < tblsanphamtrongkho.getColumnCount(); i++) {
            if (i != 2) { // Tránh áp dụng renderer này cho cột hình ảnh
                tblsanphamtrongkho.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsanphamtrongkho = new javax.swing.JTable();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

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

        tblsanphamtrongkho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Hình ảnh", "Size", "Số lượng"
            }
        ));
        tblsanphamtrongkho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamtrongkhoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblsanphamtrongkho);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
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
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            String keyword = txtTimKiem.getText().trim();
//            timKiemKhuVucKho(keyword);
//        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listKhuVucKho = khuVucKhoBUS.getAllKho();
        hienThiListKhuVucKho();
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblsanphamtrongkhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamtrongkhoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) { // Kiểm tra sự kiện double-click
            int row = tblsanphamtrongkho.getSelectedRow();
            chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
            // Lấy dữ liệu của sản phẩm từ dòng được chọn
            int maSanPham = (int) tblsanphamtrongkho.getValueAt(row, 0);
            ArrayList<ChiTietPhieuNhapDTO> listChiTietSP = chiTietPhieuNhapBUS.selectByMASP(maSanPham);
            // Hiển thị cửa sổ hoặc panel chi tiết sản phẩm với thông tin đã lấy được
            chiTietSPTrongKho = new ChiTietSPTrongKho(listChiTietSP);
            chiTietSPTrongKho.setLocationRelativeTo(null);
            chiTietSPTrongKho.setVisible(true);
        }
    }//GEN-LAST:event_tblsanphamtrongkhoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaKho;
    private javax.swing.JButton btnThemKho;
    private javax.swing.JButton btnXoaKho;
    private javax.swing.JButton btnXuatExcelKho;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JTable tblKho;
    private javax.swing.JTable tblsanphamtrongkho;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
