package Views;
import java.util.Collections;
import OOP.sem1.Hero;
import OOP.sem1.Main;
import Views.AnsiColors;

public class View {
    private static int step = 1;
    private static int l = 0;
    private static final String top10 = formatDiv("a") + String.join("", Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String midl10 = formatDiv("d") + String.join("", Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottom10 = formatDiv("g") + String.join("", Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");
    public  static  void reset(){
        step = 1;
        l = 0;
    }
    private static void tabSetter(int cnt, int max){
        int dif = max - cnt + 2;
        if (dif>0) System.out.printf("%" + dif + "s", ":\t"); else System.out.print(":\t");
    }
    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }
    private static String getChar(int x, int y){
        String out = "| ";
        for (Hero human: Main.heroOrder) {
            if (human.getLocation().getX()+1 == x && human.getLocation().getY()+1 == y){
                if (human.getHP() < 1) {
                    out = "|" + (AnsiColors.ANSI_RED + human.toString().charAt(0) + AnsiColors.ANSI_RESET);
                    break;
                }
                if (Main.heroesBlack.contains(human)) out = "|" + (AnsiColors.ANSI_PURPLE + human.toString().charAt(0) + AnsiColors.ANSI_RESET);
                if (Main.heroesWhite.contains(human)) out = "|" + (AnsiColors.ANSI_GOLDEN + human.toString().charAt(0) + AnsiColors.ANSI_RESET);
                break;
            }
        }
        return out;
    }
    public static void view() {
        if (step == 1 ){
            System.out.print(AnsiColors.ANSI_RED + "First step" + AnsiColors.ANSI_RESET);
        } else {
            System.out.print(AnsiColors.ANSI_RED + "Step:" + step + AnsiColors.ANSI_RESET);
        }
        step++;
        Main.heroOrder.forEach((v) -> l = Math.max(l, v.toString().length()));

        System.out.println("_".repeat(l*2));
        System.out.print(" Score: "
                + AnsiColors.ANSI_GOLDEN + Main.frame.panel.points[0] + AnsiColors.ANSI_RESET
                + "___" + AnsiColors.ANSI_PURPLE + Main.frame.panel.points[2]
                + AnsiColors.ANSI_RESET
        );
        System.out.println();
        System.out.print(top10 + "    ");
        System.out.print(AnsiColors.ANSI_GOLDEN + "Golden side" + AnsiColors.ANSI_RESET);
        System.out.print(" ".repeat(l-11));
        System.out.println(AnsiColors.ANSI_PURPLE + ":\tPurple side" + AnsiColors.ANSI_RESET);
        for (int i = 1; i < 11; i++) {
            System.out.print(getChar(1, i));
        }
        System.out.print("|    ");
        System.out.print((Main.heroesWhite.get(0).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesWhite.get(0)+AnsiColors.ANSI_RESET);
        tabSetter(Main.heroesWhite.get(0).toString().length(), l);
        System.out.println((Main.heroesBlack.get(0).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesBlack.get(0)+AnsiColors.ANSI_RESET);
        System.out.println(midl10);

        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(getChar(i, j));
            }
            System.out.print("|    ");
            System.out.print((Main.heroesWhite.get(i-1).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesWhite.get(i-1)+AnsiColors.ANSI_RESET);
            tabSetter(Main.heroesWhite.get(i-1).toString().length(), l);
            System.out.println((Main.heroesBlack.get(i-1).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesBlack.get(i-1)+AnsiColors.ANSI_RESET);
            System.out.println(midl10);
        }
        for (int j = 1; j < 11; j++) {
            System.out.print(getChar(10, j));
        }
        System.out.print("|    ");
        System.out.print((Main.heroesWhite.get(9).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesWhite.get(9)+AnsiColors.ANSI_RESET);
        tabSetter(Main.heroesWhite.get(9).toString().length(), l);
        System.out.println((Main.heroesBlack.get(9).isDead()?AnsiColors.ANSI_BLACK:"")+Main.heroesBlack.get(9)+AnsiColors.ANSI_RESET);
        System.out.println(bottom10);
    }


    public static void gameOver(int team){

        View.view();
        String winners = "*".repeat(25+l*2)+"\n";
                winners =
                (team == -1?AnsiColors.ANSI_WHITE + winners+ "Draw!":
                 team ==  0?AnsiColors.ANSI_GOLDEN   + winners+ "Golden win!" :
                            AnsiColors.ANSI_PURPLE  + winners+ "Purple win!")+
                "\n"+"*".repeat(25+l*2);
        System.out.println(winners);

    }
}