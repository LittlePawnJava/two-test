package cn.com.icinfo.sort.impl;

import cn.com.icinfo.sort.Sort;

/**
 * O(n^2)
 * best ??
 *
 */
public class SelectSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length ==1) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // i-1是有序的
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
