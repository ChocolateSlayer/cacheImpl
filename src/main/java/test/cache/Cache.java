package test.cache;

public interface Cache {
    void put(int key, int value);
    Integer get(int key);
    int getSize();
}
