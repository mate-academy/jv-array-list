package core.basesyntax;

public interface List<T> {
    void add(T value);

    void add(int index, T value);

    T get(int index);

    T set(int index, T value);

    T remove(int index);

   boolean remove(T value);

    int size();

}
