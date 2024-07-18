package Sem01;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = console.nextLine();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        String period;
        
        if(hour < 5) period = "Good Night, ";
        else if (hour < 12) period = "Good Morning, ";
        else if (hour < 18) period = "Good Day, ";
        else period = "Good Evening, ";
        System.out.println(period + name);
        console.close();

    }
}
