package cn.com.icinfo.sort.impl;

import cn.com.icinfo.sort.Sort;

public class MergeSort implements Sort {
    @Override
    public  void sort(int[] arr) {
        if (arr == null || arr.length == 1){
            return;
        }
        int[] temp = new int[arr.length];
        sortHelper(arr, 0, arr.length-1,temp);
    }

    private void sortHelper (int[] arr, int start, int end, int[] temp){
        if (start >= end) {
            return;
        }
        int mid = (start + end) /2;
        sortHelper(arr, start,mid,temp);
        sortHelper(arr, mid+1,end,temp);

        merger(arr, start, mid,mid+1, end, temp);

    }
    public void merger(int[] arr, int s1, int e1, int s2, int e2, int[] temp){

        int i = s1, j = s2, k=0;
        while(i <= e1 && j <= e2){
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i];
                i++;
            }else {
                temp[k++] = arr[j];
                j++;
            }
        }

        while(i <= e1){
            temp[k++] = arr[i];
            i++;
        }
        while(j <= e2){
            temp[k++] = arr[j];
            j++;
        }

        for (int l = 0; l < k; l++) {
            arr[s1 + l] = temp[l];
        }
    }
    public static void main(String[] args) {
        int[] arr = {10,1, 11,4, 6, 7, 2, 3, 5, 12,9,0};

        new MergeSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }
    }
}
