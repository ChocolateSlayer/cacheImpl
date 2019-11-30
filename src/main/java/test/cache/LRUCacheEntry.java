package test.cache;

public class LRUCacheEntry {

    private int key;
    private int value;
    private LRUCacheEntry prev;
    private LRUCacheEntry next;

    public LRUCacheEntry(int key, int value){
        this.key=key;
        this.value=value;
        this.prev = null;
        this.next = null;
    }

    public LRUCacheEntry() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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

