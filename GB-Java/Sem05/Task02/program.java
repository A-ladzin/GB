package Sem05.Task02;

import java.util.HashMap;
import java.util.Map;

public class program {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("add", "egg"));
        System.out.println(isIsomorphic("note", "code"));
        System.out.println(isIsomorphic("paper", "title"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("bar", "foo"));

    }


    public static boolean isIsomorphic(String s1, String s2)
    {
        Map<Character,Character> map = new HashMap<>();
        for(int i = 0; i < s1.length();i++)
        {
            if (map.containsKey(s1.charAt(i))){
                if(s1.charAt(i)!= s2.charAt(i))
                { 
                    return false;
                }
            } 
            else{
                if (map.containsValue(s2.charAt(i))) return false;
                map.put(map.get(s1.charAt(i)), s2.charAt(i));
            }
        }
        return true;
    }
}
