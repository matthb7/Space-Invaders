package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Invader extends JPanel
{
   public int x;
   public int y;
   public int diameter;
   private Color myColor;
   public int oneX;
   public int oneY;
   public boolean isAlive;
   public ImageIcon invader = new ImageIcon(getClass().getResource("space-invaders.jpg"));
   public int steps,a,b,c;

   public Invader(int xPos, int yPos, int myDiameter)
   {
      x = xPos;
      y = yPos;
      oneX = xPos;
      oneY = yPos;
      diameter = myDiameter;
      myColor = Color.GREEN;
      isAlive = true;
   }

   public int getX()
   {
      return x;
   }

   public int getY()
   {
      return y;
   }

   public int getDiameter()
   {
      return diameter;
   }

   public void move(int newX, int newY)
   {
      if(x-100<oneX && y==oneY)
      {
         x = newX+25;
      }
      else if(x-100==oneX && y==oneY)
      {
         y = newY+25;
      }
      else if(x!=oneX && y-25==oneY)
      {
         x = newX-25;
      }
      else if(x==oneX && y-25==oneY)
      {
         y = newY+25;
         oneY = y;
      }
   }

   public boolean die()
   {
      isAlive = false;
      return isAlive;
   }

   public boolean isAlive()
   {
      return isAlive;
   }

   public ArrayList removeLocs()
   {
      ArrayList<Integer> xLocs = new ArrayList<Integer>();
      xLocs.add(getX()-5);
      xLocs.add(getX()-4);
      xLocs.add(getX()-3);
      xLocs.add(getX()-2);
      xLocs.add(getX()-1);
      xLocs.add(getX());
      xLocs.add(getX()+1);
      xLocs.add(getX()+2);
      xLocs.add(getX()+3);
      xLocs.add(getX()+4);
      xLocs.add(getX()+5);

      return xLocs;
   }

   public void draw(Graphics myBuffer)
   {
      if(isAlive)
      {
         myBuffer.setColor(myColor);
         myBuffer.drawImage(invader.getImage(),x,y,diameter,diameter,null);
      }
   }
}




