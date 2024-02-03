package com.ozgurokanozdal.dto;

public class Item<K,V> {

    private K key;
    private V value;
    public Item(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
