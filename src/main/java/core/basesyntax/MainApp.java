package core.basesyntax;

public class MainApp {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        print(list);
        list.remove("c");
        print(list);
        list.remove("b");
        print(list);
    }
    private static void print(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
