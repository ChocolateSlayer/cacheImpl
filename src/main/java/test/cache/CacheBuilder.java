package test.cache;


public class CacheBuilder {

    private int size;
    private CacheType type;

    public CacheBuilder() {
    }

    public CacheBuilder size(int size) {
        this.size = size;
        return this;
    }


    public CacheBuilder typeOf(CacheType type) {
        this.type = type;
        return this;
    }

    public Cache build() throws Exception {
        switch (type) {
            case LFU:
                return new LFUCache(size);
            case LRU:
                return new LRUCache(size);
            default:
                throw new Exception("Wrong cache type");
        }
    }
}
