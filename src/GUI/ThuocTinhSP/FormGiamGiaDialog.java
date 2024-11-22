/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.ThuocTinhSP;

import BUS.GiamGiaBUS;
import BUS.GiamGiaSanPhamBUS;
import DTO.GiamGiaDTO;
import DTO.GiamGiaSanPhamDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author this pc
 */
public class FormGiamGiaDialog extends javax.swing.JDialog {

    GiamGiaBUS giamGiaBUS;
    GiamGiaDTO giamGiaDTO;
    GiamGiaSanPhamBUS giamGiaSanPhamBUS;
    ApDungSanPhamDialog apDungSanPhamDialog;
    private ArrayList<GiamGiaSanPhamDTO> listSanPhamDaChon;
    private GiamGia giamGia;
    private int isUpdate;

    /**
     * Creates new form NewJDialog
     */
    public FormGiamGiaDialog(GiamGia giamGia, GiamGiaDTO giamGiaDTO, int isUpdate) throws SQLException {
        initComponents();
        this.giamGia = giamGia; // Lưu tham chiếu đến GiamGia
        this.giamGiaDTO = giamGiaDTO;
        this.isUpdate = isUpdate;

// Điền thông tin hiện tại vào các trường nếu là update
        if (isUpdate == 1) { // =1 là update
            txtMaGiamGia.setEnabled(false);
            ngaybatdau.setEnabled(false);
            ngayketthuc.setEnabled(false);
            txtMaGiamGia.setText(String.valueOf(giamGiaDTO.getMagiamgia()));  // Không cho phép sửa mã giảm giá khi cập nhật
            txtTenGiamGia.setText(giamGiaDTO.getTengiamgia());
            txtPhantramgiamgia.setText(String.valueOf(giamGiaDTO.getPhantramgiam()));
            txthoadontoithieu.setText(String.valueOf(giamGiaDTO.getGiatrihoadon()));
            ngaybatdau.setDate(giamGiaDTO.getNgaybatdau());
            ngayketthuc.setDate(giamGiaDTO.getNgayketthuc());

            // Chọn trạng thái hiện tại trong comboBox
            cbbtrangthai.setSelectedItem(giamGiaDTO.getTrangthai());

            // Lấy danh sách sản phẩm đã áp dụng giảm giá cho mã này
            giamGiaSanPhamBUS = new GiamGiaSanPhamBUS();
            listSanPhamDaChon = giamGiaSanPhamBUS.selectGiamGiaSPByID(giamGiaDTO.getMagiamgia());
        } else { // ngược lại là add
            // Xử lý cho trường hợp thêm mới
            // Gán các trường mặc định cho phép người dùng nhập vào
            giamGiaBUS = new GiamGiaBUS();
            txtMaGiamGia.setText(giamGiaBUS.generateMaGiamGia());
            txtMaGiamGia.setEnabled(false);
            txtTenGiamGia.setText("");
            txtPhantramgiamgia.setText("");
            txthoadontoithieu.setText("");
            ngaybatdau.setDate(null);
            ngayketthuc.setDate(null);
            cbbtrangthai.setSelectedItem("Chưa áp dụng");  // Giá trị mặc định cho trạng thái
        }
    }

    public FormGiamGiaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FormGiamGiaDialog() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        tenThuongHieu = new javax.swing.JPanel();
        lblMaGiamGia = new javax.swing.JLabel();
        txtPhantramgiamgia = new javax.swing.JTextField();
        lblTenGiamGia = new javax.swing.JLabel();
        txthoadontoithieu = new javax.swing.JTextField();
        lblMaGiamGia1 = new javax.swing.JLabel();
        lblMaGiamGia3 = new javax.swing.JLabel();
        lblMaGiamGia5 = new javax.swing.JLabel();
        txtMaGiamGia = new javax.swing.JTextField();
        txtTenGiamGia = new javax.swing.JTextField();
        lblMaGiamGia6 = new javax.swing.JLabel();
        ngaybatdau = new com.toedter.calendar.JDateChooser();
        ngayketthuc = new com.toedter.calendar.JDateChooser();
        btnsanphamapdung = new javax.swing.JButton();
        cbbtrangthai = new javax.swing.JComboBox<>();
        btnluu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tenThuongHieu.setBackground(new java.awt.Color(255, 255, 255));

        lblMaGiamGia.setText("Mã giảm giá");

        txtPhantramgiamgia.setMinimumSize(new java.awt.Dimension(150, 30));
        txtPhantramgiamgia.setPreferredSize(new java.awt.Dimension(150, 30));
        txtPhantramgiamgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhantramgiamgiaActionPerformed(evt);
            }
        });

        lblTenGiamGia.setText("Tên giảm giá");

        txthoadontoithieu.setMinimumSize(new java.awt.Dimension(150, 30));
        txthoadontoithieu.setPreferredSize(new java.awt.Dimension(150, 30));
        txthoadontoithieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthoadontoithieuActionPerformed(evt);
            }
        });

        lblMaGiamGia1.setText("Phần trăm giảm");

        lblMaGiamGia3.setText("Ngày bắt đầu");

        lblMaGiamGia5.setText("Hóa đơn tối thiểu");

        txtMaGiamGia.setMinimumSize(new java.awt.Dimension(150, 30));
        txtMaGiamGia.setPreferredSize(new java.awt.Dimension(150, 30));
        txtMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGiamGiaActionPerformed(evt);
            }
        });

        txtTenGiamGia.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTenGiamGia.setPreferredSize(new java.awt.Dimension(150, 30));
        txtTenGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenGiamGiaActionPerformed(evt);
            }
        });

        lblMaGiamGia6.setText("Ngày kết thúc");

        btnsanphamapdung.setText("Sán phẩm áp dụng");
        btnsanphamapdung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsanphamapdungActionPerformed(evt);
            }
        });

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Có hiệu lực", "Hết hiệu lực", "Sắp bắt đầu" }));
        cbbtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtrangthaiActionPerformed(evt);
            }
        });

        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tenThuongHieuLayout = new javax.swing.GroupLayout(tenThuongHieu);
        tenThuongHieu.setLayout(tenThuongHieuLayout);
        tenThuongHieuLayout.setHorizontalGroup(
            tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tenThuongHieuLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addComponent(lblMaGiamGia3)
                        .addGap(87, 87, 87)
                        .addComponent(ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMaGiamGia6)
                        .addGap(27, 27, 27)
                        .addComponent(ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tenThuongHieuLayout.createSequentialGroup()
                                .addComponent(lblMaGiamGia)
                                .addGap(92, 92, 92)
                                .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lblTenGiamGia)
                                .addGap(30, 30, 30)
                                .addComponent(txtTenGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tenThuongHieuLayout.createSequentialGroup()
                                .addComponent(lblMaGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtPhantramgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lblMaGiamGia5)
                                .addGap(6, 6, 6)
                                .addComponent(txthoadontoithieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tenThuongHieuLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(btnsanphamapdung)
                                .addGap(52, 52, 52)
                                .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(247, 247, 247))
        );
        tenThuongHieuLayout.setVerticalGroup(
            tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tenThuongHieuLayout.createSequentialGroup()
                .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(lblMaGiamGia))
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(lblTenGiamGia))
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(txtTenGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tenThuongHieuLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaGiamGia1)
                            .addComponent(lblMaGiamGia5)))
                    .addComponent(txtPhantramgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthoadontoithieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaGiamGia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMaGiamGia6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngaybatdau, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(ngayketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tenThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnsanphamapdung, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(cbbtrangthai))
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhantramgiamgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhantramgiamgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhantramgiamgiaActionPerformed

    private void txthoadontoithieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoadontoithieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoadontoithieuActionPerformed

    private void txtMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGiamGiaActionPerformed

    private void txtTenGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenGiamGiaActionPerformed

    private void btnsanphamapdungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamapdungActionPerformed
        int maGiamGia = Integer.parseInt(txtMaGiamGia.getText().replaceAll("[GG]", "").trim());
        apDungSanPhamDialog = new ApDungSanPhamDialog(listSanPhamDaChon);
        apDungSanPhamDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Sau khi JDialog đóng, lấy danh sách sản phẩm đã chọn
                listSanPhamDaChon = apDungSanPhamDialog.getListSanPhamDaChon();

                if (listSanPhamDaChon != null && !listSanPhamDaChon.isEmpty()) {
                    // Xử lý danh sách sản phẩm đã chọn
                    System.out.println("Sản phẩm đã chọn: " + listSanPhamDaChon);
                } else {
                    System.out.println("Không có sản phẩm nào được chọn.");
                }
            }
        });
        apDungSanPhamDialog.setVisible(true); // Hiển thị hộp thoại và chờ người dùng nhấn nút

    }//GEN-LAST:event_btnsanphamapdungActionPerformed

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        if (isUpdate == 1) {
            try {
                suaGiamGia();
                giamGia.hienThiListGiamGia(); // Cập nhật lại danh sách giảm giá trên bảng
                this.dispose(); // Đóng dialog
            } catch (SQLException ex) {
                Logger.getLogger(FormGiamGiaDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("bấm lưu " + listSanPhamDaChon);
        } else {
            try {
                // Thêm mới mã giảm giá
                themGiamGia();
                giamGia.hienThiListGiamGia(); // Cập nhật lại danh sách giảm giá trên bảng
                this.dispose(); // Đóng dialog
            } catch (SQLException ex) {
                Logger.getLogger(FormGiamGiaDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnluuActionPerformed

    StringBuilder checkInputErrorMessages = new StringBuilder(); // Dùng StringBuilder để lưu trữ thông báo lỗi

    public boolean CheckInput(String tenGiamGia, String phanTramGiam, String hoaDonToiThieu) {

        // Kiểm tra các trường không được để trống
        if (tenGiamGia.isEmpty()) {
            checkInputErrorMessages.append("Tên giảm giá không được để trống.\n");
        }

        if (phanTramGiam.isEmpty()) {
            checkInputErrorMessages.append("Phần trăm giảm giá không được để trống.\n");
        } else {
            try {
                int phanTram = Integer.parseInt(phanTramGiam);
                if (phanTram < 0 || phanTram > 60) {
                    checkInputErrorMessages.append("Phần trăm giảm không hợp lệ. Phải từ 0 đến 60.\n");
                }
            } catch (NumberFormatException e) {
                checkInputErrorMessages.append("Phần trăm giảm giá phải là một số hợp lệ.\n");
            }
        }

        if (hoaDonToiThieu.isEmpty()) {
            checkInputErrorMessages.append("Giá trị hóa đơn không được để trống.\n");
        } else {
            try {
                long giaTriHoaDon = Long.parseLong(hoaDonToiThieu);
                if (giaTriHoaDon < 0) {
                    checkInputErrorMessages.append("Giá trị hóa đơn không hợp lệ.\n");
                }
            } catch (NumberFormatException e) {
                checkInputErrorMessages.append("Giá trị hóa đơn phải là một số hợp lệ.\n");
            }
        }
        // Nếu có lỗi, trả về thông báo lỗi chung
        if (checkInputErrorMessages.length() > 0) {
            return false; // Trả về false nếu có lỗi
        }

        return true; // Trả về true nếu tất cả các điều kiện hợp lệ
    }

    public boolean CheckInputAdd(String tenGiamGia, String phanTramGiam, String hoaDonToiThieu, Date ngayBatDau, Date ngayKetThuc, Date currentDate) {

        // Chuyển đổi Date sang LocalDate để so sánh chỉ ngày, tháng, năm
        LocalDate currentLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayBatDauLocalDate = ngayBatDau != null ? ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
        LocalDate ngayKetThucLocalDate = ngayKetThuc != null ? ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

        // Kiểm tra các trường không được để trống
        if (tenGiamGia.isEmpty()) {
            checkInputErrorMessages.append("Tên giảm giá không được để trống.\n");
        }

        if (phanTramGiam.isEmpty()) {
            checkInputErrorMessages.append("Phần trăm giảm giá không được để trống.\n");
        } else {
            try {
                int phanTram = Integer.parseInt(phanTramGiam);
                if (phanTram < 0 || phanTram > 60) {
                    checkInputErrorMessages.append("Phần trăm giảm không hợp lệ. Phải từ 0 đến 60.\n");
                }
            } catch (NumberFormatException e) {
                checkInputErrorMessages.append("Phần trăm giảm giá phải là một số hợp lệ.\n");
            }
        }

        if (hoaDonToiThieu.isEmpty()) {
            checkInputErrorMessages.append("Giá trị hóa đơn không được để trống.\n");
        } else {
            try {
                long giaTriHoaDon = Long.parseLong(hoaDonToiThieu);
                if (giaTriHoaDon < 0) {
                    checkInputErrorMessages.append("Giá trị hóa đơn không hợp lệ.\n");
                }
            } catch (NumberFormatException e) {
                checkInputErrorMessages.append("Giá trị hóa đơn phải là một số hợp lệ.\n");
            }
        }

        if (ngayBatDauLocalDate == null) {
            checkInputErrorMessages.append("Ngày bắt đầu không được để trống.\n");
        }

        if (ngayKetThucLocalDate == null) {
            checkInputErrorMessages.append("Ngày kết thúc không được để trống.\n");
        } else {
            // Kiểm tra ngày kết thúc không được sớm hơn ngày bắt đầu
            if (ngayBatDauLocalDate != null && ngayKetThucLocalDate.isBefore(ngayBatDauLocalDate)) {
                checkInputErrorMessages.append("Ngày kết thúc phải sau ngày bắt đầu.\n");
            }

            // Kiểm tra ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại
            if (ngayBatDauLocalDate != null && ngayBatDauLocalDate.isBefore(currentLocalDate)) {
                checkInputErrorMessages.append("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
            }

            // Kiểm tra ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại
            if (ngayKetThucLocalDate != null && ngayKetThucLocalDate.isBefore(currentLocalDate)) {
                checkInputErrorMessages.append("Ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại.");
            }
        }

        // Nếu có lỗi, trả về thông báo lỗi chung
        if (checkInputErrorMessages.length() > 0) {
            return false; // Trả về false nếu có lỗi
        }

        return true; // Trả về true nếu tất cả các điều kiện hợp lệ
    }

    private void themGiamGia() throws SQLException {
        // Lấy giá trị từ các trường nhập liệu
        String tenGiamGia = txtTenGiamGia.getText().trim();
        String phanTramGiamstr = txtPhantramgiamgia.getText().trim();
        String hoaDonToiThieustr = txthoadontoithieu.getText().trim();
        Date ngayBatDau = ngaybatdau.getDate();
        Date ngayKetThuc = ngayketthuc.getDate();
        Date currentDate = new Date();  // Lấy ngày hiện tại
        System.out.println("Ngày hiện tại: " + currentDate);

        // Kiểm tra các điều kiện cần thiết trước khi thêm mã giảm giá
        if (CheckInputAdd(tenGiamGia, phanTramGiamstr, hoaDonToiThieustr, ngayBatDau, ngayKetThuc, currentDate)) {
            // Khởi tạo đối tượng cần thiết
            giamGiaBUS = new GiamGiaBUS();
            System.out.println("ngày" + ngayBatDau);
            // Lấy mã giảm giá mới
            String maGiamGiaMoistr = txtMaGiamGia.getText().replaceAll("[GG]", "").trim();// Lấy mã giảm giá mới
            int maGiamGiaMoi = Integer.parseInt(maGiamGiaMoistr);
            int phanTramGiam = Integer.parseInt(phanTramGiamstr);
            long hoaDonToiThieu = Long.parseLong(hoaDonToiThieustr);
            String trangthai = (String) cbbtrangthai.getSelectedItem();

            giamGiaDTO = new GiamGiaDTO(maGiamGiaMoi, tenGiamGia, phanTramGiam, hoaDonToiThieu, ngayBatDau, ngayKetThuc, trangthai);

            boolean thanhCong = giamGiaBUS.insertGiamGia(giamGiaDTO);
            if (thanhCong) {
                insertGiamGiaSanPhamToDatabase(maGiamGiaMoi);
            }
        } else {
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: \n" + checkInputErrorMessages.toString(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            // Làm rỗng StringBuilder sau khi hiển thị thông báo lỗi
            checkInputErrorMessages.setLength(0);
        }
    }

    private void insertGiamGiaSanPhamToDatabase(int magiamgia) throws SQLException {
        System.out.println("list sản phẩm đã chọn" + listSanPhamDaChon);
        if (listSanPhamDaChon == null || listSanPhamDaChon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm áp dụng cho phiếu giảm giá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Thêm sản phẩm giảm giá vào database
        giamGiaSanPhamBUS = new GiamGiaSanPhamBUS();
        for (GiamGiaSanPhamDTO sanphamgiamgia : listSanPhamDaChon) {
            giamGiaSanPhamBUS.insertGiamGiaSanPham(sanphamgiamgia);
        }
        JOptionPane.showMessageDialog(null, "Thêm phiếu giảm giá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        txtMaGiamGia.setText(giamGiaBUS.generateMaGiamGia());
        txtPhantramgiamgia.setText("");
        txtTenGiamGia.setText("");
        txthoadontoithieu.setText("");
    }

    private void suaGiamGia() throws SQLException {
        // Lấy giá trị từ các trường nhập liệu
        int maGiamGia = Integer.parseInt(txtMaGiamGia.getText());
        String tenGiamGia = txtTenGiamGia.getText().trim();
        String phanTramGiamstr = txtPhantramgiamgia.getText().trim();
        String hoaDonToiThieustr = txthoadontoithieu.getText().trim();
        String trangThai = (String) cbbtrangthai.getSelectedItem();
        // Kiểm tra các điều kiện cần thiết trước khi thêm mã giảm giá
        if (CheckInput(tenGiamGia, phanTramGiamstr, hoaDonToiThieustr)) {
            // Khởi tạo đối tượng cần thiết
            giamGiaBUS = new GiamGiaBUS();

            int phanTramGiam = Integer.parseInt(phanTramGiamstr);
            long hoaDonToiThieu = Long.parseLong(hoaDonToiThieustr);
            giamGiaDTO = new GiamGiaDTO(maGiamGia, tenGiamGia, phanTramGiam, hoaDonToiThieu, trangThai);

            boolean thanhCong = giamGiaBUS.updateGiamGia(giamGiaDTO);
            if (thanhCong) {
                uppdateGiamGiaSanPhamToDatabase(maGiamGia);
            }
        } else {
            // Hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: \n" + checkInputErrorMessages.toString(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            // Làm rỗng StringBuilder sau khi hiển thị thông báo lỗi
            checkInputErrorMessages.setLength(0);
        }
    }

    private void uppdateGiamGiaSanPhamToDatabase(int magiamgia) throws SQLException {
        if (listSanPhamDaChon == null || listSanPhamDaChon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm áp dụng cho phiếu giảm giá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("bấm lưu " + magiamgia);
        // Thêm sản phẩm giảm giá vào database
        giamGiaSanPhamBUS = new GiamGiaSanPhamBUS();
        boolean success = giamGiaSanPhamBUS.updateSanPhamApDungGiamGia(magiamgia, listSanPhamDaChon);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm phiếu giảm giá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phiếu giảm giá thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsanphamapdung;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel lblMaGiamGia;
    private javax.swing.JLabel lblMaGiamGia1;
    private javax.swing.JLabel lblMaGiamGia3;
    private javax.swing.JLabel lblMaGiamGia5;
    private javax.swing.JLabel lblMaGiamGia6;
    private javax.swing.JLabel lblTenGiamGia;
    private com.toedter.calendar.JDateChooser ngaybatdau;
    private com.toedter.calendar.JDateChooser ngayketthuc;
    private javax.swing.JPanel tenThuongHieu;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtPhantramgiamgia;
    private javax.swing.JTextField txtTenGiamGia;
    private javax.swing.JTextField txthoadontoithieu;
    // End of variables declaration//GEN-END:variables
}