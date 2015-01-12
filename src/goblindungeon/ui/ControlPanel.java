/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon.ui;

import goblindungeon.Data;
import goblindungeon.Directions;
import goblindungeon.MoveAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Miles
 */
public class ControlPanel extends GameInfoPanel {
    
    private final JButton bDown = new JButton();
    private final JButton bNorth = new JButton();
    private final JButton bUp = new JButton();
    private final JButton bWest = new JButton();
    private final JButton bEast = new JButton();
    private final JButton bSouth = new JButton();
    
    ControlPanel() {
        setUpActions();
        setUpLayout();
        setUpIcons();
    }
    
    public void setExit(Directions dir, boolean enabled) {
        if(dir == Directions.down)
            bDown.setEnabled(enabled);
        else if(dir == Directions.north)
            bNorth.setEnabled(enabled);
        else if(dir == Directions.up)
            bUp.setEnabled(enabled);
        else if(dir == Directions.west)
            bWest.setEnabled(enabled);
        else if(dir == Directions.east)
            bEast.setEnabled(enabled);
        else if(dir == Directions.south)
            bSouth.setEnabled(enabled);
    }
    
    
    
    private void setUpLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx=0;gbc.gridy=0;
        add(bDown,gbc);
        
        gbc.gridx=1;gbc.gridy=0;
        add(bNorth,gbc);
        
        gbc.gridx=2;gbc.gridy=0;
        add(bUp,gbc);
        
        gbc.gridx=0;gbc.gridy=1;
        add(bWest,gbc);
        
        gbc.gridx=2;gbc.gridy=1;
        add(bEast,gbc);
        
        gbc.gridx=1;gbc.gridy=2;
        add(bSouth,gbc);
        
    }
    
    private void setUpActions() {
        bDown.setAction(new MoveAction(Directions.down));
        bNorth.setAction(new MoveAction(Directions.north));
        bUp.setAction(new MoveAction(Directions.up));
        bWest.setAction(new MoveAction(Directions.west));
        bEast.setAction(new MoveAction(Directions.east));
        bSouth.setAction(new MoveAction(Directions.south));
    }

    private void setUpIcons() {
        
        bDown.setIcon(Data.getIcon("down"));
        bNorth.setIcon(Data.getIcon("north"));
        bUp.setIcon(Data.getIcon("up"));
        bWest.setIcon(Data.getIcon("west"));
        bEast.setIcon(Data.getIcon("east"));
        bSouth.setIcon(Data.getIcon("south"));
    }
    
}
