package core.basesyntax;

public class Application {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");

        System.out.println("List size: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));
    }

}
