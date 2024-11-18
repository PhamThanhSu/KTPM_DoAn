/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonCustom extends JButton {
    public ButtonCustom(String text, String type, int fontSize) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, fontSize));
        setForeground(Color.WHITE);
        setBackground(type.equals("success") ? new Color(0, 150, 136) : type.equals("info") ? new Color (13, 202, 240) : new Color(244, 67, 54));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
