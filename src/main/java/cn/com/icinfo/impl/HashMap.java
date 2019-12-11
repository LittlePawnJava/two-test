package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Map;

public class HashMap<K,V> implements Map<K, V> {
    static class Entry<K, V>{
        final K key;
        V value;
        final int hash;
        Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public K getKey(){
            return key;
        }

        public V setValue(V newValue){
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public V getValue(){
            return value;
        }

        public boolean equals(Object o){
            if (!(o instanceof Entry)) {
                throw new IllegalArgumentException("参数类型不符合");
            }
            Entry entry = (Entry) o;
            Object key1 = getKey();
            Object key2 = entry.getKey();
            if (key1 == key2 ||(key1 != null && key1.equals(key2))) {
                Object value1 = getValue();
                Object value2 = entry.getValue();

                if (value1 == value2 ||(value1 != null && value1.equals(value2))) {
                    return true;
                }
            }

            return false;
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1); //mod
    }

    static final int DEFAULT_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    Entry[] table;
    int size;
    int threshold;//阈
    final float loadFactor;

    public HashMap(int initalCapacity, float loadFactor) {
        if (initalCapacity < 0){
            throw new IllegalArgumentException("初始容量不能为负");
        }
        if (initalCapacity >= Integer.MAX_VALUE){
            initalCapacity = Integer.MAX_VALUE;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("负载因子为负数");
        }

        int capacity = 1;
        while (capacity < initalCapacity){
            capacity <<= 1;
        }

        this.loadFactor = loadFactor;;
        threshold = (int) (initalCapacity*loadFactor);
        table = new Entry[initalCapacity];

    }

    public HashMap(int initalCapacity) {
        this(initalCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public V put(K key, V value) {
        if (key == null){
            return putNullKey(value);
        }

        int hash = hash(key);
        int i = indexFor(hash,table.length);
        Object k;
        for (Entry<K,V> e = table[i]; e != null; e = e.next){
            if(e.hash == hash && ((k = e.key) == key || key.equals(k))){
                V oldValue = e.value;
                e.value = value;
                return oldValue;

            }
        }

        addEnty(hash,key,value,i);
        return null;
    }

    private V putNullKey(V value) {
        for (Entry<K,V> e=table[0]; e != null; e = e.next){
            if (e.key == null){
                V oldValue = e.getValue();
                e.setValue(value);
                return oldValue;
            }
        }
        addEnty(0, null, value, 0);
        return null;
    }

    private void addEnty(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> entry = table[bucketIndex];
        table[bucketIndex] = new Entry(key,value,hash,entry);
        if(++size >= threshold ){
            resize(2*table.length);
        }
    }

    private void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity*loadFactor);
    }

    private void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int i = 0; i < src.length; i++) {
            Entry<K,V> entry = src[i];
            if (entry != null) {
                src[i] = null;
                do {
                    Entry<K,V> next = entry.next;
                    int index = indexFor(entry.hash,newCapacity);
                    entry.next = newTable[index];
                    newTable[index] = entry;
                    entry = next;
                }while (entry != null);
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null){
            return getNullKey(key);
        }

        int hash = hash(key);
        int i = indexFor(hash,table.length);
        for (Entry<K,V> entry = table[i]; entry != null; entry = entry.next){
            Object k;
            if (entry.hash == hash && ((k = entry.getKey()) == key || key.equals(k))) {
                return entry.value;
            }
        }

        return null;
    }

    private V getNullKey(K key) {
        for (Entry<K,V> entry = table[0]; entry != null; entry = entry.next){
            if (entry.key == null) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null) {
            return containNullValue(value);
        }

        Entry[] tab = table;
        for (int i = 0; i < table.length; i++){
            for (Entry entry = tab[i]; entry != null; entry = entry.next){
                if (value.equals(entry.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containNullValue(V value) {
        Entry[] data = table;
        for (int i = 0; i < data.length; i++){
            for (Entry entry = data[i]; entry != null; entry = entry.next){
                if(entry.getValue() == null){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Entry<K,V> pre = table[index];
        Entry<K,V> e = pre;
        while(e != null){
            Entry<K,V> next = e.next;
            Object k;
            if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k)))) {
                System.out.println(table[index] == pre);
                if (pre == e){
                   table[index] = next;
                } else {
                  pre.next = next;
                }
                size--;

                return e.value;
            }

            pre = e;
            e = next;

        }

        return e == null ? null :e.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            tab[i] = null;
        }

        size = 0;

    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<10000; i++) {
            map.put(i+"", i);
        }

        map.remove(null);

        map.remove("6240");
        for(int i=0; i<10000; i+=5) {
            map.remove(i+"");
        }

        System.out.println("Map size is: " + map.size());

        for(int i=0; i<100; i++) {
            System.out.println("The ith element is: " + map.get(i+""));
        }

        /*int test = 1;
        while (test <= 5) {
            test <<= 1;
        }

        System.out.println(test);*/
    }
}
