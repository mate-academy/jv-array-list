package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        list.set("Hello", 0);
        list.set("World", 1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.add("wqj",2);
        list.add("dbd", 1);
    }
}

