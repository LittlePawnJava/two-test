package cn.com.icinfo.impl;

import cn.com.icinfo.inter.List;

public class DoubledLinkedList<E> implements List<E> {

    private class Node{
        private E e;
        private Node pre;
        private Node next;

        public Node(E e, Node pre, Node next) {
            this.e = e;
            this.pre = pre;
            this.next = next;
        }

        public Node(E e){
            this(e, null, null);
        }
        public Node(){
            this(null, null, null);
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public DoubledLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E o) {

        return false;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E e) {

    }

    @Override
    public void add(int index, E e) {

    }

    @Override
    public E remove(int index) {
        return null;
    }
}
