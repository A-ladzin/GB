package OOP.sem1;

import Animation.IdleThread;
import Interfaces.InjuredSearch;
import Interfaces.WeakestSearch;
import Navigator.Navigator;
import OOP.sem1.util.Cycle;
import Roles.Melee;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Rogue extends Melee implements WeakestSearch {

    static public BufferedImage idle;
    static public Stack<BufferedImage> staticAttackAnimation = new Stack<>();
    static public Stack<BufferedImage> staticMoveAnimation = new Stack<>();

    static public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {

            for (int i = 0; i < 10; i++) {
                staticAttackAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Rogue/anim_000" + i + "_Warrior_02__ATTACK_00" + i + ".png.png")));
                staticMoveAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Rogue/anim_005" + i + "_Warrior_02__RUN_00" + i + ".png.png")));
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Rogue/anim_001" + i + "_Warrior_02__DIE_00" + i + ".png.png")));
                staticIdleAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Rogue/anim_003" + i + "_Warrior_02__IDLE_00" + i + ".png.png")));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public Rogue(String nameHero, int posX, int posY, int nTeam) throws IOException, InterruptedException {
        super(60, 80, 13, new int[]{18, 26}, nameHero, posX, posY, nTeam);
        moveSpeed = 0.8f;
        currentState = idle;
        flipped = true;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        moveAnimation = new Cycle<BufferedImage>(staticMoveAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        attackAnimation = new Cycle<BufferedImage>(staticAttackAnimation);
        this.animation = new IdleThread(this, idleAnimation, 80);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Rogue: " + super.toString());
    }


    @Override
    public void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException {
        if (isDead()) return;
        ArrayList<Hero> weakest = findWeakest(enemies);
        charge(weakest, enemies);

    }


    public boolean charge(ArrayList<Hero> weakest, ArrayList<Hero> enemies) throws InterruptedException {

        int index =0;
        Hero enemy = weakest.get(index);
        if (hit(enemy)) return true;
        Stack<int[]> path = Navigator.findPath(Hero.field,
                this.getLocation().getX(),
                this.getLocation().getY(),
                enemy.getLocation().getX(),
                enemy.getLocation().getY());
        while(path == null)
        {
            index++;
            if(index >= weakest.size()) return false;
            enemy = weakest.get(index);
            path = Navigator.findPath(Hero.field,
                    this.getLocation().getX(),
                    this.getLocation().getY(),
                    enemy.getLocation().getX(),
                    enemy.getLocation().getY());
        }

        path.pop();

        if ( !path.isEmpty() && field[path.firstElement()[0] - 1][path.firstElement()[1] - 1] != 0) path.remove(0);

        this.animation.cancel();

        int movePoints = 3;
        this.animation = new IdleThread(this, moveAnimation, 80);

        while (movePoints > 0 && !path.isEmpty()) {
//            Hero closest = findClosestEnemy(enemies);
//            if(hit(closest))return true;


            movePoints--;
            int[] nextPos = path.pop();
            if (nextPos[1] - 1 < this.getLocation().getY()) {
                flipped = true;
                while (nextPos[1] - 1 < this.getLocation().getY() + moveY) {
                    moveY -= 0.1f * moveSpeed;
                    Thread.sleep(20);
                }
            } else if (nextPos[1] - 1 > this.getLocation().getY()) {
                flipped = false;
                while (nextPos[1] - 1 > this.getLocation().getY() + moveY) {
                    moveY += 0.1f * moveSpeed;
                    Thread.sleep(20);
                }
            } else if (nextPos[0] - 1 < this.getLocation().getX()) {
                while (nextPos[0] - 1 < this.getLocation().getX() + moveX) {
                    moveX -= 0.1f * moveSpeed;
                    Thread.sleep(20);
                }
            } else if (nextPos[0] - 1 > this.getLocation().getX()) {
                while (nextPos[0] - 1 > this.getLocation().getX() + moveX) {
                    moveX += 0.1f * moveSpeed;
                    Thread.sleep(20);
                }
            }
            moveX = 0;
            moveY = 0;
            int nTeam = field[this.getLocation().getX()][this.getLocation().getY()];
            field[this.getLocation().getX()][this.getLocation().getY()] = 0;
            this.getLocation().setX(nextPos[0] - 1);
            this.getLocation().setY(nextPos[1] - 1);
            field[this.getLocation().getX()][this.getLocation().getY()] = nTeam;


        }

        Hero closest = findClosestEnemy(enemies);

        if(hit(closest)) return true;

        this.animation.cancel();

        this.animation = new IdleThread(this, idleAnimation, 80);
        return true;
        }
    }


