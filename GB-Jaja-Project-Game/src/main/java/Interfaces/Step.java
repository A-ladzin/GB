package Interfaces;

import java.util.ArrayList;

import OOP.sem1.Hero;

public interface Step {
    void step(ArrayList<Hero> allies, ArrayList<Hero> enemies) throws InterruptedException;
}
