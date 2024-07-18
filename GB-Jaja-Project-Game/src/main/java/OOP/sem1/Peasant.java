package OOP.sem1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Animation.IdleThread;
import Animation.TimedThread;
import Interfaces.ClosestEnemySearch;
import Interfaces.Run;
import Navigator.Navigator;
import OOP.sem1.util.Cycle;
import Roles.Melee;
import Roles.Range;

import javax.imageio.ImageIO;

public class Peasant extends Hero implements Run, ClosestEnemySearch {
    private int movePoints = 0;

    private float moveSpeed = 0.8f;

    public static BufferedImage idle;
    public static Stack<BufferedImage> staticActionAnimation = new Stack<>();
    public static Stack<BufferedImage> staticMoveAnimation = new Stack<>();
    static  public Stack<BufferedImage> staticDeadAnimation = new Stack<>();
    static  public Stack<BufferedImage> staticIdleAnimation = new Stack<>();

    static {
        try {

            for (int i = 0; i < 10; i++) {
                staticActionAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Peasant/Seller_02_Animation_00"+i+".png")));
                if(i<6) {
                    staticMoveAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Peasant/0_Minotaur_Sliding_00" + i + ".png")));


                }


            }
            for (int i = 10; i < 14; i++) {
                staticDeadAnimation.push(ImageIO.read(new File("src/main/java/GUI/Icons/Peasant/0_Minotaur_Dying_0" + i + ".png")));

            }
            idle = ImageIO.read(new File("src/main/java/GUI/Icons/Peasant.png"));
            staticIdleAnimation.push(idle);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    static private final Random rand = new Random();

    public Peasant(String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(100, 100, 0, new int[] { 4, 16 }, nameHero, posX, posY,nTeam);
        initiative = 0;
        flipped = nTeam == 3;
        deadAnimation = new Cycle<BufferedImage>(staticDeadAnimation);
        moveAnimation = new Cycle<BufferedImage>(staticMoveAnimation);
        idleAnimation = new Cycle<BufferedImage>(staticIdleAnimation);
        actionAnimation = new Cycle<BufferedImage>(staticActionAnimation);
        this.animation = new IdleThread(this,idleAnimation,80);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Крестьянин: " + super.toString());
    }

    private boolean giveBows(ArrayList<Hero> allies) throws InterruptedException {
        int minArrows = Integer.MAX_VALUE;
        Range hero = null;
        for (Hero h : allies) {
            if (h.isDead()) continue;
            if (h.getType().equals("Range") &&((Range) h).getAmmoParts() < 150 ) {
                if (minArrows > ((Range) h).getAmmoParts()) {
                    minArrows = ((Range) h).getAmmoParts();
                    hero = (Range)h;
                }

            }
        }
        if (hero != null) {
            this.animation.cancel();
            this.animation = new TimedThread(this,actionAnimation,100,1000);
            int numGiven = rand.nextInt(this.damage[0], this.damage[1]);
            hero.receiveAmmo(numGiven);
            return true;
        }
        return false;
    }

    private boolean sharpen(ArrayList<Hero> allies) throws InterruptedException {
        for(Hero h: allies){
            if (h.isDead()) continue;
            if(h instanceof Range || h instanceof Melee){
                if (!h.isSharpen()){
                    this.animation.cancel();
                    this.animation = new TimedThread(this,actionAnimation,100,1000);
                    h.setSharpen();
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Hero findClosestEnemy(ArrayList<Hero> enemies) {
        float minDistance = this.getLocation().calculateDistance(enemies.get(0).getLocation());
        Hero closest = enemies.get(0);
        for(int i = 1; i< enemies.size();i++)
        {
            float distance = enemies.get(i).getLocation().calculateDistance(this.getLocation());
            if (distance < minDistance && !enemies.get(i).isDead()){
                minDistance = distance;
                closest = enemies.get(i);
            }
        }
        return closest;
    }
    @Override
    public void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException {
        if (isDead()) return;
        Hero enemy = findClosestEnemy(enemies);
        if (enemy.getType().equals("Melee")  && enemy.getLocation().calculateDistance(this.getLocation())<= 1.5 && movePoints >1 ){
            if(run(enemy)){
                for (int i = 0; i < 4; i++) {
                    run(enemy);
                }
                return;
            };

        }
        if (movePoints < 3){
            movePoints++;
        }
        for (int i = 0; i < 3; i++) {
            Thread.sleep(50);
            switch (rand.nextInt(0,2)){
                case 0:
                    if(giveBows(allies)) break;
                    sharpen(allies);
                    break;
                case 1:
                    if(sharpen(enemies)) break;
                    giveBows(allies);
                    break;
            }
        }
        Thread.sleep(100);

    }


    @Override
    public boolean run(Hero enemy) throws InterruptedException {
        Stack<int[]> path = Navigator.runawayRoute(Hero.field,
                this.getLocation().getX(),
                this.getLocation().getY(),
                enemy.getLocation().getX(),
                enemy.getLocation().getY());
        if (path == null || path.isEmpty()) return false;
        path.pop();
        if (path.isEmpty()) return false;
        if (field[path.firstElement()[0] - 1][path.firstElement()[1] - 1] != 0) path.remove(0);
        while (movePoints > 0 && !path.isEmpty()) {
            movePoints--;
            int[] nextPos = path.pop();

            this.animation.cancel();
            this.animation = new IdleThread(this, moveAnimation, 80);

            int xCurr = this.getLocation().getX();
            int yCurr = this.getLocation().getY();
            int nTeam = field[this.getLocation().getX()][this.getLocation().getY()];
            float distance = this.getLocation().calculateDistance(enemy.getLocation());
            field[this.getLocation().getX()][this.getLocation().getY()] = 0;

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


            this.getLocation().setX(nextPos[0] - 1);
            this.getLocation().setY(nextPos[1] - 1);
            moveX = 0;
            moveY = 0;
            field[this.getLocation().getX()][this.getLocation().getY()] = nTeam;

        }
        this.animation.cancel();
        this.animation = new IdleThread(this, idleAnimation, 80);
        return true;
    }


    @Override
    public String getType() {
        return "Peasant";
    }

}
