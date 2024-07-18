package Animation.Tasks;

import Animation.DeathThread;
import OOP.sem1.Hero;
import OOP.sem1.util.Cycle;

import java.awt.image.BufferedImage;

public class DeathTask extends BaseTask{
    public DeathTask(Hero hero, Cycle<BufferedImage> animationPack){
        super(hero,animationPack);
    }

    @Override
    public void run() {
        if (animation.hasNext()){
            hero.currentState = animation.next();
        }
        else{
            this.cancel();
        }


    }
}
