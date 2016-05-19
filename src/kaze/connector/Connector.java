package kaze.connector;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kaze on 16-5-13.
 */
public class Connector {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            System.out.println("无法加在驱动");
        }
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmall", "root", "root");
            System.out.println("数据库连接成功");
            if (!conn.isClosed()) System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
