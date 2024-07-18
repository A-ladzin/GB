package Sem03.task03;

import java.util.ArrayList;
import java.util.List;

public class program {
    static List<List<String>> bookStore = new ArrayList<>();

    public static void main(String[] args) {
        add_item("QQ", "FF");
        add_item("WW", "AA");
        add_item("EE", "GG");
        add_item("RR", "HH");
        add_item("WW", "BB");
        System.out.println(bookStore);
    }

    public static void add_item(String genre,String name){
        List<String> shelf = null;
        for (List<String> i : bookStore)
        {
            if(i.get(0)== genre)
            {
                shelf = i;
            } 
        }
        if(shelf == null){
            shelf = new ArrayList<String>();
            shelf.add(genre);
            bookStore.add(shelf);
        }
        shelf.add(name);
    
    }
    
}

