package cn.com.icinfo.impl;

import cn.com.icinfo.inter.UnionFind;

import java.util.Random;

public class WeightQuickUnionUFV2 implements UnionFind {
    private int[] parent;
    private int count;//多少组
    private int[] rank;//每颗树大小

    public WeightQuickUnionUFV2(int n) {
        count = n;
        parent = new int[n];
        rank= new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private void validate(int p){
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("参数越界");
        }
    }
    //O(n)lgN
    @Override
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if(rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        } else {
            parent[rootQ] = rootP;
            rank[rootP] += 1;
        }
        count--;


    }
    //O(1)
    @Override
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    //O(1)
    @Override
    public int find(int p) {
        validate(p);
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
    //O(1)
    @Override
    public int count() {
        return count;
    }

    public  static void main(String[] args) {
        int N = 10000000;
        UnionFind uf = new WeightQuickUnionUFV2(N);

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
