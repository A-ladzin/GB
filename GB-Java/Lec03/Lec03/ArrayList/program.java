package Lec03.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class program {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>(5);
        List<Object> lLIst = new ArrayList<Object>(5);
        list.add(5);
        lLIst.add(5);
        lLIst.add("Whaa?");



        for (Integer o: list){
            System.out.println(o);
        }
        for (Object o: lLIst){
            System.out.println(o);
        }

        lLIst.get(1);
        System.out.println(lLIst);


        StringBuilder day = new StringBuilder("29");
        StringBuilder month = new StringBuilder("5");
        StringBuilder year = new StringBuilder("1992");
        StringBuilder[] date = new StringBuilder[] {month,day,year};
        List<StringBuilder> arr = Arrays.asList(date);
        System.out.println(arr);
        month.replace(0, 2, "05");
        System.out.println(arr);
        
        Iterator<StringBuilder> iter = arr.iterator();

        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
}
