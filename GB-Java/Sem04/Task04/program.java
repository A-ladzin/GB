package Sem04.Task04;

public class program {
    public static void main(String[] args) {
        MyStack ms = new MyStack();
        ms.push(3);
        System.out.println(ms.size());
        ms.push(5);
        System.out.println(ms.peek());
    }
    
    
}

