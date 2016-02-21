package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Cannon extends JPanel
{
   public int x;
   public int y;
   private Color myColor;
   public ImageIcon cannon = new ImageIcon(getClass().getResource("cannon.jpg"));

   public Cannon(int xPos)
   {
      x = xPos;
      y = 400;
   }

   public int getX()
   {
      return x;
   }

   public void moveLeft()
   {
      x-=25;
   }

   public void moveRight()
   {
      x+=25;
   }

   public void draw(Graphics myBuffer)
   {
      myBuffer.setColor(myColor);
      myBuffer.drawImage(cannon.getImage(),x,y-50,50,50,null);
   }
}




