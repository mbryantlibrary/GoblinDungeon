package goblindungeon.ui;

import goblindungeon.Directions;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * MainUI is a simple JFrame that displays 
 * It displays the GamePanel to its full extent in a CardLayout. This is so that 
 * different JPanels could be swapped out, e.g. if we wanted to extend the 
 * gamePanel by adding different screens/a main menu etc.
 * @author Miles
 */
public class MainUI extends JFrame {
    
    private final int MIN_WIDTH = 1000; //Game cannot be resized below these
    private final int MIN_HEIGHT = 800; //dimensions. 
    
    private final GamePanel gamePanel = new GamePanel();

    /**
     * Creates a MainUI with default minimum width and height.
     */
    public MainUI()  {
        this.setLayout(new CardLayout(10,10)); //only one component can be displayed
        
        //user can't resize below this
        this.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        
        //Set background colour.
        getContentPane().setBackground(new Color(20,20,20));
        
        this.setTitle("Goblin Dungeon v2.0");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        
        setLocationRelativeTo(null); //Start in center of screen
    }
    
    /**
     * Convenience method for showing a message dialog
     * @param message the String message to display
     * @param title the String title of the message dialog
     * @param OptionPaneType the type of message to diplay, from JOptionPane
     */
    public void showInfoDialog(String message, String title, int OptionPaneType) {
        JOptionPane.showMessageDialog(this, message, title, OptionPaneType);
    }
    /**
     * Convenience method for showing an 2-button option dialog
     * @param message String message to display
     * @param title String title of the message dialog
     * @param buttonsText Object array of text to display on buttons
     * @param OptionPaneType the type of message to diplay, from JOptionPane
     * @return true if YES/first button is pressed. False otherwise.
     */
    public boolean showChoiceDialog(String message, String title, Object[] buttonsText, int OptionPaneType) {
        return JOptionPane.showOptionDialog(
                this,
                message, 
                title, 
                JOptionPane.YES_NO_OPTION, 
                OptionPaneType, 
                null, 
                buttonsText, 
                buttonsText[0]
        ) == JOptionPane.YES_OPTION;
    }
    
    public void clear() {
        
    }
    
    /**
     * Pass through method, loads an image into the GamePanel
     * @param image BufferedImage to display.
     */
    public void loadImage(BufferedImage image) {
        gamePanel.loadImage(image);
    }
    
    /**
     * Sets an exit on the UI to be available/unavailable , e.g. so that only 
     * relevant control buttons are enabled.
     * @param dir Direction to set.
     * @param enabled Whether direction is available.
     */
    public void setExit(Directions dir, boolean enabled) {
        gamePanel.setExit(dir, enabled);
    }
    
    /**
     * Sets the text to display in the title panel.
     * @param title String text to display in title panel; must be non-null or 
     * a NullPointerException is thrown.
     */
    public void setGameTitle(String title) {
        if(title == null)
            throw new NullPointerException("Title must be non-null");
        gamePanel.setGameTitle(title);
    }
    /**
     * Sets the text to display in the commentary panel at the bottom - e.g. 
     * "You are in a dark, musty room."
     * @param commentary String text to display - must be non-null.
     */
    public void setGameCommentary(String commentary) {
        if(commentary == null)
            throw new NullPointerException("Commentary must be non-null");
        gamePanel.setCommentary(commentary);
    }
    /*
        Pass through methods
    */
    public void addItem(int ID, String name) { gamePanel.addItem(ID, name); }
    public void addWeapon(int ID, String name) { gamePanel.addWeapon(ID, name); }
    public void removeItem(int ID) { gamePanel.removeItem(ID); }
    public void removeWeapon(int ID) { gamePanel.removeWeapon(ID); }

    
}
