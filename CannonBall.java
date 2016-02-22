package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;

// -------------------------------------------------------------------------
/**
 *  This class serves to keep track of each CannonBall position and size.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class CannonBall extends JPanel {
    private int x, y, diameter;
    private boolean isAlive;
    private Color myColor;

    // ----------------------------------------------------------
    /**
     * Create a new CannonBall object.
     * @param xPos
     * @param myDiameter
     */
    public CannonBall(int xPos, int myDiameter) {
        x = xPos;
        y = 350;
        diameter = myDiameter;
        isAlive = true;
        myColor = Color.WHITE;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    // ----------------------------------------------------------
    /**
     * Kill CannonBall
     * @return false
     */
    public boolean die() {
        isAlive = false;
        return isAlive;
    }

    // ----------------------------------------------------------
    /**
     * Check if CannonBall is alive or dead
     * @return true or false
     */
    public boolean isAlive() {
        return isAlive;
    }

    // ----------------------------------------------------------
    /**
     * Draw CannonBall to GUI Panel
     * @param myBuffer
     */
    public void draw(Graphics myBuffer) {
        if (isAlive) {
            myBuffer.setColor(myColor);
            myBuffer.fillOval(x, y, diameter, diameter);
        }
    }

    // ----------------------------------------------------------
    /**
     * Fire CannonBall
     * @param myBuffer
     */
    public void fire(Graphics myBuffer) {
        if (isAlive) {
            myBuffer.fillOval(x, y - 25, diameter, diameter);
            y = y - 25;
        }
    }
}
