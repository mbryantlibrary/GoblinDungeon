/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Miles
 */
public class TextPanel extends GameInfoPanel {
    private final JLabel lblText = new JLabel();
    
    TextPanel() {
        setLayout(new CardLayout(30,0));
        add(lblText);
        setForeground(Color.WHITE);
        lblText.setForeground(Color.WHITE);
    }
    
    /**
     * Sets the text that this panel displays.
     * @param text String of text to display; must be non-null or a 
     * NullPointerException is thrown.
     */
    public void setText(String text) {
        lblText.setText(text);
    }
    
    /**
     * Sets font of the text displayed on this panel.
     * @param font Font of text. Must be non-null. If font size is large, panel
     * may be resized.
     */
    public void setTextFont(Font font) {
        lblText.setFont(font);
    }
    
    /**
     * Sets colour of the text displayed on this panel
     * @param color Color to display text in.
     */
    public void setColor(Color color) {
        lblText.setForeground(color);
    }
}
