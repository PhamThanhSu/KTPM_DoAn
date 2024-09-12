package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public final class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    TrangChu trangChu;
    SanPham sanPham;
    ThuocTinh thuocTinh;
    KhachHang khachHang;
    KhuVucKho khuVucKho;
    NhaCungCap nhaCungCap;
    NhanVien nhanVien;
    PhieuNhap phieuNhap;
    PhieuXuat phieuXuat;
    TaiKhoan taiKhoan;
    ThongKe thongKe;
    PhanQuyen phanQuyen;
    Login login;
    NhanVienBUS nhanVienBUS;
    TaiKhoanDTO taiKhoanDTO;

    private final Color defaultColor = new Color(255, 255, 255);
    private final Color hoverColor = new Color(187, 222, 251);
    Color BackgroundColor = new Color(240, 247, 250);
//    Color BackgroundColor = new Color(235,235,235);
    

    public Main() {
        this.setPreferredSize(new Dimension(1400, 800));
        initComponents();
        mainContent2.setBackground(BackgroundColor);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addIconTaskbar();
        addHoverBtn();
        setTitle("Hệ thống quản lý kho giày");
        JScrollPane scrollPane = new JScrollPane(taskBar);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        menuTaskbar.add(scrollPane, java.awt.BorderLayout.CENTER);
    }

    public Main(TaiKhoanDTO taiKhoanDTO) {
        this.taiKhoanDTO = taiKhoanDTO;
        this.setPreferredSize(new Dimension(1400, 800));
        initComponents();
        mainContent2.setBackground(BackgroundColor);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addIconTaskbar();
        addHoverBtn();
        setTitle("Hệ thống quản lý kho giày");
        JScrollPane scrollPane = new JScrollPane(taskBar);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        menuTaskbar.add(scrollPane, java.awt.BorderLayout.CENTER);
        phanQuyen(this.taiKhoanDTO.getManhomquyen());
        setThongTinNhanVien(this.taiKhoanDTO);
    }

    public void setThongTinNhanVien(TaiKhoanDTO taiKhoan) {
        nhanVienBUS = new NhanVienBUS();
        NhanVienDTO NhanVien = nhanVienBUS.selectByID(taiKhoan.getManv());
        lblTenNhanVien.setText(NhanVien.getHoten());
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        String tenChucVuc = taiKhoanBUS.selectTenChucVu(taiKhoan.getManhomquyen());
        lblChucVu.setText(tenChucVuc);
    }

    public void phanQuyen(int role) {
        if (role == 1) {
            // Quản lý nhân viên và tài khoản
            btnTrangChu.setVisible(false);
            btnSanPham.setVisible(false);
            btnThuocTinh.setVisible(false);
            btnKhuVucKho.setVisible(false);
            btnNhaCungCap.setVisible(false);
            btnKhachHang.setVisible(false);
            btnPhieuNhap.setVisible(false);
            btnPhieuXuat.setVisible(false);
            btnThongKe.setVisible(false);
//            btnTaiKhoan.setVisible(false);
//            btnNhanVien.setVisible(false);

        } else if (role == 2) {
            // Nhân viên thống kê
            btnTrangChu.setVisible(false);
            btnSanPham.setVisible(false);
            btnThuocTinh.setVisible(false);
            btnKhuVucKho.setVisible(false);
            btnNhaCungCap.setVisible(false);
            btnKhachHang.setVisible(false);
            btnPhieuNhap.setVisible(false);
            btnPhieuXuat.setVisible(false);
//            btnThongKe.setVisible(false);
            btnTaiKhoan.setVisible(false);
            btnNhanVien.setVisible(false);
        } else if (role == 3) {
            // Nhân viên xuất hàng
            btnTrangChu.setVisible(false);
            btnSanPham.setVisible(false);
            btnThuocTinh.setVisible(false);
            btnKhuVucKho.setVisible(false);
            btnNhaCungCap.setVisible(false);
//            btnKhachHang.setVisible(false);
            btnPhieuNhap.setVisible(false);
//            btnPhieuXuat.setVisible(false);
            btnThongKe.setVisible(false);
            btnTaiKhoan.setVisible(false);
            btnNhanVien.setVisible(false);
        } else if (role == 4) {
            // Nhân viên nhập hàng
            btnTrangChu.setVisible(false);
//            btnSanPham.setVisible(false);
//            btnThuocTinh.setVisible(false);
//            btnKhuVucKho.setVisible(false);
//            btnNhaCungCap.setVisible(false);
            btnKhachHang.setVisible(false);
//            btnPhieuNhap.setVisible(false);
            btnPhieuXuat.setVisible(false);
            btnThongKe.setVisible(false);
            btnTaiKhoan.setVisible(false);
            btnNhanVien.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void setUT() {
        FlatRobotoFont.install();
        // set font chữ
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY); // set size cho font 
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();

        UIManager.put("Table.showVerticalLines", false); // ẩn đường kể dọc
        UIManager.put("Table.showHorizontalLines", true); // hiện đường kẻ ngang
        UIManager.put("TextComponent.arc", 30);

        UIManager.put("Button.arc", 10); // Thiết lập đường cong là 10 pixel

        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);

        UIManager.put("PasswordField.showRevealButton", true);

        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
        btnTrangChuActionPerformed(null);
    }

    private void addIconTaskbar() {

        btnDangXuat.setText("<html>&nbsp;&nbsp;&nbsp;Đăng xuất</html>");
        btnKhachHang.setText("<html>&nbsp;&nbsp;&nbsp;Khách hàng</html>");
        btnKhuVucKho.setText("<html>&nbsp;&nbsp;&nbsp;Khu vực kho</html>");
        btnNhaCungCap.setText("<html>&nbsp;&nbsp;&nbsp;Nhà cung cấp</html>");
        btnNhanVien.setText("<html>&nbsp;&nbsp;&nbsp;Nhân viên</html>");
        btnPhieuNhap.setText("<html>&nbsp;&nbsp;&nbsp;Phiếu nhập</html>");
        btnPhieuXuat.setText("<html>&nbsp;&nbsp;&nbsp;Phiếu xuất</html>");
        btnSanPham.setText("<html>&nbsp;&nbsp;&nbsp;Sản phẩm</html>");
        btnTaiKhoan.setText("<html>&nbsp;&nbsp;&nbsp;Tài khoản</html>");
        btnThongKe.setText("<html>&nbsp;&nbsp;&nbsp;Thống kê</html>");
        btnThuocTinh.setText("<html>&nbsp;&nbsp;&nbsp;Thuộc tính</html>");
        btnTrangChu.setText("<html>&nbsp;&nbsp;&nbsp;Trang chủ</html>");
        btnPhanquyen.setText("<html>&nbsp;&nbsp;&nbsp;Phân Quyền</html>");

        // Đặt icon và căn chỉnh biểu tượng sang trái cho mỗi button
        btnTrangChu.setIcon(new FlatSVGIcon("./icon/home.svg"));
        btnTrangChu.setHorizontalAlignment(SwingConstants.LEFT);

        btnSanPham.setIcon(new FlatSVGIcon("./icon/product.svg"));
        btnSanPham.setHorizontalAlignment(SwingConstants.LEFT);

        btnThuocTinh.setIcon(new FlatSVGIcon("./icon/brand.svg"));
        btnThuocTinh.setHorizontalAlignment(SwingConstants.LEFT);

        btnKhuVucKho.setIcon(new FlatSVGIcon("./icon/area.svg"));
        btnKhuVucKho.setHorizontalAlignment(SwingConstants.LEFT);

        btnPhieuNhap.setIcon(new FlatSVGIcon("./icon/import.svg"));
        btnPhieuNhap.setHorizontalAlignment(SwingConstants.LEFT);

        btnPhieuXuat.setIcon(new FlatSVGIcon("./icon/export.svg"));
        btnPhieuXuat.setHorizontalAlignment(SwingConstants.LEFT);

        btnKhachHang.setIcon(new FlatSVGIcon("./icon/customer.svg"));
        btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);

        btnNhaCungCap.setIcon(new FlatSVGIcon("./icon/supplier.svg"));
        btnNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);

        btnNhanVien.setIcon(new FlatSVGIcon("./icon/staff.svg"));
        btnNhanVien.setHorizontalAlignment(SwingConstants.LEFT);

        btnTaiKhoan.setIcon(new FlatSVGIcon("./icon/account.svg"));
        btnTaiKhoan.setHorizontalAlignment(SwingConstants.LEFT);

        btnThongKe.setIcon(new FlatSVGIcon("./icon/statistical.svg"));
        btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);

        btnDangXuat.setIcon(new FlatSVGIcon("./icon/log_out.svg"));
        btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
    
        btnPhanquyen.setIcon(new FlatSVGIcon("./icon/home.svg"));
        btnPhanquyen.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void addHoverEffect(JToggleButton button) {

        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                button.setBackground(hoverColor); // Khi di chuột vào, đổi màu nền 

            }

            @Override
            public void mouseExited(MouseEvent e) {

                button.setBackground(defaultColor); // Khi di chuột ra khỏi, đổi lại màu nền mặc định

            }
        });
    }

    private void addHoverEffect(JButton button) {
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor); // Khi di chuột vào, đổi màu nền 

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultColor); // Khi di chuột ra khỏi, đổi lại màu nền mặc định
            }
        });
    }

    private void addHoverBtn() {
        addHoverEffect(btnTrangChu);
        addHoverEffect(btnDangXuat);
        addHoverEffect(btnKhachHang);
        addHoverEffect(btnKhuVucKho);
        addHoverEffect(btnNhaCungCap);
        addHoverEffect(btnNhanVien);
        addHoverEffect(btnPhieuNhap);
        addHoverEffect(btnPhieuXuat);
        addHoverEffect(btnSanPham);
        addHoverEffect(btnTaiKhoan);
        addHoverEffect(btnThongKe);
        addHoverEffect(btnThuocTinh);
        addHoverEffect(btnPhanquyen);
        // Thêm hiệu ứng hover cho từng button
    }
// Gọi phương thức này trong constructor hoặc bất kỳ nơi nào khác mà bạn muốn thêm hiệu ứng hover cho tất cả các button

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        menuTaskbar = new javax.swing.JPanel();
        info = new javax.swing.JPanel();
        lblTenNhanVien = new javax.swing.JLabel();
        bar = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        taskBar = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JToggleButton("<html>&nbsp;&nbsp;&nbsp;Trang chủ</html>");
        btnSanPham = new javax.swing.JToggleButton();
        btnThuocTinh = new javax.swing.JToggleButton();
        btnKhuVucKho = new javax.swing.JToggleButton();
        btnPhieuNhap = new javax.swing.JToggleButton();
        btnPhieuXuat = new javax.swing.JToggleButton();
        btnKhachHang = new javax.swing.JToggleButton();
        btnNhaCungCap = new javax.swing.JToggleButton();
        btnNhanVien = new javax.swing.JToggleButton();
        btnTaiKhoan = new javax.swing.JToggleButton();
        btnThongKe = new javax.swing.JToggleButton();
        btnPhanquyen = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        mainContent = new javax.swing.JPanel();
        mainContent2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        menuTaskbar.setBackground(new java.awt.Color(255, 255, 255));
        menuTaskbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuTaskbar.setPreferredSize(new java.awt.Dimension(200, 800));
        menuTaskbar.setLayout(new java.awt.BorderLayout());

        info.setBackground(new java.awt.Color(255, 255, 255));
        info.setPreferredSize(new java.awt.Dimension(136, 70));

        lblTenNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTenNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNhanVien.setText("Lê Nguyễn Hoàng Phát");

        bar.setBackground(new java.awt.Color(0, 0, 0));
        bar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bar.setPreferredSize(new java.awt.Dimension(1, 1));

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(153, 153, 153));
        lblChucVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChucVu.setText("Quản lý kho");

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bar, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addComponent(lblChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTenNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuTaskbar.add(info, java.awt.BorderLayout.NORTH);

        taskBar.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(btnTrangChu);
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnSanPham);
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnThuocTinh);
        btnThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThuocTinh.setText("Thuộc tính");
        btnThuocTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuocTinhActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnKhuVucKho);
        btnKhuVucKho.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnKhuVucKho.setText("Khu vực kho");
        btnKhuVucKho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhuVucKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuVucKhoActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnPhieuNhap);
        btnPhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPhieuNhap.setText("Phiếu nhập");
        btnPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnPhieuXuat);
        btnPhieuXuat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPhieuXuat.setText("Phiếu xuất");
        btnPhieuXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuXuatActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnKhachHang);
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnNhaCungCap);
        btnNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNhaCungCap.setText("Nhà cung cấp");
        btnNhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnNhanVien);
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnTaiKhoan);
        btnTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTaiKhoan.setText("Tài khoản");
        btnTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnThongKe);
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnPhanquyen);
        btnPhanquyen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPhanquyen.setText("Phân Quyền");
        btnPhanquyen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPhanquyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanquyenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskBarLayout = new javax.swing.GroupLayout(taskBar);
        taskBar.setLayout(taskBarLayout);
        taskBarLayout.setHorizontalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhuVucKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addComponent(btnTaiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhanquyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        taskBarLayout.setVerticalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThuocTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhuVucKho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhieuNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhieuXuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang)
                .addGap(5, 5, 5)
                .addComponent(btnNhaCungCap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhanquyen)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        menuTaskbar.add(taskBar, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(111, 70));

        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnDangXuat)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        menuTaskbar.add(jPanel1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(menuTaskbar, java.awt.BorderLayout.WEST);

        mainContent.setBackground(new java.awt.Color(235, 235, 235));
        mainContent.setPreferredSize(new java.awt.Dimension(1200, 800));
        mainContent.setLayout(new java.awt.BorderLayout());

        mainContent2.setBackground(new java.awt.Color(235, 235, 235));

        javax.swing.GroupLayout mainContent2Layout = new javax.swing.GroupLayout(mainContent2);
        mainContent2.setLayout(mainContent2Layout);
        mainContent2Layout.setHorizontalGroup(
            mainContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        mainContent2Layout.setVerticalGroup(
            mainContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        mainContent.add(mainContent2, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        try {
            sanPham = new SanPham();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        addTaskBar(mainContent2, sanPham);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuocTinhActionPerformed
        // TODO add your handling code here:
        thuocTinh = new ThuocTinh();
        addTaskBar(mainContent2, thuocTinh);
    }//GEN-LAST:event_btnThuocTinhActionPerformed

    private void btnKhuVucKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuVucKhoActionPerformed
        // TODO add your handling code here:
        khuVucKho = new KhuVucKho();
        addTaskBar(mainContent2, khuVucKho);
    }//GEN-LAST:event_btnKhuVucKhoActionPerformed

    private void btnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapActionPerformed
        // TODO add your handling code here:
        phieuNhap = new PhieuNhap(taiKhoanDTO);
        addTaskBar(mainContent2, phieuNhap);
    }//GEN-LAST:event_btnPhieuNhapActionPerformed

    private void btnPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuXuatActionPerformed
        // TODO add your handling code here:
        phieuXuat = new PhieuXuat(taiKhoanDTO);
        addTaskBar(mainContent2, phieuXuat);
    }//GEN-LAST:event_btnPhieuXuatActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        khachHang = new KhachHang();
        addTaskBar(mainContent2, khachHang);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
        // TODO add your handling code here:
        nhaCungCap = new NhaCungCap();
        addTaskBar(mainContent2, nhaCungCap);
    }//GEN-LAST:event_btnNhaCungCapActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        nhanVien = new NhanVien();
        addTaskBar(mainContent2, nhanVien);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        // TODO add your handling code here:
        taiKhoan = new TaiKhoan();
        addTaskBar(mainContent2, taiKhoan);
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        thongKe = new ThongKe();
        addTaskBar(mainContent2, thongKe);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        trangChu = new TrangChu();
        addTaskBar(mainContent2, trangChu);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnPhanquyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanquyenActionPerformed
        phanQuyen = new PhanQuyen();
        addTaskBar(mainContent2, phanQuyen);
    }//GEN-LAST:event_btnPhanquyenActionPerformed

// hàm taskbar
    public void addTaskBar(Container mainContent, JPanel panel) {
        mainContent.setLayout(new BorderLayout());
        panel.setSize(mainContent.getSize());
        mainContent.removeAll();
        mainContent.add(panel, BorderLayout.CENTER);
        mainContent.setVisible(true);
        mainContent.revalidate();
        mainContent.repaint();
    }

    public void setPanel(Container container, JPanel panel) {
        addTaskBar(container, panel);
    }
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
//        UIManager.put("Table.showVerticalLines", false);
//        UIManager.put("Table.showHorizontalLines", true);
//        UIManager.put("TextComponent.arc", 5);
//        UIManager.put("ScrollBar.thumbArc", 999);
//        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
//        UIManager.put("Button.iconTextGap", 10);
//        UIManager.put("PasswordField.showRevealButton", true);
//        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
//        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
//        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
//        UIManager.put("Table.rowHeight", 40);
//        UIManager.put("TabbedPane.selectedBackground", Color.white);
//        UIManager.put("TableHeader.height", 40);
//        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
//        UIManager.put("TableHeader.background", new Color(242, 242, 242));
//        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
//        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
//
//        Main main = new Main();
//        main.setUT();
//        main.setVisible(true);
//        main.btnTrangChuActionPerformed(null);
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bar;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JToggleButton btnKhachHang;
    private javax.swing.JToggleButton btnKhuVucKho;
    private javax.swing.JToggleButton btnNhaCungCap;
    private javax.swing.JToggleButton btnNhanVien;
    private javax.swing.JToggleButton btnPhanquyen;
    private javax.swing.JToggleButton btnPhieuNhap;
    private javax.swing.JToggleButton btnPhieuXuat;
    private javax.swing.JToggleButton btnSanPham;
    private javax.swing.JToggleButton btnTaiKhoan;
    private javax.swing.JToggleButton btnThongKe;
    private javax.swing.JToggleButton btnThuocTinh;
    private javax.swing.JToggleButton btnTrangChu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel info;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JPanel mainContent;
    private javax.swing.JPanel mainContent2;
    private javax.swing.JPanel menuTaskbar;
    private javax.swing.JPanel taskBar;
    // End of variables declaration//GEN-END:variables
}
