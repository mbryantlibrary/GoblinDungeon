package goblindungeon.ui;

import goblindungeon.Directions;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * The GamePanel arranges and displays all the relevant game information panels.
 * It also mediates access to them.
 * @author Miles
 */
public class GamePanel extends JPanel {
    
    
    GamePanel() {
        setUpLayout();
        setUpFonts();
        setOpaque(false);
    }
    
    /**
     * Sets the text to display in the title panel at the top - e.g. "Health: 100".
     * @param title String to display. Must be non-null or a NullPointerException
     * is thrown.
     */
    public void setGameTitle(String title) {
        if(title == null)
            throw new NullPointerException("Title must be non-null.");
        pnlTitle.setText(title);
    }
    
    /**
     * Sets the text to display in the commentary panel at the bottom - e.g. 
     * "You are in a dark, musty room."
     * @param commentary String text to display - must be non-null.
     */
    public void setCommentary(String commentary) {
        if(commentary == null)
            throw new NullPointerException("Commentary must be non-null");
        pnlComm.setText(commentary);
    }
    
    public void setExit(Directions dir, boolean enabled) {
        pnlControl.setExit(dir, enabled);
    }
    
    public void clear() {
        setGameTitle(""); setCommentary("");
        pnlItem.clear();
        
    }
    
    public void loadImage(BufferedImage image) {
        pnlImage.loadImage(image);
    }
    
    public void addItem(int ID, String name) { pnlItem.addItem(ID, name); }
    public void addWeapon(int ID, String name) { pnlItem.addWeapon(ID, name); }
    public void removeItem(int ID) { pnlItem.removeItem(ID); }
    public void removeWeapon(int ID) { pnlItem.removeWeapon(ID); }
    
    /**
     * Panels are arranged in a GridBagLayout (see report for a visual diagram 
     * of arrangement). 
     */
    private void setUpLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2,2,2,2);
        
        //Title
        gbc.gridx=0;gbc.gridy=0;
        gbc.weighty = 0.1; //relative height; not very thick
        gbc.gridwidth = 2; //covers two cells horizontally
        add(pnlTitle, gbc);
        
        //Image
        gbc.gridx=0;gbc.gridy=1;
        gbc.weightx = 0.7; gbc.weighty = 0.6; //relatively wide and high
        gbc.gridwidth = 1; 
        add(pnlImage, gbc);
        
        //Items
        gbc.gridx=1;gbc.gridy=1;
        gbc.weightx = 0.3; gbc.weighty = 0.6;
        add(pnlItem, gbc);
        
        //Commentary
        gbc.gridx=0;gbc.gridy=2;
        gbc.weightx = 0.7; gbc.weighty = 0.2;
        gbc.gridheight = 1;
        add(pnlComm, gbc);
        
        //Controls
        gbc.gridx=1;gbc.gridy=2;
        gbc.weightx = 0.3; gbc.weighty = 0.2;
        gbc.gridwidth = 2;
        add(pnlControl, gbc);
        
        revalidate(); //apply changes
    }
    
    /**
     * Sets fonts of the TextPanels
     */
    private void setUpFonts() {
        pnlTitle.setTextFont(new Font("SansSerif", Font.BOLD, 20));
        pnlComm.setTextFont(new Font("SansSerif", Font.PLAIN, 15));
        revalidate();
    }
    //Each of the information panels
    private final TextPanel pnlTitle = new TextPanel();
    private final ImagePanel pnlImage = new ImagePanel();
    private final ItemPanel pnlItem = new ItemPanel();
    private final TextPanel pnlComm = new TextPanel();
    private final ControlPanel pnlControl = new ControlPanel();

    
}
