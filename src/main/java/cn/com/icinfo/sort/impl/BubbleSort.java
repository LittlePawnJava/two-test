package cn.com.icinfo.sort.impl;

import cn.com.icinfo.sort.Sort;

/**
 * best O(n)
 * worst avg O(n^2)
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {10,1, 11,4, 6, 7, 2, 3, 5, 12,9,0};

        new BubbleSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }
    }
}
