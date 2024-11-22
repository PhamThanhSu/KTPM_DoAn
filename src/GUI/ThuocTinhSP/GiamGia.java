/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThuocTinhSP;

import BUS.GiamGiaBUS;
import BUS.GiamGiaSanPhamBUS;
import BUS.XuatXuBUS;
import DTO.ChiTietQuyenDTO;
import DTO.GiamGiaDTO;
import DTO.GiamGiaSanPhamDTO;
import DTO.NhomQuyenDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import DTO.XuatXuDTO;
import GUI.Component.CheckAction;
import GUI.Panel.TaoPhieuNhap;
import GUI.PhanQuyen;
import GUI.ThuocTinhSP.ApDungSanPhamDialog;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phatl
 */
public class GiamGia extends javax.swing.JPanel {

    ApDungSanPhamDialog apDungSanPhamDialog;
    FormGiamGiaDialog forrm;
    GiamGiaBUS giamGiaBUS;
    GiamGiaDTO giamGiaDTO;
    TaoPhieuNhap taoPhieuNhap;
    GiamGiaSanPhamDTO giamGiaSanPhamDTO;
    GiamGiaSanPhamBUS giamGiaSanPhamBUS;
    private ArrayList<GiamGiaSanPhamDTO> selectedProducts;
    private ArrayList<GiamGiaSanPhamDTO> listSanPhamDaChon = new ArrayList<>();
    Color BackgroundColor = new Color(240, 247, 250);

    public GiamGia(TaiKhoanDTO taiKhoanDTO) throws SQLException {
        initComponents();
        hienThiListGiamGia();

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(BackgroundColor);
        pnlTop.setBackground(BackgroundColor);
        pnlTop.setBorder(new EmptyBorder(0, 0, 10, 0));
        pnlLeft.setOpaque(false);

        lblXuatXu.setFont(new Font("Tahoma", Font.BOLD, 20));
        tblgiamgia.setFocusable(false);
        tblgiamgia.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table

        tblgiamgia.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            layGiamGiaTuBang();
        });

        btnThemGiamGia.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaGiamGia.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaGiamGia.setIcon(new FlatSVGIcon("./icon/delete.svg"));

        String[] action = {"create", "update", "delete", "view"};
        Map<String, JButton> buttonMap = new HashMap<>();
        buttonMap.put("create", btnThemGiamGia);       // Nút thêm
        buttonMap.put("delete", btnXoaGiamGia);        // Nút xóa
        buttonMap.put("update", btnSuaGiamGia);        // Nút sửa
//        buttonMap.put("detail", btnChiTietPN);    // Nút chi tiết
//        buttonMap.put("export", btnXuatExcelPN);  // Nút xuất Excel
//        buttonMap.put("import",btnNhapExcel);  // Nút nhập Excel

// Tạo đối tượng CheckAction
        CheckAction checkAction = new CheckAction(taiKhoanDTO.getManhomquyen(), "thuoctinh", action, buttonMap);

    }

    public void hienThiListGiamGia() throws SQLException {
        giamGiaBUS = new GiamGiaBUS();
        ArrayList<GiamGiaDTO> listGiamGia = giamGiaBUS.getAllGiamGia();
        DefaultTableModel model = (DefaultTableModel) tblgiamgia.getModel();
        model.setRowCount(0);
        for (GiamGiaDTO giamgia : listGiamGia) { // Thay đổi tên biến
            Object[] row = {
                giamgia.getMagiamgia(),
                giamgia.getTengiamgia(),
                giamgia.getPhantramgiam(),
                giamgia.getGiatrihoadon(),
                giamgia.getNgaybatdau(),
                giamgia.getNgayketthuc(),
                giamgia.getTrangthai()
            };
            model.addRow(row);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblgiamgia.getColumnCount(); i++) {
            tblgiamgia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    StringBuilder checkInputErrorMessages = new StringBuilder(); // Dùng StringBuilder để lưu trữ thông báo lỗi

    public boolean CheckInput(String tenGiamGia, String phanTramGiam, String hoaDonToiThieu, Date ngayBatDau, Date ngayKetThuc) {

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

        if (ngayBatDau == null) {
            checkInputErrorMessages.append("Ngày bắt đầu không được để trống.\n");
        }

        if (ngayKetThuc == null) {
            checkInputErrorMessages.append("Ngày kết thúc không được để trống.\n");
        } else {
            // Kiểm tra ngày kết thúc không được sớm hơn ngày bắt đầu
            if (ngayBatDau != null && ngayKetThuc.before(ngayBatDau)) {
                checkInputErrorMessages.append("Ngày kết thúc phải sau ngày bắt đầu.\n");
            }

            // Kiểm tra ngày bắt đầu và ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại
            Date currentDate = new Date();  // Lấy ngày hiện tại
            System.out.println("Ngày hiện tại: " + currentDate);

            Date startUtilDate = new Date(ngayBatDau.getTime());
            Date endUtilDate = new Date(ngayKetThuc.getTime());
        }

        // Nếu có lỗi, trả về thông báo lỗi chung
        if (checkInputErrorMessages.length() > 0) {
            return false; // Trả về false nếu có lỗi
        }

        return true; // Trả về true nếu tất cả các điều kiện hợp lệ
    }

    private void xoaGiamGia() throws SQLException {
        int selectedRow = tblgiamgia.getSelectedRow();
        if (selectedRow != -1) {
            int magiamgia = (int) tblgiamgia.getValueAt(selectedRow, 0);
            giamGiaBUS = new GiamGiaBUS();
            boolean thanhCong = giamGiaBUS.deleteGiamGia(magiamgia);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Đã xóa giảm giá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiListGiamGia();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một xuất xứ để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblMaGiamGia2 = new javax.swing.JLabel();
        lblMaGiamGia4 = new javax.swing.JLabel();
        scrollThuongHieu = new javax.swing.JScrollPane();
        tblgiamgia = new javax.swing.JTable();
        pnlTop = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        lblXuatXu = new javax.swing.JLabel();
        pnlLeft = new javax.swing.JPanel();
        btnThemGiamGia = new javax.swing.JButton();
        btnSuaGiamGia = new javax.swing.JButton();
        btnXoaGiamGia = new javax.swing.JButton();

        lblMaGiamGia2.setText("Mã giảm giá");

        lblMaGiamGia4.setText("Mã giảm giá");

        setLayout(new java.awt.BorderLayout());

        tblgiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giảm giá", "Tên giảm giá", "Phần trăm", "Hóa đơn tối thiểu", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"
            }
        ));
        scrollThuongHieu.setViewportView(tblgiamgia);

        add(scrollThuongHieu, java.awt.BorderLayout.CENTER);

        title.setBackground(new java.awt.Color(0, 102, 255));
        title.setForeground(new java.awt.Color(255, 255, 255));

        lblXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblXuatXu.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatXu.setText("CHƯƠNG TRÌNH GIẢM GIÁ");
        title.add(lblXuatXu);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlLeft.setPreferredSize(new java.awt.Dimension(130, 580));

        btnThemGiamGia.setText("Thêm");
        btnThemGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGiamGiaActionPerformed(evt);
            }
        });

        btnSuaGiamGia.setText("Sửa");
        btnSuaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGiamGiaActionPerformed(evt);
            }
        });

        btnXoaGiamGia.setText("Xóa");
        btnXoaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThemGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(btnSuaGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoaGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnThemGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        add(pnlLeft, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGiamGiaActionPerformed
        try {
            int isUpdate = 0;  // Tham số 1 cho biết là update, 0 là add
            giamGiaDTO = new GiamGiaDTO();
            FormGiamGiaDialog giamGiaDialog = new FormGiamGiaDialog(this, giamGiaDTO, isUpdate);
            giamGiaDialog.setLocationRelativeTo(null);
            giamGiaDialog.setVisible(true);
            hienThiListGiamGia();
        } catch (SQLException ex) {
            Logger.getLogger(GiamGia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnThemGiamGiaActionPerformed

    private void btnSuaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGiamGiaActionPerformed
        giamGiaDTO = layGiamGiaTuBang();

        // Kiểm tra xem có dữ liệu giảm giá không
        if (giamGiaDTO != null) {
            try {
                int isUpdate = 1;  // Tham số 1 cho biết là update, 0 là add
                FormGiamGiaDialog giamGiaDialog = new FormGiamGiaDialog(this, giamGiaDTO, isUpdate);
                giamGiaDialog.setLocationRelativeTo(null);
                giamGiaDialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(GiamGia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Nếu không có dữ liệu giảm giá được chọn, có thể thông báo cho người dùng
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giảm giá để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnSuaGiamGiaActionPerformed
    private GiamGiaDTO layGiamGiaTuBang() {
        // Lấy chỉ số dòng được chọn
        int selectedRow = tblgiamgia.getSelectedRow();
        taoPhieuNhap = new TaoPhieuNhap();
        // Kiểm tra nếu có dòng được chọn
        if (selectedRow != -1) {
            // Lấy chỉ số của các cột trong bảng giảm giá
            int magiamgiaColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã giảm giá", tblgiamgia);
            int tengiamgiaColumnIndex = taoPhieuNhap.getColumnIndexByName("Tên giảm giá", tblgiamgia);
            int phantramgiamColumnIndex = taoPhieuNhap.getColumnIndexByName("Phần trăm", tblgiamgia);
            int giatrihoadonColumnIndex = taoPhieuNhap.getColumnIndexByName("Hóa đơn tối thiểu", tblgiamgia);
            int ngaybatdauColumnIndex = taoPhieuNhap.getColumnIndexByName("Ngày bắt đầu", tblgiamgia);
            int ngayketthucColumnIndex = taoPhieuNhap.getColumnIndexByName("Ngày kết thúc", tblgiamgia);
            int trangthaiColumnIndex = taoPhieuNhap.getColumnIndexByName("Tình trạng", tblgiamgia);

            // Lấy giá trị từ các cột
            int maGiamGia = (Integer) tblgiamgia.getValueAt(selectedRow, magiamgiaColumnIndex);
            String tenGiamGia = (String) tblgiamgia.getValueAt(selectedRow, tengiamgiaColumnIndex);
            int phanTramGiam = (Integer) tblgiamgia.getValueAt(selectedRow, phantramgiamColumnIndex);
            long giaTriHoaDon = (long) tblgiamgia.getValueAt(selectedRow, giatrihoadonColumnIndex);
            Date ngayBatDau = (Date) tblgiamgia.getValueAt(selectedRow, ngaybatdauColumnIndex);
            Date ngayKetThuc = (Date) tblgiamgia.getValueAt(selectedRow, ngayketthucColumnIndex);
            String trangThai = (String) tblgiamgia.getValueAt(selectedRow, trangthaiColumnIndex);

            // Tạo đối tượng GiamGiaDTO từ các giá trị lấy được
            GiamGiaDTO giamGiaDTO = new GiamGiaDTO(maGiamGia, tenGiamGia, phanTramGiam, giaTriHoaDon, ngayBatDau, ngayKetThuc, trangThai);

            return giamGiaDTO;
        } else {
            // Nếu không có dòng nào được chọn
            System.out.println("Chưa chọn giảm giá.");
            return null;
        }
    }

    private void btnXoaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGiamGiaActionPerformed
        // TODO add your handling code here:
        giamGiaDTO = layGiamGiaTuBang();
        if (giamGiaDTO != null) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                try {
                    xoaGiamGia();
                } catch (SQLException ex) {
                    Logger.getLogger(GiamGia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Nếu không có dữ liệu giảm giá được chọn, có thể thông báo cho người dùng
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giảm giá để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnXoaGiamGiaActionPerformed

    public ArrayList<GiamGiaSanPhamDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(ArrayList<GiamGiaSanPhamDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaGiamGia;
    private javax.swing.JButton btnThemGiamGia;
    private javax.swing.JButton btnXoaGiamGia;
    private javax.swing.JLabel lblMaGiamGia2;
    private javax.swing.JLabel lblMaGiamGia4;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrollThuongHieu;
    private javax.swing.JTable tblgiamgia;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
