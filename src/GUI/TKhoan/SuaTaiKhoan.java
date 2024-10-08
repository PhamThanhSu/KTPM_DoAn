package GUI.TKhoan;

import BUS.NhomQuyenBUS;
import BUS.TaiKhoanBUS;
//import BUS.NhomQuyenBUS;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.TaiKhoan;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuaTaiKhoan extends javax.swing.JFrame {

    private Random randomGenerator = new Random();
    NhomQuyenBUS nhomQuyenBUS = new NhomQuyenBUS();
    TaiKhoanBUS taiKhoanBUS;
    TaiKhoanDAO taiKhoanDAO;
    TaiKhoanDTO taiKhoanDTO;
    TaiKhoan tk;
    int manv;
    int manhomquyencuataikhoan = 0;
    int manhomquyenoftaikhoan;
    public SuaTaiKhoan() {
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
        loadCombobox();
    }

    public SuaTaiKhoan(TaiKhoan tk, TaiKhoanDTO taiKhoanDTO, int manhomquyentk) throws IOException {
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
        this.taiKhoanDTO = taiKhoanDTO;
        this.tk = tk;
        loadCombobox();
        LoadDuLieu(taiKhoanDTO);
        manv = taiKhoanDTO.getManv();
        manhomquyencuataikhoan += manhomquyentk;
        System.out.println("tài khoản dto từ tài khoản" + manhomquyentk);
        manhomquyenoftaikhoan = manhomquyenoftaikhoan;
        System.out.println("mã nhóm quyền selected" + manhomquyenoftaikhoan);
    }

    private void LoadDuLieu(TaiKhoanDTO taiKhoanDTO) {
        txtTenDN.setText(taiKhoanDTO.getUsername());
        txtMatKhau.setText(taiKhoanDTO.getMatkhau());

        String tenNhomQuyen = nhomQuyenBUS.selectByID(taiKhoanDTO.getManhomquyen()).getTennhomquyen();
        cbxNhomQuyen.setSelectedItem(tenNhomQuyen);

        int trangThai = taiKhoanDTO.getTrangthai();
        if (trangThai == 1) {
            cbxTrangThai.setSelectedItem("Hoạt động");
        } else if (trangThai == 0) {
            cbxTrangThai.setSelectedItem("Ngưng hoạt động");
        }
    }

    private void loadCombobox() {
        String[] arrTenNhomQuyen = nhomQuyenBUS.getArrTenNhomQuyen();
        selectCombobox(cbxNhomQuyen, arrTenNhomQuyen);
    }

    private void selectCombobox(JComboBox comboBox, String[] obj) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String ob : obj) {
            model.addElement(ob);
        }
        comboBox.setModel(model);
    }

    private TaiKhoanDTO getInfoTaiKhoanMoi() {
        String username = txtTenDN.getText();
        String matkhau = txtMatKhau.getText();
        int maNhomquyen = nhomQuyenBUS.getAllNhomQuyen().get(this.cbxNhomQuyen.getSelectedIndex()).getManhomquyen();
        int trangthai = cbxTrangThai.getSelectedIndex();
        taiKhoanDTO = new TaiKhoanDTO(username, matkhau, maNhomquyen, trangthai);
        return taiKhoanDTO;
    }

    private void suaTaiKhoan(int manhomquyentk) throws IOException {
        TaiKhoanDTO tkNew = getInfoTaiKhoanMoi();
        tkNew.setManv(manv);
        // Sử dụng lại hàm selectTaiKhoan() từ lớp TaiKhoan
        TaiKhoanDTO selectedTaiKhoan = tk.selectTaiKhoan();
        System.out.println("kiểm tra lần 1");
        System.out.println("mã nhóm quyền tk lần 2 "+ manhomquyencuataikhoan);
        NhomQuyenDTO nhomQuyenDTO = nhomQuyenBUS.selectByID(selectedTaiKhoan.getManhomquyen());
        System.err.println("tài khoản dto" + selectedTaiKhoan.getManhomquyen());
        System.err.println("nhóm quyền dto " + nhomQuyenDTO.getTennhomquyen());
        System.err.println("combobox index" + cbxNhomQuyen.getSelectedIndex());
        System.err.println("combobox item" + cbxNhomQuyen.getSelectedItem());
        System.err.println("role của tài khoản" + cbxNhomQuyen.getSelectedItem());
        taiKhoanBUS = new TaiKhoanBUS();
        String tenNhomQuyenMoi = cbxNhomQuyen.getSelectedItem().toString(); // Lấy giá trị là chuỗi

        // Kiểm tra điều kiện, không thể sửa quyền của Quản lý kho và chính mình
        if ( (selectedTaiKhoan.getManhomquyen() ==  manhomquyencuataikhoan ) ||(selectedTaiKhoan.getManhomquyen() == 5 && !tenNhomQuyenMoi.equals(nhomQuyenDTO.getTennhomquyen()))) {
            manhomquyencuataikhoan = 0;
            JOptionPane.showMessageDialog(this, "Không thể sửa tài khoản này!");
            return; // Ngừng xử lý nếu không được phép cập nhật
        }
        boolean thanhCong = taiKhoanBUS.suaTaiKhoan(tkNew);
        if (thanhCong) {
            manhomquyencuataikhoan = 0;
            JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tk.hienThiListTaiKhoan(taiKhoanBUS.getAllTaiKhoan());
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        lblTitle = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        lblTenDN = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        lblNhomQuyen = new javax.swing.JLabel();
        cbxNhomQuyen = new javax.swing.JComboBox<>();
        lblTrangThai = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnLuu = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTop.setBackground(new java.awt.Color(0, 102, 204));

        lblTitle.setBackground(new java.awt.Color(0, 102, 204));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("SỬA TÀI KHOẢN");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblTenDN.setText("Tên đăng nhập");

        lblMatKhau.setText("Mật khẩu");

        lblNhomQuyen.setText("Nhóm quyền");

        lblTrangThai.setText("Trạng thái");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngưng hoạt động", "Hoạt động" }));

        btnLuu.setBackground(new java.awt.Color(0, 102, 204));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu thông tin");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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
                .addGap(32, 32, 32)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThai)
                    .addComponent(lblTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDN)
                    .addComponent(lblNhomQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(btnLuu)
                        .addGap(100, 100, 100)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxNhomQuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatKhau))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTenDN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNhomQuyen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxNhomQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
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
                .addContainerGap()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        
        try {
            suaTaiKhoan(manhomquyencuataikhoan);
        } catch (IOException ex) {
            Logger.getLogger(SuaTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

//    public static void main(String args[]) {
//        FlatRobotoFont.install();
//        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
//        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
//        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
//        FlatIntelliJLaf.registerCustomDefaultsSource("style");
//        FlatIntelliJLaf.setup();
//        UIManager.put("PasswordField.showRevealButton", true);
//        new SuaTaiKhoan().setVisible(true);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> cbxNhomQuyen;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNhomQuyen;
    private javax.swing.JLabel lblTenDN;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
