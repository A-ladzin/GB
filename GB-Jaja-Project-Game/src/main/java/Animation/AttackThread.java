package Animation;

import Animation.Tasks.OneShotTask;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class AttackThread extends BaseThread {

    public AttackThread(Hero hero, Cycle<BufferedImage> animationPack, int period,int idlePeriod) throws InterruptedException {
        super(hero, animationPack, period,new OneShotTask(hero,animationPack,idlePeriod));
        start();
    }

    public void start() {
        this.scheduleAtFixedRate(task,0,period);
    }
}



