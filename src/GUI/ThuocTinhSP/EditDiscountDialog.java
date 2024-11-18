package GUI.ThuocTinhSP;

import DTO.GiamGiaDTO;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class EditDiscountDialog extends JDialog {
    private JTextField txtMaGiamGia, txtTenGiamGia, txtPhantramgiamgia, txtHoaDonToiThieu;
    private JDateChooser ngaybatdau, ngayketthuc;
    private JComboBox<String> comboBoxTrangThai;  // ComboBox trạng thái
    private JButton btnSave;

    public EditDiscountDialog(GiamGiaDTO giamGiaDTO) {
        initComponents();

        // Điền thông tin hiện tại vào các trường
        txtMaGiamGia.setText(String.valueOf(giamGiaDTO.getMagiamgia()));
        txtTenGiamGia.setText(giamGiaDTO.getTengiamgia());
        txtPhantramgiamgia.setText(String.valueOf(giamGiaDTO.getPhantramgiam()));
        txtHoaDonToiThieu.setText(String.valueOf(giamGiaDTO.getGiatrihoadon()));
        ngaybatdau.setDate(giamGiaDTO.getNgaybatdau());
        ngayketthuc.setDate(giamGiaDTO.getNgayketthuc());

        // Chọn trạng thái hiện tại trong comboBox
        comboBoxTrangThai.setSelectedItem(giamGiaDTO.getTrangthai());
    }

    private void initComponents() {
        // Tạo các trường và nút lưu
        txtMaGiamGia = new JTextField(20);
        txtTenGiamGia = new JTextField(20);
        txtPhantramgiamgia = new JTextField(20);
        txtHoaDonToiThieu = new JTextField(20);
        ngaybatdau = new JDateChooser();
        ngayketthuc = new JDateChooser();
        
        // Tạo ComboBox với 2 lựa chọn trạng thái
        comboBoxTrangThai = new JComboBox<>(new String[]{"Có hiệu lực", "Hết hiệu lực"});
        
        btnSave = new JButton("Lưu");

        // Thiết lập layout cho dialog
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Mã giảm giá"));
        panel.add(txtMaGiamGia);
        panel.add(new JLabel("Tên giảm giá"));
        panel.add(txtTenGiamGia);
        panel.add(new JLabel("Phần trăm giảm"));
        panel.add(txtPhantramgiamgia);
        panel.add(new JLabel("Hóa đơn tối thiểu"));
        panel.add(txtHoaDonToiThieu);
        panel.add(new JLabel("Ngày bắt đầu"));
        panel.add(ngaybatdau);
        panel.add(new JLabel("Ngày kết thúc"));
        panel.add(ngayketthuc);
        panel.add(new JLabel("Trạng thái"));
        panel.add(comboBoxTrangThai);  // Thêm ComboBox vào dialog
        panel.add(btnSave);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        
        btnSave.addActionListener(e -> saveDiscount());
    }
    
    private void saveDiscount() {
        // Lấy thông tin từ các trường
        int maGiamGia = Integer.parseInt(txtMaGiamGia.getText());
        String tenGiamGia = txtTenGiamGia.getText();
        int phanTramGiamGia = Integer.parseInt(txtPhantramgiamgia.getText());
        int hoaDonToiThieu = Integer.parseInt(txtHoaDonToiThieu.getText());
        java.util.Date ngayBatDau = ngaybatdau.getDate();
        java.util.Date ngayKetThuc = ngayketthuc.getDate();
        String trangThai = (String) comboBoxTrangThai.getSelectedItem();  // Lấy giá trị trạng thái

        // Cập nhật đối tượng GiamGiaDTO và cơ sở dữ liệu
        GiamGiaDTO updatedGiamGiaDTO = new GiamGiaDTO(maGiamGia, tenGiamGia, phanTramGiamGia, hoaDonToiThieu, ngayBatDau, ngayKetThuc, trangThai);
        // Cập nhật vào cơ sở dữ liệu hoặc bất kỳ nơi lưu trữ dữ liệu nào
        
        JOptionPane.showMessageDialog(this, "Cập nhật giảm giá thành công!");
        this.dispose();
    }
}
