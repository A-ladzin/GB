package Sem01;

public class task03 {
    public static void main(String[] args) {
        String words = new String("Добро пожаловать на курс по Java");
        String[] arrW = words.split(" ");
        for (int i = arrW.length; i>0; i--)
        {
            System.out.print(arrW[i-1] + ' ');
        }
    }
}
