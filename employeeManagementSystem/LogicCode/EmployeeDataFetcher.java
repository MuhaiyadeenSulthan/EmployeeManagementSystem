/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.LogicCode;

import com.employeeManagementSystem.db_connection.DBConnection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mydeen
 */
public class EmployeeDataFetcher {
    public static void fetchEmployeeData(JTable employeeTable) {
        String query = "SELECT EmployeeID, EmployeeImage, Name, DateOfBirth, Department FROM Employee_Tbl";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                Object[] row = new Object[5];  // Adjust size to match the number of columns
                
                // Load specific fields
                row[0] = rs.getInt("EmployeeID");
                
                // Fetch and convert EmployeeImage to ImageIcon
                byte[] imgBytes = rs.getBytes("EmployeeImage");
                if (imgBytes != null) {
                    try {
                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH));
                        row[1] = icon;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    row[1] = null;  // No image found
                }

                row[2] = rs.getString("Name");
                row[3] = rs.getDate("DateOfBirth");
                row[4] = rs.getString("Department");

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
