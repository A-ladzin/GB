package OOP.sem1;

import GUI.MyFrame;
import Views.View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static public ArrayList<Hero> heroOrder;
    static public ArrayList<int[]> corpses = new ArrayList<>();
    public static ArrayList<Hero> rips = new ArrayList<>();

    static int winner = 0;
    static int[][] field = new int[10][10];
    public static MyFrame frame;
    public static void main(String[] args) throws InterruptedException, IOException {


        frame = new MyFrame(new ArrayList<>());

        while(true){
            winner = playRound();
            if(winner==1){
                frame.panel.points[2]++;
            }
            else if(winner == -1) frame.panel.points[0]++;
            else frame.panel.points[1]++;
        }
    }

    public static ArrayList<Hero> heroesWhite = new ArrayList<>();
    public static ArrayList<Hero> heroesBlack = new ArrayList<>();

    static ArrayList<Hero> generateCommand(int n, int y) throws IOException, InterruptedException {
        ArrayList<Hero> commandHeroes = new ArrayList<>();
        Random random = new Random();
        double rand;

        for (int i = 0; i < 10; i++) {
            rand = random.nextDouble(1., 5.) + n;
            switch ((int)rand) {
                case 1:
                    commandHeroes.add(new Crossbower(getName(), i, y,n));
                    break;
                case 2:
                    if (rand - (int)rand > 0.4){
                        commandHeroes.add(new Monk(getName(), i, y,n));

                    }
                    else commandHeroes.add(new Pikeman(getName(), i, y,n));
                    break;

                case 4:
                    if (rand - (int)rand > 0.3){
                        commandHeroes.add(new Peasant(getName(), i, y,n));

                    }
                    else {
                        if (n == 0){
                            commandHeroes.add(new Crossbower(getName(), i, y,n));
                        }
                        else commandHeroes.add(new Rogue(getName(), i, y,n));
                    }
                    break;
                case 3:
                    commandHeroes.add(new Pikeman(getName(), i, y,n));
                    break;
                case 5:
                    commandHeroes.add(new Rogue(getName(), i, y,n));
                    break;
                case 6:
                    commandHeroes.add(new Sniper(getName(), i, y,n));
                    break;
                case 7:
                    if (rand - (int)rand > 0.4){
                        commandHeroes.add(new Wizard(getName(), i, y,n));

                    }
                    else commandHeroes.add(new Rogue(getName(), i, y,n));
                    break;

            }
        }
        return commandHeroes;
    }



    static public int playRound() throws InterruptedException, IOException {

        View.reset();

        corpses.clear();
        rips.clear();

        field = new int[10][10];
        Hero.field = field;
        heroesWhite = generateCommand(0, 0);
        heroesBlack = generateCommand(3, 9);

        heroOrder = new ArrayList<>();
        frame.panel.heroes = heroOrder;



        heroOrder.addAll(heroesWhite);
        heroOrder.addAll(heroesBlack);

        for (Hero h: heroesWhite)
        {
            field[h.getLocation().getX()][h.getLocation().getY()] = 1;
        }
        for (Hero h: heroesBlack)
        {
            field[h.getLocation().getX()][h.getLocation().getY()] = 2;
        }
        heroOrder.sort((o1,o2)-> o2.getInitiative()-o1.getInitiative());
        Thread.sleep(1000);
        for(int i = 0; i < 200; i++) {

            View.view();

            if(heroesBlack.stream().allMatch(Hero::isDead)) {View.gameOver(0);return -1;} // White win
            if(heroesWhite.stream().allMatch(Hero::isDead)) {View.gameOver(1);return 1;} // Black win




            for(Hero hero: heroOrder)
            {

                if(heroesBlack.contains(hero)){
                    hero.step(heroesBlack,heroesWhite);
                    if(heroesWhite.stream().allMatch(Hero::isDead)) {View.gameOver(1);Thread.sleep(2000);return 1;} //Black win
                }
                else{
                    hero.step(heroesWhite,heroesBlack);
                    if(heroesBlack.stream().allMatch(Hero::isDead)) {View.gameOver(0);Thread.sleep(2000);return -1;}//White win
                }
            }

        }

        View.gameOver(-1);
        Thread.sleep(2000);
        return 0; //Draw
    }

    static String getName() {
        return NameOfHeroes.values()[new Random().nextInt(NameOfHeroes.values().length - 1)].name();
    }
}
