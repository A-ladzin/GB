package Sem03.task02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class program {
    public static void main(String[] args) {
        int num = 10;
        int min = 0;
        int max = 10;
        List<Integer> userList = createList(num, min, max);
        System.out.println(userList);
        userList.sort(Comparator.reverseOrder());
        System.out.println(userList);

    }
    static List<Integer> createList(int num, int min, int max){
        List<Integer> resList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int tmp = random.nextInt(min, max+1);
            resList.add(tmp);
        }
        return resList;
    }
}