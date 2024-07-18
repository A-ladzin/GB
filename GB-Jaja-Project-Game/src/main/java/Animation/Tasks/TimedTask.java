package Animation.Tasks;

import Animation.IdleThread;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class TimedTask extends BaseTask{
    int n_iter;
    int count=0;
    public TimedTask(Hero hero, Cycle<BufferedImage> animationPack,int period, int executionTime){
        super(hero,animationPack);
        this.n_iter = executionTime/period;
    }


    @Override
    public void run() {
        count++;
        hero.currentState = animation.next();
        if(n_iter<count) {
            this.cancel();
        }
    }

    @Override
    public boolean cancel() {
        super.cancel();
        try {
            hero.animation = new IdleThread(hero,hero.idleAnimation, 80);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
