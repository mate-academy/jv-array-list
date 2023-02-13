package core.basesyntax;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf(i));
        }
       list.remove("0");
        list.remove("1");
        list.remove("3");
        list.remove("4");
        System.out.println(list.isEmpty());

    }
}
