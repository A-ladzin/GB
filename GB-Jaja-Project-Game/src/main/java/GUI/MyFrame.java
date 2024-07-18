package GUI;


import OOP.sem1.Hero;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MyFrame extends JFrame{
    public MyPanel panel;

    public MyFrame(ArrayList<Hero> heroes) throws IOException {


        panel = new MyPanel(heroes);
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);





    }

    @Override
    public void paint(Graphics g) {
//        this.setSize(Math.min(this.getWidth(),this.getHeight()),Math.min(this.getWidth(),this.getHeight()));
    }



}