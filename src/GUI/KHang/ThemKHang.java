package GUI.KHang;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.KhachHang;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ThemKHang extends javax.swing.JFrame {

    private File selectedFile;
    private Random randomGenerator = new Random();
    KhachHangDAO khachHangDAO;
    KhachHangDTO khachHangDTO;
    KhachHangBUS khachHangBUS;
    KhachHang khachHang;
    public ThemKHang(KhachHang khachHang) {
        initComponents();
        this.khachHang = khachHang;
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
    }
    
    private KhachHangDTO getInfoKhacHangMoi() {
        if (txtTenKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
        } else if (txtSoDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
        }
        String tenKhachHang = txtTenKH.getText();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDT.getText();
        khachHangDTO = new KhachHangDTO(tenKhachHang,diaChi,soDienThoai);
        return khachHangDTO;
    }
    private void themKhachHang() throws IOException {
        KhachHangDTO khNew = getInfoKhacHangMoi();
        khachHangBUS = new KhachHangBUS();
        boolean thanhCong = khachHangBUS.themKhachHang(khNew);
        if (thanhCong) {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            khachHang.hienThiListKhachHang();
            dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidData() {
        String tenKhachHang = txtTenKH.getText();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDT.getText().trim();
        if (tenKhachHang.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra định dạng số điện thoại
        if (soDienThoai.length() != 10 || !soDienThoai.startsWith("0") ) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số và bắt đầu bằng số 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(soDienThoai.matches("0 1 2 3 4 5 6 7 8 9")){
            JOptionPane.showMessageDialog(null, "Số điện thoại không bao gồm kí tự");
                return false;
        }

                if (isDuplicateTenKH(tenKhachHang)) {
        JOptionPane.showMessageDialog(this, "Tên khách hàng đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }


        return true;
    }
        private boolean isDuplicateTenKH(String tenKhachHang) {
    khachHangBUS = new KhachHangBUS();
    ArrayList<KhachHangDTO> danhSachKhachHang = khachHangBUS.getAllKhachHang();
    for (KhachHangDTO kh : danhSachKhachHang) {
        if (kh.getHoten().equalsIgnoreCase(tenKhachHang)) {
            return true;
        }
    }
    return false;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        lblTenKH = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        lblSoDT = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTop.setBackground(new java.awt.Color(0, 102, 255));

        lblTitle.setBackground(new java.awt.Color(0, 102, 204));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("THÊM KHÁCH HÀNG");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        lblTenKH.setText("Tên khách hàng");

        lblSoDT.setText("Số điện thoại");

        lblDiaChi.setText("Địa chỉ");

        btnThemKH.setBackground(new java.awt.Color(0, 102, 204));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 0, 102));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Hủy bỏ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenKH)
                            .addComponent(lblSoDT)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTenKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSoDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDiaChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        if (!isValidData()) {
        return;
    }  
        try {
            themKhachHang();
        } catch (IOException ex) {
            Logger.getLogger(ThemKHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblSoDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
