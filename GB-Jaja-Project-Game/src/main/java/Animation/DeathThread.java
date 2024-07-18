package Animation;

import Animation.Tasks.DeathTask;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;


public class DeathThread extends  BaseThread{


        public DeathThread(Hero hero, Cycle<BufferedImage> animationPack, int period) throws InterruptedException {
            super(hero,animationPack,period, new DeathTask(hero,animationPack));
            start();
        }


        public void start() {
            this.scheduleAtFixedRate(task,0,period);
        }
}



