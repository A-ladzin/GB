package OOP.sem1;

import Animation.IdleThread;
import OOP.sem1.util.Cycle;
import Roles.Melee;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Pikeman extends Melee {


    static public BufferedImage idle;
    static public Stack<BufferedImage> staticMoveAnimation = new Stack<>();


    static public Stack<BufferedImage> staticAttackAnimation = new Stack<>();
    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();

    static public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {

            for (int i = 0; i < 10; i++) {
                staticAttackAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Pikeman/anim_000"+i+"_Warrior_01__ATTACK_00"+i+".png.png")));
                staticMoveAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Pikeman/anim_005"+i+"_Warrior_01__RUN_00"+i+".png.png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Pikeman/anim_001"+i+"_Warrior_01__DIE_00"+i+".png.png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Pikeman/anim_003"+i+"_Warrior_01__IDLE_00"+i+".png.png")));

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public Pikeman(String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(130, 130, 6, new int[] { 19, 32 }, nameHero, posX, posY,nTeam);
        currentState = idle;
        moveSpeed = 0.5f;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        moveAnimation = new Cycle<BufferedImage>(staticMoveAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticAttackAnimation);
        this.animation = new IdleThread(this,idleAnimation,80);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Warrior: " + super.toString());
    }

    @Override
    public void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException {
        if (isDead()) return;
        Hero enemy = findClosestEnemy(enemies);
        if(hit(enemy)) return;
        else{
            move(enemy);
            hit(enemy);
        }

    }

}
