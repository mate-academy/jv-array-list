package core.basesyntax;

public class ArrayList<T> implements List<T> {
    ArrayList<T> elementData;
    int size;
    float grow;

    public int checkIndex() {
        return (elementData.lenght - 1);
    }public ArrayList<>()

    {
    };

    @Override
    public void add(T value) {
        int index = checkIndex();
        elementData[index] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex() >= index) {
            elementData[index] = value;
        } else {
            elementData[checkIndex()] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index < checkIndex()) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        elementData[index] = null;
        int i = index;
        while(elementData.[i + 1] < elementData.length){
            elementData[i] = element[i + 1];
            i++;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) {
                this.remove(i);
            }
        }
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return elementData.length == 0;
    }
}
