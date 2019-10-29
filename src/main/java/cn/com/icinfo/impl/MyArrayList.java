package cn.com.icinfo.impl;

import cn.com.icinfo.inter.List;

import java.util.Arrays;
import java.util.Date;

public class MyArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;

    private int size;

    public MyArrayList(int capacity){

        data = (E[]) new Object[capacity];
        size = 0;

    }
    public MyArrayList(E[] arr){
        data = (E[])new Object[arr.length];
        int i = 0;
        for (E item : arr) {

            data[i++] = arr[i++];
        }

        this.size = arr.length;
    }
    public MyArrayList(){

        this(DEFAULT_CAPACITY);

    }
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E o) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("数组小标越界...");
        }

        return data[index];
    }

    public void set(int index, E e) {

        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("数组小标越界...");
        }
        data[index] = e;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组小标越界...");
        }

        if (data.length == size) {
            grow(data.length*2);
        }


        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void grow(int i) {
        data = Arrays.copyOf(data, i);
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组小标越界...");
        }
        E e = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        size--;
        data[size] = null;
        if (size < data.length/2) {
            grow(data.length/2);
        }
        return e;
    }

    public static void main(String[] args) {
        List<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 100; i++)
            list.add(i, i);

        for (int i = 0; i < 100; i++) {
            System.out.println("The " + i + "th element is: " + list.get(i));
        }

        for (int i = 0; i < 50; i += 8) {
            list.remove(i);
        }

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println("After removing, the " + i + "th element is: " + list.get(i));
        }
    }
}
