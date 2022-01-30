/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Alessandro
 */
public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    
    public static int WIDTH  = 300;
    public static int HEIGHT = WIDTH / 16 * 9;
    public static int SCALE  = 3;
    
    private Thread m_oThread;
    private JFrame m_oFrame;
    private boolean running = false;
    
    public Game()
    {
        Dimension size = new Dimension(WIDTH * SCALE,HEIGHT * SCALE);
        setPreferredSize(size);
        m_oFrame = new JFrame();
    }
    
    public synchronized void start()
    {
      running = true;
      m_oThread = new Thread(this,"Display");
      m_oThread.start();
    }
    public synchronized void stop()
    {
        running = false;
        try {
            m_oThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run()
    {
        
      
       
       while(running)
       {
           //will be done at our constraints
                   update();
           //will be done as fast as possible
                   render();
       }
       
    }

    private void update() 
    {
        System.out.println("Running......");
    }
    
    public static void main(String args[])
    {       
      Game oGame = new Game();
      oGame.m_oFrame.setResizable(false);
      oGame.m_oFrame.setTitle("Rain");
      oGame.m_oFrame.add(oGame);
      oGame.m_oFrame.pack();
      oGame.m_oFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      oGame.m_oFrame.setLocationRelativeTo(null);
      oGame.m_oFrame.setVisible(true);
      oGame.start();
     }

    private void render()
    {
     BufferStrategy bs = getBufferStrategy();
     if(bs == null)
     {
         createBufferStrategy(3);
         return;
     }
     
     
     //current graphic context
     Graphics g = bs.getDrawGraphics();
     //drow suffs here...
     //draw on the temp buffer
     
     drawBlackRectangleFullSize(g);
     
     //relase current display resources associated with the current buffer
     g.dispose();
     
     //display the next available buffer (swap)
     bs.show();
    }

    private void drawBlackRectangleFullSize(Graphics g) 
    {
      g.setColor(Color.black);
      g.fillRect(0,0, getWidth(), getHeight());
    }
            
            
}
