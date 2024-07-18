/*
 Реализовать консольное приложение, которое:
Принимает от пользователя и “запоминает” строки.
Если введено print, выводит строки в обратном порядке
Если введено revert, удаляет предыдущую введенную строку из памяти.
 */

import java.util.*;

public class program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new LinkedList<>();
        boolean work = true;
        while (work){
            String line = scanner.nextLine();
            switch (line){
                case "print":
                    for (String s: list){
                        System.out.println(s);
                    }
//                    ListIterator<String> iterator = list.listIterator(list.size());
//                    while (iterator.hasPrevious()){
//                        System.out.println(iterator.previous());
//                    }
//                    for (int i = list.size()-1; i >= 0; i--) {
//                        System.out.println(list.get(i));
//                    }
                    break;
                case "revert":
                    list.remove(0);
                    break;
                case "finish":
                    work = false;
                    break;
                default:
                    list.add(0, line);
            }
        }
    }
}