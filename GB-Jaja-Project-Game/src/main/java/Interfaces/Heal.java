package Interfaces;

import OOP.sem1.Hero;

import java.util.ArrayList;

/**
 * Heal
 */
public interface Heal {
    boolean heal(ArrayList<Hero> allies) throws InterruptedException;
    
}