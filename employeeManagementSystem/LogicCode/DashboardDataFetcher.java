package com.employeeManagementSystem.LogicCode;

import com.employeeManagementSystem.db_connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDataFetcher {
    // Fetch total employees
    public int fetchTotalEmployees() {
        int totalEmployees = 0;
        String storedProcedure = "{CALL GetTotalEmployees()}";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(storedProcedure);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalEmployees = rs.getInt("TotalEmployees"); // Assuming the column name is TotalEmployees
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalEmployees;
    }

    // Fetch total departments
    public int fetchTotalDepartments() {
        int totalDepartments = 0;
        String storedProcedure = "{CALL GetTotalDepartments()}";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(storedProcedure);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalDepartments = rs.getInt("TotalDepartments"); // Assuming the column name is TotalDepartments
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalDepartments;
    }

    // Fetch monthly pay
    public double fetchMonthlyPay() {
        double monthlyPay = 0.0;
        String storedProcedure = "{CALL GetMonthlyPay()}";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(storedProcedure);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                monthlyPay = rs.getDouble("MonthlyPay"); // Assuming the column name is MonthlyPay
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return monthlyPay;
    }
    
    public int fetchTotalLeavesApplied() {
        int totalLeavesApplied = 0;
        String sql = "SELECT COUNT(*) AS TotalLeavesApplied FROM Leave_Tbl";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalLeavesApplied = rs.getInt("TotalLeavesApplied");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalLeavesApplied;
    }
    
    public int fetchTotalLeavesApproved() {
        int totalLeavesApproved = 0;
        String sql = "SELECT COUNT(*) AS TotalLeavesApproved FROM Leave_Tbl WHERE Status = 'Approved'";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalLeavesApproved = rs.getInt("TotalLeavesApproved");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalLeavesApproved;
    }
    
    public int fetchTotalLeavesRejected() {
        int totalLeavesRejected = 0;
        String sql = "SELECT COUNT(*) AS TotalLeavesRejected FROM Leave_Tbl WHERE Status = 'Rejected'";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalLeavesRejected = rs.getInt("TotalLeavesRejected");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalLeavesRejected;
    }
    
    public int fetchTotalLeavesPending() {
        int totalLeavesPending = 0;
        String sql = "SELECT COUNT(*) AS TotalLeavesPending FROM Leave_Tbl WHERE Status = 'Pending'";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                totalLeavesPending = rs.getInt("TotalLeavesPending");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        
        return totalLeavesPending;
    }
}