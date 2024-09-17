package core.basesyntax;

public interface List<T> {
    void add(T value);

    void add(T value, int index);

    void addAll(List<T> list);

    T get(int index);

    T set(T value, int index);

    T remove(int index);

    T remove(T element);

    int size();

    boolean isEmpty();
}
