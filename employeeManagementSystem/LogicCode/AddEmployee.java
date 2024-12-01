/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.LogicCode;

import com.employeeManagementSystem.db_connection.DBConnection;
import static com.employeeManagementSystem.secutiry.EncryptDecrypt.encrypt;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 *
 * @author Mydeen
 */
public class AddEmployee {
        public void insertEmployee(String name, int employeeID, String gender, String designation, 
                               double salary, String role, String email, String password, 
                               Date dateOfBirth, String department, String marriedStatus, 
                               FileInputStream imageStream) throws Exception {
        
        // Use DBConnection to get a database connection
        try (Connection conn = DBConnection.getConnection()) {
            // Using `EXEC` to call stored procedure with PreparedStatement
            String sql = "EXEC InsertEmployee ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Set the parameters in order
                stmt.setString(1, name);
                stmt.setInt(2, employeeID);
                stmt.setString(3, gender);
                stmt.setString(4, designation);
                stmt.setDouble(5, salary);
                stmt.setString(6, role);
                stmt.setString(7, email);
                stmt.setString(8, encrypt(password));
                stmt.setDate(9, new java.sql.Date(dateOfBirth.getTime())); // Convert to SQL Date
                stmt.setString(10, department);
                stmt.setString(11, marriedStatus);
                stmt.setBinaryStream(12, imageStream);

                // Execute the stored procedure
                stmt.executeUpdate();
                System.out.println("Employee inserted successfully using stored procedure!");
            }
        }
    }
}
