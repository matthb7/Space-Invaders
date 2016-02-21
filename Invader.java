package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  This class serves to keep track of each Invader position and size.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Invader extends JPanel {
    private Color myColor;

    public int x, y, diameter;
    public int oneX, oneY;
    public boolean isAlive;
    public ImageIcon invader = new ImageIcon(
        getClass().getResource("space-invaders.jpg"));

    // ----------------------------------------------------------
    /**
     * Create a new Invader object.
     * @param xPos
     * @param yPos
     * @param myDiameter
     */
    public Invader(int xPos, int yPos, int myDiameter) {
        x = xPos;
        y = yPos;
        oneX = xPos;
        oneY = yPos;
        diameter = myDiameter;
        myColor = Color.GREEN;
        isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    // ----------------------------------------------------------
    /**
     * Change Invader Coordinates
     */
    public void move(int newX, int newY) {
        if (x - 100 < oneX && y == oneY) {
            x = newX + 25;
        }
        else if(x - 100 == oneX && y == oneY) {
            y = newY + 25;
        }
        else if(x != oneX && y - 25 == oneY){
            x = newX - 25;
        }
        else if(x == oneX && y -25 == oneY) {
            y = newY + 25;
            oneY = y;
        }
    }

    // ----------------------------------------------------------
    /**
     * Kill Invader
     * @return false
     */
    public boolean die()
    {
        isAlive = false;
        return isAlive;
    }

    // ----------------------------------------------------------
    /**
     * Check if Invader is alive or dead
     * @return true or false
     */
    public boolean isAlive()
    {
        return isAlive;
    }

    // ----------------------------------------------------------
    /**
     * Remove Invader
     * @return ArrayList of locations
     */
    public ArrayList<Integer> removeLocs()
    {
        ArrayList<Integer> xLocs = new ArrayList<Integer>();
        xLocs.add(getX() - 5);
        xLocs.add(getX() - 4);
        xLocs.add(getX() - 3);
        xLocs.add(getX() - 2);
        xLocs.add(getX() - 1);
        xLocs.add(getX());
        xLocs.add(getX() + 1);
        xLocs.add(getX() + 2);
        xLocs.add(getX() + 3);
        xLocs.add(getX() + 4);
        xLocs.add(getX() + 5);

        return xLocs;
    }

    // ----------------------------------------------------------
    /**
     * Draw Invader to GUI Panel
     * @param myBuffer
     */
    public void draw(Graphics myBuffer)
    {
        if (isAlive) {
            myBuffer.setColor(myColor);
            myBuffer.drawImage(
                invader.getImage(), x, y, diameter, diameter, null);
        }
    }
}




