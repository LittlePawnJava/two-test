package cn.com.icinfo.inter;

public interface UnionFind {
    void union(int p, int q);
    boolean connected(int p, int q);
    int find(int p);
    int count();
}

