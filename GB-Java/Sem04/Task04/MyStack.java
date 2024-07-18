package Sem04.Task04;

public class MyStack {
    private class Node{
        private Node(int v, Node n){
            value = v;
            next = n;
        }
        private int value;
        private Node next;
    }
    private Node top = null;
    private int size = 0;

    public int pop(){
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public void push(int value)
    {
        top = new Node(value, top);
        size++;
    }

    public boolean empty(){
        return (top == null);
    }

    public int peek(){
        return top.value;
    }
    
    public int size(){
        return size;
    }

}

