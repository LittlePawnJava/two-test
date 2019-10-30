package cn.com.icinfo.sort.impl;

import cn.com.icinfo.sort.Sort;

/**
 * worst 0(n^2)
 * best n-1
 */
public class InsertSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int ele = arr[i];
            int j = i - 1;

            // 二分查找优化
            while (j >= 0 && ele < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = ele;
        }
    }
    public static void main(String[] args) {
        int[] arr = {10,1, 11,4, 6, 7, 2, 3, 5, 12,9,0};

        new InsertSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }
    }
}
