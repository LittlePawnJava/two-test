package cn.com.icinfo.sort.mysort;

import cn.com.icinfo.sort.Sort;

import java.util.HashMap;
import java.util.Map;

public class SelectSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }



            }


            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }
    }

    public static void main(String[] args) {
        /*int[] arr = {10,1, 11,4, 6, 7, 2, 3, 5, 12,9,0};

        new SelectSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }*/

        Map<String,String> ee = new HashMap<>();
        ee.put("123","123");
        ee.put("123","1234");
        ee.forEach((k,v) -> System.out.println(v));
    }
}
