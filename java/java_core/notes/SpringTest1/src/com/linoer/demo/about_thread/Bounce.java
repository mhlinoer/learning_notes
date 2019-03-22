package com.linoer.demo.about_thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BounceFrame extends JFrame{
    private BallComponent component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BounceFrame(){
        setTitle("Bounce");
        component = new BallComponent();
        add(component, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel , BorderLayout.SOUTH) ;
        pack();
    }
    private void addButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }
    public void addBall ()
    {
        try {
            Ball ball = new Ball();
            component.add(ball);
            Runnable r = () ->{
              try {
                  for(int i=1; i<STEPS; i++){
                      ball.move(component.getBounds());
                      component.repaint();
                      Thread.sleep(DELAY);
                  }
              }catch (Exception e){
                  e.printStackTrace();
              }
            };
//            for (int i = 1; i <= STEPS; i++) {
//                ball.move(component.getBounds());
//                component.paint(component.getGraphics());
//                Thread.sleep(DELAY);
//            }
            Thread t = new Thread(r);
            t.start();
        }catch(Exception e){
            e.printStackTrace();
            }
        }
}

public class Bounce {
    public static void main(String[] args){
        EventQueue.invokeLater(
                () ->{
                    JFrame frame = new BounceFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
        );
    }
}
