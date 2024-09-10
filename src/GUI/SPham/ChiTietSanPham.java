/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.SPham;

import BUS.KhuVucKhoBUS;
import BUS.LoaiBUS;
import BUS.ThuongHieuBUS;
import BUS.XuatXuBUS;
import DTO.SanPhamDTO;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class ChiTietSanPham extends javax.swing.JFrame {

    ThuongHieuBUS thuongHieuBUS = new ThuongHieuBUS();
    LoaiBUS loaiBUS = new LoaiBUS();
    XuatXuBUS xuatXuBUS = new XuatXuBUS();
    KhuVucKhoBUS khuVucKhoBUS = new KhuVucKhoBUS();
    String hinhAnh;

    public ChiTietSanPham() {
        initComponents();
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        loadCombobox();
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public ChiTietSanPham(SanPhamDTO sanPhamDTO) {
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
        btnChonAnh.setEnabled(false);
        btnLuuSP.setEnabled(false);
        
        txtTenSP.setEditable(false);
        txtSizeSP.setEditable(false);
        txtGiaNhap.setEditable(false);
        txtGiaXuat.setEditable(false);

        cbxThuongHieu.setEnabled(false);
        cbxLoai.setEnabled(false);
        cbxXuatXu.setEnabled(false);
        cbxKho.setEnabled(false);

        loadCombobox();
        LoadDuLieu(sanPhamDTO);

        sanPhamDTO.getMasp();
        hinhAnh = sanPhamDTO.getHinhanh();
        System.out.println(hinhAnh);
    }

    private void LoadDuLieu(SanPhamDTO sanPhamDTO) {
        txtTenSP.setText(sanPhamDTO.getTensp());
        txtSizeSP.setText(String.valueOf(sanPhamDTO.getSize()));
        txtGiaNhap.setText(FormatVND(sanPhamDTO.getGianhap()));
        txtGiaXuat.setText(FormatVND(sanPhamDTO.getGiaxuat()));
        ImageIcon icon = new ImageIcon("./src/img_product/" + sanPhamDTO.getHinhanh());
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(lblAnhSanPham.getWidth(), lblAnhSanPham.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        lblAnhSanPham.setIcon(scaledIcon);

        String tenThuongHieu = thuongHieuBUS.getSelectByID(sanPhamDTO.getThuonghieu()).getTenthuonghieu();
        cbxThuongHieu.setSelectedItem(tenThuongHieu);

        String tenKho = khuVucKhoBUS.selectByID(sanPhamDTO.getKhuvuckho()).getTenkhuvuc();
        cbxKho.setSelectedItem(tenKho);

        String tenLoai = loaiBUS.selectByID(sanPhamDTO.getLoai()).getTenloai();
        cbxLoai.setSelectedItem(tenLoai);

        String tenXuatXu = xuatXuBUS.selectByID(sanPhamDTO.getXuatxu()).getTenxuatxu();
        cbxXuatXu.setSelectedItem(tenXuatXu);
    }

    public static String FormatVND(int vnd) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(vnd) + "đ";
    }

    private void loadCombobox() {
        String[] arrTenThuongHieu = thuongHieuBUS.getArrTenThuongHieu();
        selectCombobox(cbxThuongHieu, arrTenThuongHieu);

        String[] arrTenLoai = loaiBUS.getArrTenLoai();
        selectCombobox(cbxLoai, arrTenLoai);

        String[] arrTenXuatXu = xuatXuBUS.getArrTenXuatXu();
        selectCombobox(cbxXuatXu, arrTenXuatXu);

        String[] arrTenKhuVucKho = khuVucKhoBUS.getArrTenKho();
        selectCombobox(cbxKho, arrTenKhuVucKho);

    }

    private void selectCombobox(JComboBox comboBox, String[] obj) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String ob : obj) {
            model.addElement(ob);
        }
        comboBox.setModel(model);
    }
    
    public static int parseVND(String vnd) {
        // Remove commas and currency symbol
        String cleanString = vnd.replaceAll("[^\\d]", ""); // Remove all non-digit characters
        // Convert the cleaned string to an integer
        return Integer.parseInt(cleanString);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        anhSanPham = new javax.swing.JPanel();
        lblAnhSanPham = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        lblKho = new javax.swing.JLabel();
        txtSizeSP = new javax.swing.JTextField();
        lblSize = new javax.swing.JLabel();
        cbxThuongHieu = new javax.swing.JComboBox<>();
        lblXuatXu = new javax.swing.JLabel();
        cbxKho = new javax.swing.JComboBox<>();
        lblLoai = new javax.swing.JLabel();
        cbxXuatXu = new javax.swing.JComboBox<>();
        cbxLoai = new javax.swing.JComboBox<>();
        lblThuongHieu = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        txtGiaNhap = new javax.swing.JTextField();
        lblGiaNhap = new javax.swing.JLabel();
        txtGiaXuat = new javax.swing.JTextField();
        lblGiaXuat = new javax.swing.JLabel();
        btnLuuSP = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTop.setBackground(new java.awt.Color(51, 153, 255));

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("CHI TIẾT SẢN PHẨM");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        anhSanPham.setPreferredSize(new java.awt.Dimension(330, 240));

        javax.swing.GroupLayout anhSanPhamLayout = new javax.swing.GroupLayout(anhSanPham);
        anhSanPham.setLayout(anhSanPhamLayout);
        anhSanPhamLayout.setHorizontalGroup(
            anhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(anhSanPhamLayout.createSequentialGroup()
                .addComponent(lblAnhSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        anhSanPhamLayout.setVerticalGroup(
            anhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        lblTenSP.setText("Tên sản phẩm");

        lblKho.setText("Khu vực kho");

        lblSize.setText("Size");

        lblXuatXu.setText("Xuất xứ");

        cbxKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblLoai.setText("Loại");

        cbxXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblThuongHieu.setText("Thương hiệu");

        btnChonAnh.setText("Chọn hình ảnh");

        lblGiaNhap.setText("Giá nhập");

        lblGiaXuat.setText("Giá xuất");

        btnLuuSP.setText("Lưu");

        btnCancel.setText("Quay lại");
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
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(anhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(btnLuuSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblXuatXu, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblKho, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxKho, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxXuatXu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTenSP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlCenterLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblGiaNhap, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnlCenterLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblThuongHieu)
                                        .addComponent(lblLoai)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSizeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSize))
                            .addGap(18, 18, 18)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblGiaXuat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiaXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlCenterLayout.createSequentialGroup()
                                        .addComponent(lblTenSP)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCenterLayout.createSequentialGroup()
                                        .addComponent(lblGiaNhap)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)
                                .addComponent(lblSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSizeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(lblGiaXuat)
                                .addGap(4, 4, 4)
                                .addComponent(txtGiaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(lblKho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxKho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblXuatXu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(lblLoai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblThuongHieu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(anhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLuuSP)
                        .addComponent(btnCancel)))
                .addGap(91, 91, 91))
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
                .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
//        new ThemSanPham().setVisible(true);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel anhSanPham;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnLuuSP;
    private javax.swing.JComboBox<String> cbxKho;
    private javax.swing.JComboBox<String> cbxLoai;
    private javax.swing.JComboBox<String> cbxThuongHieu;
    private javax.swing.JComboBox<String> cbxXuatXu;
    private javax.swing.JLabel lblAnhSanPham;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblGiaXuat;
    private javax.swing.JLabel lblKho;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThuongHieu;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtGiaXuat;
    private javax.swing.JTextField txtSizeSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
