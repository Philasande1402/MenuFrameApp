package ac.za.tut.gui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuFrameGUI extends JFrame{
    
    private JMenuBar menuBars;
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    
    private JMenu subMenu1;
    private JMenu subMenu2;
   
    
    private JMenuItem fileItem1;
    private JMenuItem fileItem2;
    private JMenuItem fileItem3;
    private JMenuItem fileItem4;
    private JMenuItem fileItem5;
    
    private JMenuItem editMenu1;
    private JMenuItem editMenu2;
    private JMenuItem editMenu3;
    private JMenuItem editMenu4;
    private JMenuItem editMenu5;
    
    private JMenuItem viewSubEditors;
    
    private JMenuItem splitHorizontal;
    private JMenuItem splitVertically;
    private JMenuItem splitClear;
    
    
    public MenuFrameGUI()
    {
        setTitle("Menus Frame");
        setResizable(true);
        setSize(600, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
       menuBars = new JMenuBar();
      // menuBars.setLayout(new FlowLayout(FlowLayout.LEFT));
       
       fileMenu = new JMenu("File");
       editMenu = new JMenu("Edit");
       viewMenu = new JMenu("View");
       
       fileItem1 = new JMenuItem("New Project...");
       fileItem2 = new JMenuItem("New File...");
       
       fileItem3 = new JMenuItem("Open Project...");
       fileItem4 = new JMenuItem("Open Recent Project");
       fileItem5 = new JMenuItem("Close All Project");
       
       editMenu1 = new JMenuItem("Undo");
       editMenu2 = new JMenuItem("Redo");
       editMenu3 = new JMenuItem("Cut");
       editMenu4 = new JMenuItem("Copy");
       editMenu5 = new JMenuItem("Paste");
       
       subMenu1 = new JMenu("Editors");
       viewSubEditors = new JMenuItem("Source");
       subMenu1.add(viewSubEditors);
       
       subMenu2 = new JMenu("Split");
       splitHorizontal = new JMenuItem("Horizontally");
       splitVertically = new JMenuItem("Vertically");
       splitClear = new JMenuItem("Clear");
       subMenu2.add(splitHorizontal);
       subMenu2.add(splitVertically);
       subMenu2.add(splitClear);
       
       viewMenu.add(subMenu1);
       viewMenu.add(subMenu2);
       
       fileMenu.add(fileItem1);
       fileMenu.add(fileItem2);
       fileMenu.addSeparator();
       fileMenu.add(fileItem3);
       fileMenu.add(fileItem4);
       fileMenu.add(fileItem5);
       
       editMenu.add(editMenu1);
       editMenu.add(editMenu2);
       editMenu.addSeparator();
       editMenu.add(editMenu3);
       editMenu.add(editMenu4);
       editMenu.add(editMenu5);
       
       menuBars.add(fileMenu);
       menuBars.add(editMenu);
       menuBars.add(viewMenu);
       
        add(menuBars);
        
        setVisible(true);
    }
    
}
