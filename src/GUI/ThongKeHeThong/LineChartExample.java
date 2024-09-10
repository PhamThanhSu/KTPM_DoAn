package GUI.ThongKeHeThong;


import GUI.*;
import BUS.ThongKeBUS;
import DTO.ThongKeTrongThangDTO;
import config.MySQLConnection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class LineChartExample extends JPanel {

    public LineChartExample() {
        this.setLayout(new BorderLayout());
        // Create dataset
        DefaultCategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Doanh thu và Chi phí trong 7 ngày gần nhất", // Chart title
                "Ngày", // X-Axis Label
                "Số tiền", // Y-Axis Label
                dataset
        );

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        this.add(panel,BorderLayout.CENTER);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ThongKeBUS thongKeBUS = new ThongKeBUS();
        // Get data from database
        ArrayList<ThongKeTrongThangDTO> thongKeData = thongKeBUS.getThongKeDoanhThu7NgayGanNhat();

        // Add data to dataset
        for (ThongKeTrongThangDTO item : thongKeData) {
            dataset.addValue(item.getDoanhthu(), "Doanh thu", item.getNgay());
            dataset.addValue(item.getChiphi(), "Chi phí", item.getNgay());
            dataset.addValue(item.getLoinhuan(), "Lợi nhuận", item.getNgay()); 
        }

        return dataset;
    }
}
