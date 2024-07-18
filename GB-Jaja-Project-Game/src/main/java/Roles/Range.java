package Roles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Animation.AttackThread;
import Animation.IdleThread;
import Interfaces.InjuredSearch;
import Interfaces.Run;
import Navigator.Navigator;
import OOP.sem1.*;
import Interfaces.ClosestEnemySearch;
import Interfaces.Shot;
import OOP.sem1.util.Cycle;

import javax.imageio.ImageIO;

abstract public class Range extends Hero implements Shot, ClosestEnemySearch, InjuredSearch, Run {
    static private final Random rand = new Random();
    private int ammoParts = 70;

    private int movePoints = 0;
    protected  int maxMovePoints = 3;

    private float moveSpeed = 1;
    protected int rangeMaxDamage;


    public Range(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(health, healthMax, armor, damage, nameHero, posX, posY,nTeam);
        initiative = 3;
    }

    @Override
    public boolean shoot(Hero other) throws InterruptedException {
        if (other.getLocation().getY() < this.getLocation().getY()){
            flipped = true;
        }
        else{
            flipped = false;
        }
        if( this.ammoParts >= 10) {
            this.animation.cancel();
            this.animation = new AttackThread(this,attackAnimation,70,80);
            other.receiveDamage((int)(rand.nextInt(damage[0],damage[1]+1)*damageFactor/(Math.exp((this.getLocation().calculateDistance(other.getLocation())-rangeMaxDamage)/10))));
            this.ammoParts -= 10;
            resetDamage();
            return true;
        }
        return false;

    }

    public void receiveAmmo(int amount){
        this.ammoParts += amount;
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
    public void step(ArrayList<Hero> allies,ArrayList<Hero> enemies) throws InterruptedException {



        if(isDead()) return;
        Hero enemy = findClosestEnemy(enemies);
        if (enemy.getType().equals("Melee")  && enemy.getLocation().calculateDistance(this.getLocation())<= 1.5 && movePoints > 1){
            run(enemy);

        }
        if (movePoints < maxMovePoints){
            movePoints++;
        }
        switch (rand.nextInt(0,2)){
            case 0:
                enemy = findClosestEnemy(enemies);
                if(enemy != null) break;
            case 1:
                enemy = findMostInjured(enemies);
                if(enemy != null) break;
                enemy = findClosestEnemy(enemies);
        }

        if (!shoot(enemy)) {
            receiveAmmo(1);
        };
    }

    @Override
    public String toString() {
        return super.toString() + " Arrows: " + this.ammoParts/10;
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
        if (path.isEmpty()) return false;
        this.animation.cancel();
        this.animation = new IdleThread(this, moveAnimation, 80);
        while (movePoints > 0 && !path.isEmpty()) {
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


            int nTeam = field[this.getLocation().getX()][this.getLocation().getY()];
            field[this.getLocation().getX()][this.getLocation().getY()] = 0;
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
        return "Range";
    }

    public int getAmmoParts(){
        return ammoParts;
    }
}
