/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblindungeon.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Miles
 */
public class ImagePanel extends GameInfoPanel {
    
    private BufferedImage image;
    
    public void loadImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        if(image != null) {
            double dx = (double)getWidth() / (double)image.getWidth();
            double dy = (double)getHeight() / (double)image.getHeight();
            double scale = 0.0;
            int newX,newY,newW,newH;
            if(dx < dy) {
                //scale to X
                newX = 0;
                scale = dx;
                newY = (int)Math.round((getHeight() - image.getHeight() * scale)/ 2);
            } else {
                //scale to Y
                newY = 0;
                scale = dy;
                newX = (int)Math.round((getWidth() - image.getWidth() * scale)/ 2);
            }
            newW = (int)Math.round(image.getWidth() * scale);
            newH = (int)Math.round(image.getHeight() * scale);
            g2.drawImage(image, newX, newY, newW,newH,null);
        }
    }
    
    
    
}
