package test.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache<K, V> implements Cache<K, V> {
    private int size;
    private HashMap<K, LFUCacheEntry<V>> values;
    private HashMap<Integer, LinkedHashSet<K>> counts;
    private final static int ONE_USE = 1;
    private static int minUsage = 0;

    public LFUCache(Integer size) {
        this.size = size;
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        counts.put(1, new LinkedHashSet<>());
    }


    @Override
    public void put(K key, V value) {
        if (values.containsKey(key)) {
            LFUCacheEntry<V> entry = values.get(key);
            entry.setValue(value);
            values.put(key, entry);
            get(key);
            return;
        }
        if (values.size() >= size) {
            K LFUKey = counts.get(minUsage).iterator().next();
            counts.get(minUsage).remove(LFUKey);
            values.remove(LFUKey);
        }

        values.put(key, new LFUCacheEntry<>(value, ONE_USE));
        minUsage = ONE_USE;
        counts.get(ONE_USE).add(key);
    }


    @Override
    public V get(K key) {
        if (!values.containsKey(key)) return null;

        LFUCacheEntry<V> entry = values.get(key);
        int frequency = entry.getFrequency();
        int newFrequency = frequency + 1;
        entry.setFrequency(newFrequency);
        values.put(key, entry);

        counts.get(frequency).remove(key);
        if (!counts.containsKey(newFrequency)) counts.put(newFrequency, new LinkedHashSet<>());
        counts.get(newFrequency).add(key);
        if (frequency == minUsage) minUsage += 1;

        return entry.getValue();
    }

    @Override
    public int getSize() {
        return size;
    }
}
