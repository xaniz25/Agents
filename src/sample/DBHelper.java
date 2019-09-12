//Created by Shanice Talan on September 11, 2019
//CMPP 264 Java - Day 6 Assignment: JavaFX/SceneBuilder Application that modifies Agent table
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//connects to travelexperts database
public class DBHelper {
    public Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties userInfo = new Properties();
            userInfo.put("user", "root");
            userInfo.put("password", "");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", userInfo);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
