package test.cache;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;

public class LFUCacheTests {

    private Cache<String, String> cache;

    public LFUCacheTests() {
        try {
            this.cache = new CacheBuilder<String, String>()
                    .size(3)
                    .typeOf(CacheType.LFU)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldPutAndGetValue() {
        cache.put("a", "a");
        assertSame("Must be returned same as put", cache.get("a"), "a");
    }

    @Test
    public void shouldNotGetBeforePut() {
        cache.put("a", "a");
        assertSame(cache.get("a"), "a");
        assertNull(cache.get("b"));
        cache.put("b", "b");
        assertSame(cache.get("a"), "a");
        assertSame(cache.get("b"), "b");
    }


    @Test
    public void shouldPutTopOnGet() {
        cache.put("a", "a");
        cache.put("b", "b");
        cache.get("a");
        cache.put("c", "c");
        cache.put("d", "d");
        assertNull(cache.get("b"));
        assertSame(cache.get("a"), "a");
        assertSame(cache.get("c"), "c");
        assertSame(cache.get("d"), "d");
    }


    @Test
    public void shouldPutTopAndRenewOnPut() {
        cache.put("a", "a");
        cache.put("b", "b");
        cache.put("a", "b");
        cache.put("c", "c");
        cache.put("d", "d");
        assertNull(cache.get("b"));
        assertSame(cache.get("a"), "b");
        assertSame(cache.get("c"), "c");
        assertSame(cache.get("d"), "d");
    }

    @Test
    public void shouldRemoveRarestOnCacheOverflow() {
        cache.put("a", "a");
        cache.get("a");
        cache.put("b", "b");
        cache.get("b");
        cache.put("c", "c");
        cache.put("d", "d");
        cache.put("e", "e");
        assertNull(cache.get("c"));
        assertNull(cache.get("d"));
        assertSame(cache.get("a"), "a");
        assertSame(cache.get("b"), "b");
        assertSame(cache.get("e"), "e");
    }

}
