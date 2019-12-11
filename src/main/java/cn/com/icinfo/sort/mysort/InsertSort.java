package cn.com.icinfo.sort.mysort;

import cn.com.icinfo.sort.Sort;

public class InsertSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int j = i-1;
            int base = arr[i];
            while(j >= 0 && base < arr[j]){

                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = base;
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
