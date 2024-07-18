package Roles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Animation.AttackThread;

import Interfaces.Heal;
import Interfaces.InjuredSearch;
import OOP.sem1.Hero;
import OOP.sem1.Monk;
import OOP.sem1.Wizard;

abstract public class Support extends Hero  implements Heal, InjuredSearch {
    static protected final Random rand = new Random();

    public Support(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY,int nTeam) throws IOException, InterruptedException {
        super(health, healthMax, armor, damage, nameHero, posX, posY,nTeam);
        initiative = 1;
    }

    @Override
    public boolean heal(ArrayList<Hero> allies) throws InterruptedException {
        Hero other = findMostInjured(allies);
        if (other == null) return false;
        if (other.getLocation().getY() < this.getLocation().getY()) {
            flipped = true;
        }
        this.animation.cancel();
        this.animation = new AttackThread(this,actionAnimation,60,80);
        other.getHeal(rand.nextInt(damage[0],damage[1]));
        this.resetDamage();
        return true;

        


    }

    @Override
    public String getType() {
        return "Support";
    }

    
}
