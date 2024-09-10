package GUI;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.Export.JTableExporter;
import GUI.Component.ShowCBB;
import GUI.PXuat.ChiTietPhieuXuat;
import GUI.Panel.TaoPhieuNhap;
import GUI.Panel.TaoPhieuXuatt;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class PhieuXuat extends javax.swing.JPanel implements ActionListener, PropertyChangeListener, KeyListener, ItemListener {

    private DefaultTableModel tblModel;
    KhachHangBUS khachHangBUS;
    NhanVienBUS nhanVienBUS;
    PhieuXuat phieuXuat;
    TaoPhieuNhap taoPhieuNhap;
    PhieuXuatDAO phieuXuatDAO;
    ChiTietPhieuXuatDTO chiTietPhieuXuatDTO;
    ChiTietPhieuXuat chiTietPhieuXuat;
    ShowCBB showCBB = new ShowCBB();
    PhieuXuatBUS phieuXuatBUS = new PhieuXuatBUS();
    ArrayList<PhieuXuatDTO> selectedPXproducts;

    /**
     * Creates new form PhieuXuat
     */
    public PhieuXuat() {
        initComponents();
        addIcon();
        tblPhieuxuat.setFocusable(false);
        tblPhieuxuat.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblPhieuxuat.setFocusable(false);
        tblPhieuxuat.setAutoCreateRowSorter(true);

        tblModel = (DefaultTableModel) tblPhieuxuat.getModel();
        this.selectedPXproducts = phieuXuatBUS.getAllPhieuXuat();
        loadDataTable(this.selectedPXproducts);

        showCBB.CBBKhachHang(comboboxKH);
        showCBB.CBBNhanVienNhap(comboboxNV);

        btnChiTietPX.addActionListener(this);
        btnHuyPX.addActionListener(this);
        comboboxKH.addItemListener(this);
        comboboxNV.addItemListener(this);
        datengaybatdau.addPropertyChangeListener(this);
        datengayketthuc.addPropertyChangeListener(this);
        txtminprice.addKeyListener(this);
        txtmaxprice.addKeyListener(this);

        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên khách hàng, mã phiếu xuất...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Fillter();
            }
        });
        btnXuatExcelPX.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tblPhieuxuat);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

    }
    TaiKhoanDTO taiKhoanDTO;

    public PhieuXuat(TaiKhoanDTO taiKhoanDTO) {
        initComponents();
        addIcon();
        this.taiKhoanDTO = taiKhoanDTO;
        tblPhieuxuat.setFocusable(false);
        tblPhieuxuat.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblPhieuxuat.setFocusable(false);
        tblPhieuxuat.setAutoCreateRowSorter(true);

        tblModel = (DefaultTableModel) tblPhieuxuat.getModel();
        this.selectedPXproducts = phieuXuatBUS.getAllPhieuXuat();
        loadDataTable(this.selectedPXproducts);

        showCBB.CBBKhachHang(comboboxKH);
        showCBB.CBBNhanVienNhap(comboboxNV);
        btnChiTietPX.addActionListener(this);
        btnHuyPX.addActionListener(this);
        comboboxKH.addItemListener(this);
        comboboxNV.addItemListener(this);
        datengaybatdau.addPropertyChangeListener(this);
        datengayketthuc.addPropertyChangeListener(this);
        txtminprice.addKeyListener(this);
        txtmaxprice.addKeyListener(this);

        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên khách hàng, mã phiếu xuất...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Fillter();
            }
        });
        btnXuatExcelPX.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tblPhieuxuat);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

    }

    private void addIcon() {
        btnThemPX.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnChiTietPX.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnHuyPX.setIcon(new FlatSVGIcon("./icon/cancel.svg"));
        btnXuatExcelPX.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContentPX = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        btnThemPX = new javax.swing.JButton();
        btnChiTietPX = new javax.swing.JButton();
        btnHuyPX = new javax.swing.JButton();
        btnXuatExcelPX = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        pnlBottom = new javax.swing.JPanel();
        scpnltable = new javax.swing.JScrollPane();
        tblPhieuxuat = new javax.swing.JTable();
        pnlLoc = new javax.swing.JPanel();
        txtnhacungcap = new javax.swing.JLabel();
        comboboxKH = new javax.swing.JComboBox<>();
        txtnhacungcap1 = new javax.swing.JLabel();
        txtnhacungcap2 = new javax.swing.JLabel();
        comboboxNV = new javax.swing.JComboBox<>();
        txtnhacungcap3 = new javax.swing.JLabel();
        txtnhacungcap4 = new javax.swing.JLabel();
        txtmaxprice = new javax.swing.JTextField();
        txtnhacungcap5 = new javax.swing.JLabel();
        txtminprice = new javax.swing.JTextField();
        datengaybatdau = new com.toedter.calendar.JDateChooser();
        datengayketthuc = new com.toedter.calendar.JDateChooser();

        setLayout(new java.awt.BorderLayout());

        mainContentPX.setBackground(new java.awt.Color(240, 247, 250));
        mainContentPX.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContentPX.setPreferredSize(new java.awt.Dimension(1300, 800));
        mainContentPX.setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThemPX.setText("Thêm");
        btnThemPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPXActionPerformed(evt);
            }
        });
        pnlTop.add(btnThemPX);

        btnChiTietPX.setText("Chi tiết");
        btnChiTietPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietPXActionPerformed(evt);
            }
        });
        pnlTop.add(btnChiTietPX);

        btnHuyPX.setText("Hủy phiếu");
        btnHuyPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyPXActionPerformed(evt);
            }
        });
        pnlTop.add(btnHuyPX);

        btnXuatExcelPX.setText("Xuất excel");
        btnXuatExcelPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelPXActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcelPX);

        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

        txtTimKiem.setPreferredSize(new java.awt.Dimension(300, 30));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        pnlTop.add(txtTimKiem);

        mainContentPX.add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlBottom.setBackground(new java.awt.Color(240, 247, 250));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));

        tblPhieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã xuất phiếu", "Khách hàng", "Nhân viên nhập", "Thời gian", "Tổng tiền"
            }
        ));
        scpnltable.setViewportView(tblPhieuxuat);
        if (tblPhieuxuat.getColumnModel().getColumnCount() > 0) {
            tblPhieuxuat.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblPhieuxuat.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        pnlLoc.setBackground(new java.awt.Color(255, 255, 255));
        pnlLoc.setPreferredSize(new java.awt.Dimension(270, 700));

        txtnhacungcap.setText("Nhân viên xuất");

        comboboxKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        txtnhacungcap1.setText("Đến số tiền (VND)");

        txtnhacungcap2.setText("Từ ngày");

        comboboxNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        txtnhacungcap3.setText("Đến ngày");

        txtnhacungcap4.setText("Khách hàng");

        txtnhacungcap5.setText("Từ số tiền (VND)");

        datengaybatdau.setBackground(new java.awt.Color(255, 255, 255));

        datengayketthuc.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlLocLayout = new javax.swing.GroupLayout(pnlLoc);
        pnlLoc.setLayout(pnlLocLayout);
        pnlLocLayout.setHorizontalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtminprice)
                    .addGroup(pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmaxprice)
                            .addGroup(pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboboxKH, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtnhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnhacungcap2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnhacungcap1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboboxNV, javax.swing.GroupLayout.Alignment.TRAILING, 0, 248, Short.MAX_VALUE))
                                .addComponent(txtnhacungcap3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtnhacungcap4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnhacungcap5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(datengaybatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datengayketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlLocLayout.setVerticalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnhacungcap4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtnhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtnhacungcap2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datengaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtnhacungcap3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datengayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtnhacungcap5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtminprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtnhacungcap1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmaxprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomLayout.createSequentialGroup()
                .addComponent(pnlLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpnltable, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpnltable)
                    .addComponent(pnlLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainContentPX.add(pnlBottom, java.awt.BorderLayout.CENTER);

        add(mainContentPX, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnXuatExcelPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelPXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelPXActionPerformed

    private void btnHuyPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyPXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyPXActionPerformed

    private void btnChiTietPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietPXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietPXActionPerformed

    private void btnThemPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPXActionPerformed
        // Ẩn các thành phần hiện tại
        //mainContentPN.setVisible(false);
        pnlBottom.setVisible(false);
        pnlLoc.setVisible(false);
        pnlTop.setVisible(false);
        // Tạo một thể hiện của panel từ file java khác
        TaoPhieuXuatt taoPhieuXuat = new TaoPhieuXuatt(taiKhoanDTO);

        // Thêm taoPhieuNhap vào mainContentPN
        mainContentPX.add(taoPhieuXuat);
        //mainContentPN.setSize(1400, 800);
        // mainContentPN.setVisible(true);
        // Thực hiện cập nhật giao diện cho mainContentPN
        mainContentPX.revalidate();
        mainContentPX.repaint();
    }//GEN-LAST:event_btnThemPXActionPerformed

    //HÀM HIỆN PHIẾU XUẤT LÊN BẢNG
    public void loadDataTable(ArrayList<PhieuXuatDTO> ListPhieuXuat) {
        tblModel.setRowCount(0); // Xóa tất cả các hàng trong bảng
        khachHangBUS = new KhachHangBUS();
        nhanVienBUS = new NhanVienBUS();
        int i = 1;
        for (PhieuXuatDTO px : ListPhieuXuat) {
            String TenKhachHang = khachHangBUS.selectByID(px.getMakh()).getHoten();
            String TenNv = nhanVienBUS.selectByID(px.getManv()).getHoten();
            DecimalFormat decimalFormat = new DecimalFormat("#,### đ"); // Khởi tạo một đối tượng DecimalFormat
            Object[] rowData = {
                i++,
                px.getMaphieuxuat(),
                TenKhachHang,
                TenNv,
                px.getThoigiantao(),
                decimalFormat.format(px.getTongTien())
            };
            tblModel.addRow(rowData); // Thêm hàng mới vào bảng
        }
        // Tạo renderer để căn giữa nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int j = 0; j < tblModel.getColumnCount(); j++) {
            tblPhieuxuat.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    //CLICK VÀO BẢNG PHIẾU XUẤT
    private PhieuXuatDTO selectPhieuXuat() {
        int selectedRow = tblPhieuxuat.getSelectedRow();
        PhieuXuatDTO result = null;
        if (selectedRow != -1) {
            //int mapnColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã xuất phiếu", tblPhieuNhap); // Sử dụng đối tượng taoPhieuNhap để gọi phương thức getColumnIndexByName
            int mapn = (int) tblPhieuxuat.getValueAt(selectedRow, 1);
            phieuXuatBUS = new PhieuXuatBUS();
            result = phieuXuatBUS.selectByID(mapn);
        }
        return result;
    }

    //HÀM HỦY PHIẾU NHẬP
    public void DeletePhieuXuat() {
        taoPhieuNhap = new TaoPhieuNhap();
        int selectedRow = tblPhieuxuat.getSelectedRow();
        if (selectedRow != -1) {
            phieuXuatBUS = new PhieuXuatBUS();
            int maspColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã xuất phiếu", tblPhieuxuat);
            int mapx = (int) tblPhieuxuat.getValueAt(selectedRow, maspColumnIndex);
            boolean thanhcong = phieuXuatBUS.DeletePhieuXuat(mapx);
            if (thanhcong) {
                phieuXuatBUS = new PhieuXuatBUS();
                JOptionPane.showMessageDialog(null, "Xóa phiếu thành công");
                selectedPXproducts = phieuXuatBUS.getAllPhieuXuat();
                loadDataTable(selectedPXproducts);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm lỗi");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phảm để xóa");
        }
    }

    //CLICK
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChiTietPX) {
            if (selectPhieuXuat() != null) {
                chiTietPhieuXuat = new ChiTietPhieuXuat(selectPhieuXuat());
                chiTietPhieuXuat.setLocationRelativeTo(null);
                chiTietPhieuXuat.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
            }
        } else if (e.getSource() == btnHuyPX) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy phiếu nhập này?", "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                DeletePhieuXuat();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboboxKH || e.getSource() == comboboxNV) {
            Fillter();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == datengaybatdau && "date".equals(evt.getPropertyName())) {
            Fillter();
        } else if ("date".equals(evt.getPropertyName()) && evt.getSource() == datengayketthuc) {
            Fillter();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtmaxprice || e.getSource() == txtminprice) {
            Fillter();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void Fillter() {
        if (validateSelectDate()) {
            phieuXuatBUS = new PhieuXuatBUS();
            ArrayList<PhieuXuatDTO> listphieu = new ArrayList<>();
            String selectedKH = (String) comboboxKH.getSelectedItem();
            String selectedNv = (String) comboboxNV.getSelectedItem();
            int makh = comboboxKH.getSelectedIndex() == 0 ? 0 : phieuXuatBUS.getMakh(selectedKH);
            int manv = comboboxNV.getSelectedIndex() == 0 ? 0 : phieuXuatBUS.getManv(selectedNv);

            Date time_start = datengaybatdau.getDate() != null ? datengaybatdau.getDate() : new Date(0);
            Date time_end = datengayketthuc.getDate() != null ? datengayketthuc.getDate() : new Date(System.currentTimeMillis());
            String min_price = String.valueOf(txtminprice.getText());
            String max_price = txtmaxprice.getText();
            listphieu = phieuXuatBUS.fillerPhieuXuat(makh, manv, time_start, time_end, min_price, max_price);
            String searchText = txtTimKiem.getText();
            ArrayList<PhieuXuatDTO> rs = phieuXuatBUS.search(searchText, listphieu); //lọc theo ô tìm kiếm
            loadDataTable(rs);
        }
    }

    public boolean validateSelectDate() {
        Date time_start = datengaybatdau.getDate();
        Date time_end = datengayketthuc.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            datengaybatdau.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            datengayketthuc.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            datengayketthuc.setCalendar(null);
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietPX;
    private javax.swing.JButton btnHuyPX;
    private javax.swing.JButton btnThemPX;
    private javax.swing.JButton btnXuatExcelPX;
    private javax.swing.JComboBox<String> comboboxKH;
    private javax.swing.JComboBox<String> comboboxNV;
    private com.toedter.calendar.JDateChooser datengaybatdau;
    private com.toedter.calendar.JDateChooser datengayketthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainContentPX;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlLoc;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scpnltable;
    public javax.swing.JTable tblPhieuxuat;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtmaxprice;
    private javax.swing.JTextField txtminprice;
    private javax.swing.JLabel txtnhacungcap;
    private javax.swing.JLabel txtnhacungcap1;
    private javax.swing.JLabel txtnhacungcap2;
    private javax.swing.JLabel txtnhacungcap3;
    private javax.swing.JLabel txtnhacungcap4;
    private javax.swing.JLabel txtnhacungcap5;
    // End of variables declaration//GEN-END:variables
}
