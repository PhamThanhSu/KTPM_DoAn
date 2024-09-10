/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.PNhap;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamPhieuNhapBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.LoaiDAO;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.Component.Export.writePDF;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class ChiTietPhieuNhap extends javax.swing.JFrame {
    NhaCungCapDAO nhaCungCapDAO;
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    ThuongHieuDAO thuongHieuDAO;
    LoaiDAO loaiDAO;
    XuatXuDAO xuatXuDAO;
    PhieuNhapDTO phieunhap;
    NhaCungCapBUS nhaCungCapBUS;
    SanPhamPhieuNhapBUS sanPhamPhieuNhapBUS;
    PhieuNhapBUS phieuNhapBUS;
    NhanVienBUS nhanVienBUS;
    ChiTietPhieuNhapDTO chiTietPhieuNhapDTO;
    
    private ArrayList<SanPhamDTO> selectedProducts = new ArrayList<>();
    
    int rowNum = 1; 
    public ChiTietPhieuNhap() {
        initComponents();
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
        
}
     
     public ChiTietPhieuNhap(PhieuNhapDTO phieuNhapDTO){
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
        txtnhacungcap.setEditable(false);
        txtnhanviennhap.setEditable(false);
        txtthoigiantao.setEditable(false);
        
        tblsanphamchitiet.setDefaultEditor(Object.class, null);
        tblsanphamchitiet.setFocusable(false);
        loadDuLieu(phieuNhapDTO);
     }
    
    private void loadDuLieu(PhieuNhapDTO phieuNhapDTO) {
        nhaCungCapBUS = new NhaCungCapBUS();
        phieuNhapBUS = new PhieuNhapBUS();
        nhanVienBUS = new NhanVienBUS();
        String tenncc = nhaCungCapBUS.selectByID(phieuNhapDTO.getManhacungcap()).getTenncc();
        String TenNv = nhanVienBUS.selectByID(phieuNhapDTO.getMaNV()).getHoten();
        txtmaphieu.setText("PN" + String.valueOf(phieuNhapDTO.getMaphieunhap()));
        txtnhanviennhap.setText(String.valueOf(phieuNhapDTO.getMaNV()));
        txtnhacungcap.setText(String.valueOf(tenncc));
        txtthoigiantao.setText(String.valueOf(phieuNhapDTO.getThoigiantao()));
        txtnhanviennhap.setText(TenNv);
        
// Lấy danh sách chi tiết phiếu nhập
        ArrayList<ChiTietPhieuNhapDTO> listChiTiet = phieuNhapBUS.getListChiTietPhieuNhap(phieuNhapDTO.getMaphieunhap());
        if (listChiTiet != null && !listChiTiet.isEmpty()) {
            // Lặp qua từng chi tiết phiếu nhập để lấy thông tin sản phẩm và thêm vào bảng
            for (ChiTietPhieuNhapDTO chiTietPhieuNhapDTO : listChiTiet) {
                int masp = chiTietPhieuNhapDTO.getMasp();
                int soluong = chiTietPhieuNhapDTO.getSoluong();
                ArrayList<SanPhamDTO> ListSanPhamPhieuNhap = sanPhamPhieuNhapBUS.getListSanPham(masp);
                updatetableaddedproducts(ListSanPhamPhieuNhap, tblsanphamchitiet, soluong);
            }
        } else {
            // Hiển thị thông báo nếu không có chi tiết phiếu nhập nào được tìm thấy
            JOptionPane.showMessageDialog(null, "Không tìm thấy chi tiết phiếu nhập cho mã phiếu nhập: " + phieuNhapDTO.getMaphieunhap());
        }
    }

    public void updatetableaddedproducts(ArrayList<SanPhamDTO> productList, JTable table, int soluong) {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        nhaCungCapDAO = new NhaCungCapDAO();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.setRowCount(0); // Xóa hết dữ liệu trong bảng
        for (SanPhamDTO product : productList) {
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
                decimalFormat.format(product.getGianhap()), // Định dạng giá nhập
                soluong
            });
        }
        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnhacungcap = new javax.swing.JTextField();
        txtnhanviennhap = new javax.swing.JTextField();
        txtthoigiantao = new javax.swing.JTextField();
        txtmaphieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanphamchitiet = new javax.swing.JTable();
        btnExportPdf = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTop.setBackground(new java.awt.Color(51, 153, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(667, 60));

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("CHI TIẾT PHIẾU NHẬP");

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
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        getContentPane().add(pnlTop, java.awt.BorderLayout.PAGE_START);

        jLabel1.setText("Mã phiếu");

        jLabel2.setText("Nhà cung cấp");

        jLabel3.setText("Thời gian tạo");

        jLabel4.setText("Nhân viên nhập");

        txtnhacungcap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhacungcapActionPerformed(evt);
            }
        });

        txtnhanviennhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhanviennhapActionPerformed(evt);
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
                "STT", "Mã SP", "Tên sản phẩm", "Size", "Xuất xứ", "Loại", "Thương hiệu", "Giá nhập", "Số lượng"
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
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(198, 198, 198)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 156, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(145, 145, 145))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txtnhanviennhap, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(txtnhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtthoigiantao, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnhanviennhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtthoigiantao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnhacungcapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhacungcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnhacungcapActionPerformed

    private void txtnhanviennhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhanviennhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnhanviennhapActionPerformed

    private void txtthoigiantaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtthoigiantaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtthoigiantaoActionPerformed

    private void txtmaphieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaphieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaphieuActionPerformed

    private void btnExportPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPdfActionPerformed
        writePDF w = new writePDF();
        int mapn = Integer.parseInt(txtmaphieu.getText().replaceAll("[PN]", "").trim());
        w.writePN(mapn);
    }//GEN-LAST:event_btnExportPdfActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnhuyActionPerformed

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if (source == btnhuy) {
//            dispose();
//        }
//        if (source == btnExportPdf) {
//            writePDF w = new writePDF();
////            if (this.phieuxuat != null) {
////                w.writePX(phieuxuat.getMaphieu());
////            }
////            if (this.phieunhap != null) {
////                w.writePN(phieunhap.getMaphieunhap());
////            }
//            //w.writePN(phieunhap.getMaphieunhap());
//            w.writePN(19);
//        }
//    }
    
    
    
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
            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietPhieuNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportPdf;
    private javax.swing.JButton btnhuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblsanphamchitiet;
    private javax.swing.JTextField txtmaphieu;
    private javax.swing.JTextField txtnhacungcap;
    private javax.swing.JTextField txtnhanviennhap;
    private javax.swing.JTextField txtthoigiantao;
    // End of variables declaration//GEN-END:variables
}
