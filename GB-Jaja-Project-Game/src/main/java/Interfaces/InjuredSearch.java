package Interfaces;

import OOP.sem1.Hero;

import java.util.ArrayList;

public interface InjuredSearch {
    default Hero findMostInjured(ArrayList<Hero> team) {
        int minHP = Integer.MAX_VALUE;
        Hero chosen = null;
        for (Hero h: team)
        {
            if(h.isInjured() && h.getHP()<minHP){
                chosen = h;
                minHP = h.getHP();
            }
        }
        return chosen;
    }
}
