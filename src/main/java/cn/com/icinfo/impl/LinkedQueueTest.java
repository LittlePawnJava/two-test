package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Queue;

public class LinkedQueueTest<E> implements Queue<E> {
    private class Node{
        private E data;
        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E val) {
            this(val, null);
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public LinkedQueueTest() {
        this.size = 0;
        this.head = null;
        this.tail = null;
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
        Node pre = tail;
        tail = new Node(e);
        if (size == 0) {
            head = tail;
        } else {
            pre.next = tail;
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
        Queue<Integer> arrayStack = new LinkedQueueTest<Integer>();
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
        System.out.println(arrayStack.peek());
    }

}
