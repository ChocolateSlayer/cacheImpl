package test.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache<K, V> implements Cache<K, V> {

    private int size;
    private HashMap<K, LFUCacheEntry<V>> values;
    private HashMap<Integer, LinkedHashSet<K>> counts;
    private static Integer min = 0;

    public LFUCache(Integer size) {
        this.size = size;
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        counts.put(1, new LinkedHashSet<>());
    }


    @Override
    public void put(K key, V value) {
        if (size < 1) return;
        if (values.containsKey(key)) {
            LFUCacheEntry<V> entry = values.get(key);
            entry.setValue(value);
            values.put(key, entry);
            get(key);
            return;
        }
        if (values.size() >= size) {
            K LFUKey = counts.get(min).iterator().next();
            counts.get(min).remove(LFUKey);
            values.remove(LFUKey);
        }

        values.put(key, new LFUCacheEntry<>(value, 1));

        min = 1;
        counts.get(1).add(key);
    }


    @Override
    public V get(K key) {
        if (!values.containsKey(key)) return null;

        LFUCacheEntry<V> entry = values.get(key);
        int frequency = entry.getFrequency();
        entry.setFrequency(frequency + 1);
        values.put(key, entry);

        counts.get(frequency).remove(key);
        if (!counts.containsKey(frequency + 1)) counts.put(frequency + 1, new LinkedHashSet<>());
        counts.get(frequency + 1).add(key);
        if (frequency == min) min += 1;

        return entry.getValue();
    }

    @Override
    public int getSize() {
        return size;
    }
}
