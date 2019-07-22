/**
 * AWT Sample application
 *
 * @author 
 * @version 1.00 05/03/03
 */
 
 import javax.comm.*;
 import java.io.*;
 import java.util.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 
public class Server 
{
    
    private static void ShowGUI() 
    {
        JFrame.setDefaultLookAndFeelDecorated(true);

        ServerFrame frame = new ServerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) 
    {
    	
    	
    	ServerFrame frame = new ServerFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ShowGUI();
    	
    }
}    
