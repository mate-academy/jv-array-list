package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Element at index 1: " + list.get(1));

        list.remove("Banana");
        System.out.println("Size after removal: " + list.size());

        try {
            list.get(10);
        } catch (ArrayListIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        list.add("Orange", 1);
        System.out.println("Element at index 1: " + list.get(1));
    }
}
