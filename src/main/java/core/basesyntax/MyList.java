package core.basesyntax;

public interface MyList<T> {
    boolean add(T value);

    void add(T value, int index);

    void addAll(MyList<T> list);

    T get(int index);

    void set(T value, int index);

    T remove(int index);

    T remove(Object element);

    int size();

    boolean isEmpty();
}
