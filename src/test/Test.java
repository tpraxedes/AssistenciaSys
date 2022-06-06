/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import java.text.ParseException;
import javax.swing.JFrame;

/**
 *
 * @author thiag
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        FormLogin formLogin = new FormLogin();
        JFrame loginFrame = new JFrame();
        loginFrame.add(formLogin);
        loginFrame.pack();
        loginFrame.setVisible(true);        
        
        
    }
    
}
