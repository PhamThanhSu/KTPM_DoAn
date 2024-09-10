/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import config.MySQLConnection;
import java.sql.PreparedStatement;
import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.SanPhamPhieuNhapBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.LoaiDAO;
import DAO.NhaCungCapDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.SanPhamPhieuNhapDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import javax.swing.table.DefaultTableModel;
import GUI.Component.BuildTable;
import GUI.Main;
import GUI.PhieuNhap;
import GUI.PXuat.ChonKhachHang;
import GUI.PhieuXuat;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author this pc
 */
public class TaoPhieuXuatt extends javax.swing.JPanel implements MouseListener, ActionListener {

    ThuongHieuDAO thuongHieuDAO;
    NhaCungCapDAO nhaCungCapDAO;
    PhieuXuatDAO phieuXuatDAO;
    LoaiDAO loaiDAO;
    XuatXuDAO xuatXuDAO;
    SanPhamBUS sanPhamBUS;
    SanPhamPhieuNhapBUS sanPhamPhieuNhapBUS;
    PhieuXuatDTO phieuXuatDTO;
    ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;
    ChiTietPhieuXuatDTO chiTietPhieuXuatDTO;
    PhieuNhap phieuNhap;
    PhieuXuat phieuXuat;
    KhachHangDTO khachHang;
    KhachHangBUS khachHangBUS;
    ChonKhachHang chonKhachHang;
    Main main;
    
    //NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private ArrayList<SanPhamDTO> selectedProducts = new ArrayList<>();
    ArrayList<SanPhamDTO> listpr = new ArrayList<>();
    private TaoPhieuNhap taoPhieuNhap;
    private DefaultTableModel tblModel;
    
    long totalPrice = 0;
    int rowNum = 1;
    int maKH = -1;
    public TaoPhieuXuatt() {
        initComponents();
        btnsuasanpham.setVisible(false);
        btnxoasanpham.setVisible(false);
        BuildTable buildTable = new BuildTable();
        phieuXuatDAO = new PhieuXuatDAO();
        sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        khachHangBUS = new KhachHangBUS();
        listpr = sanPhamPhieuNhapBUS.getListSanPham();
        buildTable.updateTableProducts(tblsoluongsanpham, listpr);
        tblsoluongsanpham.addMouseListener(this);
        
        // Tạo mã phiếu xuat mới
        int soLuongPhieuXuatDaTao = phieuXuatDAO.getLatestMaPhieuXuat(); 
        int maPhieuXuat = ++soLuongPhieuXuatDaTao;
        txtmaphieuxuat.setText("PX" + (maPhieuXuat));
        txtmaphieuxuat.setEditable(false);
        txtkhachhang.setEditable(false);
        txttensanpham.setEditable(false);
        txtnhanvienxuat.setEditable(false);
        txtmasanphampx.setEditable(false);
        txtsoluongton.setEditable(false);
        txtgiaxuat.setEditable(false);
        
        tblsoluongsanpham.setDefaultEditor(Object.class, null);
        tblsoluongsanpham.setFocusable(false);
        tblthongtinspdathempx.setDefaultEditor(Object.class, null);
        tblthongtinspdathempx.setFocusable(false);
        
        btnchonkhachhang.addActionListener(this);
        btnxuathang.addActionListener(this);
        tblsoluongsanpham.addMouseListener(this);
        tblthongtinspdathempx.addMouseListener(this);
        
        txttimkiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txttimkiem.putClientProperty("JTextField.showClearButton", true);
        txttimkiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txttimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sanPhamBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> rs = sanPhamBUS.search(txttimkiem.getText());
                buildTable.updateTableProducts(tblsoluongsanpham, rs);
            }
        });
        
    }
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    TaiKhoanDTO taiKhoanDTO;
    public TaoPhieuXuatt(TaiKhoanDTO taiKhoanDTO) {
        this.taiKhoanDTO = taiKhoanDTO;
        initComponents();
        btnsuasanpham.setVisible(false);
        btnxoasanpham.setVisible(false);
        BuildTable buildTable = new BuildTable();
        phieuXuatDAO = new PhieuXuatDAO();
        sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        khachHangBUS = new KhachHangBUS();
        listpr = sanPhamPhieuNhapBUS.getListSanPham();
        buildTable.updateTableProducts(tblsoluongsanpham, listpr);
        tblsoluongsanpham.addMouseListener(this);
        
        // Tạo mã phiếu xuat mới
        int soLuongPhieuXuatDaTao = phieuXuatDAO.getLatestMaPhieuXuat(); 
        int maPhieuXuat = ++soLuongPhieuXuatDaTao;
        txtmaphieuxuat.setText("PX" + (maPhieuXuat));
        txtmaphieuxuat.setEditable(false);
        txtkhachhang.setEditable(false);
        txttensanpham.setEditable(false);
        txtnhanvienxuat.setEditable(false);
        txtmasanphampx.setEditable(false);
        txtsoluongton.setEditable(false);
        txtgiaxuat.setEditable(false);
        
        tblsoluongsanpham.setDefaultEditor(Object.class, null);
        tblsoluongsanpham.setFocusable(false);
        tblthongtinspdathempx.setDefaultEditor(Object.class, null);
        tblthongtinspdathempx.setFocusable(false);
        
        btnchonkhachhang.addActionListener(this);
        btnxuathang.addActionListener(this);
        tblsoluongsanpham.addMouseListener(this);
        tblthongtinspdathempx.addMouseListener(this);
        
        txttimkiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txttimkiem.putClientProperty("JTextField.showClearButton", true);
        txttimkiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txttimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sanPhamBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> rs = sanPhamBUS.search(txttimkiem.getText());
                buildTable.updateTableProducts(tblsoluongsanpham, rs);
            }
        });
        txtnhanvienxuat.setText(nhanVienBUS.selectByID(taiKhoanDTO.getManv()).getHoten());
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        taoPhieuNhap = new TaoPhieuNhap();
        if (e.getSource() == tblsoluongsanpham) {
            int selectedRow = tblsoluongsanpham.getSelectedRow();
            if (selectedRow != -1) {
                int maspColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã SP", tblsoluongsanpham);
                int masp = (int) tblsoluongsanpham.getValueAt(selectedRow, maspColumnIndex);
                SanPhamDTO result = taoPhieuNhap.selectSanPham(masp); 
                boolean found = false;
                int soluongcheck = 0;

                for (int i = 0; i < tblthongtinspdathempx.getRowCount(); i++) {
                    int maspcheck = (int) tblthongtinspdathempx.getValueAt(i, 1);
                    soluongcheck = (int) tblthongtinspdathempx.getValueAt(i, 8);
                    if (result.getMasp() == maspcheck) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    btnsuasanpham.setVisible(true);
                    btnxoasanpham.setVisible(true);
                    txtsoluong.setText(String.valueOf(soluongcheck));                   
                    btnthem.setVisible(false);
                } else {
                    btnsuasanpham.setVisible(false);
                    btnxoasanpham.setVisible(false);
                    txtsoluong.setText("");
                    btnthem.setVisible(true);
                } 
                if (result != null) {
                    txtmasanphampx.setText(String.valueOf(result.getMasp())); 
                    txttensanpham.setText(result.getTensp());
                    txtgiaxuat.setText(String.valueOf(result.getGiaxuat())); 
                    txtsoluongton.setText(String.valueOf(result.getSoluongton()));
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin sản phẩm");
                }
            }
        }else if (e.getSource() == tblthongtinspdathempx) {
            int selectedRow = tblthongtinspdathempx.getSelectedRow();
            if (selectedRow != -1) {
                int maspColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã SP", tblthongtinspdathempx);
                int soluongColumnIndex = taoPhieuNhap.getColumnIndexByName("Số lượng", tblthongtinspdathempx);
                int masp = (int) tblthongtinspdathempx.getValueAt(selectedRow, maspColumnIndex);
                int soluongcheck = (int) tblthongtinspdathempx.getValueAt(selectedRow, soluongColumnIndex);
                SanPhamDTO result = taoPhieuNhap.selectSanPham(masp); // Gọi hàm selectSanPham và truyền masp vào

                txtmasanphampx.setText(String.valueOf(result.getMasp()));
                txtsoluong.setText(String.valueOf(soluongcheck));
                txttensanpham.setText(result.getTensp());
                txtgiaxuat.setText(String.valueOf(result.getGiaxuat()).replaceAll("[.,đ]", "").trim());
                txtsoluongton.setText(String.valueOf(result.getSoluongton()));
                btnthem.setVisible(false);
                btnsuasanpham.setVisible(true);
                btnxoasanpham.setVisible(true);
            }
        }
    }

    public void CheckCombobox(JComboBox<String> combobox) {
        sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> list = sanPhamBUS.getAllSanPham();
        String tensp = txttensanpham.getText();
        combobox.removeAllItems();
        for (SanPhamDTO sp : list) {
            if (tensp.equals(sp.getTensp())) { // Sử dụng equals() để so sánh hai chuỗi
                System.out.println(sp.getTensp());
                combobox.addItem(String.valueOf(sp.getSize())); // Ép sang kiểu chuỗi trước khi thêm vào combobox    
            }
        }
    }
// Các phương thức khác trong class TaoPhieuNhap

    @Override
    public void mousePressed(MouseEvent e) {
        // Xử lý khi chuột được nhấn
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Xử lý khi chuột được thả
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Xử lý khi chuột đi vào component
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Xử lý khi chuột rời khỏi component
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerpanel = new javax.swing.JPanel();
        leftcontent = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        containernhap = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmasanphampx = new javax.swing.JTextField();
        txtgiaxuat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttensanpham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtsoluongton = new javax.swing.JTextField();
        txtsoluong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnsuasanpham = new javax.swing.JButton();
        btnxoasanpham = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblthongtinspdathempx = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsoluongsanpham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtmaphieuxuat = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtnhanvienxuat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JLabel();
        btnxuathang = new javax.swing.JButton();
        txtkhachhang = new javax.swing.JTextField();
        btnchonkhachhang = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        containerpanel.setBackground(new java.awt.Color(255, 255, 255));
        containerpanel.setLayout(new java.awt.BorderLayout());

        btnthem.setBackground(new java.awt.Color(0, 153, 255));
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm sản phẩm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        containernhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        containernhap.setPreferredSize(new java.awt.Dimension(447, 385));

        jLabel1.setText("Mã sản phẩm");

        jLabel2.setText("Tên sản phẩm");

        txtmasanphampx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmasanphampxActionPerformed(evt);
            }
        });

        txtgiaxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiaxuatActionPerformed(evt);
            }
        });

        jLabel4.setText("Giá xuất");

        jLabel7.setText("Số lượng tồn");

        txtsoluongton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluongtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Số lượng");

        btnsuasanpham.setBackground(new java.awt.Color(255, 153, 51));
        btnsuasanpham.setForeground(new java.awt.Color(255, 255, 255));
        btnsuasanpham.setText("Sửa sản phẩm");
        btnsuasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuasanphamActionPerformed(evt);
            }
        });

        btnxoasanpham.setBackground(new java.awt.Color(255, 102, 153));
        btnxoasanpham.setForeground(new java.awt.Color(255, 255, 255));
        btnxoasanpham.setText("Xóa sản phẩm");
        btnxoasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasanphamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containernhapLayout = new javax.swing.GroupLayout(containernhap);
        containernhap.setLayout(containernhapLayout);
        containernhapLayout.setHorizontalGroup(
            containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containernhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containernhapLayout.createSequentialGroup()
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmasanphampx, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(containernhapLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txttensanpham))
                        .addContainerGap())
                    .addGroup(containernhapLayout.createSequentialGroup()
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(containernhapLayout.createSequentialGroup()
                                .addComponent(txtgiaxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(containernhapLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(containernhapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnsuasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        containernhapLayout.setVerticalGroup(
            containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containernhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttensanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtmasanphampx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgiaxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsuasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        tblthongtinspdathempx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Size", "Xuất xứ", "Loại", "Thương hiệu", "Giá xuất", "Số lượng"
            }
        ));
        tblthongtinspdathempx.setRowHeight(30);
        jScrollPane2.setViewportView(tblthongtinspdathempx);

        tblsoluongsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "Số lượng tồn", "Size"
            }
        ));
        tblsoluongsanpham.setFocusTraversalPolicyProvider(true);
        tblsoluongsanpham.setRequestFocusEnabled(false);
        tblsoluongsanpham.setRowHeight(40);
        jScrollPane3.setViewportView(tblsoluongsanpham);

        javax.swing.GroupLayout leftcontentLayout = new javax.swing.GroupLayout(leftcontent);
        leftcontent.setLayout(leftcontentLayout);
        leftcontentLayout.setHorizontalGroup(
            leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftcontentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(leftcontentLayout.createSequentialGroup()
                        .addGroup(leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftcontentLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txttimkiem)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(containernhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        leftcontentLayout.setVerticalGroup(
            leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftcontentLayout.createSequentialGroup()
                .addGroup(leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftcontentLayout.createSequentialGroup()
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(containernhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        containerpanel.add(leftcontent, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Nhân viên xuất");

        jLabel17.setText("Khách hàng");

        jLabel18.setText("Mã phiếu xuất");

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("TỔNG TIỀN:");

        txttongtien.setText("0đ");

        btnxuathang.setBackground(new java.awt.Color(0, 153, 51));
        btnxuathang.setForeground(new java.awt.Color(255, 255, 255));
        btnxuathang.setText("Xuất hàng");
        btnxuathang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuathangActionPerformed(evt);
            }
        });

        txtkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkhachhangActionPerformed(evt);
            }
        });

        btnchonkhachhang.setText("...");
        btnchonkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonkhachhangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtkhachhang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnchonkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addComponent(txtmaphieuxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addComponent(txtnhanvienxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                            .addComponent(btnxuathang, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtmaphieuxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtnhanvienxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnchonkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxuathang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        containerpanel.add(jPanel1, java.awt.BorderLayout.EAST);

        add(containerpanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        try {
            // Gọi phương thức xử lý thêm sản phẩm vào hàng chờ
            ThemSanPhamVaoHangCho();
            updateTotalPrice();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnthemActionPerformed

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // Biểu thức chính quy kiểm tra chuỗi có chứa số hay không
    }
    
    public void ThemSanPhamVaoHangCho(){
        if(txtmasanphampx.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
        }
        else if (txtsoluong.getText().isEmpty() || !isNumeric(txtsoluong.getText())) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống và phải là một số!");
        }
        else if (Integer.parseInt(txtsoluong.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0");
        }
        else if (Integer.parseInt(txtsoluong.getText()) > Integer.parseInt(txtsoluongton.getText())) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải nhỏ hơn số lượng tồn");
            txtsoluong.setText("");
        }
        else {
            sanPhamBUS = new SanPhamBUS();
            int masp = Integer.parseInt(txtmasanphampx.getText());
            tblModel = (DefaultTableModel) tblthongtinspdathempx.getModel();
            SanPhamDTO newProductList = sanPhamBUS.selectByID(masp);
            // Cập nhật dữ liệu trong bảng
            updatetableaddedproducts(newProductList, tblthongtinspdathempx);
        }
    }

    public void updatetableaddedproducts(SanPhamDTO product, JTable table) {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        nhaCungCapDAO = new NhaCungCapDAO();        
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //String selectedSize = (String) cbbcauhinh.getSelectedItem(); // Ép kiểu sang String nếu cần
       
            String TenLoai = loaiDAO.selectById(product.getLoai()).getTenloai();
            String TenThuongHieu = thuongHieuDAO.selectById(product.getThuonghieu()).getTenthuonghieu();
            String XuatXu = xuatXuDAO.selectById(product.getXuatxu()).getTenxuatxu();
            int soluong = (int) Integer.valueOf(txtsoluong.getText());
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ"); 

            model.addRow(new Object[]{
                rowNum++,
                product.getMasp(),
                product.getTensp(),
                product.getSize(),
                XuatXu,
                TenLoai,
                TenThuongHieu,
                decimalFormat.format(product.getGiaxuat()), 
                soluong
            });
            totalPrice += product.getGiaxuat()* soluong;

        txttongtien.setText(decimalFormat.format(totalPrice));
        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//// Sau khi thêm sản phẩm, tạo một danh sách mới để ngừng nhận dữ liệu từ productList
//         productList = new ArrayList<>();
        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        txtmasanphampx.setText("");
        txttensanpham.setText("");
        txtgiaxuat.setText("");
        txtsoluongton.setText("");
        txtsoluong.setText("");
    }


    private void txtmasanphampxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmasanphampxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmasanphampxActionPerformed

    private void txtgiaxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiaxuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiaxuatActionPerformed

    private void txtsoluongtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluongtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluongtonActionPerformed

    private void btnxuathangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuathangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnxuathangActionPerformed

    private void btnchonkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonkhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnchonkhachhangActionPerformed

    private void btnsuasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuasanphamActionPerformed
        // Lấy giá trị số lượng từ JTextField
        String updatesoluong = txtsoluong.getText();

        // Kiểm tra xem người dùng đã chọn hàng nào trong bảng chưa
        int selectedRow = tblthongtinspdathempx.getSelectedRow();
        if (selectedRow != -1) {
            try {
                // Chuyển đổi giá trị số lượng thành số nguyên
                int soluong = Integer.parseInt(updatesoluong);
                if (soluong > 0 && soluong <= Integer.valueOf(txtsoluongton.getText())) {
                    // Cập nhật số lượng mới trong bảng
                    tblthongtinspdathempx.setValueAt(soluong, selectedRow, 8);
                     // Cập nhật lại tổng tiền sau khi sửa số lượng
                    updateTotalPrice();
                } else if(soluong > Integer.valueOf(txtsoluongton.getText())){
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm phải nhỏ hơn số lượng tồn");
                }
                else {
                    // Hiển thị thông báo khi số lượng nhập vào là một số âm
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm phải lớn hơn 0.");
                }
            } catch (NumberFormatException ex) {
                // Xử lý nếu người dùng nhập không phải là một số
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số nguyên.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để cập nhật số lượng.");
        }
    }//GEN-LAST:event_btnsuasanphamActionPerformed

    private void btnxoasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoasanphamActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblthongtinspdathempx.getModel();
        int selectedRow = tblthongtinspdathempx.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            updateTotalPrice();
            txtmasanphampx.setText("");
            txtsoluong.setText("");
            txttensanpham.setText("");
            txtgiaxuat.setText("");
            txtsoluongton.setText("");
        }
    }//GEN-LAST:event_btnxoasanphamActionPerformed

    private void txtkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkhachhangActionPerformed

    // Phương thức để cập nhật tổng tiền sau khi sửa số lượng
private void updateTotalPrice() {
    // Khởi tạo biến tổng tiền
    long totalPrice = 0;

     // Tính lại tổng tiền dựa trên số lượng mới của từng sản phẩm
    for (int i = 0; i < tblthongtinspdathempx.getRowCount(); i++) {
        int soluong = (int) tblthongtinspdathempx.getValueAt(i, 8); // Lấy số lượng từ cột 8 (cột số lượng)
        String giaxuatStr = (String) tblthongtinspdathempx.getValueAt(i, 7); // Lấy giá nhập từ cột 7 (cột giá nhập) dưới dạng chuỗi
        giaxuatStr = giaxuatStr.replaceAll("[.,đ]", "").trim();
        long giaxuat = Long.parseLong(giaxuatStr); 
        totalPrice += soluong * giaxuat; 
    }
    // Hiển thị tổng tiền mới sau khi cập nhật
    DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
    txttongtien.setText(decimalFormat.format(totalPrice));
}    
//    public int SelectedMaKH() {
//    //ArrayList<KhachHangDTO> khachHangList = khachHangBUS.getAllKhachHang();
//    chonKhachHang = new ChonKhachHang();
//    int maKH = chonKhachHang.themKhachHang();
//    System.out.println("mã khách hàng: " + maKH);
////    for (KhachHangDTO khachHang : khachHangList) {
////        if (khachHang.getHoten().equals(TenKH)) {
////            maKH = khachHang.getMaKH();
////            break;
////        }
////    }
//    return maKH; 
//}
    //HÀM TẠO PHIẾU XUẤT
    public boolean CreatePhieuXuat(){
        try {
        String maphieuxuatstr = txtmaphieuxuat.getText().replaceAll("[PXs.,đ]", "").trim();
        int maphieuxuat = Integer.parseInt(maphieuxuatstr);
        //String tenKhachHang = String.valueOf(txtkhachhang.getText());
        int makh = maKH;
            System.out.println("ma" + makh);
        //int makh = 3;  //Lỗi lấy giá trị từ txtkhachhang
        int manv = taiKhoanDTO.getManv();
        long tongtien = 0;
        String tongtienStr = txttongtien.getText().replaceAll("[.,đ]", "").trim();
        tongtien = Long.parseLong(tongtienStr);
        long now = System.currentTimeMillis();
        Timestamp currentTime = new Timestamp(now);
        
        PhieuXuatDTO px = new PhieuXuatDTO(maphieuxuat, currentTime, tongtien, manv, makh, 1);
        phieuXuatDAO = new PhieuXuatDAO();
        phieuXuatDAO.insertPhieuXuat(px, now);
        addChiTietPhieuXuatToDatabase();
        JOptionPane.showMessageDialog(null, "Tạo phiếu xuất thành công");
        return true;
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi tạo phiếu nhập: " + ex.getMessage());
        return false;
    }
    }
    
    //HÀM THÊM PHIẾU XUẤT VÀO DATABASE
    // Phương thức thêm chi tiết phiếu nhập vào cơ sở dữ liệu
private void addChiTietPhieuXuatToDatabase() {
    DefaultTableModel model = (DefaultTableModel) tblthongtinspdathempx.getModel();
    ArrayList<ChiTietPhieuXuatDTO> chiTietPhieuXuatList = new ArrayList<>();
    boolean hasProductsToImport = false;
    String maphieuxuatstr = txtmaphieuxuat.getText().replaceAll("[PX.,đ]", "").trim();
    int maphieuxuat = Integer.parseInt(maphieuxuatstr);

    for (int i = 0; i < model.getRowCount(); i++) {
        int masp = (int) model.getValueAt(i, 1);
        int soluong = (int) model.getValueAt(i, 8);
        String dongiastr = model.getValueAt(i, 7).toString();
        int dongia = Integer.parseInt(dongiastr.replaceAll("[.,đ]", "").trim());
        try {
            chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
            chiTietPhieuXuatDAO.updateSoluongton(masp, soluong);
            chiTietPhieuXuatDTO = new ChiTietPhieuXuatDTO(maphieuxuat, masp, soluong, dongia);
            chiTietPhieuXuatList.add(chiTietPhieuXuatDTO);
            hasProductsToImport = true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là một số nguyên dương");
            hasProductsToImport = false;
            break;
        }
    }

    if (hasProductsToImport) {
        chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
        chiTietPhieuXuatDAO.insert(chiTietPhieuXuatList);
    }
}
    
    //HÀM KHI CLICK XUẤT HÀNG
    public void ClickXuatHang(){
        // Kiểm tra số lượng hàng trong bảng 
            int rowCount = tblthongtinspdathempx.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào để xuất. Vui lòng nhập sản phẩm.");
            } else {
                if (txtkhachhang.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng");
                } 
                else if (CreatePhieuXuat()) {
                    // Tạo đối tượng mới của panel PhieuNhap
                    phieuXuat = new PhieuXuat(taiKhoanDTO);
                    main = new Main();
                    // Kiểm tra và hiển thị panel PhieuNhap
                    if (main != null) {
                        main.setPanel(containerpanel, phieuXuat);
                    } else {
                        System.out.println("Biến main chưa được khởi tạo!");
                    }
                }
            }
    }
    
    // Phương thức actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnchonkhachhang) {
            // Xử lý khi nút btnchonkhachhang được click
            showChonKhachHangPanel(); // Gọi phương thức hiển thị panel chọn khách hàng
        } else if (e.getSource() == btnxuathang) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu xuất !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                ClickXuatHang();
            }
        }
    }

    // Phương thức hiển thị panel chọn khách hàng
    private void showChonKhachHangPanel() {
    chonKhachHang = new ChonKhachHang();
    JDialog dialog = new JDialog(null, "Chọn khách hàng", Dialog.ModalityType.APPLICATION_MODAL); // Thêm tên cho JDialog
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Thiết lập hành động khi đóng cửa sổ
    dialog.getContentPane().add(chonKhachHang); // Thêm panel chọn khách hàng vào JDialog
    dialog.pack(); // Đặt kích thước của JDialog dựa trên kích thước của panel
    dialog.setLocationRelativeTo(null); // Hiển thị JDialog ở trung tâm màn hình
    dialog.setVisible(true); // Hiển thị JDialog
    //setKhachHang(SelectedMaKH());
    txtkhachhang.setText(String.valueOf(chonKhachHang.SelectKhachHang().getHoten()));
    maKH = chonKhachHang.SelectKhachHang().getMaKH();
        System.out.println("makh" + maKH);
}
//    // Phương thức để nhận tên khách hàng từ ChonKhachHang
//    public void setKhachHang(int index) {
//        KhachHangDTO khachhang = khachHangBUS.selectByID(index);
//        txtkhachhang.setText(khachhang.getHoten());
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchonkhachhang;
    private javax.swing.JButton btnsuasanpham;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoasanpham;
    private javax.swing.JButton btnxuathang;
    private javax.swing.JPanel containernhap;
    private javax.swing.JPanel containerpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel leftcontent;
    private javax.swing.JTable tblsoluongsanpham;
    private javax.swing.JTable tblthongtinspdathempx;
    private javax.swing.JTextField txtgiaxuat;
    public javax.swing.JTextField txtkhachhang;
    private javax.swing.JTextField txtmaphieuxuat;
    public javax.swing.JTextField txtmasanphampx;
    private javax.swing.JTextField txtnhanvienxuat;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txtsoluongton;
    private javax.swing.JTextField txttensanpham;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JLabel txttongtien;
    // End of variables declaration//GEN-END:variables
}
