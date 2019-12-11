package cn.com.icinfo.impl;

import cn.com.icinfo.inter.UnionFind;

import java.util.Random;

public class QuickFindUF implements UnionFind {
    private int[] id;
    private int count;//多少组

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private void validate(int p){
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("参数越界");
        }
    }
    //O(n)
    @Override
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pId = id[p];
        int qId = id[q];
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }


    }
    //O(1)
    @Override
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    //O(1)
    @Override
    public int find(int p) {
        validate(p);
        return id[p];
    }
    //O(1)
    @Override
    public int count() {
        return count;
    }

    public  static void main(String[] args) {
        int N = 1000000;
        UnionFind uf = new QuickFindUF(N);

        Random rand = new Random();

        long startTime = System.nanoTime();

        for(int i=0; i<N; i++) {
            int a = rand.nextInt(N);
            int b = rand.nextInt(N);
            uf.union(a, b);
        }

        for(int i=0; i<N; i++) {
            int a = rand.nextInt(N);
            int b = rand.nextInt(N);
            uf.connected(a, b);
        }

        long elasped = System.nanoTime() - startTime;

        System.out.println("The time elasped is: " + elasped);

    }
}
