package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;

// -------------------------------------------------------------------------
/**
 *  This class serves to keep track of the Cannon position
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Cannon extends JPanel {
    private Color myColor;

    public int x, y;
    public ImageIcon cannon = new ImageIcon(getClass().getResource("cannon.jpg"));

    // ----------------------------------------------------------
    /**
     * Create a new Cannon object.
     * @param xPos
     */
    public Cannon(int xPos) {
        x = xPos;
        y = 400;
    }

    public int getX() {
        return x;
    }

    // ----------------------------------------------------------
    /**
     * Move Cannon 25 pixels right across GUI Panel
     */
    public void moveLeft() {
        x -= 25;
    }

    // ----------------------------------------------------------
    /**
     * Move Cannon 25 pixels right across GUI Panel
     */
    public void moveRight() {
        x += 25;
    }

    // ----------------------------------------------------------
    /**
     * Draw Cannon to GUI Panel
     * @param myBuffer
     */
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(myColor);
        myBuffer.drawImage(cannon.getImage(),x,y-50,50,50,null);
    }
}