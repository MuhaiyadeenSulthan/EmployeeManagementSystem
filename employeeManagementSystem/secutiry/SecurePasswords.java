/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.secutiry;

import com.employeeManagementSystem.db_connection.DBConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mydeen
 */
public class SecurePasswords {

    public static void main(String[] args) {
        Connection connection = null;

        connection = DBConnection.getConnection(); // Establish connection to the DB

        try {
            // Step 1: Get all users and their passwords from the Admin table
            String query = "SELECT UserID, Username, Password FROM Admin";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String plainPassword = rs.getString("Password");

                // Step 2: Hash the plain password using SHA-256
                if (plainPassword.length() != 64) {
                    String hashedPassword = SecurePasswords.hashPassword(plainPassword);
                    // Step 3: Update the password with the hashed password
                    String updateQuery = "UPDATE Admin SET Password = ? WHERE UserID = ?";
                    PreparedStatement updatePs = connection.prepareStatement(updateQuery);
                    updatePs.setString(1, hashedPassword);
                    updatePs.setInt(2, userId);
                    updatePs.executeUpdate();
                    updatePs.close(); // Close the update PreparedStatement
                }
            }

            // Step 4: Close resources
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Hashing method using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
