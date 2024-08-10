package za.ac.tut.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MessageEncryptori {

    private static final int SHIFT = 3;

    public static String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();
        
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encryptedText.append((char) ((c - base + SHIFT) % 26 + base));
            } else {
                encryptedText.append(c);
            }
        }
        
        return encryptedText.toString();
    }
    
}
