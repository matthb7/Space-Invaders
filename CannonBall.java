package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class CannonBall extends JPanel
{
   public int x;
   public int y=350;
   public int diameter;
   private Color myColor;
   public int oneX;
   public int oneY=350;
   public boolean isAlive;

   public CannonBall(int xPos, int myDiameter)
   {
      x = xPos;
      y = 350;
      oneX = xPos;
      isAlive = true;
      diameter = myDiameter;
      myColor = Color.WHITE;
   }

   public int getY()
   {
      return y;
   }

   public int getX()
   {
      return x;
   }

   public boolean isAlive()
   {
      return isAlive;
   }

   public boolean die()
   {
      isAlive = false;
      return isAlive;
   }

   public void draw(Graphics myBuffer)
   {
      if(isAlive)
      {
         myBuffer.setColor(myColor);
         myBuffer.fillOval(x,y,diameter,diameter);
      }
   }

   public void fire(Graphics myBuffer)
   {
      if(isAlive)
      {
         myBuffer.fillOval(x,y-25,diameter,diameter);
         y = y-25;
      }
   }
}
