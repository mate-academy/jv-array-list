package core.basesyntax;

public interface List<T> {

    void add(T value);

    void add(T value, int index);

    void addAll(List<T> list);

    T get(int index);

    void set(T value, int index);

    T remove(int index);

    T remove(T t);

    int size();

    boolean isEmpty();

}
