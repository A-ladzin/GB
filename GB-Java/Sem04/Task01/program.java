package Sem04.Task01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class program {
    
    public static void main(String[] args) {
        boolean flag = true;
        String str;
        Scanner scanner = new Scanner(System.in);
        LinkedList<String> lst = new LinkedList<String>();
        while (flag) {
            System.out.print(">>> ");
            str = scanner.nextLine();
            if (str.equals("end")) break;
            String [] strArr = str.split("~");
            System.out.println();
            int num = Integer.parseInt(strArr[1]);
            if (strArr[0].equals("print"))
            {
                System.out.println(lst.remove(num));
                continue;
            }
            lst.add(num,str);

        }

    }
}
