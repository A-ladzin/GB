package Animation;

import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

abstract public class BaseThread extends Timer{

    public TimerTask task;
    protected Hero hero;
    protected Cycle<BufferedImage> animation;
    protected int period;

    public BaseThread(Hero hero, Cycle<BufferedImage> animationPack, int period, TimerTask task) throws InterruptedException {
        this.hero = hero;
        this.animation = animationPack;
        this.period = period;
        this.task = task;

    }

}
