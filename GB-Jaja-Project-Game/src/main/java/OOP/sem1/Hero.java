package OOP.sem1;

/*
Крестьянин	Разбойник	Снайпер	Колдун
копейщик	арбалетчик	монах
 */

/*
 * Monk heal unlimited mana cant move
 * Crossbower cant move shot
 * Pikeman move front-side attack mellee
 * Wizard - monk analogy
 * Sniper cb analog shot cant move
 * Rogue move attack
 * Peasant cant move cant heal carry bows for cb and sniper

 *
 Peasant Robber Sniper Sorcerer
spearman crossbowman monk
 *
 * */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import Animation.*;
import Interfaces.Step;
import OOP.sem1.util.Cycle;

import javax.imageio.ImageIO;

public abstract class Hero implements Step{
    public Cycle<BufferedImage> deadAnimation;
    public Cycle<BufferedImage> moveAnimation;
    public Cycle<BufferedImage> attackAnimation;
    public Cycle<BufferedImage> actionAnimation;

    public Cycle<BufferedImage> idleAnimation;

    public BaseThread animation;

    public boolean flipped = false;


    public BufferedImage currentState;
    public static int[][] field;

    private int nTeam;
    public int getTeam(){
        return nTeam;
    }

    static public int toInt(boolean a){
        return a?1:0;
    }

    protected int health, healthMax, armor;

    protected int[] damage;
    protected int dullDamage;

    protected String nameHero;
    protected Vector2 position;
    protected   float damageFactor;
    protected float armorFactor;

    private boolean disarmed;
    private boolean sharpen;
    private  boolean undressed;

    public float moveX = 0;
    public float moveY = 0;

    protected boolean isIdle = true;

    public void setIdle(boolean b){
        isIdle = b;
    }

    protected void resetStatus()
    {
        damageFactor = 1;
        armorFactor = 1;
        disarmed = false;
        sharpen = false;
        undressed = false;
    }

    public String getStatus(){
        return " Damage: " + Arrays.toString(new int[]{(int)(damage[0]*damageFactor), (int)(damage[1]*damageFactor)})
                + "---"
                + "Sharpen ".repeat(toInt(sharpen))
                + "Disarmed ".repeat(toInt(disarmed))
                + "Undressed ".repeat(toInt(undressed))
                + "Coordinates: " + this.getLocation();
    }

    public Hero(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        this.health = health;
        this.healthMax = healthMax;
        this.armor = armor;
        this.damage = damage;
        this.dullDamage = damage[0];
        this.nameHero = nameHero;
        this.position = new Vector2(posX, posY);
        this.nTeam = nTeam;
        resetStatus();



    }

    public void printEnemiesDistance(ArrayList<Hero> enemies) {
        enemies.forEach(n -> System.out.print(position.calculateDistance(n.position) + ", "));
        System.out.println();
    }

    public void die() throws InterruptedException {
        field[(int)position.posX][(int)position.posY] = 0;
        Main.corpses.add(new int[]{this.getLocation().getX(), this.getLocation().getY()});
        this.animation.cancel();
        this.animation = new DeathThread(this,deadAnimation,100);
        Main.rips.add(this);
        this.position.posX = -11;
        this.position.posY = -11;


    }

    public void receiveDamage(int damage) throws InterruptedException {
        this.health -= (damage - (int)(this.armor*armorFactor));
        if (this.health < 1) {
            die();
            return;
        }
        resetArmor();
    }

    public void getHeal(int power) {
        this.health += power;
        if (this.healthMax < this.health)
            this.health = this.healthMax;
    }

    public Vector2 getLocation() {
        return this.position;
    }

    public boolean isInjured() {
        return this.health > 0 && this.health < this.healthMax;
    }

    public boolean isDead() {
        return this.health < 1;
    }

    public String getName() {
        return this.nameHero;
    }

    @Override
    public void step(ArrayList<Hero> allies,ArrayList<Hero> enemies) throws InterruptedException {
        System.out.println("Not implemented");
    }

    protected int initiative;

    public int getInitiative(){
        return initiative;
    }

    public int getHP(){
        return health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setDisarmed(){
        damageFactor = 0.5f;
        disarmed = true;
    }

    public void setUndressed(){
        armorFactor = 0.35f;
        undressed = true;
    }

    public void setSharpen()
    {
        damage[0] = damage[1];
        sharpen = true;
    }

    public void resetDamage()
    {
        damage[0] = dullDamage;
        damageFactor = 1;
        sharpen = false;
        disarmed = false;
    }

    public void resetArmor(){
        armorFactor = 1;
        undressed = false;

    }

    public boolean isUndressed(){

        return undressed;

    }

    public boolean isDisarmed()
    {
        return disarmed;
    }

    public boolean isSharpen() {
        return sharpen;
    }

    @Override
    public String toString() {
        return nameHero + " здоровье: " + health + "/" + healthMax + " броня: " + (int)(armor*armorFactor) + getStatus();
    }


    abstract public String getType();
}
