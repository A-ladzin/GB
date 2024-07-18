package Interfaces;

import Roles.Melee;
import OOP.sem1.Hero;

public interface Run {
    boolean run(Hero enemy) throws InterruptedException;
}
