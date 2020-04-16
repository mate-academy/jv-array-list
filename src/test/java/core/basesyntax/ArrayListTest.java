package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class ArrayListTest {

    @Test
    public void addElementToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add(null);
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals("Test failed! Size of array should be " + 4 + "but it is "
                + arrayList.size(), 4, arrayList.size());
        Assert.assertEquals("Test failed! Can't correct add element",
                "Test", arrayList.get(0));
        Assert.assertNull("Test failed! Can't correct add element", arrayList.get(1));
        Assert.assertEquals("Test failed! Can't correct add element",
                "for", arrayList.get(2));
        Assert.assertEquals("Test failed! Can't correct add element",
                "Mate", arrayList.get(3));
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
        arrayList.add(null, 0);
        Assert.assertEquals("Test failed! Size of array should be " + 5 + "but it is "
                + arrayList.size(), 5, arrayList.size());
        Assert.assertNull(arrayList.get(0));
        arrayList.add("value", 5);
        Assert.assertEquals("Test failed! Can't correct add element by index " + 5,
                "value", arrayList.get(5));
        Assert.assertEquals("Test failed! Size of array should be " + 6 + "but it is "
                + arrayList.size(), 6, arrayList.size());
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

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addElementInTheNegativePosition() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java", -6);
    }

    @Test
    public void checkingResize() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add("First + " + i);
        }
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals("Test failed! Array can't resize correctly",
                    "First + " + i, arrayList.get(i));
        }
    }

    @Test
    public void removeElementFromArrayListByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add(null);
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertEquals(4, arrayList.size());
        String actualResult = arrayList.remove(2);
        Assert.assertEquals("Test failed! Returned value should be " + actualResult,
                "Java", actualResult);
        Assert.assertEquals("Test failed! Size of array after removed element should be "
                + 3 + "but it is " + arrayList.size(), 3, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by index ",
                "Private", arrayList.get(2));
        actualResult = arrayList.remove(0);
        Assert.assertEquals("Test failed! Returned value should be " + actualResult,
                "String", actualResult);
        Assert.assertEquals(2, arrayList.size());
        Assert.assertNull("Test failed! Can't remove element by index ", arrayList.get(0));
        actualResult = arrayList.remove(1);
        Assert.assertEquals("Test failed! Returned value should be null",
                "Private", actualResult);
        Assert.assertEquals(1, arrayList.size());
        Assert.assertNull("Test failed! Can't remove element by index ", arrayList.get(0));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementFromArrayListByNonExistentIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.remove(5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementFromArrayListByNegativeIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.remove(-5);
    }

    @Test
    public void removeElementFromArrayListByValue() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.add(null);
        Assert.assertEquals(4, arrayList.size());
        String actualResult = arrayList.remove("Java");
        Assert.assertEquals("Test failed! Returned value should be " + actualResult,
                "Java", actualResult);
        Assert.assertEquals("Test failed! Size of array after removed element should be "
                + 3 + "but it is " + arrayList.size(), 3, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by value ",
                "Private", arrayList.get(1));
        actualResult = arrayList.remove("String");
        Assert.assertEquals("Test failed! Returned value should be " + actualResult,
                "String", actualResult);
        Assert.assertEquals(2, arrayList.size());
        Assert.assertEquals("Test failed! Can't remove element by index ",
                "Private", arrayList.get(0));
        actualResult = arrayList.remove(null);
        Assert.assertNull("Test failed! Returned value should be null", actualResult);
        Assert.assertEquals("Test failed! Size of array after removed element should be "
                + 1 + "but it is " + arrayList.size(), 1, arrayList.size());

    }

    @Test(expected = NoSuchElementException.class)
    public void removeElementFromArrayListByNonExistentValue() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertNull(arrayList.remove("Public"));
    }

    @Test
    public void removeObjectValueByValue() {
        Cat firstCat = new Cat("Fantic", "grey");
        Cat secondCat = new Cat("Barsik", "black");
        Cat thirdCat = new Cat("Tom", "white");
        Cat fourthCat = new Cat("Barsik", "black");
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(firstCat);
        cats.add(secondCat);
        cats.add(thirdCat);
        Assert.assertEquals("Test failed! Size of array should be " + 3 + "but it is "
                + cats.size(), 3, cats.size());
        Cat actualResult = cats.remove(fourthCat);
        Assert.assertEquals("Test failed! Returned value should be " + actualResult.toString(),
                fourthCat, actualResult);
        Assert.assertEquals("Test failed! Size of array should be " + 2 + "but it is "
                + cats.size(), 2, cats.size());
    }

    @Test
    public void setValueInIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("5");
        arrayList.add("115");
        Assert.assertEquals("115", arrayList.get(1));
        arrayList.set("511", 1);
        Assert.assertEquals("Test failed! Size of array should be " + 2 + "but it is "
                + arrayList.size(), 2, arrayList.size());
        Assert.assertEquals("Test failed! Can't set value by special position",
                "511", arrayList.get(1));
        arrayList.set(null, 0);
        Assert.assertEquals("Test failed! Size of array should be " + 2 + "but it is "
                + arrayList.size(), 2, arrayList.size());
        Assert.assertNull("Test failed! Can't set value by special position",
                arrayList.get(0));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setValueInTheNonExistentPosition() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.set("Third", 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setValueInTheNegativePosition() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.set("Third", -2);
    }

    @Test
    public void getElementByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        arrayList.add(null);
        String actualResult = arrayList.get(2);
        Assert.assertEquals("Third", actualResult);
        actualResult = arrayList.get(3);
        Assert.assertNull(actualResult);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getElementByNonExistedIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        arrayList.get(3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getElementByNegativeIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.get(-2);
    }

    @Test
    public void checkIsNotEmpty() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        Assert.assertFalse("Test failed! ArrayList shouldn't be empty", arrayList.isEmpty());
    }

    @Test
    public void checkIsEmpty() {
        ArrayList<String> arrayList = new ArrayList<>();
        Assert.assertTrue("Test failed! ArrayList should be empty", arrayList.isEmpty());
        arrayList.add("First");
        arrayList.remove(0);
        Assert.assertTrue("Test failed! ArrayList should be empty", arrayList.isEmpty());
    }
}
