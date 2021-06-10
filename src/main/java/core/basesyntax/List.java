package core.basesyntax;

public interface List<E> {
    void add(E value);

    void add(E value, int index);

    void addAll(List<E> list);

    E get(int index);

    void set(E value, int index);

    E remove(int index);

    E remove(E element);

    int size();

    boolean isEmpty();
}
