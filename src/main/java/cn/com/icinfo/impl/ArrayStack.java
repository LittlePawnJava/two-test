package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] data;
    private int top;

    public ArrayStack(int capacity) {
        top = -1;
        this.size = 0;
        this.data = (E[]) new Object[capacity];
    }
    public ArrayStack(){
        this(DEFAULT_CAPACITY);
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
        if (size == data.length) {
            grow(data.length*2);
        }

        data[++top] = item;
        size++;
    }

    private void grow(int capacity) {
        data = Arrays.copyOf(data,capacity);
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new IllegalArgumentException("没有元素");
        }
        size--;
        return data[top--];
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw new IllegalArgumentException("没有元素");
        }
        return data[top];
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
