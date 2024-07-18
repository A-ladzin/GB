package Animation;

import Animation.Tasks.IdleTask;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class IdleThread extends BaseThread
{


        public IdleThread(Hero hero, Cycle<BufferedImage> animationPack, int period) throws InterruptedException {
        super(hero,animationPack,period,new IdleTask(hero,animationPack));
        start();

    }


    public void start() {
        scheduleAtFixedRate(task,0,period);
    }

}

