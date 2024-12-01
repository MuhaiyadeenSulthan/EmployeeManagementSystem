/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.employeeManagementSystem.MainAdminPage;

import com.employeeManagementSystem.LogicCode.DashboardDataFetcher;
import com.employeeManagementSystem.TableActionForDisplayEmployee.TableActionCellRender;
import com.employeeManagementSystem.TableActionForDisplayEmployee.TableActionCellEditor;
import static com.employeeManagementSystem.LogicCode.EmployeeDataFetcher.fetchEmployeeData;
import com.employeeManagementSystem.LogicCode.AddEmployee;
import com.employeeManagementSystem.LogicCode.LeaveDataFetcher;
import com.employeeManagementSystem.TableActionForDisplayEmployee.ImageRender;
import com.employeeManagementSystem.TableActionForDisplayEmployee.TableActionEvent;
import com.employeeManagementSystem.app.LoginPage;
import com.employeeManagementSystem.db_connection.DBConnection;
import static com.employeeManagementSystem.secutiry.EncryptDecrypt.encrypt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author Mydeen
 */

public class DashboardPage extends javax.swing.JFrame {

    /**
     * Creates new form MainAdminPage
     */
    
    private File employeeImageFile; //Instance Variable for image chooser.
    private DefaultTableModel model;
    private String username;
    
    public DashboardPage() {
        initComponents();
    }
    
    public DashboardPage(String username) {
    initComponents();  // This initializes your UI components
    lbl_welcomeUser.setText("Welcome, " + username);  // Set the username on the label
    this.username = username;
    populateDashboardData();
    fetchEmployeeData(table);
    LeaveDataFetcher.fetchLeaveData(tbl_manageLeave);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                int EmployeeID = (int) table.getValueAt(row, 0);
                displayEmployeeDetails(EmployeeID);
                jTabbedPane2.setSelectedIndex(3);
            }
            
            @Override
            public void onEdit(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                // Retrieve the Employee ID for the selected row
                int EmployeeID = (int) table.getValueAt(row, 0);

                // Call method to populate employee details in the Edit Employee form
                populateEmployeeDetails(EmployeeID);

                // Switch to Edit Employee tab
                jTabbedPane2.setSelectedIndex(2);
            }

            @Override
            public void onLeave(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                jTabbedPane2.setSelectedIndex(4); // Switch to the Manage Leave tab

                // Adjust the column index to retrieve a non-ImageIcon value for the username
                Object usernameObj = table.getValueAt(row, 2);
                String selectedUsername;

                if (usernameObj instanceof String) {
                    selectedUsername = (String) usernameObj;
                } else {
                    System.out.println("Error: Expected a String but found " + usernameObj.getClass().getName());
                    return;
                }

                System.out.println("Selected username: " + selectedUsername);
                loadLeaveData(selectedUsername); // Load leave data for the selected username
            }


            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                // Show a confirmation dialog
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this employee?",
                        "Delete Confirmation",
                        javax.swing.JOptionPane.YES_NO_OPTION
                );

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    int EmployeeID = (int) table.getValueAt(row, 0);

                    // Call method to delete the employee from the database
                    boolean isDeleted = deleteEmployee(EmployeeID);

                    if (isDeleted) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
                        // Remove the row from the table model
                        ((javax.swing.table.DefaultTableModel) table.getModel()).removeRow(row);
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Failed to delete employee.");
                    }
                }
            }
        };
        
        
    table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
    table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    table.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
}

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqlDateModel1 = new org.jdatepicker.impl.SqlDateModel();
        sqlDateModel2 = new org.jdatepicker.impl.SqlDateModel();
        utilCalendarModel1 = new org.jdatepicker.impl.UtilCalendarModel();
        utilDateModel1 = new org.jdatepicker.impl.UtilDateModel();
        jPanel30 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_welcomeUser = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        panel_dash = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_totalEmployees = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_totalDepartments = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbl_monthlyPay = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbl_leaveApplied = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbl_leaveApproved = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbl_leavePending = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbl_leaveRejected = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jpanel_manageEmployee = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        btn_addNewEmployee = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        txt_employeeID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        date_dob = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        cmb_gender = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        cmb_maritalStatus = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cmb_designation = new javax.swing.JComboBox<>();
        cmb_department = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_salary = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        pwd_password = new javax.swing.JPasswordField();
        jLabel43 = new javax.swing.JLabel();
        cmb_role = new javax.swing.JComboBox<>();
        btn_cancel = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        lbl_employeeImage = new javax.swing.JLabel();
        btn_uploadImage = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_name1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txt_email1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        date_dob1 = new com.toedter.calendar.JDateChooser();
        jLabel57 = new javax.swing.JLabel();
        cb_gender1 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        cb_marriedStatus1 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        cb_designation1 = new javax.swing.JComboBox<>();
        cb_department1 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txt_salary1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        pwd_password1 = new javax.swing.JPasswordField();
        jLabel63 = new javax.swing.JLabel();
        cb_role1 = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        btn_back1 = new javax.swing.JButton();
        btn_updateEditModule = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        btn_uploadImage1 = new javax.swing.JButton();
        lbl_profilePicture1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lbl_fetchname = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lbl_fetchEmpID = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lbl_fetchDOB = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lbl_fetchGender = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lbl_fetchDepartment = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        lbl_fetchMaritalStatus = new javax.swing.JLabel();
        lbl_profilePicture = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_leave = new javax.swing.JTable();
        btn_leaveBack = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        tab4 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        pendingButton = new javax.swing.JButton();
        approvedButton = new javax.swing.JButton();
        rejectedButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_manageLeave = new javax.swing.JTable();
        clearFilterButton = new javax.swing.JButton();
        LeavePanel = new javax.swing.JPanel();
        UpdateLeavePanel = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        leaveTypeLabel = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        startDateLabel = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        btn_back2 = new javax.swing.JButton();
        approveButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        tab5 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        employeeNameComboBox = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        basicSalaryField = new javax.swing.JTextField();
        monthlyAllowanceField = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        deductionField = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        payDateField = new com.toedter.calendar.JDateChooser();
        addSalaryButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        tab6 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        pwd_conpass = new javax.swing.JPasswordField();
        btn_clear = new javax.swing.JButton();
        btn_submit = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtCurrentUsername = new javax.swing.JTextField(username);
        jLabel80 = new javax.swing.JLabel();
        pwd_newpass = new javax.swing.JPasswordField();
        lbl_message = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        panel_dashboard = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_employees = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel_leaves = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panel_salary = new javax.swing.JPanel();
        icon_salary = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panel_settings = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(150, 100, 0, 0));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Pristina", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee Management System");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lbl_welcomeUser.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        lbl_welcomeUser.setForeground(new java.awt.Color(255, 255, 255));
        lbl_welcomeUser.setText("Welcome,");
        jPanel2.add(lbl_welcomeUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 280, 40));

        btn_logout.setBackground(new java.awt.Color(0, 204, 204));
        btn_logout.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_logout.setText("LOGOUT");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        jPanel2.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 70));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 0));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Dashboard Overview");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(0, 153, 153));

        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\employees.png")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel20.setText("Total Employees");

        lbl_totalEmployees.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_totalEmployees.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_totalEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_totalEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(0, 153, 153));

        jLabel41.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\department.png")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel23.setText("Total Departments");

        lbl_totalDepartments.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_totalDepartments.setText("0");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_totalDepartments, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_totalDepartments, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(0, 153, 153));

        jLabel25.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\salary.png")); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel26.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel26.setText("Monthly Pay");

        lbl_monthlyPay.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_monthlyPay.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(lbl_monthlyPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_monthlyPay, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 153, 153));
        jLabel28.setText("Leave Details");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(0, 153, 153));

        jLabel29.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\document.png")); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel30.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel30.setText("Leave Applied");

        lbl_leaveApplied.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_leaveApplied.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_leaveApplied, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 118, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_leaveApplied, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(0, 153, 153));

        jLabel32.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\correct.png")); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel33.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel33.setText("Leave Approved");

        lbl_leaveApproved.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_leaveApproved.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_leaveApproved, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 120, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_leaveApproved, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jPanel21.setBackground(new java.awt.Color(0, 153, 153));

        jLabel38.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\pending.png")); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel39.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel39.setText("Leave Pending");

        lbl_leavePending.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_leavePending.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_leavePending, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 118, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_leavePending, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(0, 153, 153));

        jLabel35.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\wrong.png")); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel36.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel36.setText("Leave Rejected");

        lbl_leaveRejected.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_leaveRejected.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_leaveRejected, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_leaveRejected, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_dashLayout = new javax.swing.GroupLayout(panel_dash);
        panel_dash.setLayout(panel_dashLayout);
        panel_dashLayout.setHorizontalGroup(
            panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_dashLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_dashLayout.createSequentialGroup()
                        .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_dashLayout.createSequentialGroup()
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_dashLayout.createSequentialGroup()
                                    .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(171, 171, 171)
                                    .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(82, Short.MAX_VALUE))))
        );
        panel_dashLayout.setVerticalGroup(
            panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(panel_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_dashLayout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(78, Short.MAX_VALUE))
                    .addGroup(panel_dashLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_dash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                .addGap(0, 65, Short.MAX_VALUE)
                .addComponent(panel_dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", tab1);

        tab2.setBackground(new java.awt.Color(255, 255, 0));

        jTabbedPane2.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 204, 0));
        jLabel21.setText("Manage Employees");

        btn_addNewEmployee.setBackground(new java.awt.Color(0, 153, 153));
        btn_addNewEmployee.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btn_addNewEmployee.setForeground(new java.awt.Color(255, 255, 255));
        btn_addNewEmployee.setText("Add New Employee");
        btn_addNewEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addNewEmployeeActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Image", "Name", "DOB", "Department", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setRowHeight(45);
        table.setSelectionBackground(new java.awt.Color(0, 153, 153));
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jpanel_manageEmployeeLayout = new javax.swing.GroupLayout(jpanel_manageEmployee);
        jpanel_manageEmployee.setLayout(jpanel_manageEmployeeLayout);
        jpanel_manageEmployeeLayout.setHorizontalGroup(
            jpanel_manageEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_manageEmployeeLayout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(jpanel_manageEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_addNewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jpanel_manageEmployeeLayout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel_manageEmployeeLayout.setVerticalGroup(
            jpanel_manageEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_manageEmployeeLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_addNewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanel_manageEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jpanel_manageEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Manage Employees", jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(1012, 648));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(916, 548));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Add New Employee");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Name");

        txt_name.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("Email");

        txt_email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txt_employeeID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("Employee ID");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 153));
        jLabel24.setText("Date of Birth");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 153));
        jLabel27.setText("Gender");

        cmb_gender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Gender", "Male", "Female", "Others" }));
        cmb_gender.setToolTipText("");

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 153));
        jLabel31.setText("Marital Status");

        cmb_maritalStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmb_maritalStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Marital status", "Married", "Single" }));
        cmb_maritalStatus.setToolTipText("");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 153));
        jLabel34.setText("Designation");

        cmb_designation.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmb_designation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Designation", "Software Engineer", "Team Lead", "Project Manager", "Business Analyst", "DevOps Engineer", "UI/UX Designer", "Full Stack Developer", "Data Analyst", "Database Administrator", "Human Resources Manager", "Network Engineer", "Cloud Architect" }));
        cmb_designation.setToolTipText("");

        cmb_department.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmb_department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Department", "Human Resources (HR)", "IT (Information Technology)", "Engineering", "Data Analytics", "Business Development", "Design", "Project Management", "Customer Support", "Quality Assurance (QA)", "Training and Development" }));
        cmb_department.setToolTipText("");

        jLabel37.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 153));
        jLabel37.setText("Department");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 153, 153));
        jLabel40.setText("Salary");

        txt_salary.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salaryActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 153, 153));
        jLabel42.setText("Password");

        pwd_password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 153, 153));
        jLabel43.setText("Role");

        cmb_role.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmb_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Role", "Employee", "Admin" }));

        btn_cancel.setBackground(new java.awt.Color(0, 153, 153));
        btn_cancel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_cancel.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancel.setText("BACK");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(0, 153, 153));
        btn_save.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 255, 255));
        btn_save.setText("SAVE");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 153, 153));
        jLabel44.setText("Upload Image");

        lbl_employeeImage.setBackground(new java.awt.Color(245, 245, 245));
        lbl_employeeImage.setForeground(new java.awt.Color(245, 245, 245));

        btn_uploadImage.setBackground(new java.awt.Color(0, 153, 153));
        btn_uploadImage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_uploadImage.setForeground(new java.awt.Color(255, 255, 255));
        btn_uploadImage.setText("Choosen File");
        btn_uploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_designation, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(btn_cancel)))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_department, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_maritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(btn_save)
                                        .addGap(52, 52, 52)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_employeeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(262, Short.MAX_VALUE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(370, 370, 370)
                                .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(249, 249, 249)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(249, 249, 249)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(pwd_password, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(214, 214, 214)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(224, 224, 224)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(txt_employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(date_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(cmb_role, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(249, 249, 249)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_uploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwd_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(date_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_role, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_maritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_designation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_department, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_employeeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_uploadImage)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 960, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add New Employee", jPanel5);

        jPanel7.setBackground(new java.awt.Color(243, 243, 243));
        jPanel7.setMinimumSize(new java.awt.Dimension(0, 648));

        jPanel31.setPreferredSize(new java.awt.Dimension(1012, 648));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 153, 153));
        jLabel47.setText("EDIT EMPLOYEE");

        jLabel49.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 153, 153));
        jLabel49.setText("Name");

        txt_name1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_name1ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 153, 153));
        jLabel51.setText("Email");

        txt_email1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 153, 153));
        jLabel55.setText("Date of Birth");

        jLabel57.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 153, 153));
        jLabel57.setText("Gender");

        cb_gender1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_gender1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Gender", "Male", "Female", "Others" }));
        cb_gender1.setToolTipText("");

        jLabel58.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 153, 153));
        jLabel58.setText("Marital Status");

        cb_marriedStatus1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_marriedStatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Marital status", "Married", "Single" }));
        cb_marriedStatus1.setToolTipText("");

        jLabel59.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 153, 153));
        jLabel59.setText("Designation");

        cb_designation1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_designation1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Designation", "Software Engineer", "Team Lead", "Project Manager", "Business Analyst", "DevOps Engineer", "UI/UX Designer", "Full Stack Developer", "Data Analyst", "Database Administrator", "Human Resources Manager", "Network Engineer", "Cloud Architect" }));
        cb_designation1.setToolTipText("");

        cb_department1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_department1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Department", "Human Resources (HR)", "IT (Information Technology)", "Engineering", "Data Analytics", "Business Development", "Design", "Project Management", "Customer Support", "Quality Assurance (QA)", "Training and Development" }));
        cb_department1.setToolTipText("");

        jLabel60.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 153, 153));
        jLabel60.setText("Department");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 153, 153));
        jLabel61.setText("Salary");

        txt_salary1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 153, 153));
        jLabel62.setText("Password");

        pwd_password1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 153, 153));
        jLabel63.setText("Role");

        cb_role1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_role1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Role", "Employee", "Admin" }));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 153, 153));
        jLabel64.setText("Upload Image");

        btn_back1.setBackground(new java.awt.Color(0, 153, 153));
        btn_back1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_back1.setForeground(new java.awt.Color(255, 255, 255));
        btn_back1.setText("BACK");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });

        btn_updateEditModule.setBackground(new java.awt.Color(0, 153, 153));
        btn_updateEditModule.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_updateEditModule.setForeground(new java.awt.Color(255, 255, 255));
        btn_updateEditModule.setText("UPDATE");
        btn_updateEditModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateEditModuleActionPerformed(evt);
            }
        });

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        btn_uploadImage1.setBackground(new java.awt.Color(0, 153, 153));
        btn_uploadImage1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_uploadImage1.setForeground(new java.awt.Color(255, 255, 255));
        btn_uploadImage1.setText("CHOOSE FILE");
        btn_uploadImage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadImage1ActionPerformed(evt);
            }
        });

        lbl_profilePicture1.setBackground(new java.awt.Color(51, 255, 204));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cb_designation1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_gender1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(date_dob1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btn_back1)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_role1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cb_marriedStatus1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_department1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_email1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(btn_updateEditModule)
                        .addGap(136, 136, 136)))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_salary1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_profilePicture1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pwd_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_uploadImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(435, 435, 435))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(date_dob1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(cb_gender1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(cb_designation1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(pwd_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txt_salary1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(lbl_profilePicture1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_email1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(cb_role1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(cb_marriedStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_department1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_updateEditModule, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(btn_uploadImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane2.addTab("Edit Employee", jPanel7);

        jPanel6.setBackground(new java.awt.Color(243, 243, 243));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 204, 0));
        jLabel45.setText("Employee Details");

        jLabel46.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel46.setText("Name : ");

        lbl_fetchname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel48.setText("Employee ID : ");

        lbl_fetchEmpID.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel50.setText("Date of Birth : ");

        lbl_fetchDOB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel52.setText("Gender : ");

        lbl_fetchGender.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel54.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel54.setText("Department : ");

        lbl_fetchDepartment.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel56.setText("Marital Status : ");

        lbl_fetchMaritalStatus.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btn_back.setBackground(new java.awt.Color(0, 153, 153));
        btn_back.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel45))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lbl_profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fetchname, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fetchEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fetchDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fetchGender, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fetchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fetchMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(653, 653, 653)
                        .addComponent(btn_back)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(lbl_fetchname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_fetchEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_fetchDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_fetchGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_fetchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_fetchMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jTabbedPane2.addTab("Employee Details", jPanel6);

        jPanel9.setMinimumSize(new java.awt.Dimension(1012, 640));
        jPanel9.setPreferredSize(new java.awt.Dimension(1015, 648));

        jLabel53.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 204, 0));
        jLabel53.setText("Manage Leaves");

        tbl_leave.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_leave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sno", "Employee Name", "Leave Type", "From Date", "To Date", "Description", "Applied Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_leave.setRowHeight(40);
        tbl_leave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_leaveMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_leave);

        btn_leaveBack.setBackground(new java.awt.Color(0, 153, 153));
        btn_leaveBack.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_leaveBack.setForeground(new java.awt.Color(255, 255, 255));
        btn_leaveBack.setText("BACK");
        btn_leaveBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leaveBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_leaveBack)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btn_leaveBack)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("\n", jPanel9);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, Short.MAX_VALUE)
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", tab2);

        tab4.setBackground(new java.awt.Color(245, 245, 245));

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jLabel66.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel66.setText("MANAGE LEAVES");

        pendingButton.setBackground(new java.awt.Color(0, 153, 153));
        pendingButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pendingButton.setForeground(new java.awt.Color(255, 255, 255));
        pendingButton.setText("Pending");
        pendingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingButtonActionPerformed(evt);
            }
        });

        approvedButton.setBackground(new java.awt.Color(0, 153, 153));
        approvedButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        approvedButton.setForeground(new java.awt.Color(255, 255, 255));
        approvedButton.setText("Approved");
        approvedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvedButtonActionPerformed(evt);
            }
        });

        rejectedButton.setBackground(new java.awt.Color(0, 153, 153));
        rejectedButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rejectedButton.setForeground(new java.awt.Color(255, 255, 255));
        rejectedButton.setText("Reject");
        rejectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectedButtonActionPerformed(evt);
            }
        });

        tbl_manageLeave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sno", "Username", "Leave Type ", "From Date", "To Date", "Applied Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_manageLeave.setRowHeight(40);
        tbl_manageLeave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_manageLeaveMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_manageLeave);

        clearFilterButton.setBackground(new java.awt.Color(0, 153, 153));
        clearFilterButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        clearFilterButton.setForeground(new java.awt.Color(255, 255, 255));
        clearFilterButton.setText("Clear Filter");
        clearFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(pendingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(approvedButton)
                        .addGap(47, 47, 47)
                        .addComponent(rejectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearFilterButton))
                .addGap(45, 45, 45))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approvedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rejectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clearFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("tab1", jPanel38);

        UpdateLeavePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 204, 0));
        jLabel65.setText("Leave Details");

        jLabel67.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel67.setText("Name : ");

        nameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel69.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel69.setText("Leave Type : ");

        leaveTypeLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel70.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel70.setText("Start Date : ");

        startDateLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel71.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel71.setText("End Date : ");

        endDateLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel72.setText("Action : ");

        btn_back2.setBackground(new java.awt.Color(0, 153, 153));
        btn_back2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_back2.setForeground(new java.awt.Color(255, 255, 255));
        btn_back2.setText("BACK");
        btn_back2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back2ActionPerformed(evt);
            }
        });

        approveButton.setBackground(new java.awt.Color(0, 153, 51));
        approveButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        approveButton.setForeground(new java.awt.Color(255, 255, 255));
        approveButton.setText("APPROVE");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        rejectButton.setBackground(new java.awt.Color(204, 0, 0));
        rejectButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rejectButton.setForeground(new java.awt.Color(255, 255, 255));
        rejectButton.setText("REJECT");
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel73.setText("Status : ");

        statusLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout UpdateLeavePanelLayout = new javax.swing.GroupLayout(UpdateLeavePanel);
        UpdateLeavePanel.setLayout(UpdateLeavePanelLayout);
        UpdateLeavePanelLayout.setHorizontalGroup(
            UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(leaveTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(237, 237, 237))
                                .addComponent(startDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(237, 237, 237))
                                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_back2)
                                .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(approveButton)
                                    .addGap(31, 31, 31)
                                    .addComponent(rejectButton)))))
                    .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel65)))
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        UpdateLeavePanelLayout.setVerticalGroup(
            UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UpdateLeavePanelLayout.createSequentialGroup()
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leaveTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UpdateLeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rejectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(btn_back2)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout LeavePanelLayout = new javax.swing.GroupLayout(LeavePanel);
        LeavePanel.setLayout(LeavePanelLayout);
        LeavePanelLayout.setHorizontalGroup(
            LeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeavePanelLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(UpdateLeavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        LeavePanelLayout.setVerticalGroup(
            LeavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeavePanelLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(UpdateLeavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jTabbedPane4.addTab("tab2", LeavePanel);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tab4);

        tab5.setBackground(new java.awt.Color(245, 245, 245));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 0));
        jLabel7.setText("Add New Salary");

        departmentComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select Department", "Human Resources (HR)", "IT (Information Technology)", "Engineering", "Data Analytics", "Business Development", "Design", "Project Management", "Customer Support", "Quality Assurance (QA)", "Training and Development" }));
        departmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentComboBoxActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Department");

        jLabel68.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 153, 153));
        jLabel68.setText("Employees");

        employeeNameComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel74.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 153, 153));
        jLabel74.setText("Basic Salary");

        basicSalaryField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        monthlyAllowanceField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel75.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 153, 153));
        jLabel75.setText("Monthly Allowance");

        jLabel76.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 153, 153));
        jLabel76.setText("Dedections");

        deductionField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel77.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 153, 153));
        jLabel77.setText("Pay Date");

        addSalaryButton.setBackground(new java.awt.Color(0, 153, 153));
        addSalaryButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addSalaryButton.setForeground(new java.awt.Color(255, 255, 255));
        addSalaryButton.setText("ADD SALARY");
        addSalaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSalaryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSalaryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(basicSalaryField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(departmentComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 227, Short.MAX_VALUE)
                                        .addComponent(deductionField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(204, 204, 204)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(monthlyAllowanceField)
                                    .addGroup(jPanel34Layout.createSequentialGroup()
                                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(employeeNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(payDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(157, 157, 157))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(monthlyAllowanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeeNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(basicSalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deductionField, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(payDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(addSalaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab5", tab5);

        tab6.setBackground(new java.awt.Color(245, 245, 245));

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        pwd_conpass.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pwd_conpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwd_conpassActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(0, 204, 204));
        btn_clear.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_submit.setBackground(new java.awt.Color(0, 204, 204));
        btn_submit.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        btn_submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit.setText("SUBMIT");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 153, 153));
        jLabel78.setText("Current Username");

        jLabel79.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 153, 153));
        jLabel79.setText("New password");

        txtCurrentUsername.setEditable(false);
        txtCurrentUsername.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel80.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 153, 153));
        jLabel80.setText("Confirm Password");

        pwd_newpass.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pwd_newpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwd_newpassActionPerformed(evt);
            }
        });

        lbl_message.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N
        lbl_message.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pwd_newpass)
                    .addComponent(jLabel80)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                        .addComponent(btn_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(btn_submit))
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurrentUsername)
                    .addComponent(pwd_conpass)
                    .addComponent(lbl_message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel78)
                .addGap(18, 18, 18)
                .addComponent(txtCurrentUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel79)
                .addGap(19, 19, 19)
                .addComponent(pwd_newpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel80)
                .addGap(18, 18, 18)
                .addComponent(pwd_conpass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbl_message, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear)
                    .addComponent(btn_submit))
                .addGap(48, 48, 48))
        );

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11.setText("Change Password");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab6", tab6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -40, 1010, 740));

        jPanel3.setBackground(new java.awt.Color(4, 66, 66));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_dashboard.setBackground(new java.awt.Color(4, 66, 66));
        panel_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_dashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_dashboardMouseExited(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\dashboard.png")); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel3.setFont(new java.awt.Font("Perpetua", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard");

        javax.swing.GroupLayout panel_dashboardLayout = new javax.swing.GroupLayout(panel_dashboard);
        panel_dashboard.setLayout(panel_dashboardLayout);
        panel_dashboardLayout.setHorizontalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashboardLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panel_dashboardLayout.setVerticalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jPanel3.add(panel_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 42, -1, -1));

        panel_employees.setBackground(new java.awt.Color(4, 66, 66));
        panel_employees.setPreferredSize(new java.awt.Dimension(180, 42));
        panel_employees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_employeesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_employeesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_employeesMouseExited(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\employees.png")); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(72, 30));

        jLabel6.setFont(new java.awt.Font("Perpetua", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Employees");
        jLabel6.setPreferredSize(new java.awt.Dimension(82, 22));

        javax.swing.GroupLayout panel_employeesLayout = new javax.swing.GroupLayout(panel_employees);
        panel_employees.setLayout(panel_employeesLayout);
        panel_employeesLayout.setHorizontalGroup(
            panel_employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        panel_employeesLayout.setVerticalGroup(
            panel_employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.add(panel_employees, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 114, 189, 52));

        panel_leaves.setBackground(new java.awt.Color(4, 66, 66));
        panel_leaves.setPreferredSize(new java.awt.Dimension(180, 42));
        panel_leaves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_leavesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_leavesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_leavesMouseExited(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\leaves.png")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Perpetua", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Leaves");

        javax.swing.GroupLayout panel_leavesLayout = new javax.swing.GroupLayout(panel_leaves);
        panel_leaves.setLayout(panel_leavesLayout);
        panel_leavesLayout.setHorizontalGroup(
            panel_leavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leavesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_leavesLayout.setVerticalGroup(
            panel_leavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leavesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_leavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(panel_leaves, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 189, 52));

        panel_salary.setBackground(new java.awt.Color(4, 66, 66));
        panel_salary.setPreferredSize(new java.awt.Dimension(180, 42));
        panel_salary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_salaryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_salaryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_salaryMouseExited(evt);
            }
        });

        icon_salary.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\salary.png")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Perpetua", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Salary");

        javax.swing.GroupLayout panel_salaryLayout = new javax.swing.GroupLayout(panel_salary);
        panel_salary.setLayout(panel_salaryLayout);
        panel_salaryLayout.setHorizontalGroup(
            panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salaryLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(icon_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_salaryLayout.setVerticalGroup(
            panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon_salary, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(panel_salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 189, 53));

        panel_settings.setBackground(new java.awt.Color(4, 66, 66));
        panel_settings.setPreferredSize(new java.awt.Dimension(180, 42));
        panel_settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_settingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_settingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_settingsMouseExited(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mydeen\\Desktop\\-\\Employee_Management_System\\Images\\settings.png")); // NOI18N

        jLabel14.setFont(new java.awt.Font("Perpetua", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Settings");

        javax.swing.GroupLayout panel_settingsLayout = new javax.swing.GroupLayout(panel_settings);
        panel_settings.setLayout(panel_settingsLayout);
        panel_settingsLayout.setHorizontalGroup(
            panel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_settingsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panel_settingsLayout.setVerticalGroup(
            panel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(panel_settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 189, 53));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dashboardMouseEntered
        // TODO add your handling code here:
        panel_dashboard.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_panel_dashboardMouseEntered

    private void panel_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dashboardMouseExited
        // TODO add your handling code here:
        panel_dashboard.setBackground(new Color(4,66,66));
    }//GEN-LAST:event_panel_dashboardMouseExited

    private void panel_employeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_employeesMouseEntered
        // TODO add your handling code here:
        panel_employees.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_panel_employeesMouseEntered

    private void panel_employeesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_employeesMouseExited
        // TODO add your handling code here:
        panel_employees.setBackground(new Color(4,66,66));
    }//GEN-LAST:event_panel_employeesMouseExited

    private void panel_leavesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_leavesMouseExited
        // TODO add your handling code here:
        panel_leaves.setBackground(new Color(4,66,66));
    }//GEN-LAST:event_panel_leavesMouseExited

    private void panel_leavesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_leavesMouseEntered
        // TODO add your handling code here:
        panel_leaves.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_panel_leavesMouseEntered

    private void panel_settingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_settingsMouseEntered
        // TODO add your handling code here:
        panel_settings.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_panel_settingsMouseEntered

    private void panel_settingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_settingsMouseExited
        // TODO add your handling code here:
        panel_settings.setBackground(new Color(4,66,66));
    }//GEN-LAST:event_panel_settingsMouseExited

    private void panel_salaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_salaryMouseExited
        // TODO add your handling code here:
        panel_salary.setBackground(new Color(4,66,66));
    }//GEN-LAST:event_panel_salaryMouseExited

    private void panel_salaryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_salaryMouseEntered
        // TODO add your handling code here:
        panel_salary.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_panel_salaryMouseEntered

    private void panel_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dashboardMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_panel_dashboardMouseClicked

    private void panel_employeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_employeesMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_panel_employeesMouseClicked

    private void panel_leavesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_leavesMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_panel_leavesMouseClicked

    private void panel_salaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_salaryMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_panel_salaryMouseClicked

    private void panel_settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_settingsMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        txtCurrentUsername.setText(username);
    }//GEN-LAST:event_panel_settingsMouseClicked

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        LoginPage lp = new LoginPage();
        this.dispose();
        lp.setVisible(true);
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        saveEmployee();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void btn_addNewEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addNewEmployeeActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btn_addNewEmployeeActionPerformed

    private void txt_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_name1ActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_updateEditModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateEditModuleActionPerformed
        int employeeID = getSelectedEmployeeID(); // Replace with your method to get the selected employee ID
        updateEmployeeDetails(employeeID);
    }//GEN-LAST:event_btn_updateEditModuleActionPerformed

    private void btn_uploadImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadImage1ActionPerformed
        // TODO add your handling code here:
        chooseImageFile(lbl_profilePicture1);

        Icon icon = lbl_profilePicture1.getIcon(); // Get the current icon
        if (icon != null) {
            byte[] employeeImage = imageToByte(icon); // Call the method
            if (employeeImage != null) {
                // Proceed with the logic to update the employee image in the database
            } else {
                JOptionPane.showMessageDialog(this, "Error converting image to byte array!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No image selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_uploadImage1ActionPerformed

    private void btn_leaveBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leaveBackActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btn_leaveBackActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btn_backActionPerformed

    private void pendingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingButtonActionPerformed
        // TODO add your handling code here:
       filterLeaveData("Pending");
    }//GEN-LAST:event_pendingButtonActionPerformed

    private void btn_back2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(0);
    }//GEN-LAST:event_btn_back2ActionPerformed

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        // TODO add your handling code here:
        updateLeaveStatus("Approved");
    }//GEN-LAST:event_approveButtonActionPerformed
    
    private void filterLeaveData(String status) {
        // Clear the existing rows from the table
        DefaultTableModel model = (DefaultTableModel) tbl_manageLeave.getModel();
        model.setRowCount(0);

        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            String sql;

            // If status is "All", fetch all records
            if (status.equals("All")) {
                sql = "{CALL GetLeaveDetails(NULL)}";
            } else {
                // Otherwise, filter by the selected status (Pending, Approved, Rejected)
                sql = "{CALL GetLeaveDetails(?)}";
            }

            cstmt = conn.prepareCall(sql);

            // If status is not "All", set the status parameter for filtering
            if (!status.equals("All")) {
                cstmt.setString(1, status);
            }

            rs = cstmt.executeQuery();

            // Iterate through the result set and add rows to the table
            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("Sno"),
                    rs.getString("Username"),
                    rs.getString("Leave_Type"),
                    rs.getDate("From_Date"),
                    rs.getDate("To_Date"),
                    rs.getDate("Applied_Date"),
                    rs.getString("Status")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
        }
    }

    
    private void refreshLeaveTable() {
    // Assuming `leaveTableModel` is the model used by your JTable for displaying leave data
    DefaultTableModel model = (DefaultTableModel) tbl_manageLeave.getModel();
    model.setRowCount(0); // Clear existing rows

    Connection conn = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;

    try {
        conn = DBConnection.getConnection();
        String sql = "{CALL GetLeaveDetails()}";
        cstmt = conn.prepareCall(sql);
        rs = cstmt.executeQuery();

        while (rs.next()) {
            // Get data from result set and add to the table model
            Object[] rowData = {
                rs.getInt("Sno"),
                rs.getString("Username"),
                rs.getString("Leave_Type"),
                rs.getDate("From_Date"),
                rs.getDate("To_Date"),
                rs.getDate("Applied_Date"),
                rs.getString("Status")
            };
            model.addRow(rowData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) { /* handle exception */ }
        try { if (cstmt != null) cstmt.close(); } catch (SQLException e) { /* handle exception */ }
        try { if (conn != null) conn.close(); } catch (SQLException e) { /* handle exception */ }
    }
}

    private void updateLeaveStatus(String newStatus) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            // Get the connection from DBConnection class
            conn = DBConnection.getConnection();

            // SQL query to update the status in Leaave_Tbl
            String sql = "{CALL UpdateLeaveStatus(?, ?)}";
            cstmt = conn.prepareCall(sql);

            // Set parameters for the query
            cstmt.setString(1, newStatus);
            cstmt.setString(2, nameLabel.getText());  // Assuming nameLabel displays the Username

            // Execute the update
            int rowsAffected = cstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Leave status updated to " + newStatus + " successfully.");
                // Optionally, you could refresh the table or perform other actions here
                refreshLeaveTable();                
            } else {
                JOptionPane.showMessageDialog(null, "No pending leave found for this user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating leave status.");
        } finally {
            // Clean up resources
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
        }
    }

    
    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectButtonActionPerformed
        // TODO add your handling code here:
        updateLeaveStatus("Rejected");
    }//GEN-LAST:event_rejectButtonActionPerformed

    private void tbl_manageLeaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_manageLeaveMouseClicked
        if (evt.getClickCount() == 2 && tbl_manageLeave.getSelectedRow() != -1) {
        int selectedRow = tbl_manageLeave.getSelectedRow();
        String employeeName = tbl_manageLeave.getValueAt(selectedRow, 1).toString();  // Get employee name
        String leaveType = tbl_manageLeave.getValueAt(selectedRow, 2).toString();  // Get leave type
        String fromDate = tbl_manageLeave.getValueAt(selectedRow, 3).toString();    // Get start date
        String toDate = tbl_manageLeave.getValueAt(selectedRow, 4).toString();      // Get end date
        String status = tbl_manageLeave.getValueAt(selectedRow, 6).toString();      // Get status
        
        // Fetch the leave details and update the leave details panel
        fetchLeaveDetails(employeeName, leaveType, fromDate, toDate, status);

            // Switch to the leave details page/tab
            jTabbedPane4.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tbl_manageLeaveMouseClicked

    private void approvedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvedButtonActionPerformed
        // TODO add your handling code here:
        filterLeaveData("Approved");
    }//GEN-LAST:event_approvedButtonActionPerformed

    private void rejectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectedButtonActionPerformed
        // TODO add your handling code here:
        filterLeaveData("Rejected");
    }//GEN-LAST:event_rejectedButtonActionPerformed

    private void clearFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilterButtonActionPerformed
        // TODO add your handling code here:
        filterLeaveData("All");
    }//GEN-LAST:event_clearFilterButtonActionPerformed

    private void departmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentComboBoxActionPerformed
        // TODO add your handling code here:
        String selectedDepartment = (String) departmentComboBox.getSelectedItem();
        populateEmployeeNameComboBox(selectedDepartment);
    }//GEN-LAST:event_departmentComboBoxActionPerformed
    
    public void addSalaryRecord(String employeeName, double basicSalary, double monthlyAllowance, double deduction, java.sql.Date payDate) {
        // Calculate net pay (basic salary + allowance - deduction)
        double netPay = basicSalary + monthlyAllowance - deduction;
        CallableStatement cstmt = null;

        // SQL query to insert salary details
        String insertSalarySql = "{CALL InsertSalaryDetails(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection()) {
            
            cstmt = conn.prepareCall(insertSalarySql);
            
            // Insert into Salary_Tbl with EmployeeName and Department
            cstmt.setString(1, employeeName);
            cstmt.setString(2, departmentComboBox.getSelectedItem().toString()); // Assuming department combo box
            cstmt.setDouble(3, basicSalary);
            cstmt.setDouble(4, monthlyAllowance);
            cstmt.setDouble(5, deduction);
            cstmt.setDouble(6, netPay);
            cstmt.setDate(7, payDate);

            int rowsInserted = cstmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Salary record added successfully.");
                
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add salary record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while adding salary record.", "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for salary, allowance, and deduction.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addSalaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSalaryButtonActionPerformed
        // TODO add your handling code here:
        String employeeName = employeeNameComboBox.getSelectedItem().toString();
        double basicSalary = Double.parseDouble(basicSalaryField.getText());
        double monthlyAllowance = Double.parseDouble(monthlyAllowanceField.getText());
        double deduction = Double.parseDouble(deductionField.getText());
        java.util.Date utilDate = payDateField.getDate();

        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a valid pay date.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.sql.Date payDate = new java.sql.Date(utilDate.getTime());

        // Call the method to add salary record
        addSalaryRecord(employeeName, basicSalary, monthlyAllowance, deduction, payDate);
        clearSalaryForm();
    }//GEN-LAST:event_addSalaryButtonActionPerformed

    private void pwd_newpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwd_newpassActionPerformed
        // TODO add your handling code here

    }//GEN-LAST:event_pwd_newpassActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        pwd_newpass.setText("");
        pwd_conpass.setText("");
        lbl_message.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed
    
    private void updatePassword() throws Exception {
        String newPassword = new String(pwd_newpass.getPassword());
        String confirmPassword = new String(pwd_conpass.getPassword());

        // Validate input
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            lbl_message.setForeground(Color.RED);
            lbl_message.setText("Please enter a new password and confirm it.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            lbl_message.setForeground(Color.RED);
            lbl_message.setText("Passwords do not match. Please try again.");
            return;
        }

        // Update password in the database
        String query = "UPDATE Employee_Tbl SET Password = ? WHERE Name = ?";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Uncomment the following line if you want to test without encryption
            // preparedStatement.setString(1, newPassword);
            // Encrypt and set the new password
            preparedStatement.setString(1, encrypt(newPassword));
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                lbl_message.setForeground(Color.GREEN);
                lbl_message.setText("Successfully updated the password.");
                pwd_newpass.setText("");
                pwd_conpass.setText("");
            } else {
                lbl_message.setForeground(Color.RED);
                lbl_message.setText("Error updating password. Please try again.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            lbl_message.setText("Database error. Please try again later.");
        } catch (Exception ex) {
            ex.printStackTrace();
            lbl_message.setText("Unexpected error. Please try again later.");
        }
    }
    
    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        try {
            updatePassword();
        } catch (Exception ex) {
            ex.printStackTrace();
            lbl_message.setText("Unexpected error while updating password.");
        }
    }//GEN-LAST:event_btn_submitActionPerformed

    private void pwd_conpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwd_conpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwd_conpassActionPerformed

    private void txt_salaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_salaryActionPerformed

    private void btn_uploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadImageActionPerformed
        // TODO add your handling code here:
        chooseImageFile(lbl_employeeImage);
    }//GEN-LAST:event_btn_uploadImageActionPerformed

    private void tbl_leaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_leaveMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_leaveMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tableMouseClicked
    
    private void clearSalaryForm() {
        // Clear the text fields
        basicSalaryField.setText("");
        monthlyAllowanceField.setText("");
        deductionField.setText("");

        // Reset the combo boxes
        employeeNameComboBox.setSelectedIndex(0);  // Reset to first item or default item
        departmentComboBox.setSelectedIndex(0);    // Reset to first item or default item

        // Clear the JDateChooser
        payDateField.setDate(null);  // Reset the date field to null
    }
    private void populateEmployeeNameComboBox(String department) {
        employeeNameComboBox.removeAllItems(); // Clear existing items

        String query = "SELECT Name FROM Employee_Tbl WHERE Department = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, department);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String employeeName = rs.getString("Name");
                employeeNameComboBox.addItem(employeeName); // Assuming employeeNameComboBox is the combo box for employee names
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchLeaveDetails(String employeeName, String leaveType, String fromDate, String toDate, String status) {
        nameLabel.setText(employeeName);
        leaveTypeLabel.setText(leaveType); // Update Leave Type field
        startDateLabel.setText(fromDate);  // Update From Date field
        endDateLabel.setText(toDate);      // Update End Date field
        statusLabel.setText(status);       // Update Status field

        // Fetch and display the employee image based on employeeName
        fetchEmployeeImage(employeeName);
    }

    private void fetchEmployeeImage(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Fetching image for Username: " + username); // Check the input username

            // Get a connection from DBConnection
            conn = DBConnection.getConnection();

            // SQL query to retrieve the employee's image from Employee_Tbl
            String sql = "SELECT EmployeeImage FROM Employee_Tbl WHERE Name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username); // Set username (or email) in the query
            rs = pstmt.executeQuery();

            // Check if any data is retrieved
            if (rs.next()) {
                System.out.println("Image found for user: " + username);

                // Retrieve the image blob
                Blob imageBlob = rs.getBlob("EmployeeImage");
                if (imageBlob != null) {
                    InputStream inputStream = imageBlob.getBinaryStream();
                    BufferedImage image = ImageIO.read(inputStream); // Read the image

                    // Scale the image to fit the label
                    Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage)); // Set the image icon on the label
                } else {
                    System.out.println("No image found for the selected user.");
                }
            } else {
                System.out.println("No image found for the selected user.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle exceptions
        } finally {
            // Clean up resources
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                /* handle exception */ }
        }
    }


    private int getSelectedEmployeeID() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0); // Adjust column index as necessary
        } else {
            JOptionPane.showMessageDialog(this, "No employee selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Indicate no selection
        }
    }
            
    private void populateDashboardData() {
        DashboardDataFetcher dataFetcher = new DashboardDataFetcher();

        try {
            int totalEmployees = dataFetcher.fetchTotalEmployees();
            int totalDepartments = dataFetcher.fetchTotalDepartments();
            double monthlyPay = dataFetcher.fetchMonthlyPay();
            
            int totalLeavesApplied = dataFetcher.fetchTotalLeavesApplied();
            int totalLeavesApproved = dataFetcher.fetchTotalLeavesApproved();
            int totalLeavesRejected = dataFetcher.fetchTotalLeavesRejected();
            int totalLeavesPending = dataFetcher.fetchTotalLeavesPending();

            // Set the data to the respective labels
            lbl_totalEmployees.setText(String.valueOf(totalEmployees));
            lbl_totalDepartments.setText(String.valueOf(totalDepartments));
            lbl_monthlyPay.setText(String.format("%.2f", monthlyPay));
            
            lbl_leaveApplied.setText(String.valueOf(totalLeavesApplied));
            lbl_leaveApproved.setText(String.valueOf(totalLeavesApproved));
            lbl_leaveRejected.setText(String.valueOf(totalLeavesRejected));
            lbl_leavePending.setText(String.valueOf(totalLeavesPending));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching dashboard data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void chooseImageFile(JLabel imageLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image File");

        // Set the file filter to allow only image files
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(imageFilter);

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File employeeImageFile = fileChooser.getSelectedFile(); // Store the selected file

            try {
                // Read and display the image in the label
                Image img = ImageIO.read(employeeImageFile);
                Image scaledImg = img.getScaledInstance(imageLabel.getWidth(),
                        imageLabel.getHeight(),
                        Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImg));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening image file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    
    private void saveEmployee() {
        try {
            String name = txt_name.getText();
            int employeeID = Integer.parseInt(txt_employeeID.getText());
            String gender = (String) cmb_gender.getSelectedItem();
            String designation = (String) cmb_designation.getSelectedItem();
            double salary = Double.parseDouble(txt_salary.getText());
            String role = (String) cmb_role.getSelectedItem();
            String email = txt_email.getText();
            String password = new String(pwd_password.getPassword());
            Date dateOfBirth = date_dob.getDate();
            String department = (String) cmb_department.getSelectedItem();
            String marriedStatus = (String) cmb_maritalStatus.getSelectedItem();

            // Convert employeeImageFile to FileInputStream if it’s selected
            FileInputStream imageStream = employeeImageFile != null ? new FileInputStream(employeeImageFile) : null;

            AddEmployee addemp = new AddEmployee();
            addemp.insertEmployee(name, employeeID, gender, designation, salary, role,
                    email, password, dateOfBirth, department, marriedStatus,
                    imageStream);

            fetchEmployeeData(table);
            JOptionPane.showMessageDialog(DashboardPage.this, "Employee added successfully!");
            jTabbedPane2.setSelectedIndex(0); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void clearFields() {
        txt_name.setText("");
        txt_employeeID.setText("");
        cmb_gender.setSelectedIndex(0);
        cmb_designation.setSelectedIndex(0);
        txt_salary.setText("");
        cmb_role.setSelectedIndex(0);
        txt_email.setText("");
        pwd_password.setText("");
        date_dob.setDate(null);
        cmb_department.setSelectedIndex(0);
        cmb_maritalStatus.setSelectedIndex(0);
        lbl_employeeImage.setIcon(null);
        employeeImageFile = null;
    }

    private void displayEmployeeDetails(int employeeID) {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT Name, EmployeeID, DateOfBirth, Gender, Department, MarriedStatus, EmployeeImage FROM Employee_Tbl WHERE EmployeeID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Set textual details
                lbl_fetchname.setText(resultSet.getString("Name"));
                lbl_fetchEmpID.setText(resultSet.getString("EmployeeID"));
                lbl_fetchDOB.setText(resultSet.getString("DateOfBirth"));
                lbl_fetchGender.setText(resultSet.getString("Gender"));
                lbl_fetchDepartment.setText(resultSet.getString("Department"));
                lbl_fetchMaritalStatus.setText(resultSet.getString("MarriedStatus"));

                // Retrieve and set the employee image
                Blob imageBlob = resultSet.getBlob("EmployeeImage");
                if (imageBlob != null) {
                    InputStream inputStream = imageBlob.getBinaryStream();
                    Image img = ImageIO.read(inputStream);
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(lbl_profilePicture.getWidth(), lbl_profilePicture.getHeight(), Image.SCALE_SMOOTH));
                    lbl_profilePicture.setIcon(icon);
                } else {
                    lbl_profilePicture.setIcon(null); // or set a default icon
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void populateEmployeeDetails(int employeeID) {
        try {
            // Establish database connection
            Connection conn = DBConnection.getConnection();

            // SQL query to retrieve the employee details
            String query = "SELECT Name, Gender, Designation, Salary, Role, Email, Password, DateOfBirth, "
                    + "Department, MarriedStatus, EmployeeImage FROM Employee_Tbl WHERE EmployeeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeID);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Populate fields if record is found
            if (rs.next()) {
                txt_name1.setText(rs.getString("Name"));
                cb_gender1.setSelectedItem(rs.getString("Gender"));
                cb_designation1.setSelectedItem(rs.getString("Designation"));
                txt_salary1.setText(String.valueOf(rs.getDouble("Salary")));
                cb_role1.setSelectedItem(rs.getString("Role"));
                txt_email1.setText(rs.getString("Email"));
                pwd_password1.setText(rs.getString("Password"));
                cb_department1.setSelectedItem(rs.getString("Department"));
                cb_marriedStatus1.setSelectedItem(rs.getString("MarriedStatus"));
                
                // Handle Date of Birth
                String dobString = rs.getString("DateOfBirth");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format to your DB date format
                Date dob = dateFormat.parse(dobString);
                date_dob1.setDate(dob);
                
                byte[] imageBytes = rs.getBytes("EmployeeImage");
                if (imageBytes != null) {
                    // Create ImageIcon from byte array
                    ImageIcon originalImageIcon = new ImageIcon(imageBytes);

                    // Resize the image
                    Image originalImage = originalImageIcon.getImage();
                    Image resizedImage = originalImage.getScaledInstance(240, 200, Image.SCALE_SMOOTH); // Set your desired width and height

                    // Set the resized image to the JLabel
                    lbl_profilePicture1.setIcon(new ImageIcon(resizedImage));
                } else {
                    lbl_profilePicture1.setIcon(null);  // Set default or null image
                }
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found.");
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving employee data.");
        }
    }
    
    private void updateEmployeeDetails(int employeeID) {
        try {
            
            Connection conn = DBConnection.getConnection();
            
            // Retrieve data from fields
            String name = txt_name1.getText();
            String gender = cb_gender1.getSelectedItem().toString();
            String designation = cb_designation1.getSelectedItem().toString();
            double salary = Double.parseDouble(txt_salary1.getText());
            String role = cb_role1.getSelectedItem().toString();
            String email = txt_email1.getText();
            String password = new String(pwd_password1.getPassword());
            String department = cb_department1.getSelectedItem().toString();
            String marriedStatus = cb_marriedStatus1.getSelectedItem().toString();
            Date dob = date_dob1.getDate();
            byte[] employeeImage = imageToByte(lbl_profilePicture1.getIcon());

            // Convert Date to String format (if needed)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dobString = dateFormat.format(dob);

            // SQL Update Query
            String updateQuery = "UPDATE Employee_Tbl SET Name=?, Gender=?, Designation=?, Salary=?, "
                    + "Role=?, Email=?, Password=?, DateOfBirth=?, Department=?, MarriedStatus=?, "
                    + "EmployeeImage=? WHERE EmployeeID=?";

            // Assuming you have a connection object 'conn'
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, designation);
            pstmt.setDouble(4, salary);
            pstmt.setString(5, role);
            pstmt.setString(6, email);
            pstmt.setString(7, encrypt(password));
            pstmt.setString(8, dobString);
            pstmt.setString(9, department);
            pstmt.setString(10, marriedStatus);
            pstmt.setBytes(11, employeeImage);
            pstmt.setInt(12, employeeID);  // Use the passed employeeID

            // Execute update
            pstmt.executeUpdate();
            fetchEmployeeData(table);
            JOptionPane.showMessageDialog(null, "Employee details updated successfully!");

            // Optionally refresh the table or reset fields here
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating employee details.");
        }
    }

    private byte[] imageToByte(Icon icon) {
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();

            // Create a BufferedImage from the Image
            BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_RGB
            );

            // Draw the Image onto the BufferedImage
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            // Write the BufferedImage to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "jpg", baos); // Change to "png" if needed
                baos.flush();
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null; // Return null if the icon is not an ImageIcon
    }
    
    private boolean deleteEmployee(int EmployeeID) {
    try {
        // Assuming you have a DBConnection class to handle your database connections
        java.sql.Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM Employee_Tbl WHERE EmployeeID = ?";
        java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, EmployeeID);

        int affectedRows = pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return affectedRows > 0; // Return true if deletion was successful
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
private void loadLeaveData(String username) {
    // Clear previous data
    DefaultTableModel model = (DefaultTableModel) tbl_leave.getModel();
    model.setRowCount(0);

    // Query to fetch leave details for a specific employee based on the username
    String query = "SELECT Sno, Username, Leave_Type, From_Date, To_Date, Description, Applied_Date, Status FROM Leave_Tbl WHERE Username = ?";
    
    System.out.println("Loading leave data for username: " + username); // Debugging print statement

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        // Set the username parameter
        pstmt.setString(1, username);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false; // Flag to check if data is present
            while (rs.next()) {
                hasData = true;
                int sno = rs.getInt("Sno");
                String employeeName = rs.getString("Username"); // Assuming Username is the employee's name
                String leaveType = rs.getString("Leave_Type");
                Date fromDate = rs.getDate("From_Date");
                Date toDate = rs.getDate("To_Date");
                String description = rs.getString("Description");
                Date appliedDate = rs.getDate("Applied_Date");
                String status = rs.getString("Status");

                // Add row to the table
                model.addRow(new Object[]{sno, employeeName, leaveType, fromDate, toDate, description, appliedDate, status});
            }
            
            if (!hasData) {
                System.out.println("No leave data found for username: " + username); // Debugging output if no data is found
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching leave data.");
    }
}



private void populateDepartmentComboBox() {
    // Clear any existing items in the combo box
    departmentComboBox.removeAllItems();

    String sql = "SELECT DISTINCT Department FROM Employee_Tbl";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        // Check if the combo box is not null
        if (departmentComboBox != null) {
            while (rs.next()) {
                String department = rs.getString("Department");
                departmentComboBox.addItem(department);
            }
        } else {
            System.out.println("departmentComboBox is not initialized.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeavePanel;
    private javax.swing.JPanel UpdateLeavePanel;
    private javax.swing.JButton addSalaryButton;
    private javax.swing.JButton approveButton;
    private javax.swing.JButton approvedButton;
    private javax.swing.JTextField basicSalaryField;
    private javax.swing.JButton btn_addNewEmployee;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_back2;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_leaveBack;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_submit;
    private javax.swing.JButton btn_updateEditModule;
    private javax.swing.JButton btn_uploadImage;
    private javax.swing.JButton btn_uploadImage1;
    private javax.swing.JComboBox<String> cb_department1;
    private javax.swing.JComboBox<String> cb_designation1;
    private javax.swing.JComboBox<String> cb_gender1;
    private javax.swing.JComboBox<String> cb_marriedStatus1;
    private javax.swing.JComboBox<String> cb_role1;
    private javax.swing.JButton clearFilterButton;
    private javax.swing.JComboBox<String> cmb_department;
    private javax.swing.JComboBox<String> cmb_designation;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JComboBox<String> cmb_maritalStatus;
    private javax.swing.JComboBox<String> cmb_role;
    private com.toedter.calendar.JDateChooser date_dob;
    private com.toedter.calendar.JDateChooser date_dob1;
    private javax.swing.JTextField deductionField;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JComboBox<String> employeeNameComboBox;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel icon_salary;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel jpanel_manageEmployee;
    private javax.swing.JLabel lbl_employeeImage;
    private javax.swing.JLabel lbl_fetchDOB;
    private javax.swing.JLabel lbl_fetchDepartment;
    private javax.swing.JLabel lbl_fetchEmpID;
    private javax.swing.JLabel lbl_fetchGender;
    private javax.swing.JLabel lbl_fetchMaritalStatus;
    private javax.swing.JLabel lbl_fetchname;
    private javax.swing.JLabel lbl_leaveApplied;
    private javax.swing.JLabel lbl_leaveApproved;
    private javax.swing.JLabel lbl_leavePending;
    private javax.swing.JLabel lbl_leaveRejected;
    private javax.swing.JLabel lbl_message;
    private javax.swing.JLabel lbl_monthlyPay;
    private javax.swing.JLabel lbl_profilePicture;
    private javax.swing.JLabel lbl_profilePicture1;
    private javax.swing.JLabel lbl_totalDepartments;
    private javax.swing.JLabel lbl_totalEmployees;
    private javax.swing.JLabel lbl_welcomeUser;
    private javax.swing.JLabel leaveTypeLabel;
    private javax.swing.JTextField monthlyAllowanceField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel panel_dash;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_employees;
    private javax.swing.JPanel panel_leaves;
    private javax.swing.JPanel panel_salary;
    private javax.swing.JPanel panel_settings;
    private com.toedter.calendar.JDateChooser payDateField;
    private javax.swing.JButton pendingButton;
    private javax.swing.JPasswordField pwd_conpass;
    private javax.swing.JPasswordField pwd_newpass;
    private javax.swing.JPasswordField pwd_password;
    private javax.swing.JPasswordField pwd_password1;
    private javax.swing.JButton rejectButton;
    private javax.swing.JButton rejectedButton;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel1;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel2;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    private javax.swing.JTable table;
    private javax.swing.JTable tbl_leave;
    private javax.swing.JTable tbl_manageLeave;
    private javax.swing.JTextField txtCurrentUsername;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_email1;
    private javax.swing.JTextField txt_employeeID;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_name1;
    private javax.swing.JTextField txt_salary;
    private javax.swing.JTextField txt_salary1;
    private org.jdatepicker.impl.UtilCalendarModel utilCalendarModel1;
    private org.jdatepicker.impl.UtilDateModel utilDateModel1;
    // End of variables declaration//GEN-END:variables
}