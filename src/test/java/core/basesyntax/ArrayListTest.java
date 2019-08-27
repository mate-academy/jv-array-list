package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

    @Test
    public void addElementToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals("Test failed! Size of array should be " + 3 + "but it is "
                + arrayList.size(), 3, arrayList.size());
        Assert.assertEquals("Test failed! Can't correct add element",
                "Test", arrayList.get(0));
        Assert.assertEquals("Test failed! Can't correct add element",
                "for", arrayList.get(1));
        Assert.assertEquals("Test failed! Can't correct add element",
                "Mate", arrayList.get(2));
    }

    @Test
    public void checkingResize() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add("First + " + i);
        }
        Assert.assertEquals("Test failed! Array can't resize",
                "First + 990", arrayList.get(990));
    }

    @Test
    public void addElementToArrayListByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals("Test failed! Size of array should be " + 3 + "but it is "
                + arrayList.size(), 3, arrayList.size());
        arrayList.add("Academy", 1);
        Assert.assertEquals("Test", arrayList.get(0));
        Assert.assertEquals("Test failed! Can't correct add element by index " + 1,
                "Academy", arrayList.get(1));
        Assert.assertEquals("for", arrayList.get(2));
        Assert.assertEquals("Mate", arrayList.get(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addElementInTheNonExistentPosition() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Second", 5);
    }

    @Test
    public void addListToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals(3, arrayList.size());
        ArrayList<String> newArrayList = new ArrayList<>();
        newArrayList.add("Academy");
        newArrayList.add("Kiev");
        arrayList.addAll(newArrayList);
        Assert.assertEquals("Test failed! Size of array should be " + 5 + "but it is "
                + arrayList.size(), 5, arrayList.size());
        Assert.assertEquals("Academy", arrayList.get(3));
        Assert.assertEquals("Kiev", arrayList.get(4));
    }

    @Test
    public void removeElementFromArrayListByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertEquals(3, arrayList.size());
        arrayList.remove(1);
        Assert.assertEquals("Test failed! Size of array after removed element should be "
                + 2 + "but it is " + arrayList.size(), 2, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by index ",
                "Private", arrayList.get(1));
        arrayList.remove(0);
        Assert.assertEquals(1, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by index ",
                "Private", arrayList.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementFromArrayListByNonExistentIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.remove(5);
    }

    @Test
    public void removeElementFromArrayListByValue() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertEquals(3, arrayList.size());
        arrayList.remove("Java");
        Assert.assertEquals("Test failed! Size of array after removed element should be "
                + 2 + "but it is " + arrayList.size(), 2, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by value ",
                "Private", arrayList.get(1));
        arrayList.remove("String");
        Assert.assertEquals(1, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by index ",
                "Private", arrayList.get(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementFromArrayListByNonExistentValue() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertNull(arrayList.remove("Public"));
    }

    @Test
    public void setValueInIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("5");
        arrayList.add("115");
        Assert.assertEquals("115", arrayList.get(1));
        arrayList.set("511", 1);
        Assert.assertEquals("Test failed! Can't set value by special position",
                "511", arrayList.get(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setValueInTheNonExistentPosition() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.set("Third", 5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getElementByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        arrayList.get(10);
    }

    public void checkIsNotEmpty() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        Assert.assertFalse("Test failed! ArrayList shouldn't be empty", arrayList.isEmpty());
    }

    public void checkIsEmpty() {
        ArrayList<String> arrayList = new ArrayList<>();
        Assert.assertTrue("Test failed! ArrayList should be empty", arrayList.isEmpty());
        arrayList.add("First");
        arrayList.remove(0);
        Assert.assertTrue("Test failed! ArrayList should be empty", arrayList.isEmpty());
    }
}
