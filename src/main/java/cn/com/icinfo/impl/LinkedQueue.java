package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Queue;
import cn.com.icinfo.inter.Stack;

public class LinkedQueue<E> implements Queue<E> {
    private class Node<E>{
        private E data;
        private Node<E> next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E val) {
            this(val, null);
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        Node<E> prev = tail;
        tail = new Node<E>(e, null);

        if (size == 0) {
            head = tail;

        }else {
            prev.next = tail;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) {
            throw  new IllegalArgumentException();
        }
        E result = head.data;

        head = head.next;
        size--;
        if (size == 0){
            tail = null;
        }

        return result;
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw  new IllegalArgumentException();
        }
        return head.data;
    }

    public static void main(String[] args) {
        Queue<Integer> arrayStack = new LinkedQueue<Integer>();
        for (int i = 0; i < 100; i++) {
            arrayStack.enqueue(i);
        }

        System.out.println(arrayStack.dequeue());
        System.out.println(arrayStack.dequeue());
        System.out.println(arrayStack.dequeue());

        int leng = arrayStack.size();
        for (int i = 0; i < leng; i++) {
            System.out.println("弹出元素 ： " + arrayStack.dequeue());
        }
    }
}
