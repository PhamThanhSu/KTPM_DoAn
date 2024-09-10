
package GUI.NVien;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.NhanVien;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class SuaNhanVien extends javax.swing.JFrame {
    
    private Random randomGenerator = new Random();
    NhanVienDAO nhanVienDAO;
    NhanVienDTO nhanVienDTO;
    NhanVienBUS nhanVienBUS;
    NhanVien nv;
    JDateChooser ngaysinh = new JDateChooser();
    int manv;

    public SuaNhanVien() {
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
        LoadDuLieu(nhanVienDTO);
        addSelectDate();
        
    }
    
    public SuaNhanVien(NhanVien nv,NhanVienDTO nhanVienDTO) {
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
        addSelectDate();
        LoadDuLieu(nhanVienDTO);
        manv = nhanVienDTO.getManv();
        this.nv = nv;
    }
    
    private void LoadDuLieu(NhanVienDTO nhanVienDTO) {   
        if (nhanVienDTO != null) {
            txtTenNV.setText(String.valueOf(nhanVienDTO.getHoten()));
            if (nhanVienDTO.getGioitinh() == 1) {
                rdbMale.setSelected(true);
            } else {
                rdbFemale.setSelected(true);
            }
            ngaysinh.setDate(nhanVienDTO.getNgaysinh());
            txtSDT.setText(String.valueOf(nhanVienDTO.getSdt()));
            txtEmail.setText(String.valueOf(nhanVienDTO.getEmail()));
        } else {
            // Xử lý trường hợp nhanVienDTO là null
            // Ví dụ: Xóa dữ liệu trên giao diện hoặc hiển thị thông báo
            txtTenNV.setText("");
            btnGroupGioiTinh.clearSelection();
            ngaysinh.setDate(null);
            txtSDT.setText("");
            txtEmail.setText("");
            JOptionPane.showMessageDialog(this, "Không có thông tin nhân viên để hiển thị", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private NhanVienDTO getInfoNhanVienMoi() {
        Date selectedDate = ngaysinh.getDate();
        ButtonModel selectedModel = btnGroupGioiTinh.getSelection();
        boolean isGenderSelected = false;
        
        if (txtTenNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên");
        } else if (containsDigit(txtTenNV.getText())) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa số");
        } else if (selectedModel == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính");
        } else if (selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh");
        } else if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
        } else if (!isNumeric(txtSDT.getText())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là số");
        } else if (txtSDT.getText().length() != 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 10 chữ số");
        } else if (!txtSDT.getText().startsWith("0")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0");
        } else if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập email");
        } else {
            isGenderSelected = true;
        }
        if (!isGenderSelected) {
            return null;
        }
        int trangthai = 1;
        int gioitinh = 0;
        String hoten = txtTenNV.getText();
        if (rdbMale.isSelected()){
            gioitinh = 1;
        }
        else if (rdbFemale.isSelected()){
            gioitinh = 0;
        }
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        
        nhanVienDTO = new NhanVienDTO(hoten, gioitinh, selectedDate, sdt, email, trangthai);
        return nhanVienDTO;
    }
    // Phương thức kiểm tra xem một chuỗi có chứa số không
    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    // Phương thức kiểm tra xem một chuỗi có chỉ chứa số không
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    // Phương thức chọn ngày
    private void addSelectDate() {
        ngaysinh.setDateFormatString("dd/MM/yyyy");
        pnlNgaySinh.add(ngaysinh).setVisible(true);
    }
    // Phương thức kiểm tra ngày
    public boolean checkDate() throws ParseException {
        Date time = ngaysinh.getDate();
        Date current_date = new Date();
        if (time != null && time.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            ngaysinh.setCalendar(null);
            return false;
        }
        return true;
    }
    // Phương thức kiểm tra số điện thoại
    private boolean isPhoneNumberValid(String phoneNumber) {
        String phonePattern = "^0[0-9]{9}$"; // Mẫu số điện thoại 10 chữ số bắt đầu bằng 0
        return phoneNumber.matches(phonePattern);
    }
    // Phương thức kiểm tra email
    public static Boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
    
    private void suaNhanVien() throws IOException {
        NhanVienDTO nvNew = getInfoNhanVienMoi();
        if (nvNew == null) {
            return;
        }
        if (nvNew.getHoten() == null || nvNew.getHoten().isEmpty()) {
            return;
        }
        try {
            if (!checkDate()) {
                return;
            }
        } catch (ParseException ex) {
            Logger.getLogger(ThemNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!isPhoneNumberValid(nvNew.getSdt())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng nhập lại");
            return;
        }
        if (!isValidEmail(nvNew.getEmail())) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ. Vui lòng nhập lại");
            return;
        }
        nvNew.setManv(manv);
        nhanVienBUS = new NhanVienBUS();
        boolean thanhCong = nhanVienBUS.suaNhanVien(nvNew);
        if (thanhCong) {
            JOptionPane.showMessageDialog(null, "Lưu thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);   
            if (nv != null) {
                nv.hienThiListNhanVien(nhanVienBUS.getAllNhanVien());
            }
            dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thông tin thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        btnGroupGioiTinh = new javax.swing.ButtonGroup();
        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        txtTenNV = new javax.swing.JTextField();
        btnThemNV = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblTenNV = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rdbMale = new javax.swing.JRadioButton();
        rdbFemale = new javax.swing.JRadioButton();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlNgaySinh = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTop.setBackground(new java.awt.Color(0, 102, 204));

        lblTitle.setBackground(new java.awt.Color(0, 102, 204));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("SỬA NHÂN VIÊN");

        btnThemNV.setBackground(new java.awt.Color(0, 102, 204));
        btnThemNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setText("Lưu thông tin");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
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

        lblTenNV.setText("Họ và tên");

        lblGioiTinh.setText("Giới tính");

        btnGroupGioiTinh.add(rdbMale);
        rdbMale.setText("Nam");

        btnGroupGioiTinh.add(rdbFemale);
        rdbFemale.setText("Nữ");

        lblNgaySinh.setText("Ngày sinh");

        lblSDT.setText("Số điện thoại");

        lblEmail.setText("Email");

        pnlNgaySinh.setLayout(new javax.swing.BoxLayout(pnlNgaySinh, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCenterLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(rdbMale)
                        .addGap(131, 131, 131)
                        .addComponent(rdbFemale))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT)
                                .addComponent(txtEmail)
                                .addComponent(lblNgaySinh)
                                .addComponent(pnlNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(btnThemNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTenNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblGioiTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbMale)
                    .addComponent(rdbFemale))
                .addGap(18, 18, 18)
                .addComponent(lblNgaySinh)
                .addGap(18, 18, 18)
                .addComponent(pnlNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblSDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        try {
            suaNhanVien();
        } catch (IOException ex) {
            Logger.getLogger(SuaNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        FlatRobotoFont.install();
//        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
//        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
//        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
//        FlatIntelliJLaf.registerCustomDefaultsSource("style");
//        FlatIntelliJLaf.setup();
//        new SuaNhanVien1().setVisible(true);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNgaySinh;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JRadioButton rdbFemale;
    private javax.swing.JRadioButton rdbMale;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables

}
