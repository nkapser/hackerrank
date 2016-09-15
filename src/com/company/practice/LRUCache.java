package com.company.practice;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by naresh.kapse on 28/05/16.
 */
public class LRUCache<K,V> {

    private HashMap<K, V> map = new HashMap<>();
    private LinkedList<K> list = new LinkedList<>();
    private final int totalSize;
    private int currentSize;

    LRUCache(int totalSize) {
        this.totalSize = totalSize;
    }

    public void put(K key, V t) {
        if (map.containsKey(key)) {
            list.remove(key);
            list.addFirst(key);
        }else {
            if (currentSize >= totalSize) {
                K k = list.getLast();
                list.remove(k);
                map.remove(k);
            }
            map.put(key, t);
            list.addFirst(key);
        }
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            list.remove(key);
            list.addFirst(key);
            return map.get(key);
        }
        return null;
    }
}
