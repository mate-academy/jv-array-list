package core.basesyntax;

public interface List<T> {

    void add(T value);

    void add(T value, int index);

    void addAll(List<T> list) throws Exception;

    T get(int index) throws Exception;

    void set(T value, int index) throws Exception;

    T remove(int index);

    T remove(T t);

    int size();

    boolean isEmpty();

}
