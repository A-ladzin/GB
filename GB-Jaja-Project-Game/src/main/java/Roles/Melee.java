package Roles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Animation.AttackThread;
import Animation.IdleThread;
import Interfaces.ClosestEnemySearch;
import Interfaces.Hit;
import Interfaces.Move;
import Navigator.Navigator;
import OOP.sem1.Rogue;
import OOP.sem1.util.Cycle;
import OOP.sem1.Hero;
import OOP.sem1.Pikeman;
import OOP.sem1.Sniper;

abstract public class Melee extends Hero implements Move, Hit, ClosestEnemySearch {
    static private final Random rand = new Random();
    protected float moveSpeed = 1.f;

    public Melee(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int nTeam) throws IOException, InterruptedException {
        super(health, healthMax, armor, damage, nameHero, posX, posY, nTeam);
        initiative = 2;
    }

    @Override
    public boolean hit(Hero other) throws InterruptedException {
        if (position.calculateDistance(other.getLocation()) <= 1) {
            if (other.getLocation().getY() < this.getLocation().getY()) {
                flipped = true;
            }
            else if (other.getLocation().getY() > this.getLocation().getY()) {
                flipped = false;
            }

            this.animation.cancel();
            this.setIdle(true);
            this.animation = new AttackThread(this,attackAnimation,100,80);
            while(this.isIdle){}
            other.receiveDamage((int) (rand.nextInt(damage[0], damage[1] + 1) * damageFactor));
            resetDamage();
            return true;
        }
        return false;

        }





    @Override
    public Hero findClosestEnemy(ArrayList<Hero> enemies) {
        float minDistance = this.position.calculateDistance(enemies.get(0).getLocation());
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
    public boolean move(Hero enemy) throws InterruptedException {

        if(getLocation().calculateDistance(enemy.getLocation()) <= 1) return false;

        Stack<int[]> path = Navigator.findPath(Hero.field,
                this.getLocation().getX(),
                this.getLocation().getY(),
                enemy.getLocation().getX(),
                enemy.getLocation().getY());
        if (path == null) return false;
        path.pop();
        int[] nextPos = path.pop();
        this.animation.cancel();
        this.animation = new IdleThread(this,moveAnimation,80);

        if(nextPos[1]-1 < this.getLocation().getY()){
            flipped = true;
            while(nextPos[1]-1 < this.getLocation().getY()+moveY){
                moveY-=0.1f*moveSpeed;
                Thread.sleep(20);
            }
        }
        else if (nextPos[1]-1 > this.getLocation().getY())
        {
            flipped = false;
            while(nextPos[1]-1 > this.getLocation().getY()+moveY){
                moveY+=0.1f*moveSpeed;
                Thread.sleep(20);
            }
        }
        else if (nextPos[0]-1 < this.getLocation().getX())
        {
            while(nextPos[0]-1 < this.getLocation().getX()+moveX){
                moveX-=0.1f*moveSpeed;
                Thread.sleep(20);
            }
        }
        else if (nextPos[0]-1 > this.getLocation().getX())
        {
            while(nextPos[0]-1 > this.getLocation().getX()+moveX){
                moveX+=0.1f*moveSpeed;
                Thread.sleep(20);
            }
        }
        this.animation.cancel();
        this.animation = new IdleThread(this,idleAnimation,80);
        moveX = 0;
        moveY = 0;
        int nTeam = field[this.getLocation().getX()][this.getLocation().getY()];
        field[this.getLocation().getX()][this.getLocation().getY()] = 0;

        this.getLocation().setX(nextPos[0]-1);
        this.getLocation().setY(nextPos[1]-1);
        field[this.getLocation().getX()][this.getLocation().getY()] = nTeam;

        return true;
    }



    @Override
    public String getType() {
        return "Melee";
    }
}
