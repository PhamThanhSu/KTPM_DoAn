package GUI;

import BUS.SanPhamBUS;
import DAO.KhuVucKhoDAO;
import DAO.LoaiDAO;
import DAO.SanPhamDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.SanPhamDTO;
import GUI.SPham.ChiTietSanPham;
import GUI.SPham.SuaSanPham;
import GUI.SPham.ThemSanPham;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class SanPham extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form SanPham
     */
    SanPhamBUS sanPhamBus = new SanPhamBUS();
    ThuongHieuDAO thuongHieuDAO;
    LoaiDAO loaiDAO;
    XuatXuDAO xuatXuDAO;
    KhuVucKhoDAO khuVucKhoDAO;
    ThemSanPham themSanPham;
    SuaSanPham suaSanPham;
    ChiTietSanPham chiTietSanPham;
    public SanPhamBUS sanPhamBUS;

    public ArrayList<DTO.SanPhamDTO> listSanPham;
    private final Color hoverColor = new Color(187, 222, 251);
    Color BackgroundColor = new Color(240, 247, 250);

    public SanPham() throws IOException {
        initComponents();

        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);

        addIcon();
        tblSanPham.setFocusable(false);
        tblSanPham.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblSanPham.setFocusable(false);
        tblSanPham.setAutoCreateRowSorter(true);

        btnThemSP.addActionListener(this);
        btnSuaSP.addActionListener(this);
        btnXoaSP.addActionListener(this);
        btnChiTietSP.addActionListener(this);
        btnXuatExcelSP.addActionListener(this);

        addHoverBtn();

        hienThiListSanPham();

        JScrollPane scrollPane = new JScrollPane(pnlTop);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        this.add(scrollPane, java.awt.BorderLayout.NORTH);

    }

    private void timKiemSanPham(String keyword) {
        ArrayList<SanPhamDTO> ketQuaTimKiem = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tenSanPham = (String) model.getValueAt(i, 1);
            int maSanPham = (int) model.getValueAt(i, 0);
            int sizeSanPham = (int) model.getValueAt(i, 3);
            if (tenSanPham.toLowerCase().contains(keyword.toLowerCase())
                    || String.valueOf(maSanPham).contains(keyword)
                    || String.valueOf(sizeSanPham).contains(keyword)) {
                ketQuaTimKiem.add(sanPhamBus.selectByID(maSanPham));
            }
        }
        hienThiListSanPham(ketQuaTimKiem);
    }

    public void hienThiListSanPham(ArrayList<SanPhamDTO> a) {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        khuVucKhoDAO = new KhuVucKhoDAO();
        sanPhamBUS = new SanPhamBUS();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPhamDTO sanPham : a) {
            Object[] row = {
                sanPham.getMasp(),
                sanPham.getTensp(),
                sanPham.getSoluongton(),
                sanPham.getSize(),
                loaiDAO.selectById(sanPham.getLoai()).getTenloai(),
                thuongHieuDAO.selectById(sanPham.getThuonghieu()).getTenthuonghieu(),
                xuatXuDAO.selectById(sanPham.getXuatxu()).getTenxuatxu(), // Giá trị ở giữa thứ 7
                khuVucKhoDAO.selectById(sanPham.getKhuvuckho()).getTenkhuvuc(),
                formatTien(sanPham.getGianhap()),
                formatTien(sanPham.getGiaxuat())};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
            tblSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void hienThiListSanPham() {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        khuVucKhoDAO = new KhuVucKhoDAO();
        sanPhamBUS = new SanPhamBUS();
        listSanPham = sanPhamBUS.getAllSanPham();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPhamDTO sanPham : listSanPham) {
            Object[] row = {
                sanPham.getMasp(),
                sanPham.getTensp(),
                sanPham.getSoluongton(),
                sanPham.getSize(),
                loaiDAO.selectById(sanPham.getLoai()).getTenloai(),
                thuongHieuDAO.selectById(sanPham.getThuonghieu()).getTenthuonghieu(),
                xuatXuDAO.selectById(sanPham.getXuatxu()).getTenxuatxu(), // Giá trị ở giữa thứ 7
                khuVucKhoDAO.selectById(sanPham.getKhuvuckho()).getTenkhuvuc(),
                formatTien(sanPham.getGianhap()),
                formatTien(sanPham.getGiaxuat())};
            model.addRow(row);
        }

        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
            tblSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public String formatTien(double tien) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0 VND");
        return decimalFormat.format(tien);
    }

    private void addIcon() {
        btnThemSP.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSuaSP.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoaSP.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnXuatExcelSP.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));
        btnNhapExcel.setIcon(new FlatSVGIcon("./icon/import_excel.svg"));
        btnChiTietSP.setIcon(new FlatSVGIcon("./icon/detail.svg"));
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
    }

    private void addHoverBtn() {
        addHoverEffect(btnThemSP);
        addHoverEffect(btnXoaSP);
        addHoverEffect(btnChiTietSP);
        addHoverEffect(btnLamMoi);
        addHoverEffect(btnXuatExcelSP);
        addHoverEffect(btnSuaSP);
    }

    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!button.isSelected()) {
                    button.setBackground(hoverColor); // Khi di chuột vào, đổi màu nền 
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.isSelected()) {
                    button.setBackground(Color.WHITE); // Khi di chuột ra khỏi, đổi lại màu nền mặc định
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnChiTietSP = new javax.swing.JButton();
        btnXuatExcelSP = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 800));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1000, 80));

        btnThemSP.setText("Thêm");
        btnThemSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemSP);

        btnSuaSP.setText("Sửa");
        btnSuaSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTop.add(btnSuaSP);

        btnXoaSP.setText("Xóa");
        btnXoaSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoaSP);

        btnChiTietSP.setText("Chi tiết");
        btnChiTietSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTop.add(btnChiTietSP);

        btnXuatExcelSP.setText("Xuất excel");
        btnXuatExcelSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTop.add(btnXuatExcelSP);

        btnNhapExcel.setText("Nhập excel");
        btnNhapExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });
        pnlTop.add(btnNhapExcel);

        jLabel1.setLabelFor(txtTimKiem);
        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

        txtTimKiem.setPreferredSize(new java.awt.Dimension(100, 30));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });
        pnlTop.add(txtTimKiem);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(130, 60));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTop.add(btnLamMoi);

        add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlCenter.setPreferredSize(new java.awt.Dimension(1200, 700));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Size", "Loại", "Thương hiệu", "Xuất xứ", "Khu vực kho", "Giá nhập", "Giá xuất"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyword = txtTimKiem.getText().trim();
            timKiemSanPham(keyword);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) { // Kiểm tra sự kiện double-click
            int row = tblSanPham.getSelectedRow();
            // Lấy dữ liệu của sản phẩm từ dòng được chọn
            int maSanPham = (int) tblSanPham.getValueAt(row, 0);
            SanPhamDTO sanPham = sanPhamBus.selectByID(maSanPham);
            // Hiển thị cửa sổ hoặc panel chi tiết sản phẩm với thông tin đã lấy được
            chiTietSanPham = new ChiTietSanPham(sanPham);
            chiTietSanPham.setLocationRelativeTo(null);
            chiTietSanPham.setVisible(true);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        listSanPham = sanPhamBus.getAllSanPham();
        hienThiListSanPham();
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        JFileChooser fileChooser = new JFileChooser(); // Tạo một file chooser để chọn tệp Excel
        int result = fileChooser.showOpenDialog(SanPham.this); // Hiển thị hộp thoại để chọn tệp Excel

        if (result == JFileChooser.APPROVE_OPTION) { // Kiểm tra xem người dùng đã chọn tệp hay chưa
            File selectedFile = fileChooser.getSelectedFile(); // Lấy đường dẫn tới tệp đã chọn
            NhapExcel(selectedFile); // Gọi phương thức để đọc và thêm dữ liệu từ tệp Excel vào cơ sở dữ liệu
        }
        listSanPham = sanPhamBUS.getAllSanPham();
        hienThiListSanPham();
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void xoaSanPham() {
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow != -1) {
            int maSP = (int) tblSanPham.getValueAt(selectedRow, 0);
            String anhCanXoa = sanPhamBus.selectAnhByMaSP(maSP);
            sanPhamBus = new SanPhamBUS();
            boolean thanhCong = sanPhamBus.xoaSanPham(maSP);
            if (thanhCong) {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công");
                listSanPham = sanPhamBus.getAllSanPham();
                hienThiListSanPham();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phảm để xóa");
        }
    }

    private SanPhamDTO selectSanPham() {
        int selectedRow = tblSanPham.getSelectedRow();
        SanPhamDTO result = null;
        if (selectedRow != -1) {
            int mathuonghieu = (int) tblSanPham.getValueAt(selectedRow, 0);
            sanPhamBus = new SanPhamBUS();
            result = sanPhamBus.selectByID(mathuonghieu);
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThemSP) {
            themSanPham = new ThemSanPham(this);
            themSanPham.setLocationRelativeTo(null);
            themSanPham.setVisible(true);
        } else if (e.getSource() == btnXoaSP) {
            xoaSanPham();
        } else if (e.getSource() == btnSuaSP) {
            if (selectSanPham() != null) {
                suaSanPham = new SuaSanPham(selectSanPham(), this);
                suaSanPham.setLocationRelativeTo(null);
                suaSanPham.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            }
        } else if (e.getSource() == btnXuatExcelSP) {
            try {
                XuatExcel.exportJTableToExcel(tblSanPham);
                JOptionPane.showMessageDialog(null, "Xuất thành công");
            } catch (IOException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnChiTietSP) {
            if (selectSanPham() != null) {
                chiTietSanPham = new ChiTietSanPham(selectSanPham());
                chiTietSanPham.setLocationRelativeTo(null);
                chiTietSanPham.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            }
        } else if (e.getSource() == btnLamMoi) {
            hienThiListSanPham();
            txtTimKiem.setText("");
            System.out.println("hello");
        }
    }

    private void NhapExcel(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file); // Tạo một luồng đầu vào từ tệp đã chọn

            Workbook workbook = new XSSFWorkbook(fileInputStream); // Đọc workbook từ luồng đầu vào
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên từ workbook

            SanPhamDAO sanPhamDAO = new SanPhamDAO(); // Tạo một đối tượng DAO để thực hiện thêm dữ liệu

            // Duyệt qua từng hàng dữ liệu trong tệp Excel và thêm vào cơ sở dữ liệu
            for (Row row : sheet) {
                SanPhamDTO sanPham = new SanPhamDTO(); // Tạo một đối tượng SanPhamDTO để lưu trữ dữ liệu từ hàng hiện tại
                // Đặt giá trị cho các thuộc tính của đối tượng SanPhamDTO từ các ô trong hàng hiện tại của tệp Excel
                sanPham.setTensp(row.getCell(0).getStringCellValue());
                sanPham.setSize((int) row.getCell(1).getNumericCellValue());
                sanPham.setLoai((int) row.getCell(2).getNumericCellValue());
                sanPham.setThuonghieu((int) row.getCell(3).getNumericCellValue());
                sanPham.setXuatxu((int) row.getCell(4).getNumericCellValue());
                sanPham.setKhuvuckho((int) row.getCell(5).getNumericCellValue());
                sanPham.setGianhap((int) row.getCell(6).getNumericCellValue());
                sanPham.setGiaxuat((int) row.getCell(7).getNumericCellValue());
                // Thêm đối tượng SanPhamDTO vào cơ sở dữ liệu
                boolean thanhCong = sanPhamDAO.themSanPham(sanPham);
                if (thanhCong) {
                    System.out.println(sanPham.getTensp());
                }
            }
            fileInputStream.close(); // Đóng luồng đầu vào
            JOptionPane.showMessageDialog(this, "Nhập dữ liệu từ Excel thành công!");
        } catch (IOException | EncryptedDocumentException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi nhập dữ liệu từ Excel!");
            ex.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietSP;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXuatExcelSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
