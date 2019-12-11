package cn.com.icinfo.sort.mysort;

import cn.com.icinfo.sort.Sort;

public class QuickSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <=1) {
            return;
        }

        //sortHelper(arr, 0, arr.length-1);
        threePath(arr, 0, arr.length-1);
    }

    private void threePath(int[] arr, int start, int end) {
        int base = arr[start];
        int lt = start;
        int eq= lt + 1;
        int gt = end;
        while (eq <= gt) {
            if (arr[eq] < base) {
                swap(arr, eq, lt);
                lt++;
                eq++;
            } else if (arr[eq] > base) {
                swap(arr,eq,gt);
                gt--;
            } else {
                eq++;
            }
        }

        sortHelper(arr,start,lt-1);
        sortHelper(arr,eq-1,end);

    }

    private void sortHelper(int[] arr, int start, int end) {
        if (start >= end){
            return;
        }
        int index  = partition2(arr,start,end);
        sortHelper(arr, start,index-1);
        sortHelper(arr, index+1, end);
    }

    // 从两头开始搜索
    private int partition2(int[] arr, int start, int end) {
        int base = arr[start];
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && arr[i] < base) {
                i++;
            }

            while (i <= j && arr[j] >= base) {
                j--;
            }

            if (i < j) {
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        swap(arr,i-1,start);
        return i-1;
    }
    // 从一头开始搜索
    private int partition(int[] arr, int start, int end) {
        int base = arr[start];
        int j = start + 1;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < base) {
                swap(arr,i,j);
                j++;
            }
        }
        swap(arr,j-1,start);
        return j-1;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4,10,4,1, 11,4, 6, 7, 4,2, 3, 5,0, 12,9,0};

        new QuickSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }
    }


}
