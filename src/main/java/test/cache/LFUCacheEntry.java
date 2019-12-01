package test.cache;

public class LFUCacheEntry<V> {
    private V value;
    private int frequency;

    public LFUCacheEntry() {
    }

    public LFUCacheEntry(V value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
