package com.linoer.demo.about_thread;

import javax.swing.*;
import java.awt.*;

public class BounceThread {
    public static void main(String[] args){
        EventQueue.invokeLater(
                ()->{
                    JFrame frame = new BounceFrame();
                    frame.setTitle("BounceThread");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
        );
    }
}
