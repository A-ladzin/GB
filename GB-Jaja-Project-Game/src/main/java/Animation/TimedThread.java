package Animation;

import Animation.Tasks.IdleTask;
import Animation.Tasks.TimedTask;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class TimedThread extends  BaseThread{
    int executionTime;
    public TimedThread(Hero hero, Cycle<BufferedImage> animationPack, int period, int executionTime) throws InterruptedException {
        super(hero,animationPack,period,new TimedTask(hero,animationPack,period,executionTime));
        start();
    }



    public void start() {
        scheduleAtFixedRate(task,0,period);

}
}
