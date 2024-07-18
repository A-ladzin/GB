package Sem04.hw01;

import java.util.LinkedList;

class MyQueue<T> {
        // Напишите свое решение ниже
    private class Node{
        private Node(T v, Node n){
            value = v;
            next = n;
        }
        private T value;
        private Node next;


        public String toString() {
            String result = "[";
            result+= value;
            result+=", ";
            Node temp = next;
            while (temp.next != null){
                result+=temp.value.toString();
                result+=", ";
                temp = temp.next;
            }
            result+=temp.value.toString();
            result +="]";
            return result;
    }
    }
    private Node head = null;
    private Node tail = null;


    public void enqueue(T element){
        // Напишите свое решение ниже
        if (head == null){
            head = new Node(element, null);
            tail = head;
        }
        else{        
            tail.next = new Node(element, null);
        tail = tail.next;
    }


    }

    public T dequeue(){
        // Напишите свое решение ниже
        T top = head.value;
        head = head.next;
        return top;
    }

    public T first(){
        return head.value;
        // Напишите свое решение ниже

    }

    public Node getElements() {
        return head;
        // Напишите свое решение ниже

    }


    
}
