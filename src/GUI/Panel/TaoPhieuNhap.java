/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Panel;

import javax.swing.*;
import BUS.SanPhamPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.LoaiDAO;
import DAO.NhaCungCapDAO;
import DAO.PhieuNhapDAO;
import DAO.SanPhamPhieuNhapDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.BuildTable;
import GUI.Main;
import GUI.PhieuNhap;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author this pc
 */
public class TaoPhieuNhap extends javax.swing.JPanel implements MouseListener {

    Main main;
    PhieuNhap phieuNhap;
    ThuongHieuDAO thuongHieuDAO;
    LoaiDAO loaiDAO;
    XuatXuDAO xuatXuDAO;
    NhaCungCapDAO nhaCungCapDAO;
    SanPhamBUS sanPhamBUS;
    SanPhamPhieuNhapBUS sanPhamPhieuNhapBUS;
    SanPhamPhieuNhapDAO sanPhamPhieuNhapDAO;
    ChiTietPhieuNhapDAO chiTiet;
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    PhieuNhapDAO phieuNhapDAO;
    ChiTietPhieuNhapDTO chiTietPhieuNhapDTO;
    //ArrayList<SanPhamDTO> productList;
    private DefaultTableModel tblModel;
    private JComboBox<String> comboBox;
    private NhaCungCapBUS nhaCungCapBUS;
    // Khai báo cấu trúc dữ liệu để lưu thông tin sản phẩm đã được chọn
    private ArrayList<SanPhamDTO> productList = new ArrayList<>();
    private ArrayList<PhieuNhapDTO> selectedPNproducts = new ArrayList<>();
    long totalPrice = 0;
    int rowNum = 1; // Biến đếm số thứ tự hàng

    public TaoPhieuNhap() {
        nhaCungCapBUS = new NhaCungCapBUS();
        sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        sanPhamPhieuNhapDAO = new SanPhamPhieuNhapDAO();
        chiTiet = new ChiTietPhieuNhapDAO();
        phieuNhapDAO = new PhieuNhapDAO();
        BuildTable buildTable = new BuildTable();
        initComponents();
        btnsuasanpham.setVisible(false);
        btnxoasanpham.setVisible(false);

        // Tạo mã phiếu nhập mới
        int soLuongPhieuNhapDaTao = phieuNhapDAO.getLatestMaPhieuNhap();
        int maPhieuNhap = ++soLuongPhieuNhapDaTao;
        txtmaphieunhap.setText("PN" + (maPhieuNhap));
        txtmaphieunhap.setEditable(false);
        txtmasanpham.setEditable(false);
        txttensanpham.setEditable(false);
        txtloinhuan.setEditable(false);
        txtnhanviennhap.setEditable(false);
        CBBNhaCungCap(cbbnhacc);

        tblsoluongsanpham.setDefaultEditor(Object.class, null);
        tblsoluongsanpham.setFocusable(false);
        tblthongtinspdathem.setDefaultEditor(Object.class, null);
        tblthongtinspdathem.setFocusable(false);

        // Gọi phương thức getListSanPham từ SanPhamPhieuNhapBUS để lấy danh sách sản phẩm từ cơ sở dữ liệu
        SanPhamPhieuNhapBUS sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        ArrayList<SanPhamDTO> list = sanPhamPhieuNhapBUS.getListSanPham();
        // Cập nhật dữ liệu vào bảng
        buildTable.updateTableProductsPN(tblsoluongsanpham, list);

        tblsoluongsanpham.addMouseListener(this);
        tblthongtinspdathem.addMouseListener(this);
        cbbnhacc.addMouseListener(this);

        txtsearch.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtsearch.putClientProperty("JTextField.showClearButton", true);
        txtsearch.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txtsearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sanPhamBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> rs = sanPhamBUS.searchPN(txtsearch.getText());
                buildTable.updateTableProductsPN(tblsoluongsanpham, rs);
            }
        });
    }
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    TaiKhoanDTO taiKhoanDTO;

    public TaoPhieuNhap(TaiKhoanDTO taiKhoanDTO) {
        nhaCungCapBUS = new NhaCungCapBUS();
        this.taiKhoanDTO = taiKhoanDTO;
        sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        sanPhamPhieuNhapDAO = new SanPhamPhieuNhapDAO();
        chiTiet = new ChiTietPhieuNhapDAO();
        phieuNhapDAO = new PhieuNhapDAO();
        BuildTable buildTable = new BuildTable();
        initComponents();
        btnsuasanpham.setVisible(false);
        btnxoasanpham.setVisible(false);

        // Tạo mã phiếu nhập mới
        int soLuongPhieuNhapDaTao = phieuNhapDAO.getLatestMaPhieuNhap();
        int maPhieuNhap = ++soLuongPhieuNhapDaTao;
        txtmaphieunhap.setText("PN" + (maPhieuNhap));
        txtmaphieunhap.setEditable(false);
        txtmasanpham.setEditable(false);
        txttensanpham.setEditable(false);
        txtloinhuan.setEditable(false);
        txtnhanviennhap.setEditable(false);
        CBBNhaCungCap(cbbnhacc);

        tblsoluongsanpham.setDefaultEditor(Object.class, null);
        tblsoluongsanpham.setFocusable(false);
        tblthongtinspdathem.setDefaultEditor(Object.class, null);
        tblthongtinspdathem.setFocusable(false);

        // Gọi phương thức getListSanPham từ SanPhamPhieuNhapBUS để lấy danh sách sản phẩm từ cơ sở dữ liệu
        SanPhamPhieuNhapBUS sanPhamPhieuNhapBUS = new SanPhamPhieuNhapBUS();
        ArrayList<SanPhamDTO> list = sanPhamPhieuNhapBUS.getListSanPham();
        // Cập nhật dữ liệu vào bảng
        buildTable.updateTableProductsPN(tblsoluongsanpham, list);

        tblsoluongsanpham.addMouseListener(this);
        tblthongtinspdathem.addMouseListener(this);

        txtsearch.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtsearch.putClientProperty("JTextField.showClearButton", true);
        txtsearch.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txtsearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sanPhamBUS = new SanPhamBUS();
                ArrayList<SanPhamDTO> rs = sanPhamBUS.searchPN(txtsearch.getText());
                buildTable.updateTableProductsPN(tblsoluongsanpham, rs);
            }
        });
        txtnhanviennhap.setText(nhanVienBUS.selectByID(taiKhoanDTO.getManv()).getHoten());
        cbbnhacc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object selectedNCC = cbbnhacc.getSelectedItem();

                    // Kiểm tra xem selectedNCC có null không trước khi tiếp tục
                    if (selectedNCC != null) {
                        NhaCungCapDTO nhaCungCapDTO = nhaCungCapBUS.selectByTenNCC((String) selectedNCC);

                        // Kiểm tra nhaCungCapDTO có null không trước khi truy cập thuộc tính
                        if (nhaCungCapDTO != null) {
                            txtloinhuan.setText(String.valueOf(nhaCungCapDTO.getPhantramloinhuan()));
                        } else {
                            txtloinhuan.setText("");  // Đặt giá trị mặc định nếu không tìm thấy nhà cung cấp
                            JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp này!");
                        }
                    }
                }
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblsoluongsanpham) {
            int selectedRow = tblsoluongsanpham.getSelectedRow();
            if (selectedRow != -1) {
                int maspColumnIndex = getColumnIndexByName("Mã SP", tblsoluongsanpham);
                int masp = (int) tblsoluongsanpham.getValueAt(selectedRow, maspColumnIndex);
                SanPhamDTO result = selectSanPham(masp); // Gọi hàm selectSanPham và truyền masp vào

                boolean found = false;
                int soluongcheck = 0;
                String gianhapcheck = null;
                for (int i = 0; i < tblthongtinspdathem.getRowCount(); i++) {
                    int maspcheck = (int) tblthongtinspdathem.getValueAt(i, 1);
                    soluongcheck = (int) tblthongtinspdathem.getValueAt(i, 8);
                    gianhapcheck = (String) tblthongtinspdathem.getValueAt(i, 7);
                    if (result.getMasp() == maspcheck) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    btnsuasanpham.setVisible(true);
                    btnxoasanpham.setVisible(true);
                    txtsoluong.setText(String.valueOf(soluongcheck));
                    txtgianhap.setText(gianhapcheck.replaceAll("[.,đ]", "").trim());
                    btnthem.setVisible(false);
                } else {
                    btnsuasanpham.setVisible(false);
                    btnxoasanpham.setVisible(false);
                    txtsoluong.setText("");
                    txtgianhap.setText("");
                    btnthem.setVisible(true);
                }

                if (result != null) {
                    txtmasanpham.setText(String.valueOf(result.getMasp()));
                    txttensanpham.setText(result.getTensp());
//                    txtgianhap.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin sản phẩm");
                }
            }
        } else if (e.getSource() == tblthongtinspdathem) {
            int selectedRow = tblthongtinspdathem.getSelectedRow();
            if (selectedRow != -1) {
                int maspColumnIndex = getColumnIndexByName("Mã SP", tblthongtinspdathem);
                int soluongColumnIndex = getColumnIndexByName("Số lượng", tblthongtinspdathem);
                int gianhapColumnIndex = getColumnIndexByName("Giá nhập", tblthongtinspdathem);
                int masp = (int) tblthongtinspdathem.getValueAt(selectedRow, maspColumnIndex);
                String gianhapstr = (String) tblthongtinspdathem.getValueAt(selectedRow, gianhapColumnIndex);
                int soluongcheck = (int) tblthongtinspdathem.getValueAt(selectedRow, soluongColumnIndex);
                SanPhamDTO result = selectSanPham(masp); // Gọi hàm selectSanPham và truyền masp vào

                txtmasanpham.setText(String.valueOf(result.getMasp()));
                txtsoluong.setText(String.valueOf(soluongcheck));
                txttensanpham.setText(result.getTensp());
                txtgianhap.setText(String.valueOf(gianhapstr).replaceAll("[.,đ]", "").trim());
                btnthem.setVisible(false);
                btnsuasanpham.setVisible(true);
                btnxoasanpham.setVisible(true);
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

    public SanPhamDTO selectSanPham(int masp) {
        sanPhamBUS = new SanPhamBUS();
        SanPhamDTO result = sanPhamBUS.selectByID(masp);
        return result;
    }
// Phương thức để lấy chỉ số của cột dựa trên tên cột

    public int getColumnIndexByName(String columnName, JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy tên cột
    }

    public void CheckCombobox(JComboBox<String> combobox) {
        sanPhamBUS = new SanPhamBUS();
        ArrayList<SanPhamDTO> list = sanPhamBUS.getAllSanPham();
        String tensp = txttensanpham.getText();
        // Xóa tất cả các mục trong comboBox
        combobox.removeAllItems();
        for (SanPhamDTO sp : list) {
            if (tensp.equals(sp.getTensp())) { // Sử dụng equals() để so sánh hai chuỗi
                System.out.println(sp.getTensp());
                combobox.addItem(String.valueOf(sp.getSize())); // Ép sang kiểu chuỗi trước khi thêm vào combobox    
            }
        }
    }
    // Phương thức cập nhật dữ liệu vào bảng

    public void updatetableaddedproducts(ArrayList<SanPhamDTO> productList, JTable table) {
        thuongHieuDAO = new ThuongHieuDAO();
        loaiDAO = new LoaiDAO();
        xuatXuDAO = new XuatXuDAO();
        nhaCungCapDAO = new NhaCungCapDAO();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0); // Xóa hết dữ liệu trong bảng

        String gianhapstr = txtgianhap.getText();
        int gianhap = Integer.parseInt(gianhapstr);
        for (SanPhamDTO product : productList) {
            String TenLoai = loaiDAO.selectById(product.getLoai()).getTenloai();
            String TenThuongHieu = thuongHieuDAO.selectById(product.getThuonghieu()).getTenthuonghieu();
            String XuatXu = xuatXuDAO.selectById(product.getXuatxu()).getTenxuatxu();
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
            int soluong = Integer.valueOf(txtsoluong.getText());
            // Thêm mỗi sản phẩm vào bảng
            model.addRow(new Object[]{
                rowNum++,
                product.getMasp(),
                product.getTensp(),
                product.getSize(),
                XuatXu,
                TenLoai,
                TenThuongHieu,
                decimalFormat.format(gianhap), // Định dạng giá nhập
                soluong
            });
            totalPrice += gianhap * soluong;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        txttongtien.setText(decimalFormat.format(totalPrice));
        // Tạo renderer để hiển thị nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Áp dụng renderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        txtmasanpham.setText("");
        txtsoluong.setText("");
        txttensanpham.setText("");
        txtloinhuan.setText("");

    }

    // Phương thức để tạo mô hình dữ liệu cho JTable từ danh sách sản phẩm
    public DefaultTableModel buildTableModel(ArrayList<SanPhamDTO> list) {
        // Định nghĩa tên cột
        //String[] columnNames = {"Mã SP", "Tên sản phẩm", "Số lượng"};

        // Tạo DefaultTableModel với tên cột
        DefaultTableModel model = new DefaultTableModel();

        // Thêm dòng vào mô hình từ danh sách
        for (SanPhamDTO sp : list) {
            Object[] rowData = {sp.getMasp(), sp.getTensp(), sp.getSoluongton()};
            model.addRow(rowData);
        }

        return model;
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
        txtsearch = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsoluongsanpham = new javax.swing.JTable();
        containernhap = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmasanpham = new javax.swing.JTextField();
        txtloinhuan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttensanpham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        btnxoasanpham = new javax.swing.JButton();
        btnsuasanpham = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblthongtinspdathem = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtmaphieunhap = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtnhanviennhap = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbbnhacc = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JLabel();
        btnnhaphang = new javax.swing.JButton();

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

        containernhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Mã sản phẩm");

        jLabel2.setText("Tên sản phẩm");

        txtmasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmasanphamActionPerformed(evt);
            }
        });

        txtloinhuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloinhuanActionPerformed(evt);
            }
        });

        jLabel4.setText("Lợi nhuận (%)");

        jLabel7.setText("Số lượng");

        btnxoasanpham.setBackground(new java.awt.Color(255, 102, 153));
        btnxoasanpham.setForeground(new java.awt.Color(255, 255, 255));
        btnxoasanpham.setText("Xóa sản phẩm");
        btnxoasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasanphamActionPerformed(evt);
            }
        });

        btnsuasanpham.setBackground(new java.awt.Color(255, 153, 51));
        btnsuasanpham.setForeground(new java.awt.Color(255, 255, 255));
        btnsuasanpham.setText("Sửa sản phẩm");
        btnsuasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuasanphamActionPerformed(evt);
            }
        });

        jLabel5.setText("Giá nhập");

        txtgianhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgianhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containernhapLayout = new javax.swing.GroupLayout(containernhap);
        containernhap.setLayout(containernhapLayout);
        containernhapLayout.setHorizontalGroup(
            containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containernhapLayout.createSequentialGroup()
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containernhapLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(containernhapLayout.createSequentialGroup()
                                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmasanpham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(containernhapLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttensanpham)
                                            .addGroup(containernhapLayout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(containernhapLayout.createSequentialGroup()
                                        .addGap(161, 161, 161)
                                        .addComponent(txtloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containernhapLayout.createSequentialGroup()
                                .addComponent(btnsuasanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnxoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(containernhapLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containernhapLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(336, 336, 336)))
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
                    .addComponent(txtmasanpham))
                .addGap(18, 18, 18)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtloinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(172, 172, 172)
                .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(containernhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containernhapLayout.createSequentialGroup()
                    .addGap(98, 98, 98)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(247, Short.MAX_VALUE)))
        );

        tblthongtinspdathem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Size", "Xuất xứ", "Loại", "Thương hiệu", "Giá nhập", "Số lượng"
            }
        ));
        tblthongtinspdathem.setRowHeight(30);
        jScrollPane2.setViewportView(tblthongtinspdathem);

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
                            .addComponent(txtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(leftcontentLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(containernhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        leftcontentLayout.setVerticalGroup(
            leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftcontentLayout.createSequentialGroup()
                .addGroup(leftcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftcontentLayout.createSequentialGroup()
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(containernhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        containerpanel.add(leftcontent, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Nhân viên nhập");

        jLabel17.setText("Nha cung cấp");

        jLabel18.setText("Mã phiếu nhập");

        cbbnhacc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn nhà cung cấp" }));
        cbbnhacc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbnhaccActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("TỔNG TIỀN:");

        txttongtien.setText("0đ");

        btnnhaphang.setBackground(new java.awt.Color(0, 153, 51));
        btnnhaphang.setForeground(new java.awt.Color(255, 255, 255));
        btnnhaphang.setText("Nhập hàng");
        btnnhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhaphangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtmaphieunhap)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnhanviennhap)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbnhacc, 0, 287, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnnhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtmaphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtnhanviennhap, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbnhacc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        containerpanel.add(jPanel1, java.awt.BorderLayout.EAST);

        add(containerpanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnnhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhaphangActionPerformed
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu nhập !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            // Kiểm tra số lượng hàng trong bảng thongtinsanphamdatthem
            int rowCount = tblthongtinspdathem.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào để nhập. Vui lòng nhập sản phẩm.");
            } else {
                String ncc = "Chọn nhà cung cấp";
                if (cbbnhacc.getSelectedItem().equals(ncc)) {
                    JOptionPane.showMessageDialog(leftcontent, "Vui lòng chọn nhà cung cấp!");
                } else if (createPhieuNhap()) {
                    try {
                        // Tạo đối tượng mới của panel PhieuNhap
                        PhieuNhap phieunhap = new PhieuNhap(taiKhoanDTO);
                        main = new Main();
                        // Kiểm tra và hiển thị panel PhieuNhap
                        if (main != null) {
                            main.setPanel(containerpanel, phieunhap);
                        } else {
                            System.out.println("Biến main chưa được khởi tạo!");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TaoPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnnhaphangActionPerformed

    private boolean createPhieuNhap() {
        try {
            String tenncc = (String) cbbnhacc.getSelectedItem();
            int mancc = SelectedMaNCC(tenncc);
            NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
            nhaCungCapDTO = nhaCungCapBUS.selectByID(mancc);
            int phantramloinhuan = nhaCungCapDTO.getPhantramloinhuan();
//            txtloinhuan.setText(String.valueOf(phantramloinhuan));
            String maphieunhapstr = txtmaphieunhap.getText().replaceAll("[PN.,đ]", "").trim();
            int maphieunhap = Integer.parseInt(maphieunhapstr);
            int manv = taiKhoanDTO.getManv();
            long tongtien = 0;
            String tongtienStr = txttongtien.getText().replaceAll("[.,đ]", "").trim();
            tongtien = Long.parseLong(tongtienStr);
            long now = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(now);

            PhieuNhapDTO pn = new PhieuNhapDTO(maphieunhap, timestamp, mancc, manv, tongtien, 1);

            phieuNhapDAO = new PhieuNhapDAO();
            phieuNhapDAO.insertPhieuNhap(pn, now);
            addChiTietPhieuNhapToDatabase();
            JOptionPane.showMessageDialog(null, "Tạo phiếu nhập thành công");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void addChiTietPhieuNhapToDatabase() {
        DefaultTableModel model = (DefaultTableModel) tblthongtinspdathem.getModel();
        ArrayList<ChiTietPhieuNhapDTO> chiTietPhieuNhapList = new ArrayList<>();
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        boolean hasProductsToImport = false;
        String maphieunhapstr = txtmaphieunhap.getText().replaceAll("[PN.,đ]", "").trim();
        int maphieunhap = Integer.parseInt(maphieunhapstr);
        int loinhuan = Integer.parseInt(txtloinhuan.getText());
        for (int i = 0; i < model.getRowCount(); i++) {
            int masp = (int) model.getValueAt(i, 1);
            int soluong = (int) model.getValueAt(i, 8);
            String gianhapsrt = model.getValueAt(i, 7).toString();
            int gianhap = Integer.parseInt(gianhapsrt.replaceAll("[.,đ]", "").trim());
            int giaxuat = gianhap + (gianhap * loinhuan / 100);
            try {
                chiTiet.updateSoluongton(masp, soluong);
                ChiTietPhieuNhapDTO chiTietPhieuNhapDTO = new ChiTietPhieuNhapDTO(maphieunhap, masp, soluong, gianhap, giaxuat);
                chiTietPhieuNhapDAO.updateSoluongCTPN(masp, soluong, maphieunhap);
                chiTietPhieuNhapList.add(chiTietPhieuNhapDTO);
                hasProductsToImport = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là một số nguyên dương");
                hasProductsToImport = false;
                break;
            }
        }

        if (hasProductsToImport) {
            chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
            chiTietPhieuNhapDAO.insert(chiTietPhieuNhapList);
        }
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // Biểu thức chính quy kiểm tra chuỗi có chứa số hay không
    }

    public void ThemSanPhamVaoHangCho() {
        if (txtmasanpham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
        } else if (txtsoluong.getText().isEmpty() || !isNumeric(txtsoluong.getText())) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống và phải là một số!");
        } else if (Integer.parseInt(txtsoluong.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0");
        } else {
            int masp = Integer.parseInt(txtmasanpham.getText());
            tblModel = (DefaultTableModel) tblthongtinspdathem.getModel(); // Lấy mô hình dữ liệu từ bảng
            ArrayList<SanPhamDTO> newProductList = sanPhamPhieuNhapDAO.getListSanPham(masp);
            // Cập nhật dữ liệu trong bảng
            updatetableaddedproducts(newProductList, tblthongtinspdathem);
        }
    }


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

    private void txtloinhuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloinhuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloinhuanActionPerformed

    private void txtmasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmasanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmasanphamActionPerformed

    private void cbbnhaccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbnhaccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbnhaccActionPerformed

    private void btnxoasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoasanphamActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblthongtinspdathem.getModel();
        int selectedRow = tblthongtinspdathem.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            updateTotalPrice();
            txtmasanpham.setText("");
            txtsoluong.setText("");
            txttensanpham.setText("");
            txtloinhuan.setText("");
        }
    }//GEN-LAST:event_btnxoasanphamActionPerformed

    private void btnsuasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuasanphamActionPerformed
        // Lấy giá trị số lượng từ JTextField
        String updatesoluong = txtsoluong.getText();
        String gianhapstr = txtgianhap.getText();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");

        // Kiểm tra xem người dùng đã chọn hàng nào trong bảng tblthongtinspdathempx hoặc tblsoluongsanpham
        int selectedRowSPDaChon = tblthongtinspdathem.getSelectedRow();
        int selectedRowSPDangChon = tblsoluongsanpham.getSelectedRow();

        if (selectedRowSPDaChon != -1) { // Kiểm tra bảng tblthongtinspdathempx
            handleUpdateSelectedRow(updatesoluong, gianhapstr, selectedRowSPDaChon, tblthongtinspdathem);
        } else if (selectedRowSPDangChon != -1) {
            handleUpdateByMaSP(updatesoluong, gianhapstr, selectedRowSPDangChon);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để cập nhật số lượng.");
        }

    }//GEN-LAST:event_btnsuasanphamActionPerformed

    private void handleUpdateSelectedRow(String updatesoluong, String gianhapstr, int selectedRow, JTable table) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
            // Chuyển đổi giá trị số lượng thành số nguyên
            int soluong = Integer.parseInt(updatesoluong);
            int gianhap = Integer.parseInt(gianhapstr);
            if (soluong > 0) {
                // Cập nhật số lượng mới trong bảng
                table.setValueAt(soluong, selectedRow, 8);
                table.setValueAt(decimalFormat.format(gianhap), selectedRow, 7);
                updateTotalPrice();
            } else {
                    // Hiển thị thông báo khi số lượng nhập vào là một số âm
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm phải lớn hơn 0.");
                }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số nguyên.");
        }
    }
    
    private void handleUpdateByMaSP(String updatesoluong, String gianhapstr, int selectedRowSPDangChon) {
        try {
            int soluong = Integer.parseInt(updatesoluong);
            int maspColumnIndex = getColumnIndexByName("Mã SP", tblsoluongsanpham);
            int masp = (int) tblsoluongsanpham.getValueAt(selectedRowSPDangChon, maspColumnIndex);
            int gianhap = Integer.parseInt(gianhapstr);
            if (soluong > 0) {
                if (updateSoluongVaGiaNhapByMaSP(masp, soluong, gianhap)) {
                    updateTotalPrice();
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có Mã SP tương ứng để cập nhật.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số nguyên.");
        }
    }
    
    private boolean updateSoluongVaGiaNhapByMaSP(int masp, int soluong, int gianhap) {
        int rowCount = tblthongtinspdathem.getRowCount();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        for (int i = 0; i < rowCount; i++) {
            int maspInTable = (int) tblthongtinspdathem.getValueAt(i, getColumnIndexByName("Mã SP", tblthongtinspdathem));
            if (maspInTable == masp) {
                tblthongtinspdathem.setValueAt(decimalFormat.format(gianhap), i, 7);
                tblthongtinspdathem.setValueAt(soluong, i, 8);
                return true;
            }
        }
        return false;
    }

    private void txtgianhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgianhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgianhapActionPerformed

    // Phương thức để cập nhật tổng tiền sau khi sửa số lượng
    private void updateTotalPrice() {
        // Khởi tạo biến tổng tiền
        long totalPrice = 0;

        // Tính lại tổng tiền dựa trên số lượng mới của từng sản phẩm
        for (int i = 0; i < tblthongtinspdathem.getRowCount(); i++) {
            int soluong = (int) tblthongtinspdathem.getValueAt(i, 8); // Lấy số lượng từ cột 8 (cột số lượng)
            String gianhapStr = (String) tblthongtinspdathem.getValueAt(i, 7); // Lấy giá nhập từ cột 7 (cột giá nhập) dưới dạng chuỗi
            gianhapStr = gianhapStr.replaceAll("[.,đ]", "").trim();
            long gianhap = Long.parseLong(gianhapStr);
            totalPrice += soluong * gianhap;
        }

        // Hiển thị tổng tiền mới sau khi cập nhật
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        txttongtien.setText(decimalFormat.format(totalPrice));
    }

    //Cập nhật sản phẩm đã bấm nút nhập hàng vào bảng phiếu nhập
    public void updateInputTable(ArrayList<PhieuNhapDTO> danhSachPhieuNhap) {
        PhieuNhap phieunhap = new PhieuNhap();
        nhaCungCapDAO = new NhaCungCapDAO();
        int stt = 1;
        DefaultTableModel model = (DefaultTableModel) phieunhap.tblPhieuNhap.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng trong bảng

        for (PhieuNhapDTO pn : danhSachPhieuNhap) {
            String TenNcc = nhaCungCapDAO.selectById(pn.getManhacungcap()).getTenncc();
            Object[] rowData = {
                stt++,
                pn.getMaphieunhap(),
                TenNcc,
                pn.getMaNV(),
                pn.getThoigiantao(),
                pn.getTongTien()
            };
            model.addRow(rowData); // Thêm hàng mới vào bảng
        }
    }

    public int SelectedMaNCC(String TenNccCanTim) {
        ArrayList<NhaCungCapDTO> nhaCungCapList = nhaCungCapBUS.getAllNhaCungCap();
        int maNhaCungCap = -1; // Giá trị mặc định nếu không tìm thấy

        for (NhaCungCapDTO nhaCungCap : nhaCungCapList) {
            if (nhaCungCap.getTenncc().equals(TenNccCanTim)) {
                maNhaCungCap = nhaCungCap.getMancc();
                break;
            }
        }

        return maNhaCungCap;
    }

    // Thêm nhà cung cấp vào combobox
    public void CBBNhaCungCap(JComboBox<String> comboBox) {
        // Gọi phương thức getAllNhaCungCap từ nhaCungCapBUS để lấy danh sách nhà cung cấp từ cơ sở dữ liệu
        ArrayList<NhaCungCapDTO> nhaCungCapList = nhaCungCapBUS.getAllNhaCungCap();
        ArrayList<String> tenNhaCungCapList = new ArrayList<>();

        for (NhaCungCapDTO nhaCungCap : nhaCungCapList) {
            tenNhaCungCapList.add(nhaCungCap.getTenncc());
        }
        // Thêm từng tên nhà cung cấp vào ComboBox
        for (String tenNhaCungCap : tenNhaCungCapList) {
            comboBox.addItem(tenNhaCungCap);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnnhaphang;
    private javax.swing.JButton btnsuasanpham;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoasanpham;
    private javax.swing.JComboBox<String> cbbnhacc;
    private javax.swing.JPanel containernhap;
    private javax.swing.JPanel containerpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel leftcontent;
    private javax.swing.JTable tblsoluongsanpham;
    private javax.swing.JTable tblthongtinspdathem;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtloinhuan;
    private javax.swing.JTextField txtmaphieunhap;
    public javax.swing.JTextField txtmasanpham;
    private javax.swing.JTextField txtnhanviennhap;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensanpham;
    private javax.swing.JLabel txttongtien;
    // End of variables declaration//GEN-END:variables

}
