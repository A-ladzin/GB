package OOP.sem1;

import Animation.AttackThread;
import Animation.IdleThread;
import Animation.ThreadUtil;
import Interfaces.Disarm;
import OOP.sem1.util.Cycle;
import Roles.Melee;
import Roles.Range;
import Roles.Support;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Monk extends Support implements Disarm{



    static public BufferedImage idle;
    static public Stack<BufferedImage> staticHealAnimation = new Stack<>();
    static public Stack<BufferedImage> staticDisarmAnimation = new Stack<>();

    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static  public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {

            for (int i = 0; i < 10; i++) {
                staticHealAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Monk/Fairy_01__FLY_ATTACK_00"+i+".png")));
                staticDisarmAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Monk/Fairy_01__ATTACK_00"+i+".png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Monk/Fairy_01__DIE_00"+i+".png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Monk/Fairy_01__IDLE_00"+i+".png")));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    public Monk(String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(50, 50, 1, new int[] { 2, 3 }, nameHero, posX, posY,nTeam);
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticDisarmAnimation);
        actionAnimation = new Cycle<BufferedImage>(staticHealAnimation);

        this.animation = new IdleThread(this,idleAnimation,80);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Монах: " + super.toString());
    }

    @Override
    public void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException {
        if (isDead()) return;

        for (int i = 0; i < 3; i++) {
            switch (rand.nextInt(0,2)){


                case 0:
                    if(heal(allies)) break;
                    disarm(enemies);
                    break;
                case 1:
                    if(disarm(enemies)) break;
                    heal(allies);
                    break;
            }
        }

    }
    private boolean disarm(ArrayList<Hero> enemies) throws InterruptedException {
        Hero target = null;
        for(Hero h: enemies){
            if (h.isDead()) continue;
            if(h.getType().equals("Range") || h.getType().equals("Melee") || h.getType().equals("Support"))
            {
                if (!h.isDisarmed()) target = h;
                break;
            }
        }

        if(target == null) return false;
        if (target.getLocation().getY() < this.getLocation().getY()) {
            flipped = true;
        } else {
            flipped = false;
        }
        this.animation.cancel();
        this.animation = new AttackThread(this,attackAnimation,60,80);
        target.setDisarmed();
        return true;
    }

}

