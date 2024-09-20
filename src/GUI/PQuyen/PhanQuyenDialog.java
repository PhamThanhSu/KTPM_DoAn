package GUI.PQuyen;

import BUS.NhomQuyenBUS;
import DAO.NhomQuyenDAO;
import DAO.QuyenChucNangDAO;
import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;
import DTO.QuyenChucNangDTO;
import DTO.TaiKhoanDTO;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import GUI.Component.ButtonCustom;
import GUI.PhanQuyen;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PhanQuyenDialog extends JDialog implements ActionListener {

    private PhanQuyen phanQuyen;
    TaiKhoanDTO taiKhoanDTO;
    private JLabel lblTennhomquyen;
    private JTextField txtTennhomquyen;
    private JPanel jpTop, jpLeft, jpCen, jpBottom;
    private JCheckBox[][] listCheckBox;
    private int sizeQuyencn, sizeHanhdong;
    private ArrayList<QuyenChucNangDTO> quyencn;
    private ArrayList<ChiTietQuyenDTO> ctQuyen;
    private ArrayList<ChiTietQuyenDTO> dsChiTietQuyen;
    String[] mahanhdong = {"view", "create", "update", "delete"};
    private NhomQuyenDTO nhomquyenDTO;
    private NhomQuyenBUS nhomquyenBUS;
    private NhomQuyenDAO nhomquyenDAO;
    private JButton btnAddNhomQuyen, btnHuybo;
    private boolean isUpdate;
    int index;

//    // Constructor mới nhận thêm tham số `isUpdate`
//    public PhanQuyenDialog(NhomQuyenDTO nhomQuyenDTO, ArrayList<ChiTietQuyenDTO> dsChiTietQuyen, String tenchucnang, boolean isUpdate) throws SQLException {
//        this.nhomquyenDTO = nhomQuyenDTO;
//        this.dsChiTietQuyen = dsChiTietQuyen;
//        this.isUpdate = isUpdate; // Gán giá trị cho biến
//        initComponents(tenchucnang); // Khởi tạo giao diện
//        initUpdate(); // Cập nhật giao diện với dữ liệu chi tiết quyền nếu cần
//    }
    public PhanQuyenDialog(NhomQuyenDTO nhomQuyenDTO, ArrayList<ChiTietQuyenDTO> dsChiTietQuyen, String tenchucnang, PhanQuyen phanQuyen, boolean isUpdate) throws SQLException {
        this.phanQuyen = phanQuyen;
        this.nhomquyenDTO = nhomQuyenDTO;
        this.dsChiTietQuyen = dsChiTietQuyen;
        this.isUpdate = isUpdate; // Gán giá trị cho biến
        initComponents(tenchucnang); // Khởi tạo giao diện
        initUpdate(); // Cập nhật giao diện với dữ liệu chi tiết quyền
    }

    private void setCheckboxes() {
        // Kiểm tra nếu danh sách chi tiết quyền không null
        if (dsChiTietQuyen != null) {
            for (ChiTietQuyenDTO quyen : dsChiTietQuyen) {
                // Duyệt qua danh sách các quyền chức năng và hành động để chọn checkbox phù hợp
                for (int i = 0; i < quyencn.size(); i++) {
                    for (int j = 0; j < mahanhdong.length; j++) {
                        if (quyen.getMaChucNang().equals(quyencn.get(i).getMaChucNang())
                                && quyen.getHanhDong().equals(mahanhdong[j])) {
                            listCheckBox[i][j].setSelected(true);
                        }
                    }
                }
            }
        }
    }

    public void initComponents(String tenchucnang) throws SQLException {
        quyencn = QuyenChucNangDAO.getInstance().getAllQuyenChucNang();
        ctQuyen = ChiTietQuyenDAO.getInstance().getChiTietQuyen(Integer.toString(nhomquyenDTO.getManhomquyen()));

        String[] hanhdong = {"Xem", "Tạo mới", "Cập nhật", "Xoá"};

        this.setSize(new Dimension(1000, 580));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        // Hiển thị tên nhóm quyền
        jpTop = new JPanel(new BorderLayout(20, 10));
        jpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpTop.setBackground(Color.WHITE);
        lblTennhomquyen = new JLabel("Tên nhóm quyền");
        txtTennhomquyen = new JTextField();
        txtTennhomquyen.setPreferredSize(new Dimension(150, 35));
        jpTop.add(lblTennhomquyen, BorderLayout.WEST);
        jpTop.add(txtTennhomquyen, BorderLayout.CENTER);

        // Khởi tạo jpLeft
        jpLeft = new JPanel(new GridLayout(quyencn.size() + 1, 1));
        jpLeft.setBorder(new EmptyBorder(0, 20, 0, 14));
        jpLeft.setBackground(Color.WHITE);
        JLabel dmcnl = new JLabel("Danh mục chức năng ");
        dmcnl.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 15));
        jpLeft.add(dmcnl);
        for (QuyenChucNangDTO i : quyencn) {
            JLabel lblTenchucnang = new JLabel(i.getTenChucNang());
            jpLeft.add(lblTenchucnang);
        }

        // Hiển thị chức năng CRUD        
        sizeQuyencn = quyencn.size();
        sizeHanhdong = hanhdong.length;
        jpCen = new JPanel(new GridLayout(sizeQuyencn + 1, sizeHanhdong));
        jpCen.setBackground(Color.WHITE);
        listCheckBox = new JCheckBox[sizeQuyencn][sizeHanhdong];

        for (int i = 0; i < sizeHanhdong; i++) {
            JLabel lblhanhdong = new JLabel(hanhdong[i]);
            lblhanhdong.setHorizontalAlignment(SwingConstants.CENTER);
            jpCen.add(lblhanhdong);
        }

        for (int i = 0; i < sizeQuyencn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                listCheckBox[i][j] = new JCheckBox();
                listCheckBox[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpCen.add(listCheckBox[i][j]);
            }
        }

        jpBottom = new JPanel(new FlowLayout());
        jpBottom.setBackground(Color.white);
        jpBottom.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Điều chỉnh nhãn của nút dựa trên biến `isUpdate`
        if (isUpdate) {
            btnAddNhomQuyen = new ButtonCustom("Cập nhật nhóm quyền", "success", 14);
        } else {
            btnAddNhomQuyen = new ButtonCustom("Thêm nhóm quyền", "success", 14);
        }
        btnAddNhomQuyen.addActionListener(this);
        jpBottom.add(btnAddNhomQuyen);

        btnHuybo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuybo.addActionListener(this);
        jpBottom.add(btnHuybo);

        this.add(jpTop, BorderLayout.NORTH);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpCen, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddNhomQuyen) {
            try {
                if (isUpdate) {
                    updatePermissionsToDatabase(); // Thực hiện cập nhật

                } else {
                    addNewNhomQuyen(); // Thêm mới nhóm quyền
                    JOptionPane.showMessageDialog(rootPane, "Thêm nhóm quyền thành công!");

                }
                this.dispose(); // Đóng dialog sau khi thành công
            } catch (SQLException ex) {
                Logger.getLogger(PhanQuyenDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnHuybo) {
            this.dispose(); // Đóng dialog nếu bấm "Huỷ bỏ"
        }
    }

    // Phương thức để thêm mới nhóm quyền
    private void addNewNhomQuyen() throws SQLException {
        NhomQuyenDTO newNhomQuyen = new NhomQuyenDTO();
        nhomquyenDAO = new NhomQuyenDAO();
        newNhomQuyen.setTennhomquyen(txtTennhomquyen.getText());

        // Thêm nhóm quyền mới vào database thông qua DAO
        nhomquyenDAO.themNhomQuyen(newNhomQuyen);
        // Cập nhật lại đối tượng nhomquyenDTO hiện tại thành nhóm quyền mới
        nhomquyenDTO = newNhomQuyen;
        // Lấy mã nhóm quyền vừa thêm
        int manhomquyenMoi = newNhomQuyen.getManhomquyen();

        // Sau khi thêm nhóm quyền, lưu chi tiết quyền
        updatePermissionsToDatabase();
        phanQuyen.loadData();
    }

    // Phương thức cập nhật chi tiết quyền trong database (không thay đổi)
    private void updatePermissionsToDatabase() throws SQLException {
        // Kiểm tra nếu mã nhóm quyền là 'Quản lý kho' hoặc mã 5
        if (nhomquyenDTO.getTennhomquyen().equals("Quản lý kho") || nhomquyenDTO.getManhomquyen() == 5) {
            JOptionPane.showMessageDialog(this, "Không thể cập nhật nhóm quyền này.");
            return; // Ngừng xử lý nếu không được phép cập nhật
        }

        // Tạo danh sách quyền chi tiết để lưu
        ArrayList<ChiTietQuyenDTO> updatedChiTietQuyen = new ArrayList<>();

        for (int i = 0; i < sizeQuyencn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                boolean isSelected = listCheckBox[i][j].isSelected();
                String maChucNang = quyencn.get(i).getMaChucNang();
                String hanhDong = mahanhdong[j];

                if (isSelected) {
                    ChiTietQuyenDTO chiTietQuyenDTO = new ChiTietQuyenDTO();
                    chiTietQuyenDTO.setMaNhomQuyen(nhomquyenDTO.getManhomquyen());
                    chiTietQuyenDTO.setMaChucNang(maChucNang);
                    chiTietQuyenDTO.setHanhDong(hanhDong);
                    updatedChiTietQuyen.add(chiTietQuyenDTO);
                }
            }
        }

        // Cập nhật vào database thông qua DAO
        ChiTietQuyenDAO chiTietQuyenDAO = new ChiTietQuyenDAO();

        // Xoá các quyền cũ trước khi thêm mới
        chiTietQuyenDAO.deleteAllChiTietQuyen(nhomquyenDTO.getManhomquyen());

        // Thêm danh sách quyền chi tiết mới vào database
        for (ChiTietQuyenDTO quyen : updatedChiTietQuyen) {
            chiTietQuyenDAO.addChiTietQuyen(quyen);
        }
        JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
    }

    public void initUpdate() {
        // Đặt tên nhóm quyền khi cập nhật
        if (nhomquyenDTO != null) {
            this.txtTennhomquyen.setText(nhomquyenDTO.getTennhomquyen());
        }

        // Đặt trạng thái checkbox dựa trên quyền chi tiết khi cập nhật
        if (ctQuyen != null) {
            for (ChiTietQuyenDTO k : ctQuyen) {
                for (int i = 0; i < sizeQuyencn; i++) {
                    for (int j = 0; j < sizeHanhdong; j++) {
                        if (k.getHanhDong().equals(mahanhdong[j]) && k.getMaChucNang().equals(quyencn.get(i).getMaChucNang())) {
                            listCheckBox[i][j].setSelected(true);
                        }
                    }
                }
            }
        }
    }
}
