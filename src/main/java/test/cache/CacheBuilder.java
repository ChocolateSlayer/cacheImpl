package test.cache;


public class CacheBuilder<K, V> {

    private int size;
    private CacheType type;

    public CacheBuilder() {
    }

    public CacheBuilder<K, V> size(int size) {
        this.size = size;
        return this;
    }


    public CacheBuilder<K, V> typeOf(CacheType type) {
        this.type = type;
        return this;
    }

    public Cache<K, V> build() throws Exception {
        if (size < 1) throw new Exception("You must specify cache size > 0");
        if (type == null) throw new Exception("You must specify cache type of LRU or LFU");

        switch (type) {
            case LFU:
                return new LFUCache<>(size);
            case LRU:
                return new LRUCache<>(size);
            default:
                throw new Exception("Wrong cache type");
        }
    }
}
