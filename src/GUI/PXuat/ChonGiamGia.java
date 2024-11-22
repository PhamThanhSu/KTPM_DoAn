/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.PXuat;

import BUS.GiamGiaBUS;
import BUS.KhachHangBUS;
import DAO.ChiTietPhieuNhapDAO;
import DTO.KhachHangDTO;
import DAO.KhachHangDAO;
import DTO.GiamGiaDTO;
import GUI.Panel.TaoPhieuNhap;
import GUI.Panel.TaoPhieuXuatt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class ChonGiamGia extends javax.swing.JPanel implements MouseListener, ActionListener {

    DefaultTableModel model;
    ArrayList<GiamGiaDTO> listGiamGia;
    GiamGiaBUS giamGiaBUS;
    TaoPhieuNhap taoPhieuNhap;
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    private TaoPhieuXuatt taoPhieuXuat;
    private int maGiamGia = -1;
    private long tongtiengoc;

    /**
     * Creates new form ChonKhachHang
     */
    public ChonGiamGia() throws SQLException {
        this(0);
    }

    public ChonGiamGia(long tongtiengoc) throws SQLException {
        initComponents();
        giamGiaBUS = new GiamGiaBUS();
        taoPhieuNhap = new TaoPhieuNhap();
        this.tongtiengoc = tongtiengoc;
        listGiamGia = giamGiaBUS.getGiamGiaTuNgayHienTai();

        tblgiamgia.setDefaultEditor(Object.class, null);
        tblgiamgia.setFocusable(false);

        btnchongiamgia.addActionListener(this);

        LoadDataTable(listGiamGia);
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        btnchongiamgia = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblgiamgia = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Tìm kiếm");

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        btnchongiamgia.setText("Chọn giảm giá");
        btnchongiamgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchongiamgiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnchongiamgia, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnchongiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1.add(pnlTop, java.awt.BorderLayout.NORTH);

        tblgiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã giảm giá", "Tên giảm giá", "Phần trăm giảm", "Giá trị hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tblgiamgia);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel1.add(pnlBottom, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void btnchongiamgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchongiamgiaActionPerformed

    }//GEN-LAST:event_btnchongiamgiaActionPerformed

    public int themGiamGia() throws SQLException {
        GiamGiaDTO selectedGiamGia = SelectGiamGia();

        if (selectedGiamGia != null) {
            maGiamGia = selectedGiamGia.getMagiamgia();
            System.out.println("vừa chọn " + maGiamGia);
            // Đóng dialog chứa panel
            Window window = SwingUtilities.getWindowAncestor(this); // Lấy ra cửa sổ chứa panel
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                dialog.dispose(); // Đóng dialog
            }
        } else {
            JOptionPane.showMessageDialog(pnlBottom, "Không có mã giảm giá nào được chọn.");
            System.out.println("Không có mã giảm giá nào được chọn.");
        }
        System.out.println("đã chọn " + maGiamGia);
        return maGiamGia;

    }

//HÀM LẤY RA GIẢM GIÁ KHI CLICK VÀO KHÁCH HÀNG TRONG BẢNG 
    public GiamGiaDTO SelectGiamGia() throws SQLException {
        int SelectedRow = tblgiamgia.getSelectedRow();
        GiamGiaDTO result = null;
        if (SelectedRow != -1) {
            int maggColumnIndex = taoPhieuNhap.getColumnIndexByName("Mã giảm giá", tblgiamgia);
            int magiamgia = (int) tblgiamgia.getValueAt(SelectedRow, maggColumnIndex);
            result = giamGiaBUS.SelectGiamGiabyID(magiamgia);
        }
        return result;
    }

    //HÀM HIỆN GIẢM GIÁ LÊN BẢNG
    private void LoadDataTable(ArrayList<GiamGiaDTO> listGiamGia) {
        model = (DefaultTableModel) tblgiamgia.getModel();
        model.setRowCount(0);

        Set<Integer> invalidRows = new HashSet<>();

        // Duyệt qua danh sách giảm giá và kiểm tra tính hợp lệ
        for (int stt = 0; stt < listGiamGia.size(); stt++) {
            GiamGiaDTO giamgia = listGiamGia.get(stt);
            boolean isValid = isValidDiscount(giamgia);

            Object[] rowData = {
                stt + 1, // Thêm số thứ tự
                giamgia.getMagiamgia(),
                giamgia.getTengiamgia(),
                giamgia.getPhantramgiam(),
                giamgia.getGiatrihoadon(),
                giamgia.getNgaybatdau(),
                giamgia.getNgayketthuc(),
                giamgia.getTrangthai()
            };
            model.addRow(rowData);

            // Lưu chỉ số dòng không hợp lệ
            if (!isValid) {
                invalidRows.add(stt);
            }
        }

        // Cập nhật renderer cho bảng
        setTableRenderer(invalidRows);

        // Cập nhật chế độ chọn dòng
        setRowSelectionModel(invalidRows);
    }

    // Phương thức kiểm tra tính hợp lệ của giảm giá
    private boolean isValidDiscount(GiamGiaDTO giamgia) {
        return giamgia.getGiatrihoadon() <= tongtiengoc;
    }

    // Cập nhật renderer cho bảng
    private void setTableRenderer(Set<Integer> invalidRows) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (invalidRows.contains(row)) {
                    c.setBackground(new Color(245, 245, 245)); // Làm mờ nền
                } else {
                    c.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE); // Hiệu ứng khi chọn
                }
                return c;
            }
        };

        for (int i = 0; i < tblgiamgia.getColumnCount(); i++) {
            tblgiamgia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // Cập nhật chế độ chọn dòng
    private void setRowSelectionModel(Set<Integer> invalidRows) {
        tblgiamgia.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (invalidRows.contains(index0)) {
                    clearSelection(); // Bỏ qua dòng không hợp lệ
                } else {
                    super.setSelectionInterval(index0, index1); // Cho phép chọn dòng hợp lệ
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnchongiamgia) {
            try {
                // Chỉ khi nhấn vào nút "Chọn giảm giá" mới gọi themGiamGia()
                maGiamGia = themGiamGia();
//                if (maGiamGia != -1) {
//                    // Thực hiện các thao tác khác nếu cần sau khi nhận mã giảm giá
//                    System.out.println("Mã giảm giá đã chọn: " + maGiamGia);
//                }
            } catch (SQLException ex) {
                Logger.getLogger(ChonGiamGia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchongiamgia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblgiamgia;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}