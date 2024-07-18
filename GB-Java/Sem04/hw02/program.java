package Sem04.hw02;

import java.util.LinkedList;
import java.util.Map;


/**
 * program
 */
public class program {
    public static void main(String[] args) {
     Printer.main(new String[0]);
    }
public static class LLTasks {
    public LinkedList<Object> revert(LinkedList<Object> ll) {
        // Напишите свое решение ниже
        LinkedList<Object> result = new LinkedList<>();
        while ( !ll.isEmpty())
        {
            result.add(ll.pollLast());
        }
        return result;
        
}
}

// Не удаляйте этот класс - он нужен для вывода результатов на экран и проверки
public static class Printer{ 
    public static void main(String[] args) { 
        LinkedList<Object> ll = new LinkedList<>();

        if (args.length == 0 || args.length != 4) {
        // При отправке кода на Выполнение, вы можете варьировать эти параметры
            ll.add(1);
            ll.add("One");
            ll.add(2);
            ll.add("Two");
        } else {
            ll.add(Integer.parseInt(args[0]));
            ll.add(args[1]);
            ll.add(Integer.parseInt(args[2]));
            ll.add(args[3]);
        }

        LLTasks answer = new LLTasks();
        System.out.println(ll);
        LinkedList<Object> reversedList = answer.revert(ll);
        System.out.println(reversedList);
    }
}
    
}
