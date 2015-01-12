/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon.ui;

import goblindungeon.HealthUsedAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Miles
 */
public class ItemPanel extends GameInfoPanel {
    
    private final ListPanel items = new ListPanel("Items:");
    private final ListPanel weapons = new ListPanel("Weapons:");
    
    public enum ItemType {
        ITEM,WEAPON
    }
    
    ItemPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(items);add(weapons);
        setOpaque(false);
    }
    
    public void addItem(int ID, String name) {
        items.add(new ListItem(ID, name, ItemType.ITEM));
        revalidate();
    }
    
    public void addWeapon(int ID, String name) {
        weapons.add(new ListItem(ID, name, ItemType.WEAPON));
        revalidate();
    }
    
    public void removeItem(int ID) {
        items.removeItem(ID);
        revalidate();
    }
    
    public void removeWeapon(int ID) {
        weapons.removeItem(ID);
        revalidate();
    }
    
    public void clear() {
        items.clear(); weapons.clear();
    }
    
    private class ListPanel extends JPanel {
        ListPanel(String title) {
            setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
            JLabel lblTitle = new JLabel(title);
            add(lblTitle);
            lblTitle.setForeground(Color.WHITE);
            lblTitle.setFont(new Font("SansSerif",Font.BOLD,15));
            setOpaque(false);
        }
        public void removeItem (int ID) {
            for(Component c : getComponents()) {
                if(c instanceof ListItem) {
                    ListItem item = (ListItem)c;
                    if(ID == item.ID) {
                        remove(item);
                        return;
                    }
                }
            }
            throw new IndexOutOfBoundsException("Item with ID " + ID + " not found in ListPanel");
        }
        public void clear() {
            for(Component c : getComponents()) {
                if(c instanceof ListItem) {
                    remove(c);
                }
            }
        }
    }
    
    private class ListItem extends JPanel {        
        private final int ID;
        private final String name;
        private final JButton btnUse = new JButton();
        private final JLabel lblName = new JLabel();
        
        ListItem(int ID, String name, ItemType type) {
            this.ID = ID; this.name = name;
            lblName.setText(name);
            lblName.setForeground(Color.WHITE);
            lblName.setFont(new Font("SansSerif",Font.PLAIN,12));
            
            setLayout(new FlowLayout());
            add(lblName);
            
            if(type == ItemType.ITEM) {
                btnUse.setAction(new HealthUsedAction(ID));
                btnUse.setText("Use"); 
                add(btnUse);
            }
            btnUse.setForeground(new Color(40,40,40));
            
            
            setOpaque(false);
            setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        //Getters
        public int getID() {
            return ID;
        }
        public String getItemName() {
            return name;
        }
    }
}
