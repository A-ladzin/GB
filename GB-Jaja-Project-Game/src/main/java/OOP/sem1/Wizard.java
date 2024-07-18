package OOP.sem1;

import Animation.AttackThread;
import Animation.IdleThread;
import Animation.ThreadUtil;
import OOP.sem1.util.Cycle;
import Roles.Support;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Wizard extends Support {


    static public BufferedImage idle;
    static public Stack<BufferedImage> staticHealAnimation = new Stack<>();
    static public Stack<BufferedImage> staticUndressAnimation = new Stack<>();

    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static  public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {

            for (int i = 0; i < 10; i++) {
                staticUndressAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Wizard/anim_000"+i+"_Fairy_02__ATTACK_00"+i+".png.png")));
                staticHealAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Wizard/anim_003"+i+"_Fairy_02__FLY_ATTACK_00"+i+".png.png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Wizard/anim_001"+i+"_Fairy_02__DIE_00"+i+".png.png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Wizard/anim_005"+i+"_Fairy_02__IDLE_00"+i+".png.png")));


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public Wizard(String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(50, 50, 1, new int[] { 3, 4 }, nameHero, posX, posY,nTeam);
        currentState = idle;
        flipped = true;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticUndressAnimation);
        actionAnimation = new Cycle<BufferedImage>(staticHealAnimation);
        this.animation = new IdleThread(this,idleAnimation,80);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Wizard: " + super.toString());
    }

    @Override
    public void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException {
        if (isDead()) return;
        for (int i = 0; i < 3; i++) {
            switch (rand.nextInt(0,2)){
                case 0:
                    if(heal(allies)) break;
                    undress(enemies);
                    break;
                case 1:
                    if(undress(enemies)) break;
                    heal(allies);
                    break;
            }
        }

    }

    private boolean undress(ArrayList<Hero> enemies) throws InterruptedException {
        for (Hero h: enemies)
        {
            if (!h.isUndressed() && !(h instanceof Peasant))
            {
                if (h.getLocation().getY() < this.getLocation().getY()) {
                    flipped = true;
                }
                this.animation.cancel();
                this.animation = new AttackThread(this,attackAnimation,60,80);
                h.setUndressed();
                return true;
            }
        }
        return false;
    }
}
