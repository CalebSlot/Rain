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
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Norway Today netbeans Tools options font e colors
 * @author Alessandro
 */
public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    
    public static final int FRAME_WIDTH  = 300;
    public static final float RESOULTION_W = 16f / 9f;
    public static final float RESOLUTION_H = 9f / 16f;
    public static final int FRAME_HEIGHT = (int)(FRAME_WIDTH * RESOLUTION_H);
    public static final int SCALE  = 3;
    
    private Thread m_oThread;
    private JFrame m_oFrame;
    private boolean m_bRunning = false;
    
    //this will be our raster image where we will be write custom pixels value
    private BufferedImage m_oImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_INT_RGB);
    //the raw pixel as ints
    private int[] m_viPixel = ((DataBufferInt)m_oImage.getRaster().getDataBuffer()).getData();
            
    public Game()
    {
        Dimension size = new Dimension(FRAME_WIDTH * SCALE,FRAME_HEIGHT * SCALE);
        setPreferredSize(size);
        m_oFrame = new JFrame();
    }
    
    public synchronized void start()
    {
      m_bRunning = true;
      m_oThread = new Thread(this,"Display");
      m_oThread.start();
    }
    public synchronized void stop()
    {
        m_bRunning = false;
        try {
            m_oThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run()
    {
        
      
       
       while(m_bRunning)
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
