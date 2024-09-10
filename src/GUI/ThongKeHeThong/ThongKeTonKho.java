package GUI.ThongKeHeThong;

import BUS.ThongKeBUS;
import DTO.ThongKeTonKhoDTO;
import GUI.Component.Formater;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author this pc
 */
public class ThongKeTonKho extends javax.swing.JPanel implements ActionListener, KeyListener, PropertyChangeListener{
    ThongKeBUS thongKeBUS = new ThongKeBUS();
    ArrayList<ThongKeTonKhoDTO> listTonKho; 
    Formater formater = new Formater();
    
    public ThongKeTonKho() {
        initComponents();
        listTonKho = thongKeBUS.getThongKeTonKho();
        txttimkiem.putClientProperty("JTextField.showClearButton", true);
        txttimkiem.addKeyListener(this);
        datefrom.addPropertyChangeListener(this);
        dateto.addPropertyChangeListener(this);
        loadDataTable(listTonKho);
        btnXuatExcel.setBackground(Color.green);
    }
    
    private void loadDataTable(ArrayList<ThongKeTonKhoDTO> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tblkho.getModel();
        tableModel.setRowCount(0); // Xóa tất cả các dòng trong bảng trước khi tải dữ liệu mới
    int i = 1;
    for (ThongKeTonKhoDTO dto : list) {
       
        Object[] rowData = new Object[]{
            i++,
            dto.getMasp(), 
            dto.getTensanpham(), 
            dto.getSize(),
            dto.getNhaptrongky(),
            dto.getXuattrongky(), 
            dto.getToncuoiky(),
        };
        tableModel.addRow(rowData); // Thêm dòng dữ liệu vào bảng
    }
    formater.setColumnAlignment(tblkho);
}

    public void filter() throws ParseException{
        if (checkDate()) {
            String input = txttimkiem.getText();
            if (input == null) {
                input = "";
            }

            Date time_start = datefrom.getDate();
            if (time_start == null) {
                time_start = new Date(0);
            }

            Date time_end = dateto.getDate();
            if (time_end == null) {
                time_end = new Date(System.currentTimeMillis());
            }

            this.listTonKho = thongKeBUS.filterTonKho(input, new Date(time_start.getTime()), new Date(time_end.getTime()));
            loadDataTable(listTonKho);
        }
    }

    public boolean checkDate() throws ParseException{
        Date time_start = datefrom.getDate();
        Date time_end = dateto.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            datefrom.setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateto.setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateto.setCalendar(null);
            return false;
        }
        return true;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkho = new javax.swing.JTable();
        pnlleft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        datefrom = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        dateto = new com.toedter.calendar.JDateChooser();
        btnXuatExcel = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        tblkho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Size", "Số lượng nhập", "Số lượng xuất", "Tồn cuối kỳ"
            }
        ));
        jScrollPane1.setViewportView(tblkho);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlleft.setBackground(new java.awt.Color(255, 255, 255));
        pnlleft.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Tìm kiếm sản phẩm");

        jLabel2.setText("Từ ngày");

        datefrom.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Đến ngày");

        dateto.setBackground(new java.awt.Color(255, 255, 255));

        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlleftLayout = new javax.swing.GroupLayout(pnlleft);
        pnlleft.setLayout(pnlleftLayout);
        pnlleftLayout.setHorizontalGroup(
            pnlleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(datefrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txttimkiem)
            .addGroup(pnlleftLayout.createSequentialGroup()
                .addGroup(pnlleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlleftLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pnlleftLayout.setVerticalGroup(
            pnlleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlleftLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(618, Short.MAX_VALUE))
        );

        jPanel1.add(pnlleft, java.awt.BorderLayout.WEST);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            filter();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            filter();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatExcel;
    private com.toedter.calendar.JDateChooser datefrom;
    private com.toedter.calendar.JDateChooser dateto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlleft;
    private javax.swing.JTable tblkho;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
