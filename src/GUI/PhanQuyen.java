/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.NhomQuyenBUS;
import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.CheckAction;
import GUI.PQuyen.PhanQuyenDialog;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class PhanQuyen extends javax.swing.JPanel implements ActionListener {

    PhanQuyenDialog phanQuyenDialog;
    NhomQuyenDTO nhomQuyenDTO;
    NhomQuyenBUS nhomQuyenBUS;

    private DefaultTableModel tblModel;

    /**
     * Creates new form PhanQuyen
     */
    public PhanQuyen(TaiKhoanDTO taiKhoanDTO) throws SQLException {
        initComponents();
        addIcon();

        tblModel = new DefaultTableModel(new Object[]{"Mã nhóm quyền", "Tên nhóm quyền"}, 0);
        tblquyen.setModel(tblModel); // Đặt model cho bảng tblquyen

        Color BackgroundColor = new Color(240, 247, 250);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(1200, 800));

        pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlCenter.setBackground(BackgroundColor);
        tblquyen.setFocusable(false);
        tblquyen.setDefaultEditor(Object.class, null); // set ko cho sửa dữ liệu trên table
        tblquyen.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblquyen.setFocusable(false);
        tblquyen.setAutoCreateRowSorter(true);

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXuatExcel.addActionListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1200, 800));
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);

        String[] action = {"create", "update", "delete", "view"};
        Map<String, JButton> buttonMap = new HashMap<>();
        buttonMap.put("create", btnThem);       // Nút thêm
        buttonMap.put("delete", btnXoa);        // Nút xóa
        buttonMap.put("update", btnSua);        // Nút sửa
//        buttonMap.put("detail", btnChiTietPX);    // Nút chi tiết
        buttonMap.put("export", btnXuatExcel);  // Nút xuất Excel
//        buttonMap.put("import",btnNhapExcel);  // Nút nhập Excel

// Tạo đối tượng CheckAction
        CheckAction checkAction = new CheckAction(taiKhoanDTO.getManhomquyen(), "nhomquyen", action, buttonMap);

        
        // Tải dữ liệu vào bảng
        loadData();
    }

    private void loadData() {
        // Khởi tạo NhomQuyenBUS nếu chưa có
        if (nhomQuyenBUS == null) {
            nhomQuyenBUS = new NhomQuyenBUS();
        }
        // Lấy danh sách nhóm quyền từ BUS
        ArrayList<NhomQuyenDTO> listNhomQuyen = nhomQuyenBUS.getAllNhomQuyen();
        // Tải dữ liệu vào bảng
        loadDataTable(listNhomQuyen);
    }

    public void loadDataTable(ArrayList<NhomQuyenDTO> listnhomquyen) {
        tblModel.setRowCount(0); // Xóa tất cả các hàng trong bảng
        int i = 1;

        for (NhomQuyenDTO nq : listnhomquyen) {
            // Lấy tên nhóm quyền từ BUS
            NhomQuyenDTO nqDTO = nhomQuyenBUS.selectByID(nq.getManhomquyen());
            String TenNq = (nqDTO != null) ? nqDTO.getTennhomquyen() : "Không có tên";

            // Dữ liệu hàng mới
            Object[] rowData = {
                nq.getManhomquyen(), // Mã nhóm quyền
                TenNq // Tên nhóm quyền
            };

            tblModel.addRow(rowData); // Thêm hàng mới vào bảng
        }

        // Tạo renderer để căn giữa nội dung ở giữa ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho từng cột trong bảng
        for (int j = 0; j < tblModel.getColumnCount(); j++) {
            tblquyen.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblquyen = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlTop.add(btnThem);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlTop.add(btnSua);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlTop.add(btnXoa);

        btnXuatExcel.setText("Xuất excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        pnlTop.add(btnXuatExcel);

        jLabel1.setText("Tìm kiếm :");
        pnlTop.add(jLabel1);

        txtTimKiem.setPreferredSize(new java.awt.Dimension(200, 30));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });
        pnlTop.add(txtTimKiem);

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlTop.add(btnLamMoi);

        add(pnlTop, java.awt.BorderLayout.NORTH);

        tblquyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhóm quyền", "Tên nhóm quyền"
            }
        ));
        jScrollPane2.setViewportView(tblquyen);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void addIcon() {
        btnThem.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btnSua.setIcon(new FlatSVGIcon("./icon/edit.svg"));
        btnXoa.setIcon(new FlatSVGIcon("./icon/delete.svg"));
        btnXuatExcel.setIcon(new FlatSVGIcon("./icon/export_excel.svg"));
        btnLamMoi.setIcon(new FlatSVGIcon("./icon/refresh.svg"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {

        } else if (e.getSource() == btnSua) {
            try {
                // Lấy chỉ số dòng được chọn
                int selectedRow = tblquyen.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy mã nhóm quyền từ dòng được chọn trong bảng
                    String maNhomQuyen = tblquyen.getValueAt(selectedRow, 0).toString();
                    String tenNhomQuyen = tblquyen.getValueAt(selectedRow, 1).toString();
                    int ma = Integer.parseInt(maNhomQuyen);
                    // Tạo đối tượng NhomQuyenDTO
                    NhomQuyenDTO nhomQuyenDTO = new NhomQuyenDTO(ma, tenNhomQuyen);

                    // Lấy chi tiết quyền của nhóm quyền từ cơ sở dữ liệu
                    ChiTietQuyenDAO chiTietQuyenDAO = new ChiTietQuyenDAO();
                    ArrayList<ChiTietQuyenDTO> dsChiTietQuyen = chiTietQuyenDAO.getChiTietQuyen(maNhomQuyen);
                    String tenchucnang = "Cập nhật nhóm quyền";
                    // Khởi tạo dialog và truyền dữ liệu quyền vào dialog
                    phanQuyenDialog = new PhanQuyenDialog(nhomQuyenDTO, dsChiTietQuyen, tenchucnang);

                    // Hiển thị dialog
                    phanQuyenDialog.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(pnlCenter, "Vui lòng chọn nhóm quyền cần xem");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhanQuyen.class.getName()).log(Level.SEVERE, "Lỗi khi khởi tạo PhanQuyenDialog: ", ex);
            }
        } else if (e.getSource() == btnXoa) {
            // Xử lý xóa quyền
        } else if (e.getSource() == btnXuatExcel) {
            // Xử lý xuất Excel
        } else if (e.getSource() == btnLamMoi) {
            // Xử lý làm mới dữ liệu
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblquyen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
