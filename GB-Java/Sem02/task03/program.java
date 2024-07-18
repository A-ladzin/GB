package Sem02.task03;

public class program {
 public static void main(String[] args) {
    System.out.println(isPalindrome("А роза упала на лапу Азора"));
 }  
 
 public static boolean isPalindrome(String s)
 {

    s = s.toLowerCase();
    s = s.replaceAll(" ", "");
    int l = 0;
    int r = s.length()-1;
    while(l<r){
        if (s.charAt(l) != s.charAt(r)) return false;
        l++;
        r--;
    }
    return true;
 }
}
