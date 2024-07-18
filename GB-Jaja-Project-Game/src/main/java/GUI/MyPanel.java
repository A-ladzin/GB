package GUI;

import OOP.sem1.Hero;
import OOP.sem1.Main;
import Roles.Range;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPanel extends JPanel implements ActionListener {
    public int[] points = new int[]{0,0,0};
    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 800;




    final BufferedImage background = ImageIO.read(new File("src/main/java/GUI/sky-bridgeLowRes.png"));

    JLabel label;
    Timer timer;
    public ArrayList<Hero> heroes;
    MyPanel(ArrayList<Hero> heroes) throws IOException {
        label = new JLabel();
        label.setBounds(500,200,200,200);



        this.heroes = heroes;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));


//        this.setBackground(Color.decode("#1A095C"));
        this.setBackground(Color.decode("#667775"));
        timer = new Timer(1,this);
        timer.start();
        this.add(label);
        label.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        int sideSize = Math.min(this.getWidth(),this.getHeight());
        int xCell = sideSize/10;
        int ySize = (int)(sideSize*0.564);
        int yCell = ySize/10;
        float prop = 0.275f;

//        this.setSize(sideSize,sideSize);
        super.paint(g);


        Graphics2D g2D = (Graphics2D) g;
        Image scaledBackground = background.getScaledInstance(sideSize,sideSize, Image.SCALE_DEFAULT);
        g2D.drawImage(scaledBackground,0,0,null);

        for (int i = 0; i < Main.rips.size(); i++) {
            g2D.drawImage(Main.rips.get(i).currentState,
                    Main.corpses.get(i)[1]*xCell+xCell*Hero.toInt(Main.rips.get(i).flipped),
                    Main.corpses.get(i)[0]*yCell+(int)(sideSize*prop),
                    xCell*(int)(Math.pow(-1,Hero.toInt(Main.rips.get(i).flipped))),
                    xCell,
                    null);
        }

        int width = Math.min(this.getWidth(),this.getHeight());
        int height = Math.min(this.getWidth(),this.getHeight());

;

        ArrayList<Hero> tempHeroes = (ArrayList<Hero>)heroes.clone();
        tempHeroes.sort((h1,h2) -> h1.getLocation().getX() - h2.getLocation().getX());
        for(Hero h: tempHeroes){
            if(h.getTeam()!= 0) {
                g2D.setColor(Color.decode("#9A10AA"));

            }
            else g2D.setColor(Color.decode("#CF8805"));

            g2D.fill3DRect(h.getLocation().getY()*xCell+xCell/9+(int)(h.moveY*xCell)+Hero.toInt(h.flipped)*xCell/5*3,h.getLocation().getX()*yCell+4+(int)(sideSize*prop)+(int)(h.moveX*yCell),xCell/4,xCell/2,true);


            g2D.drawImage(h.currentState, h.getLocation().getY()*xCell+xCell*Hero.toInt(h.flipped)+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+(int)(sideSize*prop)+(int)(h.moveX*yCell),xCell*(int)(Math.pow(-1,Hero.toInt(h.flipped))),xCell,null);
            g2D.setColor(Color.GREEN);
            g2D.fillRect(h.getLocation().getY()*xCell+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+(int)(sideSize*prop)+(int)(h.moveX*yCell), xCell/10,xCell);
            g2D.setColor(Color.RED);
            g2D.fillRect(h.getLocation().getY()*xCell+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+(int)(sideSize*prop)+(int)(h.moveX*yCell), xCell/10,(int)((float)(h.getHealthMax()-h.getHP())/h.getHealthMax()*xCell));
            g2D.setColor(Color.decode("#7A7A7A"));
            g2D.drawRect(h.getLocation().getY()*xCell+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+(int)(sideSize*prop)+(int)(h.moveX*yCell), xCell/10,(int)((float)(h.getHealthMax()-h.getHP())/h.getHealthMax()*xCell));
            g2D.drawRect(h.getLocation().getY()*xCell+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+(int)(sideSize*prop)+(int)(h.moveX*yCell), xCell/10,xCell);


            if (h.getType() == "Range"){
                g2D.setColor(Color.YELLOW);
                for (int i = 0; i < ((Range)h).getAmmoParts()/10; i++) {
                    g2D.drawLine(h.getLocation().getY()*xCell+xCell-xCell/10+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+i*xCell/16+(int)(sideSize*prop)+(int)(h.moveX*yCell),h.getLocation().getY()*xCell+xCell-xCell/32+(int)(h.moveY*xCell),h.getLocation().getX()*yCell+i*xCell/16+(int)(sideSize*prop)+(int)(h.moveX*yCell));
                    if (i>15) break;
                }
            }
            label.setFont(new Font("Serif", Font.BOLD, 64));
            label.setBounds(sideSize+100,200,sideSize,200);
            label.setText(Arrays.toString(points));




        }






    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }



}
