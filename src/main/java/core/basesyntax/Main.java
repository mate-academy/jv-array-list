package core.basesyntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int index = 1;
        int[] array = {1,99,3,4,5};
        System.out.println(array[index]);
        System.out.println(Arrays.toString(array));
        for (int i = index; i < array.length-1; i++) {
            array[i] = array[i + 1];
        }
        array = Arrays.copyOf(array,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
