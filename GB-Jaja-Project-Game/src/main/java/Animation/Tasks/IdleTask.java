package Animation.Tasks;

import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class IdleTask extends BaseTask {


    public IdleTask(Hero hero, Cycle<BufferedImage> animationPack){
        super(hero,animationPack);
    }

    @Override
    public void run() {

        hero.currentState = animation.next();
    }
}
