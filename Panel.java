package com.SpaceInvaders2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// -------------------------------------------------------------------------
/**
 *  This is a recreation of the classic Space Invaders game. This class serves
 *  as the main panel for the GUI.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Panel extends JPanel implements KeyListener {
    private Timer t;
    private BufferedImage myImage;
    private Graphics myBuffer;

    private JButton start;
    private JLabel labelLevel;
    private JLabel labelScore;
    private JLabel labelEnd;

    private ArrayList<Invader> list;
    private ArrayList<CannonBall> balls = new ArrayList<CannonBall>();
    private Cannon can;
    private CannonBall ball;

    private int timerCount = 150;
    private int invaderCount = 150;
    private int levelCount = 1;
    private int scoreCount = 0;

    // ----------------------------------------------------------
    /**
     * Create a new Panel object.
     */
    public Panel() {
        setLayout(new FlowLayout());

        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BorderLayout());

        myImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0, 0, 400, 400);

        can = new Cannon(180);
        add(can);
        can.draw(myBuffer);

        start = new JButton("START");
        start.addKeyListener(new KeyList());
        subpanel.add(start, BorderLayout.NORTH);

        labelLevel = new JLabel("\tLevel " + levelCount);
        labelLevel.setForeground(Color.BLACK);
        subpanel.add(labelLevel, BorderLayout.WEST);

        labelScore = new JLabel("Score: " + scoreCount);
        labelScore.setForeground(Color.BLACK);
        subpanel.add(labelScore, BorderLayout.EAST);

        labelEnd = new JLabel("");
        labelEnd.setForeground(Color.BLACK);
        subpanel.add(labelEnd, BorderLayout.SOUTH);

        add(subpanel);

        t = new Timer(timerCount, new Listener());
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(Color.BLACK);
            myBuffer.fillRect(0,0,400,400);
            for(int k = 0; k < list.size(); k++) {
                Invader x = list.get(k);
                x.move(x.getX(), x.getY());
                ArrayList<Integer> xLocs = x.removeLocs();
                for(int a = 0; a < balls.size(); a++) {
                    for(int i = 0; i < xLocs.size(); i++) {
                        if(xLocs.get(i) == balls.get(a).getX()
                            && x.getY() == balls.get(a).getY()) {
                            if(x.isAlive()) {
                                x.die();
                                invaderCount++;
                                scoreCount++;
                            }
                            labelLevel.setText("\tLevel " + levelCount);
                            labelScore.setText("\tScore: " + scoreCount);

                            if(invaderCount == 48) {
                                invaderCount = 0;
                                timerCount = timerCount/2;
                                levelCount++;
                                labelLevel.setText("\tLevel " + levelCount);
                                labelScore.setText("\tScore: " + scoreCount);
                                makeInvaders();
                            }
                        }
                    }
                }

                if(x.getY() == 350) {
                    labelEnd.setText("YOU LOSE");
                    t.stop();
                    scoreCount = 0;
                    levelCount = 0;
                    break;
                }
                x.draw(myBuffer);
            }
            can.draw(myBuffer);
            for(int a = 0; a < balls.size(); a++) {
                balls.get(a).fire(myBuffer);
            }
            repaint();
        }
    }

    // ----------------------------------------------------------
    /**
     * Main method sets up GUI
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel());
        frame.setVisible(true);
        frame.setFocusable(true);
    }

    private class KeyList extends KeyAdapter {
        public void keyPressed(KeyEvent k) {
            if (k.getKeyCode() == KeyEvent.VK_S) {
                makeInvaders();
                labelLevel.setText("\tLevel " + levelCount);
                labelScore.setText("\tScore: " + scoreCount);
                t.start();
            }
            if (k.getKeyCode() == KeyEvent.VK_F) {
                ball = new CannonBall(can.getX()+20,5);
                ball.draw(myBuffer);
                balls.add(ball);
                repaint();
            }
            if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                ball = new CannonBall(can.getX()+20,5);
                ball.draw(myBuffer);
                balls.add(ball);
                repaint();
            }
            if (k.getKeyCode() == KeyEvent.VK_LEFT) {
                can.moveLeft();
            }
            if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
                can.moveRight();
            }
        }

        public void keyReleased(KeyEvent k) {
            //Implementation Not Necessary
        }

        public void keyTyped(KeyEvent k) {
            //Implementation Not Necessary
        }
    }

    public void keyPressed(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void keyReleased(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void keyTyped(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10000, 100000);
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    // ----------------------------------------------------------
    /**
     * Creates list of Invaders with positions in block formation
     */
    public void makeInvaders() {
        list = new ArrayList<Invader>();
        for(int y = 50; y < 150; y += 25) {
            for(int x = 0; x < 300; x += 25) {
                Invader z = new Invader(x, y, 25);
                list.add(z);
            }
        }
    }
}