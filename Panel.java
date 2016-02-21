package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel implements KeyListener
{
   private Timer t;
   private Timer t1;
   private BufferedImage myImage;
   private Graphics myBuffer;
   private ArrayList<Invader> list, removed;
   private Cannon can;
   private CannonBall ball;
   public ArrayList<CannonBall> balls = new ArrayList<CannonBall>();
   public int invCount, timerCount=150;
   public String message = "";
   public ImageIcon space = new ImageIcon(getClass().getResource("NewSpace2.jpg"));
   public JButton start;
   public int level = 1;
   public JLabel labelL;
   public JLabel labelS;
   public JLabel labelEnd;
   public int count = 0;
   public Boolean keys [] = new Boolean[2];

   public Panel()
   {
      setLayout(new FlowLayout());

      JPanel subpanel = new JPanel();
      subpanel.setLayout(new BorderLayout());

      myImage = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,400,400);

      can = new Cannon(180);
      add(can);
      can.draw(myBuffer);

      start = new JButton("START");
      start.addKeyListener(new KeyList());
      subpanel.add(start, BorderLayout.NORTH);

      labelL = new JLabel("\tLevel " + level);
      labelL.setForeground(Color.BLACK);
      subpanel.add(labelL, BorderLayout.WEST);

      labelS = new JLabel("Score: " + count);
      labelS.setForeground(Color.BLACK);
      subpanel.add(labelS, BorderLayout.EAST);

      labelEnd = new JLabel("");
      labelEnd.setForeground(Color.BLACK);
      subpanel.add(labelEnd, BorderLayout.SOUTH);

      add(subpanel);

      t = new Timer(timerCount, new Listener());
   }

   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myBuffer.setColor(Color.BLACK);
         myBuffer.fillRect(0,0,400,400);
         for(int k=0; k<list.size(); k++)
         {
            Invader x = list.get(k);
            x.move(x.getX(),x.getY());
            ArrayList<Integer> xLocs = x.removeLocs();
            for(int a=0; a<balls.size(); a++)
            {
               for(int i=0; i<xLocs.size(); i++)
               {
                  if(xLocs.get(i) == balls.get(a).getX() && x.getY() == balls.get(a).getY())
                  {
                     if(x.isAlive())
                     {
                        x.die();
                        invCount++;
                        count++;
                     }
                     labelL.setText("\tLevel " + level);
                     labelS.setText("\tScore" + count);

                     if(invCount == 48)
                     {
                        invCount = 0;
                        timerCount = timerCount/2;
                        level++;
                        labelL.setText("\tLevel " + level);
                        labelS.setText("\tScore" + count);
                        makeInvaders();
                     }
                  }
               }
            }

            if(x.getY() == 350)
            {
               labelEnd.setText("YOU LOSE");
               t.stop();
               count = 0;
               level = 0;
               break;
            }
            x.draw(myBuffer);
         }
         can.draw(myBuffer);
         for(int a=0; a<balls.size(); a++)
         {
            balls.get(a).fire(myBuffer);
         }
         repaint();
      }
   }

   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Space Invaders");
      frame.setSize(400,400);
      frame.setLocation(200,200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new Panel());
      frame.setVisible(true);
      frame.setFocusable(true);
   }

   private class KeyList extends KeyAdapter
   {
       public void keyPressed(KeyEvent k)
       {
           if (k.getKeyCode() == KeyEvent.VK_S)
           {
               makeInvaders();
               labelL.setText("\tLevel " + level);
               labelS.setText("\tScore" + count);
               t.start();
           }
           if (k.getKeyCode() == KeyEvent.VK_F)
           {
               ball = new CannonBall(can.getX()+20,5);
               ball.draw(myBuffer);
               balls.add(ball);
               repaint();
           }
           if (k.getKeyCode() == KeyEvent.VK_SPACE)
           {
               ball = new CannonBall(can.getX()+20,5);
               ball.draw(myBuffer);
               balls.add(ball);
               repaint();
           }
           if (k.getKeyCode() == KeyEvent.VK_LEFT)
           {
               can.moveLeft();
           }
           if (k.getKeyCode() == KeyEvent.VK_RIGHT)
           {
               can.moveRight();
           }
       }

       public void keyReleased(KeyEvent k)
       {
           //
       }

       public void keyTyped(KeyEvent k)
       {
           //
       }
   }

   public void keyPressed(KeyEvent k)
   {
       //
   }

   public void keyReleased(KeyEvent k)
   {
       //
   }

   public void keyTyped(KeyEvent k)
   {
       //
   }

   public void paintComponent(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.fillRect(0,0,10000,100000);
      g.drawImage(myImage,0,0,getWidth(),getHeight(),null);
   }

   public void makeInvaders()
   {
      list = new ArrayList<Invader>();
      for(int y=50; y<150; y+=25)
      {
         for(int x=0; x<300; x+=25)
         {
            Invader z = new Invader(x,y,25);
            list.add(z);
         }
      }
   }
}








