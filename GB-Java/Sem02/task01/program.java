package Sem02.task01;

public class program {
    public static void main(String[] args) {
        System.out.println(task01(5,'a','b'));
    }

    public static String task01(int n, char c1, char c2){
        StringBuilder result = new StringBuilder();
        boolean temp = true;
        while (result.length() < n){
            result.append(temp?c1:c2);
            temp = !temp;
            
        }
        return result.toString();
    }

    public static String task02(String s)
    {
        
    }


}
