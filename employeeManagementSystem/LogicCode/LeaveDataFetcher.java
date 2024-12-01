/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.LogicCode;

import com.employeeManagementSystem.db_connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mydeen
 */
public class LeaveDataFetcher {
    public static void fetchLeaveData(JTable leaveTable) {
        String query = "SELECT Sno, Username, Leave_Type, From_Date, To_Date, Applied_Date, Status FROM Leave_Tbl";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
            model.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                Object[] row = new Object[7];  // Adjust size to match the number of columns

                row[0] = rs.getInt("Sno");
                row[1] = rs.getString("Username");
                row[2] = rs.getString("Leave_Type");
                row[3] = rs.getDate("From_Date");
                row[4] = rs.getDate("To_Date");
                row[5] = rs.getDate("Applied_Date");
                row[6] = rs.getString("Status");

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
