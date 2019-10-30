package cn.com.icinfo.recursive;

import cn.com.icinfo.impl.LinkedQueue;

public class RecursiveTest {
    private static class Node<E>{
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
    public static int getLinkedCount(Node list){

        if (list == null){
            return 0;
        }
        return 1 + getLinkedCount(list.next);
    }

    public static void printList(Node list){
        if (list == null){
            return ;
        }
        System.out.println(list.data);
        printList(list.next);
    }

    public static void printListReverse(Node list){
        if (list == null){
            return ;
        }

        printListReverse(list.next);
        System.out.println(list.data);
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<Integer>(0);
        Node<Integer> node2 = new Node<Integer>(1);
        Node<Integer> node3 = new Node<Integer>(2);
        Node<Integer> node4 = new Node<Integer>(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printListReverse(node1);
    }

}
