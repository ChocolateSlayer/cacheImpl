package test.cache;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;

@RunWith(SpringRunner.class)
public class LRUCacheTests {

    private LRUCache cache;

    public LRUCacheTests() {
        try {
            this.cache = (LRUCache) new CacheBuilder()
                    .size(3)
                    .typeOf(CacheType.LRU)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldBeCreatedEmpty() {
        assertNull(cache.get(1));
    }

    @Test
    public void shouldPutAndGetValue() {
        cache.put(1, 1);
        assertSame("Must be returned same as put", cache.get(1), 1);
    }

    @Test
    public void shouldWorkBelowCacheSize() {
        cache.put(1, 1);
        assertSame(cache.get(1), 1);
        assertNull(cache.get(2));
        cache.put(2, 2);
        assertSame(cache.get(1), 1);
        assertSame(cache.get(2), 2);
    }

    @Test
    public void shouldRemoveOldestOnCacheOverflow() throws Exception {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        assertNull(cache.get(1));
        assertSame(cache.get(2), 2);
        assertSame(cache.get(3), 3);
        assertSame(cache.get(4), 4);
    }

    @Test
    public void shouldPutTopOnGet() throws Exception {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.put(4, 4);
        assertNull(cache.get(2));
        assertSame(cache.get(1), 1);
        assertSame(cache.get(3), 3);
        assertSame(cache.get(4), 4);
    }

    @Test
    public void shouldPutTopOnPut() throws Exception {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        assertNull(cache.get(2));
        assertSame(cache.get(1), 2);
        assertSame(cache.get(3), 3);
        assertSame(cache.get(4), 4);
    }
}
