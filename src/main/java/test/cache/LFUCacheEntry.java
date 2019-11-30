package test.cache;

public class LFUCacheEntry {
    private int value;
    private int frequency;

    public LFUCacheEntry() {
    }

    public LFUCacheEntry(int value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
