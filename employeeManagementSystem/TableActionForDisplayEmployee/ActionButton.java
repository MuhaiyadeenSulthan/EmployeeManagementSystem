/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employeeManagementSystem.TableActionForDisplayEmployee;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mydeen
 */
public class ActionButton extends JButton{
    
    private boolean mousePress;
    
    public ActionButton(){
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(4,4,4,4));
        addMouseListener (new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = true;
            }
            
            
        });
    }
}
