/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.LogicCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HiddenTabsExample extends JFrame {
    private JTabbedPane tabbedPane;

    public HiddenTabsExample() {
        // Set up the JFrame
        setTitle("Hidden Tabs Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the JTabbedPane
        tabbedPane = new JTabbedPane();

        // Add panels to the JTabbedPane
        JPanel panel1 = createPanel("Panel 1 - Dashboard");
        JPanel panel2 = createPanel("Panel 2 - Employee Management");
        JPanel panel3 = createPanel("Panel 3 - Leave Management");

        tabbedPane.addTab("Dashboard", panel1);
        tabbedPane.addTab("Employee Management", panel2);
        tabbedPane.addTab("Leave Management", panel3);

        // Hide the tab headers (removing the tab components)
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0; // Hide the tab headers
            }
        });

        // Sidebar for navigation
        JPanel sidebarPanel = new JPanel(new GridLayout(3, 1));
        JButton btnDashboard = new JButton("Dashboard");
        JButton btnEmployee = new JButton("Employee Management");
        JButton btnLeave = new JButton("Leave Management");

        // Add action listeners to buttons to switch between tabs
        btnDashboard.addActionListener(e -> tabbedPane.setSelectedIndex(0)); // Switch to first tab
        btnEmployee.addActionListener(e -> tabbedPane.setSelectedIndex(1)); // Switch to second tab
        btnLeave.addActionListener(e -> tabbedPane.setSelectedIndex(2)); // Switch to third tab

        // Add buttons to the sidebar
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnEmployee);
        sidebarPanel.add(btnLeave);

        // Add sidebar and tabbedPane to the frame
        add(sidebarPanel, BorderLayout.WEST);
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Create a simple panel with a label
    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HiddenTabsExample frame = new HiddenTabsExample();
            frame.setVisible(true);
        });
    }
}