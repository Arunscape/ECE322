import org.junit.jupiter.api.Test;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayLibTest{


    @Test
    public void reverseTest(){
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] ret = ArrayLib.reverse(arr, Integer.class);
        Integer[] expected = {9,8,7,6,5,4,3,2,1};
        assertArrayEquals(expected, ret);
    }

    @Test
    public void uniqueTest(){
        Integer[] arr = {1,1, 2, 6, 3, 4, 4, 5, 6, 6, 6, 7, 8, 9, null};
        Integer[] ret = ArrayLib.unique(arr, Integer.class);
        sort(ret);
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, ret);
    }

    @Test
    public void intersectionTest(){
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] b = {3, 4, 5};
        Integer[] ret = ArrayLib.intersection(a, b, Integer.class);
        assertArrayEquals(b, ret);
    }

    @Test
    public void unionTest(){
        Integer[] a = {1, 2, 3, 4};
        Integer[] b = {5, 6, 7, 8, 9};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] ret = ArrayLib.union(a, b, Integer.class);
        assertArrayEquals(expected, ret);
    }

    @Test
    public void indexOfTest(){
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, null};

        assertEquals(4, ArrayLib.indexOf(arr, Integer.valueOf(5)));
        assertEquals(-1, ArrayLib.indexOf(arr, 10));

        assertEquals(-1, ArrayLib.indexOf(arr, null));
    }

    @Test
    public void withoutTest(){
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] ret = ArrayLib.without(a, Integer.class, 3, 4, 5, 10);
        Integer[] expected = {1, 2, 6, 7, 8, 9};
        assertArrayEquals(expected, ret);
    }

    @Test
    public void withoutTestRemoveTwo(){
        Integer[] a = {1, 2, 3, 4, 4, 5, 6, 7, 8, 9};
        Integer[] ret = ArrayLib.without(a, Integer.class, 3, 4,4,  5, 10);
        Integer[] expected = {1, 2, 6, 7, 8, 9};
        assertArrayEquals(expected, ret);
    }

    @Test
    public void withoutTestRemoveFirstElement(){
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] ret = ArrayLib.without(a, Integer.class, 1, 3, 4, 5);
        Integer[] expected = {2, 6, 7, 8, 9};
        assertArrayEquals(expected, ret);
    }

    @Test
    public void intersectionTestDuplicate(){
        Integer[] a = {1, 2, 2, 3, 4};
        Integer[] b = {2, 2, 3, 4};

        Integer[] expected = {2, 2, 3, 4};
        assertArrayEquals(expected, ArrayLib.intersection(a, b, Integer.class));
    }

    // 36/37 mutation tests killed, the one that survives is because of the bug that we commented out above

}