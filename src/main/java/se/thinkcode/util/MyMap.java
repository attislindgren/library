package se.thinkcode.util;


import java.util.*;

public class MyMap<K, V> implements Map<K, V> {
    private List<Data<K, V>> data;
    private final int stepLength = 3;


    public MyMap() {
        data = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            data.add(null);
        }
    }

    void bigger() {
        List<Data<K, V>> old = new ArrayList<>(data);
        int sizeMap = nextSize();
        data = new ArrayList<>();
        for (int i = 0; i < sizeMap; i++) {
            data.add(null);
        }
        for (Data<K, V> current : old) {
            if (current != null) {
                K key = current.key;
                V value = current.value;
                this.put(key, value);
            }
        }
    }

    private int nextSize() {
        return 23;
    }

    @Override
    public int size() {
        int count = 0;
        for (Data<K, V> d : data) {
            if (d != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (Data<K, V> d : data) {
            if (d != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        List<Object> keys = new ArrayList<>();
        for (Data<K, V> d : data) {
            if (d != null) {
                Object current_key = d.key;
                keys.add(current_key);
            }
        }
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        List<Object> keys = new ArrayList<>();
        for (Data<K, V> d : data) {
            if (d != null) {
                Object current_key = d.key;
                keys.add(current_key);
            }
        }
        for (Object key : keys) {
            if (get(key) == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int pos = key.hashCode() % data.size();
        int absPosition = Math.abs(pos);
        for (int i = 0; i < data.size(); i++) {
            Data<K, V> d = data.get(absPosition);
            if (data.get(absPosition).key == key) {
                return d.value;
            }
            absPosition = (absPosition + stepLength) % data.size();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size() >= (data.size() * 0.75)) {
            bigger();
        }
        Data<K, V> d = new Data<>(key, value);
        int pos = key.hashCode() % data.size();
        int absPosition = Math.abs(pos);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(absPosition) == null) {
                data.set(absPosition, d);
                return value;
            }
            absPosition = (absPosition + stepLength) % data.size();
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        V value = get(key);
        int pos = key.hashCode() % data.size();
        int absPosition = Math.abs(pos);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(absPosition).key == key) {
                data.set(absPosition, null);
                return value;
            }
            absPosition = (absPosition + stepLength) % data.size();
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.size(); i++) {
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
