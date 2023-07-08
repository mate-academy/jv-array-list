package core.basesyntax;

public class Element<T> {
    private final T value;

    public Element(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Element<?> element = (Element<?>) o;

        return (value == element.value)
                || (value != null && value.equals(element.value));
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
