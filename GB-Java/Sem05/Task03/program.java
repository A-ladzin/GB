

package Sem05.Task03;

import java.util.HashMap;
import java.util.Stack;

public class program {
     
    public static void main(String[] args) {
        System.out.println(isValid("a+(d*3)"));
        System.out.println(isValid("[6+(3*3)]"));
        System.out.println(isValid("{a}[+]{(d*3)}"));
        System.out.println(isValid("<{a}+{(d*3)}>"));
        System.out.println(isValid("a+(d*3))"));
        System.out.println(isValid("[a+(1*3)"));
        System.out.println(isValid("[a+(1]*3)"));
        System.out.println(isValid("{a+]}{(d*3)}"));
    }

    public static boolean isValid(String s)
    {

        Stack<Character> st = new Stack<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');
        map.put('<', '>');
        for(Character ch : s.toCharArray())
        {
            
            if(map.containsKey(ch))
            {
                st.push(ch);
            }
            if(map.containsValue(ch))
            {
                if (st.empty() || map.get(st.pop())!=ch){
                    return false;
                }
            }
            
        }
        return st.empty();
    }
}


