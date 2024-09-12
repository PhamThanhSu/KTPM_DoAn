import javax.swing.*;
import java.awt.*;

public class ButtonCustom extends JButton {
    public ButtonCustom(String text, String type, int fontSize) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, fontSize));
        setForeground(Color.WHITE);
        setBackground(type.equals("success") ? new Color(0, 150, 136) : new Color(244, 67, 54));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
