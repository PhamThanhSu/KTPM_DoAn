/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.SPham;

import BUS.KhuVucKhoBUS;
import BUS.LoaiBUS;
import BUS.SanPhamBUS;
import BUS.ThuongHieuBUS;
import BUS.XuatXuBUS;
import DTO.SanPhamDTO;
import GUI.SanPham;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ThemSanPham extends javax.swing.JFrame {

    private File selectedFile;
    private Random randomGenerator = new Random();
    ThuongHieuBUS thuongHieuBUS = new ThuongHieuBUS();
    LoaiBUS loaiBUS = new LoaiBUS();
    XuatXuBUS xuatXuBUS = new XuatXuBUS();
    KhuVucKhoBUS khuVucKhoBUS = new KhuVucKhoBUS();
    SanPhamDTO sanPhamDTO;
    SanPhamBUS sanPhamBUS;
    SanPham sp;

    public ThemSanPham(SanPham sp) {
        initComponents();
        this.sp = sp;
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

    private void addAnh() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);

                if (img != null) {
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(lblAnhSanPham.getWidth(), lblAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
                    lblAnhSanPham.removeAll();
                    lblAnhSanPham.setIcon(icon);
                    lblAnhSanPham.repaint();

                } else {
                    JOptionPane.showMessageDialog(this, "Selected file is not a valid image.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
               
            }
        }
    }

    private String copyImageToProductDirectory(String sourceImagePath) {
        File sourceImageFile = new File(sourceImagePath);
        String destinationDirectory = "./src/img_product/";
        File destFolder = new File(destinationDirectory);

        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        // Lấy tên của ảnh từ đường dẫn của nó
        String imageName = sourceImageFile.getName();

        // Thêm số ngẫu nhiên vào tên file ảnh để tránh trùng lặp
        int randomNumber = randomGenerator.nextInt(1000);
        String[] parts = imageName.split("\\.");
        String newImageName = parts[0] + "_" + randomNumber + "." + parts[1];

        Path destinationPath = Paths.get(destinationDirectory, newImageName);
        try {
            Files.copy(sourceImageFile.toPath(), destinationPath);
        } catch (IOException ex) {
            Logger.getLogger(ThemSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Image copied successfully to " + destinationPath);
        return newImageName;
    }

    private SanPhamDTO getInfoSanPhamMoi() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(null, "vui lòng chọn hình ảnh");
        } else if (txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm");
        } else if (!isInteger(txtSizeSP.getText()) || Integer.parseInt(txtSizeSP.getText()) < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập size hợp lệ");
        } else if (!isInteger(txtGiaNhap.getText())) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá nhập là số");
        } else if (!isInteger(txtGiaXuat.getText())) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá xuất là số");
        }

        String hinhAnh = selectedFile.getPath();
        String tenSanPham = txtTenSP.getText();
        int sizeSanPham = Integer.parseInt(txtSizeSP.getText());
        int maLoaiSanPham = loaiBUS.getAllLoai().get(this.cbxLoai.getSelectedIndex()).getMaloai();
        int maThuongHieuSanPham = thuongHieuBUS.getAllThuongHieu().get(this.cbxThuongHieu.getSelectedIndex()).getMathuonghieu();
        int maXuatXuSanPham = xuatXuBUS.getAllXuatXu().get(this.cbxXuatXu.getSelectedIndex()).getMaxuatxu();
        int maKhuVucKhoSanPham = khuVucKhoBUS.getAllKho().get(this.cbxKho.getSelectedIndex()).getMakhuvuc();
        int giaNhapSanPham = Integer.parseInt(txtGiaNhap.getText());
        int giaXuatSanPham = Integer.parseInt(txtGiaXuat.getText());
        sanPhamDTO = new SanPhamDTO(tenSanPham, sizeSanPham, hinhAnh, maXuatXuSanPham, maLoaiSanPham, maThuongHieuSanPham, maKhuVucKhoSanPham, giaNhapSanPham, giaXuatSanPham);
        return sanPhamDTO;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void themSanPham() throws IOException {
        SanPhamDTO spNew = getInfoSanPhamMoi();
        sanPhamBUS = new SanPhamBUS();

        // Kiểm tra xem sản phẩm đã tồn tại trong cơ sở dữ liệu hay chưa
        if (sanPhamBUS.checkSize(spNew.getTensp(), spNew.getSize())) {
            // Nếu chưa tồn tại, thêm sản phẩm vào cơ sở dữ liệu
            String tenHinhAnh = copyImageToProductDirectory(spNew.getHinhanh());
            spNew.setHinhanh(tenHinhAnh);
            boolean thanhCong = sanPhamBUS.themSanPham(spNew);

            // Kiểm tra xem việc thêm sản phẩm đã thành công hay không
            if (thanhCong) {

                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                sp.hienThiListSanPham();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOne = new javax.swing.JPanel();
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
        btnTaoCauHinh = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        anhSanPham.setPreferredSize(new java.awt.Dimension(330, 240));

        lblAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));

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
        cbxKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKhoActionPerformed(evt);
            }
        });

        lblLoai.setText("Loại");

        cbxXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiActionPerformed(evt);
            }
        });

        lblThuongHieu.setText("Thương hiệu");

        btnChonAnh.setText("Chọn hình ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        lblGiaNhap.setText("Giá nhập");

        lblGiaXuat.setText("Giá xuất");

        btnTaoCauHinh.setBackground(new java.awt.Color(0, 255, 0));
        btnTaoCauHinh.setText("Lưu");
        btnTaoCauHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoCauHinhActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
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
                        .addComponent(btnTaoCauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblLoai)
                                    .addComponent(lblThuongHieu)))))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSizeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSize))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblGiaXuat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
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
                                .addGap(6, 6, 6)
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
                        .addComponent(btnTaoCauHinh)
                        .addComponent(btnCancel)))
                .addGap(91, 91, 91))
        );

        pnlTop.setBackground(new java.awt.Color(51, 153, 255));

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("THÊM SẢN PHẨM MỚI");

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
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelOneLayout = new javax.swing.GroupLayout(panelOne);
        panelOne.setLayout(panelOneLayout);
        panelOneLayout.setHorizontalGroup(
            panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelOneLayout.setVerticalGroup(
            panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOneLayout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnTaoCauHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoCauHinhActionPerformed
        try {
            themSanPham();
        } catch (IOException ex) {
            Logger.getLogger(ThemSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTaoCauHinhActionPerformed

    private void cbxLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLoaiActionPerformed

    private void cbxKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKhoActionPerformed


    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        addAnh();
        String hinhAnh = selectedFile.getPath();
        System.out.println(hinhAnh);
    }//GEN-LAST:event_btnChonAnhActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel anhSanPham;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnTaoCauHinh;
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
    private javax.swing.JPanel panelOne;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtGiaXuat;
    private javax.swing.JTextField txtSizeSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
