package test.cache;

import java.util.HashMap;

public class LRUCache<K, V> implements Cache<K, V> {

    private int size;
    private HashMap<K, LRUCacheEntry<K, V>> values;
    private LRUCacheEntry first;
    private LRUCacheEntry last;


    public LRUCache(Integer size) {
        this.size = size;
        this.values = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        if (size < 1) return;

        if (values.containsKey(key)) {
            LRUCacheEntry<K, V> entry = values.get(key);
            entry.setValue(value);
            removeEntry(entry);
            putEntryAtTop(entry);
        } else {
            LRUCacheEntry entry = new LRUCacheEntry<>(key, value);

            if (values.size() >= size) {
                values.remove(last.getKey());
                removeEntry(last);
            }
            putEntryAtTop(entry);
            values.put(key, entry);
        }
    }

    @Override
    public V get(K key) {

        if (!values.containsKey(key)) return null;

        LRUCacheEntry<K, V> entry = values.get(key);
        removeEntry(entry);
        putEntryAtTop(entry);

        return entry.getValue();
    }

    private void putEntryAtTop(LRUCacheEntry entry) {
        entry.setNext(first);
        entry.setPrev(null);

        if (first != null) first.setPrev(entry);
        if (last == null) last = first;

        first = entry;
    }

    private void removeEntry(LRUCacheEntry entry) {

        if (entry.getPrev() != null) {
            entry.getPrev().setNext(entry.getNext());
        } else {
            first = entry.getNext();
        }

        if (entry.getNext() != null) {
            entry.getNext().setPrev(entry.getPrev());
        } else {
            last = entry.getPrev();
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
