package core.basesyntax;

public interface List<T> {
    void add(T element);
    T get(int index);
    void remove(T element);
    int size();
    boolean isEmpty();
}