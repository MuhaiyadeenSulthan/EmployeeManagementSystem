/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mydeen
 */
public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish connection
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManagementSystem;encrypt=true;TrustServerCertificate=true";
            String user = "mydeen"; // My username
            String password = "rishana"; // My password
            conn = DriverManager.getConnection(dbURL, user, password); 
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Driver not found
        } catch (SQLException e) {
            e.printStackTrace(); // Connection error
        }
        return conn;
    }
}
