package com.jing.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCutils {
    final static String userName = "root";
    final static String password = "root1234";
    final static String dbName = "nurse_housing";
    static Connection connection;

    public JDBCutils() {

    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + password);
            }
        } catch (Exception e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
}
