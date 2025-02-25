package core.basesyntax;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("World");
        list.add("Java");

        System.out.println("Size of list: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));

        list.remove("World");
        System.out.println("Size of list after removal: " + list.size());

        // Testing index out of bounds
        try {
            list.get(5);
        } catch (ArrayListIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Testing element not found
        try {
            list.remove("NotExist");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
