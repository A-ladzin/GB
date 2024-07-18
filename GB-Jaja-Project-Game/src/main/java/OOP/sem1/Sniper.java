package OOP.sem1;

import Animation.IdleThread;
import OOP.sem1.util.Cycle;
import Roles.Range;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Sniper extends Range {

    static public BufferedImage idle;
    static public Stack<BufferedImage> staticAttackAnimation = new Stack<>();
    static public Stack<BufferedImage> staticMoveAnimation = new Stack<>();

    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {
            for (int i = 0; i <6 ; i++) {
                staticAttackAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Sniper/attack"+i+".png")));
                staticMoveAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Sniper/2_entity_000_RUN_00"+i+".png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Sniper/2_entity_000_DIE_00"+i+".png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Sniper/2_entity_000_IDLE_00"+i+".png")));
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public Sniper(String nameHero, int posX, int posY, int nTeam) throws IOException, InterruptedException {
        super(90, 90, 5, new int[] { 10, 30 }, nameHero, posX, posY,nTeam);
        rangeMaxDamage = 7;
        currentState = idle;
        flipped = true;
        maxMovePoints = 4;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        moveAnimation = new Cycle<BufferedImage>(staticMoveAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticAttackAnimation);
        this.animation = new IdleThread(this,idleAnimation,80);

    }

    protected Vector2 position;



    @Override
    public String toString() {
        return ("Sniper: " + super.toString());
    }

}
