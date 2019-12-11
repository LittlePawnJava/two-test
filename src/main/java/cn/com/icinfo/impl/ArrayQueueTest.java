package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Queue;
import cn.com.icinfo.inter.Stack;

public class ArrayQueueTest<E> implements Queue<E> {
    private static final int DAFAULT_CAPACITY = 10;
    private int size;
    private E[] data;
    private int head;
    private int tail;

    public ArrayQueueTest(int capacity) {
        this.size = 0;
        this.data = (E[]) new Object[capacity];
        this.tail = -1;
        this.head = -1;
    }

    public ArrayQueueTest() {
        this(DAFAULT_CAPACITY);
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
        if (data.length == size){
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
        if (capacity <= DAFAULT_CAPACITY) {
            return;
        }
        E[] oldData = data;
        data = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            data[i] = oldData[(head+i) % oldData.length];
        }

        head = 0;
        tail = size - 1;
    }

    @Override
    public E dequeue() {
        if (size <= 0) {
            throw  new IllegalArgumentException("数组越界");
        }

        E result = data[head];
        head = (head +1) % data.length;
        size--;
        if (size < data.length/2) {
            grow(data.length/2);
        }
        return result;
    }

    @Override
    public E peek() {
        if (size <= 0) {
            throw  new IllegalArgumentException("数组越界");
        }
        return data[head];
    }
    public static void main(String[] args) {
        Queue<Integer> arrayStack = new ArrayQueueTest<Integer>();
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
