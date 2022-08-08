package core.basesyntax;

public interface List<T> {
    void add(T value);

    void add(T value, int index);

    void addAll(List<T> list);

    T get(int index);

    void set(T value, int index);

    T remove(int index);

    T remove(T element);

    int size();

    boolean isEmpty();

    default T[] toArray() {
        T[] items = (T[]) new Object[size()];
        for (int i = 0; i < size(); i++) {
            items[i] = get(i);
        }
        return items;
    }
}
