package com.company.tuple;

public record Tuple<K, V>(K first, V second) {

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
