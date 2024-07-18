package Animation.Tasks;

import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;
import java.util.TimerTask;

abstract public class BaseTask extends TimerTask {
    Hero hero;
    Cycle<BufferedImage> animation;

    public BaseTask(Hero hero, Cycle<BufferedImage> animationPack){
        this.hero = hero;
        this.animation = animationPack;
    }



}
