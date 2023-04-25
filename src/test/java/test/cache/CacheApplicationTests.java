package test.cache;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

class CacheApplicationTests {
    @Test
    public void shouldCreateExpectedObject() throws Exception {

        int size = 5;
        Cache lruCache = new CacheBuilder()
                .size(size)
                .typeOf(CacheType.LRU)
                .build();

        assertThat(lruCache, instanceOf(LRUCache.class));
        assertEquals(lruCache.getSize(), size);

        Cache lfuCache = new CacheBuilder()
                .size(size)
                .typeOf(CacheType.LFU)
                .build();

        assertThat(lfuCache, instanceOf(LFUCache.class));
        assertEquals(lruCache.getSize(), size);
    }
}
