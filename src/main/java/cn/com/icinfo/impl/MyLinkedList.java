package cn.com.icinfo.impl;

import cn.com.icinfo.inter.List;



public class MyLinkedList<E> implements List<E> {
    private class Node {
        private E data; // 数据域
        private Node next; // 指针域，指向下一个Node

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }
    }

    private int size;
    private Node head;

    public MyLinkedList() {
        head = null;
        size = 0;
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
        Node p = head;
        while (p != null) {
            if (p.data.equals(o)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    @Override
    public int indexOf(E e) {
        Node p = head;
        int i = 0;
        while (p != null) {
            if (p.data.equals(e)) {
                return i;
            }
            p = p.next;
            i++;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标越界");
        }
        Node p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public void set(int index, E e) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标越界");
        }
        Node p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.data = e;

    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("下标越界");
        }

        Node p = head;
        Node node;
        //如果是头指针
        if (index == 0) {
            node = new Node(e, head);
            head = node;
            //尾指针
        } else if (index == size) {
            node = new Node(e);
            if (head == null) {
                head = node;
            } else {
                while (p.next != null) {
                    p = p.next;
                }
                p.next = node;
            }

        } else {
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            p.next = new Node(e, p.next);
        }

        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标越界");
        }

        if (head == null) {
            return null;
        }
        Node p = head;
        E resault;
        if (index == 0) {
            resault = head.data;
            head = head.next;
            size --;
            return resault;
        } else if (index == size - 1) {
            if (head.next == null) {
                resault = head.data;
                head = null;
                size--;
                return resault;
            } else {
                while (p.next.next != null) {
                    p = p.next;
                }
                resault = p.next.data;
                p.next = null;
                size--;
                return resault;
            }
        }

        //中间删除
        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }
        Node result = p.next;
        p.next = result.next;
        result.next = null;
        size--;
        return result.data;


    }
    public static void main(String[] args) {
        List<Integer> list = new MyLinkedList<>();

        for(int i=0; i<10; i++)
            list.add(i, i);

        list.set(1,300);

        System.out.println(list.get(2));

        /*for(int i=0; i<10; i++) {
            System.out.println("The " + i + "th element is: " + list.get(i));
        }

        list.remove(5);

        for(int i=0; i<list.getSize(); i++) {
            System.out.println("After removing, the " + i + "th element is: " + list.get(i));
        }
*/
    }
}
