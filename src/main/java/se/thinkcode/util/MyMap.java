package se.thinkcode.util;


import java.util.*;

public class MyMap<K, V> implements Map<K, V> {
    private final List<Data<K, V>> data;
    private final int stepLength = 3;
    private final int sizeMap = 11;


    public MyMap() {
        data = new ArrayList<>(sizeMap);
        for (int i = 0; i < sizeMap; i++) {
            data.add(null);
        }
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < sizeMap; i++) {
            if (this.data.get(i) != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < sizeMap; i++) {
            if (this.data.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        List<Object> keys = new ArrayList<>();
        for (int i = 0; i < sizeMap; i++) {
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
        for (int i = 0; i < sizeMap; i++) {
            if (this.data.get(i) != null) {
                Object current_key = data.get(i).key;
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
        int pos = key.hashCode() % sizeMap;
        for (int i = 0; i < sizeMap; i++) {
            Data<K, V> d = data.get(pos);
            if (data.get(pos).key == key) {
                return d.value;
            }
            pos = (pos + stepLength) % sizeMap;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Data<K, V> d = new Data<>(key, value);
        int pos = key.hashCode() % sizeMap;
        int absPosition = Math.abs(pos);
        for (int i = 0; i < sizeMap; i++) {
            if (data.get(absPosition) == null) {
                data.set(absPosition, d);
                return value;
            }
            absPosition = (absPosition + stepLength) % sizeMap;
        }
        return null; //om den inte kan få plats gör map större
    }

    @Override
    public V remove(Object key) {
        V value = get(key);
        int pos = key.hashCode() % sizeMap;
        for (int i = 0; i < sizeMap; i++) {
            if (data.get(pos).key == key) {
                data.set(pos, null);
                return value;
            }
            pos = (pos + stepLength) % sizeMap;
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
        for (int i = 0; i < sizeMap; i++) {
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
