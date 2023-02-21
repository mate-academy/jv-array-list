package core.basesyntax;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "",0);
        }
        list.log();
    }
}
