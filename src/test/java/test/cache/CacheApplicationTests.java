package test.cache;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(SpringRunner.class)
@SpringBootTest
class CacheApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void shouldCreateExpectedObject() throws Exception {

        int size = 3;
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



    @Test
    public void lruValueCanBePutAndGet() throws Exception {
        Cache lruCache = new CacheBuilder()
                .size(3)
                .typeOf(CacheType.LRU)
                .build();

        int key = 1;
        int value = 1;
        lruCache.put(key, value);

        assertSame("Must be returned same as put", lruCache.get(key), value);
    }

}
