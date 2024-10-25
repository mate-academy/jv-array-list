## Common mistakes (jv-array-list)

#### Don't begin class or method implementation with an empty line.
Remove all redundant empty lines, be careful :)
#### Don't use Objects, Arrays, or any other util class.
#### Any magic numbers should be constants
Your code should be easy to read. Move all hardcoded values to constant fields and give them informative names.

Bad example:
```java
public class Shelf {
    private Object[] items;

    public Shelf() {
        items = new Object[6];
    }
}
```
Good example:
```java
public class Shelf {
    private static final int MAX_ITEMS_NUMBER = 6;
    private Object[] items;

    public Shelf() {
        items = new Object[MAX_ITEMS_NUMBER];
    }
}
```
#### Don't complicate if-else construction. [Detailed explanation.](https://mate-academy.github.io/jv-program-common-mistakes/java-core/complicated-if-else.html)
#### Don't create repeating code.
If the logic of your code repeats - move it to a separate private method. For example, the index checks for the `get()`, `set()`, and `remove()` methods should be moved to a separate method.

There is even a software design principle called [DRY](https://dzone.com/articles/software-design-principles-dry-and-kiss) that urges you not to repeat yourself.
#### Use informative names for your variables and methods.
Do not use abstract words like `string` or `array` as variable names. Do not use one-letter names. 
By the name of the variable, it should be clear what it is responsible for. The name of the method should make it clear what it does.

Bad example:
```java
    private void grow() {
        if (size == elementData.length) {
            // grow logic
        }
    }
```
Good example:
```java
    private void grow() {
        // grow logic
    }
```
Another good example:
```java
    private void growIfArrayFull() {
        if (size == elementData.length) {
            // grow logic
        }
    }
```
In the first example, we have `grow()` with `if` inside. 
This means that sometimes the array grows and sometimes it doesn't. When a developer who reads the code sees a call to the `grow()` method, 
he thinks that the array will be increased. Only if he reads the body of the `grow()` method, he will understand that the array does not always grow. 
The method name says nothing about this conditional logic.
#### Don't create redundant variables.
Redundant variables are confusing and make your code less clean and much more difficult to read. Not to mention they occupy stack memory.
#### Use [System.arraycopy()](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#arraycopy-java.lang.Object-int-java.lang.Object-int-int-) to move your array elements.
#### Resize the array in a separate method.
According to one of the important principles called the **Single Responsibility** principle, any software component must have only one responsibility, that is, to be in charge of doing just one concrete thing.
#### Make your exceptions informative.
If you throw an Exception, you should always add an informative message so that it would be clear from the stack trace what exactly went wrong.
