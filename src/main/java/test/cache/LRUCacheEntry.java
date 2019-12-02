package test.cache;

public class LRUCacheEntry<K, V> {
    private K key;
    private V value;
    private LRUCacheEntry prev;
    private LRUCacheEntry next;

    public LRUCacheEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public LRUCacheEntry() {
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

    public LRUCacheEntry getPrev() {
        return prev;
    }

    public void setPrev(LRUCacheEntry prev) {
        this.prev = prev;
    }

    public LRUCacheEntry getNext() {
        return next;
    }

    public void setNext(LRUCacheEntry next) {
        this.next = next;
    }
}

