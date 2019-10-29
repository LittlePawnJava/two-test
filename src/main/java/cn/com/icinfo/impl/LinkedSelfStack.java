package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Stack;

public class LinkedSelfStack<E> implements Stack<E> {
    private class Node{
        private E data;
        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
        public Node(E val){
            this(val, null);
        }
    }

    private Node head;
    private int size;

    public LinkedSelfStack() {
        this.head = null;
        this.size = 0;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E item) {
        head = new Node(item, head);
        size++;
    }

    @Override
    public E pop() {
        if(size == 0){
            throw new IllegalArgumentException();
        }
        E result = head.data;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public E peek() {
        if(size == 0){
            throw new IllegalArgumentException();
        }
        return head.data;
    }

    public static void main(String[] args) {
        Stack<Integer> arrayStack = new LinkedSelfStack<>();
        for (int i = 0; i < 100; i++) {
            arrayStack.push(i);
        }

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());

        int leng = arrayStack.size();
        for (int i = 0; i < leng; i++) {
            System.out.println("弹出元素 ： " + arrayStack.pop());
        }
    }
}
