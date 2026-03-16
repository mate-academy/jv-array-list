package core.basesyntax;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Is list empty? " + list.isEmpty());

        list.add(4);
        list.add(7);
        list.add(5);
        list.add(23);
        System.out.println("Added elements: " + listToString(list));

        list.add(13, 3);
        System.out.println("After adding element by index: " + listToString(list));

        ArrayList<Integer> other = new ArrayList<>();
        other.add(5);
        other.add(20);
        list.addAll(other);
        System.out.println("After adding all: " + listToString(list));

        System.out.println("Get element: " + list.get(0));

        list.set(9, 2);
        System.out.println("After setting element: " + listToString(list));

        list.remove(5);
        System.out.println("After removing element by index: " + listToString(list));

        try {
            Integer removed = list.remove((Integer) 20);
            System.out.println("Removed element: " + removed);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }
        System.out.println("After removing element by value: " + listToString(list));

        System.out.println("Size: " + list.size());

        System.out.println("Is list empty now? " + list.isEmpty());
    }

    private static <T> String listToString(ArrayList<T> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
