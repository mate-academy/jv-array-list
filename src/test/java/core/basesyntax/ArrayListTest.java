package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

    @Test(timeout = 1000)
    public void addElementToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals(3, arrayList.size());
        Assert.assertEquals("Test", arrayList.get(0));
        Assert.assertEquals("for", arrayList.get(1));
        Assert.assertEquals("Mate", arrayList.get(2));
    }

    @Test(timeout = 1000)
    public void addElementToArrayListByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        Assert.assertEquals(3, arrayList.size());
        arrayList.add("Academy", 0);
        Assert.assertEquals("Academy", arrayList.get(0));
        Assert.assertEquals("Test", arrayList.get(1));
        Assert.assertEquals("for", arrayList.get(2));
        Assert.assertEquals("Mate", arrayList.get(3));
    }

    @Test(timeout = 1000)
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
        Assert.assertEquals(5, arrayList.size());
        Assert.assertEquals("Academy", arrayList.get(3));
        Assert.assertEquals("Kiev", arrayList.get(4));
    }

    @Test(timeout = 1000)
    public void removeElementFromArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Java");
        arrayList.add("Private");
        Assert.assertEquals(3, arrayList.size());
        arrayList.remove("Java");
        Assert.assertEquals(2, arrayList.size());
        Assert.assertEquals("Private", arrayList.get(1));
        arrayList.remove(0);
        Assert.assertEquals(1, arrayList.size());
        Assert.assertEquals("Private", arrayList.get(0));
    }

    @Test(timeout = 1000)
    public void setValueInIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("5");
        arrayList.add("115");
        Assert.assertEquals("115", arrayList.get(1));
        arrayList.set("511", 1);
        Assert.assertEquals("511", arrayList.get(1));
    }

    @Test(timeout = 1000)
    public void checkIsEmptyMethod() {
        ArrayList<String> arrayList = new ArrayList<>();
        Assert.assertTrue(arrayList.isEmpty());
        arrayList.add("1");
        Assert.assertFalse(arrayList.isEmpty());
    }
}
