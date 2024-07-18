package Interfaces;

import OOP.sem1.Hero;

import java.util.ArrayList;

public interface WeakestSearch {

    default ArrayList<Hero> findWeakest(ArrayList<Hero> team) {
        ArrayList<Hero> result = (ArrayList<Hero>)team.clone();
        result.removeIf(Hero::isDead);
        result.sort(((h1,h2) -> h1.getHP() - h2.getHP()));

        return result;

    }
}
