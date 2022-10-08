package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 200; i ++) {
            integerArrayList.add(i);
        }
        integerArrayList.set(12,-4);
    }
}
