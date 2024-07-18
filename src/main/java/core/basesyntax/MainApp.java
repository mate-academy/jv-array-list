package core.basesyntax;

public class MainApp {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 0; i < 5; i++) {
            list.add(2);
        }
        System.out.println(list);
        list.remove(Integer.valueOf(2));
        list.remove(Integer.valueOf(2));
        System.out.println(list);
    }

}
