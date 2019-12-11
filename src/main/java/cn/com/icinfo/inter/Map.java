package cn.com.icinfo.inter;


public interface Map<K, V> {
    public  V put(K key, V value);

    public V get(K key);

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public V remove(K key);

    public int size();

    public boolean isEmpty();

    public void clear();
}