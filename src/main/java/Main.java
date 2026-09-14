import core.basesyntax.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(0, 0);
        Integer remove = list.remove(0);

        list.size();
    }
}
