package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            list.add(i);

        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.remove(23);


    }
}
