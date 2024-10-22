package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    //Lưu ý: tạo file db.properties (tạo ở ngoài cùng) (đuôi mở rộng là properties, thể tạo txt xong đổi lại thành properties)
    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("db.properties")) {
            properties.load(input);
            URL = properties.getProperty("url");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thiết lập và trả về kết nối
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Đăng ký Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Phương thức để đóng kết nối
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
