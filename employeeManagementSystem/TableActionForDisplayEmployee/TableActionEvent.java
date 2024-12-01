/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.employeeManagementSystem.TableActionForDisplayEmployee;

/**
 *
 * @author Mydeen
 */
public interface TableActionEvent {
    public void onView(int row);
    public void onEdit(int row);
    public void onLeave(int row);
    public void onDelete(int row);
}
