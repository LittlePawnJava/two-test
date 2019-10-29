package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Queue;
import cn.com.icinfo.inter.Stack;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        this.tail = -1;
        this.head = -1;
    }
    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
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
        if (data.length == size) {
            grow(data.length*2);
        }

        tail = (tail+1) % data.length;
        data[tail] = e;
        if (size == 0) {
            head = tail;
        }
        size++;
    }

    private void grow(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            return;
        }
        E[] oldData = data;
        data = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            data[i] = oldData[(head + i) % oldData.length];
        }
        head = 0;
        tail = size - 1;
    }

    @Override
    public E dequeue() {
        if (size == 0){
            throw new IllegalArgumentException();
        }
        E result = data[head];
        head = (head + 1) % data.length;
        size--;
        if (size < data.length/2) {
            grow(data.length/2);
        }
        return result;
    }

    @Override
    public E peek() {
        if (size == 0){
            throw new IllegalArgumentException();
        }
        return data[head];
    }

    public static void main(String[] args) {
        Stack<Integer> arrayStack = new ArrayStack<Integer>();
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
