package Animation.Tasks;

import Animation.IdleThread;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class OneShotTask extends BaseTask{
    int idlePeriod;

    public OneShotTask(Hero hero, Cycle<BufferedImage> animationPack,int idlePeriod){
        super(hero,animationPack);
        this.idlePeriod = idlePeriod;
        hero.setIdle(false);
    }


    @Override
    public void run() {

        if(animation.hasNext())
        {
            hero.currentState = animation.next();
        }
        else{
            try {
                hero.animation = new IdleThread(hero,hero.idleAnimation,idlePeriod);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hero.setIdle(true);
            this.cancel();
        }

    }
}
