package se.thinkcode.util;


import java.util.*;

public class MyMap<K, V> implements Map<K, V> {
    private final List<Data<K, V>> data;

    public MyMap() {
        data = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            data.add(null);
        }
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < 11; i++) {
            if (this.data.get(i) != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < 11; i++) {
            if (this.data.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        List<Object> keys = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (this.data.get(i) != null) {
                Object current_key = data.get(i).key;
                keys.add(current_key);
            }
        }
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        List<Object> keys = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (this.data.get(i) != null) {
                Object current_key = data.get(i).key;
                keys.add(current_key);
            }
        }

        for (int i = 0; i < keys.size(); i++) {
            Object key = keys.get(i);
            if (get(key) == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int pos = key.hashCode() % 11;
        Data<K, V> d = data.get(pos);
        return d.value;
    }

    @Override
    public V put(K key, V value) {
        Data<K, V> d = new Data<>(key, value);
        int pos = key.hashCode() % 11;
        data.set(pos, d);
        return value;
    }

    @Override
    public V remove(Object key) {
        V value = get(key);
        int pos = key.hashCode() % 11;
        data.set(pos, null);
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void clear() {
        for (int i = 0; i < 11; i++) {
            if (this.data.get(i) != null) {
                data.set(i, null);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public Collection<V> values() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    private static class Data<K, V> {
        K key;
        V value;

        Data(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
