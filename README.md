# cacheImpl
Simple implementation of in memory cache for objects. 

Use CacheBuilder to create cache object and configure its size and eviction strategy 

```Java
Cache<String, String> cache = new CacheBuilder<String, String>()
                    .size(3)
                    .typeOf(CacheType.LFU)
                    .build();
```

Here is 2 test classes for testing cache work. 

LRUCacheTests provides tests for 
* Item should be put and get from cache
* Item should'not be get before put
* The oldest entry should be removed on cache overflow
* Item should be put at first position on get
* Item should be put at first position on put if already in cache

LFUCacheTests provides tests for 
* Item should be put and get from cache
* Item should'not be get before put
* The rarest entry should be removed on cache overflow
* Item should be put at first position on get
* Item should be put at first position on put if already in cache

CacheApplicationTests provides test for checking if CacheBuildes creates expected object

Run `$mvn test` to run tests
