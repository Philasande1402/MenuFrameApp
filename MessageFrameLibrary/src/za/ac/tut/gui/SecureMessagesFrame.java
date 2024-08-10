package za.ac.tut.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.encryptor.MessageEncryptori;
import za.ac.tut.message.Message;

/**
 *
 * @author LENOVO
 */
public class SecureMessagesFrame extends JFrame{
    
    //PANEL
    private JPanel fileMenuPnl;
    private JPanel headPnl;
    private JPanel plainPnl;
    private JPanel encryptePnl;
    private JPanel plainEncryPnl;
    private JPanel mainPnl;
    private JPanel fileMenuPnlHeadPnl;
    
    //LABEL
    private JLabel headLbl;
    
    //MENUBAR
    private JMenuBar menuBarFile;
    
    //MENU
    private JMenu myFile;
    
    //MENUITEM
    private JMenuItem openFile;
    private JMenuItem encryptFile;
    private JMenuItem saveEncryFile;
    private JMenuItem clearFile;
    private JMenuItem exitFile;
    
    //SCROLL BANE
    private JScrollPane textAreaPane;
    private JScrollPane textAreaPane2;
    
    //TEXTAREA
    private JTextArea plainTxt;
    private JTextArea encryptedTxt;
    private static SecretKey secretKey;
    
    public SecureMessagesFrame()
    {
        setTitle("Secure Messages");
        setSize(700, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        fileMenuPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBarFile = new JMenuBar();
        myFile = new JMenu("File");
        
        openFile = new JMenuItem("Open file...");
        openFile.addActionListener(new OpenFile());
        
        encryptFile = new JMenuItem("Encrypt message...");
        encryptFile.addActionListener(new EncryptMessageAction());
        saveEncryFile = new JMenuItem("Save encrypted message...");
        saveEncryFile.addActionListener(new SaveEncry());
        clearFile = new JMenuItem("Clear");
        clearFile.addActionListener(new ClearFile());
        exitFile = new JMenuItem("Exit");
        exitFile.addActionListener(new ExitFile());
        
        myFile.add(openFile);
        myFile.add(encryptFile);
        myFile.add(saveEncryFile);
        myFile.addSeparator();
        myFile.add(clearFile);
        myFile.add(exitFile);
        menuBarFile.add(myFile);
        
        fileMenuPnl.add(menuBarFile);
        
        
        headPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headLbl = new JLabel("Message Encryptor");
        headLbl.setBorder(new BevelBorder(BevelBorder.RAISED));
        headLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD+Font.ITALIC, 20));
        headLbl.setForeground(Color.BLUE);
        
        headPnl.add(headLbl);
        
        fileMenuPnlHeadPnl = new JPanel(new GridLayout(2, 1, 1, 1));
        fileMenuPnlHeadPnl.add(fileMenuPnl);
        fileMenuPnlHeadPnl.add(headPnl);
        
        
        plainPnl = new JPanel(new FlowLayout());
        plainPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2),"Plain message"));
        plainTxt = new JTextArea(10, 20);
        plainTxt.setEditable(false);
        textAreaPane = new JScrollPane(plainTxt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        plainPnl.add(textAreaPane);
        
        
        encryptePnl = new JPanel(new FlowLayout());
        encryptePnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2),"Encryed message"));
        encryptedTxt = new JTextArea(10, 20);
        encryptedTxt.setEditable(false);
        textAreaPane2 = new JScrollPane(encryptedTxt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        encryptePnl.add(textAreaPane2);
        
        
        plainEncryPnl = new JPanel(new FlowLayout());
        plainEncryPnl.add(plainPnl);
        plainEncryPnl.add(encryptePnl);
        
        add(plainEncryPnl);
        
        
        mainPnl = new JPanel(new BorderLayout());
        mainPnl.add(fileMenuPnlHeadPnl,BorderLayout.NORTH);
        mainPnl.add(plainEncryPnl);
        
        add(mainPnl);
        
        pack();
        setVisible(true);
    }
    
    private class ExitFile implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
        
    }
    
    private class ClearFile implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            plainTxt.setText("");
            encryptedTxt.setText("");
        }
        
    }
    
    private class OpenFile implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) {
            File file;
            String message;
            String line=null;
            JFileChooser fc = new JFileChooser();
            
            int value = fc.showOpenDialog(SecureMessagesFrame.this);
            
            if(value == JFileChooser.APPROVE_OPTION)
            {
                BufferedReader br = null;
                try {
                    file = fc.getSelectedFile();
                    br = new BufferedReader(new FileReader(file));
                    while((message=br.readLine()) !=null)
                    {
                        line +=message+"\n";
                    }
                    br.close();
                    plainTxt.setText(line);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }
    
    private class SaveEncry implements ActionListener
    {

        public void actionPerformed(ActionEvent e) {
            
            String message = encryptedTxt.getText();
            
            Message themessage = new Message(message);
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try 
                {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(themessage.toString());
                    JOptionPane.showMessageDialog(null, "File saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    
    private class EncryptMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String plainText = plainTxt.getText();
            plainTxt.setText(plainText);
            String encryptedText = MessageEncryptori.encrypt(plainText);
            encryptedTxt.setText(encryptedText);
        }
    }
    
    
}
