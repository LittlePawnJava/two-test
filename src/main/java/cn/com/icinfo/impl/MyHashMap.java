package cn.com.icinfo.impl;

import cn.com.icinfo.inter.Map;

public class MyHashMap<K,V> implements Map<K, V> {
    static class Entry<K,V> {
        K key;
        V value;
        int hash;
        Entry<K,V> next;

        public Entry(K key, V value, int hash, Entry next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        K getKey(){
            return key;
        }

        V getValue(){
            return value;
        }

        V setValue(V newValue){
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o){
            if (!(o instanceof Entry)) {
                throw new IllegalArgumentException("参数类型不符合");
            }
            Entry<K,V> entry = (Entry<K, V>) o;
            K key1 = getKey();
            K key2 = entry.key;
            if (key1 == key2 || (key1 != null && key1.equals(key2))) {
                V vlue1 = getValue();
                V vlue2 = entry.value;
                if (vlue1 == vlue2 || (vlue1 != null && vlue1.equals(vlue2))) {
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
    int threshold;
    float loadFactor;
    int capacity;

    public MyHashMap(float loadFactor, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("初始容量不能为负数");
        }
        // 防止整数越界 int
        if (capacity >= Integer.MAX_VALUE) {
            capacity = Integer.MAX_VALUE;
        }

        if (loadFactor < 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("load factor is not can be user");
        }

        int baseCapacity = 1;
        while(baseCapacity <capacity){
            baseCapacity <<= 1;
        }
        this.table = new Entry[baseCapacity];
        this.size = 0;
        this.threshold = (int) (loadFactor * baseCapacity);
        this.loadFactor = loadFactor;
        this.capacity = capacity;
    }

    public MyHashMap(int capacity) {
        this(DEFAULT_LOAD_FACTOR, capacity);
    }

    public MyHashMap(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(key,value);
        }
        int hash = hash(key);
        int tableIndex = indexFor(hash,table.length);
        for (Entry<K,V> entry = table[tableIndex]; entry != null; entry = entry.next) {
            Object k;
            if (entry.hash == hash && ((k = entry.key) == key || key.equals(k))) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        addEntry(key,value,hash,tableIndex);
        return null;
    }

    private void addEntry(K key, V value, int hash, int tableIndex) {
        Entry<K,V> entry = table[tableIndex];
        table[tableIndex] = new Entry(key,value,hash,entry);
        if (++size >= threshold) {
            resize(table.length * 2);
        }
    }

    private void resize(int newCapacity) {
        Entry[] src = table;
        if (src.length >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold =(int) (newCapacity*loadFactor);

    }

    private void transfer(Entry[] newTable) {
        Entry[] oldTable = table;
        for (int i = 0; i < oldTable.length; i++){
            Entry<K,V> entry = oldTable[i];
            if (entry != null) {
                oldTable[i] = null;
                do{
                    Entry<K,V> next = entry.next;
                    int newIndex = indexFor(entry.hash,newTable.length);
                    entry.next = newTable[newIndex];
                    newTable[newIndex] = entry;
                    entry = next;
                }while (entry != null);
            }

        }
    }

    private V putForNullKey(K key, V value) {
        for (Entry<K,V> e = table[0]; e != null; e = e.next){
            if (e.key == key ) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(null,value,0,0);
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
           return getForNullKey(key);
        }

        int hash = hash(key);
        int index  = indexFor(hash, table.length);
        for (Entry<K,V> entry = table[index]; entry != null; entry = entry.next) {
            Object k;
            if (entry.hash == hash|| ((k = entry.key) == key || (k != null && k.equals(key)))) {
                return entry.value;
            }
        }
        return null;
    }

    private V getForNullKey(K key) {
        for(Entry<K,V> entry = table[0]; entry != null; entry = entry.next){
            if(entry.key == null) {
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
            return containsValueForNull(value);
        }
        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            for(Entry e=tab[i]; e!=null; e=e.next) {
                if(value.equals(e.value)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean containsValueForNull(V value) {
        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            for(Entry e=tab[i]; e!=null; e=e.next) {
                if(e.value == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index  = indexFor(hash,table.length);
        Entry<K,V> pre = table[index];
        Entry<K,V> e = pre;
        while(e != null){
            Entry<K,V> next = e.next;
            Object k;
            if(hash == e.hash && ((k = e.key) == key || (key !=null && key.equals(k)))){
                if(pre == e){
                    table[index] = next;
                }else {
                    pre.next = next;
                }
                size--;
                return e.value;
            }

            pre = e;
            e = next;
        }
        return null;
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
        if(table.length <= 0){
            throw new IllegalArgumentException("集合为空");
        }
        for (int i = 0; i < table.length; i++){

            table[i] = null;
        }
        size = 0;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<100; i++) {
            map.put(i+"", i);
        }

        for(int i=0; i<100; i+=5) {
            map.remove(i+"");
        }

        System.out.println("Map size is: " + map.size());

        for(int i=0; i<100; i++) {
            System.out.println("The ith element is: " + map.get(i+""));
        }
    }
}
