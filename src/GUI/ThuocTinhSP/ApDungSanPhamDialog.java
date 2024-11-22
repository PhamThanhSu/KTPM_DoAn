package GUI.ThuocTinhSP;

import BUS.GiamGiaBUS;
import BUS.NhomQuyenBUS;
import DAO.NhomQuyenDAO;
import DAO.QuyenChucNangDAO;
import DAO.ChiTietQuyenDAO;
import DAO.SanPhamDAO; // DAO class to fetch product data from the database
import DTO.ChiTietQuyenDTO;
import DTO.GiamGiaSanPhamDTO;
import DTO.NhomQuyenDTO;
import DTO.QuyenChucNangDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ApDungSanPhamDialog extends JDialog implements ActionListener {

    private ArrayList<GiamGiaSanPhamDTO> selectedProducts = new ArrayList<>();
    private GiamGiaBUS giamGiaBUS;
    private GiamGia giamGia;
    TaiKhoanDTO taiKhoanDTO;
    private JTable tblchonsanphamapdung;
    private JButton btnApDung, btnHuybo;
    private JTextField txtTennhomquyen;
    private GiamGiaSanPhamDTO giamGiaSanPhamDTO;
    private boolean isUpdate;

    // Thêm phương thức setListSanPhamDaChon để nhận danh sách sản phẩm đã chọn từ bên ngoài
    public void setListSanPhamDaChon(ArrayList<GiamGiaSanPhamDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    // Phương thức getListSanPhamDaChon để trả về danh sách sản phẩm đã chọn
    public ArrayList<GiamGiaSanPhamDTO> getListSanPhamDaChon() {
        return selectedProducts;
    }

    public ApDungSanPhamDialog(GiamGia giamGia) {
        this.giamGia = giamGia;
    }

    public ApDungSanPhamDialog(ArrayList<GiamGiaSanPhamDTO> selectedProducts) {
        //this.setModal(true);
        this.selectedProducts = selectedProducts != null ? selectedProducts : new ArrayList<>();
        initComponents();
        loadProductData(this.selectedProducts); // Nạp dữ liệu sản phẩm với trạng thái checkbox
    }

    private void initComponents() {
        this.setSize(new Dimension(1000, 580));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        JPanel jpTop = new JPanel(new BorderLayout(20, 10));
        jpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpTop.setBackground(Color.WHITE);

        JLabel lblTennhomquyen = new JLabel("Chọn sản phẩm áp dụng cho phiếu giảm giá");
        JButton tbnchontatcasp = new ButtonCustom("Chọn tất cả", "info", 14);
        jpTop.add(lblTennhomquyen, BorderLayout.WEST);
        jpTop.add(tbnchontatcasp, BorderLayout.EAST);
        // Nút thêm sản phẩm vào list để áp dụng phiếu giảm giá
        btnApDung = new ButtonCustom("Áp dụng", "success", 14);
        btnApDung.addActionListener(this);
        btnHuybo = new ButtonCustom("Hủy bỏ", "danger", 14);
        btnHuybo.addActionListener(this);

        JPanel jpBottom = new JPanel(new FlowLayout());
        jpBottom.add(btnApDung);
        jpBottom.add(btnHuybo);

        String[] columnNames = {
            "Chọn", "Mã sản phẩm", "Tên sản phẩm", "Size", "Số lượng tồn", "Loại", "Thương hiệu", "Xuất xứ", "Khu vực kho"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        tblchonsanphamapdung = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblchonsanphamapdung);
        // Set column widths
        tblchonsanphamapdung.getColumnModel().getColumn(2).setPreferredWidth(170); // Set width for "Tên sản phẩm" column

        this.add(jpTop, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        tbnchontatcasp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAllRows();
            }
        });

        this.setVisible(true);
    }

    private void selectAllRows() {
        DefaultTableModel model = (DefaultTableModel) tblchonsanphamapdung.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt(true, row, 0); // Set the first column (checkbox column) to true
        }
    }

    private void loadProductData(ArrayList<GiamGiaSanPhamDTO> selectedProducts) {
        ArrayList<SanPhamDTO> products = SanPhamDAO.getInstance().getAllSanPham();
        DefaultTableModel model = (DefaultTableModel) tblchonsanphamapdung.getModel();
        model.setRowCount(0);

        for (SanPhamDTO product : products) {
            boolean isSelected = selectedProducts.stream()
                    .anyMatch(sp -> sp.getMasp() == product.getMasp());

            model.addRow(new Object[]{
                isSelected, // Set trạng thái checkbox
                product.getMasp(),
                product.getTensp(),
                product.getSize(),
                product.getSoluongton(),
                product.getLoai(),
                product.getThuonghieu(),
                product.getXuatxu(),
                product.getKhuvuckho()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnApDung) {
            try {
                clickButtonApDungChonSanPham();
            } catch (SQLException ex) {
                Logger.getLogger(ApDungSanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        } else if (e.getSource() == btnHuybo) {
            this.dispose();
        }
    }
// Phương thức cho nút Áp dụng

    public void clickButtonApDungChonSanPham() throws SQLException {
        //ArrayList<GiamGiaSanPhamDTO> selectedProducts = new ArrayList<>();
        giamGiaBUS = new GiamGiaBUS();
        String maGiamGiaMoistr = giamGiaBUS.generateMaGiamGia().replaceAll("[GG]", "").trim(); // Lấy mã giảm giá mới
        int maGiamGiaMoi = Integer.parseInt(maGiamGiaMoistr);
        //Lưu danh sách đã chọn vào biến toàn cục
        this.selectedProducts = danhSachSanPhamApDungGiamGia(maGiamGiaMoi);
        System.out.println("selectedProducts " + selectedProducts);
        if (!selectedProducts.isEmpty()) {
            setListSanPhamDaChon(this.selectedProducts); // Gán giá trị cho listSanPhamDaChon
            System.out.println("đã setlist  " + getListSanPhamDaChon());
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm thành công, bấm nút thêm để lưu phiếu giảm giá!");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào!");
        }
        this.dispose();
    }
// Phương thức để lấy danh sách sản phẩm đã chọn

    public ArrayList<GiamGiaSanPhamDTO> danhSachSanPhamApDungGiamGia(int magiamgia) throws SQLException {
        ArrayList<GiamGiaSanPhamDTO> selectedProductsTemp = new ArrayList<>();

        // Lặp qua từng hàng trong bảng
        for (int row = 0; row < tblchonsanphamapdung.getRowCount(); row++) {
            Boolean isSelected = (Boolean) tblchonsanphamapdung.getValueAt(row, 0); // Cột 0 là checkbox
            if (isSelected != null && isSelected) {
                int maSP = (Integer) tblchonsanphamapdung.getValueAt(row, 1); // Mã sản phẩm
                GiamGiaSanPhamDTO product = new GiamGiaSanPhamDTO(magiamgia, maSP);
                selectedProductsTemp.add(product);
            }
        }
        return selectedProductsTemp;
    }

}
