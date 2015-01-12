/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon.ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Miles
 */
public class GameInfoPanel extends JPanel {
    
    GameInfoPanel() {
        setBorder();
        setOpaque(false);
        setForeground(Color.WHITE);
    }
    
    protected final void setBorder() {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }
}
