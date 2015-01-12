/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon;

import java.awt.image.BufferedImage;

/**
 *
 * @author Miles
 */
public class GoalRoom extends Room {

    public GoalRoom(String description, BufferedImage image) {
        super(description,image);
        item = null;
        enemy = null;
    }
    
}
