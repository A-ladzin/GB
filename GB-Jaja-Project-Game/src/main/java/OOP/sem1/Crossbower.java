package OOP.sem1;


import Animation.IdleThread;
import OOP.sem1.util.Cycle;
import Roles.Range;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class Crossbower extends Range {

    public static BufferedImage idle;



    static public Stack<BufferedImage> staticAttackAnimation = new Stack<>();
    static public Stack<BufferedImage> staticMoveAnimation = new Stack<>();

    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static public Stack<BufferedImage> staticIdleAnimation = new Stack<>();


    static {
        try {
            for (int i = 0; i <10 ; i++) {
                staticAttackAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Crossbower/200x200_000"+i+"_Warrior_03__ATTACK_00"+i+".png.png")));
                staticMoveAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Crossbower/200x200_005"+i+"_Warrior_03__RUN_00"+i+".png.png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Crossbower/200x200_001"+i+"_Warrior_03__DIE_00"+i+".png.png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Crossbower/200x200_003"+i+"_Warrior_03__IDLE_00"+i+".png.png")));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public Crossbower(String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(90, 90, 4, new int[]{17, 28}, nameHero, posX, posY,nTeam);
        rangeMaxDamage = 5;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        moveAnimation = new Cycle<BufferedImage>(staticMoveAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticAttackAnimation);
        this.animation = new IdleThread(this,idleAnimation,80);

    }
    protected Vector2 position;


    @Override
    public String toString() {
        return ("Crossbower: " + super.toString());
    }

}
