/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.PXuat;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DAO.ChiTietPhieuXuatDAO;
import DAO.LoaiDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import GUI.Component.Export.writePDF;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class ChiTietPhieuXuat extends javax.swing.JFrame {
    ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;
    ThuongHieuDAO thuongHieuDAO;
    LoaiDAO loaiDAO;
    XuatXuDAO xuatXuDAO;
    KhachHangBUS khachHangBUS;
    SanPhamBUS sanPhamBUS;
    PhieuXuatBUS phieuXuatBUS;
    NhanVienBUS nhanVienBUS;
    
    ChiTietPhieuXuatDTO chiTietPhieuXuatDTO;
    
    private ArrayList<SanPhamDTO> selectedProducts = new ArrayList<>();
    
    int rowNum = 1; // Biến đếm số thứ tự hàng
    public ChiTietPhieuXuat() {
        initComponents();
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setLocationRelativeTo(null);
        this.setResizable(false);      
        
    }
     public ChiTietPhieuXuat(PhieuXuatDTO phieuXuatDTO){
        initComponents();
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);        

        txtmaphieu.setEditable(false);
        txtkhachhang.setEditable(false);
        txtnhanvienxuat.setEditable(false);
        txtthoigiantao.setEditable(false);
        
        tblsanphamchitiet.setDefaultEditor(Object.class, null);
        tblsanphamchitiet.setFocusable(false);
        
        LoadDuLieu(phieuXuatDTO);
     }
    
    private void LoadDuLieu(PhieuXuatDTO phieuXuatDTO) {
        khachHangBUS = new KhachHangBUS();
        phieuXuatBUS = new PhieuXuatBUS();
        sanPhamBUS = new SanPhamBUS();
        nhanVienBUS = new NhanVienBUS();
        
        String tenKhachHang = String.valueOf(khachHangBUS.selectByID(phieuXuatDTO.getMakh()).getHoten());
        String TenNv = nhanVienBUS.selectByID(phieuXuatDTO.getManv()).getHoten();
        
        txtmaphieu.setText("PX" + String.valueOf(phieuXuatDTO.getMaphieuxuat()));
        txtnhanvienxuat.setText(TenNv);
        txtkhachhang.setText(tenKhachHang);
        txtthoigiantao.setText(String.valueOf(phieuXuatDTO.getThoigiantao()));

// Lấy danh sách chi tiết phiếu nhập
        ArrayList<ChiTietPhieuXuatDTO> listChiTiet = phieuXuatBUS.getAllChiTietPhieuXuat(phieuXuatDTO.getMaphieuxuat());
        if (listChiTiet != null && !listChiTiet.isEmpty()) {
            // Lặp qua từng chi tiết phiếu nhập để lấy thông tin sản phẩm và thêm vào bảng
            for (ChiTietPhieuXuatDTO chiTietPhieuNhapDTO : listChiTiet) {
                int masp = chiTietPhieuNhapDTO.getMasp();
                int soluong = chiTietPhieuNhapDTO.getSoluong();
                SanPhamDTO sanPhamPhieuXuat = sanPhamBUS.selectByID(masp);
                updatetableaddedproducts(sanPhamPhieuXuat, tblsanphamchitiet, soluong);
            }
        } else {
            // Hiển thị thông báo nếu không có chi tiết phiếu nhập nào được tìm thấy
            JOptionPane.showMessageDialog(null, "Không tìm thấy chi tiết phiếu nhập cho mã phiếu nhập: " + phieuXuatDTO.getMaphieuxuat());
        }
    }

    public void updatetableaddedproducts(SanPhamDTO product, JTable table, int soluong) {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.setRowCount(0); // Xóa hết dữ liệu trong bảng
            String TenLoai = loaiDAO.selectById(product.getLoai()).getTenloai();
            String TenThuongHieu = thuongHieuDAO.selectById(product.getThuonghieu()).getTenthuonghieu();
            String XuatXu = xuatXuDAO.selectById(product.getXuatxu()).getTenxuatxu();

            DecimalFormat decimalFormat = new DecimalFormat("#,### đ"); 
            model.addRow(new Object[]{
                rowNum++,
                product.getMasp(),
                product.getTensp(),
                product.getSize(),
                XuatXu,
                TenLoai,
                TenThuongHieu,
                decimalFormat.format(product.getGiaxuat()), // Định dạng giá nhập
                soluong
            });
        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCenter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtkhachhang = new javax.swing.JTextField();
        txtnhanvienxuat = new javax.swing.JTextField();
        txtthoigiantao = new javax.swing.JTextField();
        txtmaphieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanphamchitiet = new javax.swing.JTable();
        btnExportPdf = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mã phiếu");

        jLabel2.setText("Khách hàng");

        jLabel3.setText("Thời gian tạo");

        jLabel4.setText("Nhân viên xuất");

        txtkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkhachhangActionPerformed(evt);
            }
        });

        txtnhanvienxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhanvienxuatActionPerformed(evt);
            }
        });

        txtthoigiantao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtthoigiantaoActionPerformed(evt);
            }
        });

        txtmaphieu.setPreferredSize(new java.awt.Dimension(80, 22));
        txtmaphieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaphieuActionPerformed(evt);
            }
        });

        tblsanphamchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Size", "Xuất xứ", "Loại", "Thương hiệu", "Giá xuất", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(tblsanphamchitiet);

        btnExportPdf.setBackground(new java.awt.Color(153, 204, 255));
        btnExportPdf.setText("Xuất file PDF");
        btnExportPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPdfActionPerformed(evt);
            }
        });

        btnhuy.setBackground(new java.awt.Color(255, 51, 51));
        btnhuy.setText("Hủy bỏ");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(btnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtnhanvienxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(198, 198, 198)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtthoigiantao, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 125, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnhanvienxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtthoigiantao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExportPdf, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pnlTop.setBackground(new java.awt.Color(51, 153, 255));

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("CHI TIẾT PHIẾU XUẤT");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        getContentPane().add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkhachhangActionPerformed

    private void txtnhanvienxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhanvienxuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnhanvienxuatActionPerformed

    private void txtthoigiantaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtthoigiantaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtthoigiantaoActionPerformed

    private void txtmaphieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaphieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaphieuActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnhuyActionPerformed

    private void btnExportPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPdfActionPerformed
        writePDF w = new writePDF();
        int mapx = Integer.parseInt(txtmaphieu.getText().replaceAll("[PX]", "").trim());
        w.writePX(mapx);
    }//GEN-LAST:event_btnExportPdfActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietPhieuXuat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportPdf;
    private javax.swing.JButton btnhuy;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblsanphamchitiet;
    private javax.swing.JTextField txtkhachhang;
    private javax.swing.JTextField txtmaphieu;
    private javax.swing.JTextField txtnhanvienxuat;
    private javax.swing.JTextField txtthoigiantao;
    // End of variables declaration//GEN-END:variables
}
