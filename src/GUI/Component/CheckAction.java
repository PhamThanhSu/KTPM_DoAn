package GUI.Component;

import BUS.NhomQuyenBUS;
import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JToolBar;

public final class CheckAction extends JToolBar {

    private final NhomQuyenBUS nhomquyenBus = new NhomQuyenBUS();
    private Map<String, JButton> buttons = new HashMap<>();

    // Constructor nhận đối tượng Map chứa các nút
     public CheckAction(int manhomquyen, String chucnang, String[] listBtn, Map<String, JButton> buttons) throws SQLException {
        this.setBackground(Color.WHITE);
        this.setRollover(true);

        // Ánh xạ giữa hành động và các nút tương ứng
        Map<String, String[]> actionToButtonsMap = new HashMap<>();
        actionToButtonsMap.put("view", new String[]{"detail", "export"});  // Hành động view bật các nút Chi tiết và Xuất Excel
        actionToButtonsMap.put("create", new String[]{"create", "import"}); // Hành động create bật các nút Thêm và Nhập Excel
        actionToButtonsMap.put("update", new String[]{"update"});           // Hành động sửa chỉ bật nút Sửa
        actionToButtonsMap.put("delete", new String[]{"delete"});           // Hành động xóa chỉ bật nút Xóa

        // Duyệt qua từng hành động và kiểm tra quyền
        for (String action : listBtn) {
            String[] relatedButtons = actionToButtonsMap.get(action); // Lấy danh sách nút tương ứng với hành động
            if (relatedButtons != null) {
                for (String btnName : relatedButtons) {
                    JButton button = buttons.get(btnName); // Lấy nút từ Map
                    if (button != null) {
                        if (nhomquyenBus.checkPermisson(manhomquyen, chucnang, action)) {
                            button.setEnabled(true); // Kích hoạt nút nếu có quyền
                        } else {
                            button.setEnabled(false); // Vô hiệu hóa nút nếu không có quyền
                        }
                    }
                }
            }
        }
    }
}
