
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;

import org.junit.*;
public class MyArrayListTester {
    static final int DEFAULT_CAPACITY = 10;
    static final int DEFAULT_SIZE = 0;
    static final int MY_CAPACITY = 3;

    Object[] arr = new Object[10];
    Integer[] arrInts = {1,2,3};

    private MyArrayList list1, list2, list3, list4, list5;

    /**
     * runs before a test to set default constructors
     */
    @Before
    public void setUp() throws Exception {
        list1 = new MyArrayList();
        list2 = new MyArrayList(DEFAULT_CAPACITY);
        list3 = new MyArrayList(MY_CAPACITY);
        list4 = new MyArrayList(arr);
        list5 = new MyArrayList<Integer>(arrInts);
    }

    /**
     * Tests the constructors when their is invalid input
     * to see if a exception is thrown
     */
    @Test
    public void testInvalidConstructor() {
        //invalid input to see if an exception is thrown
        try {
            MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }

    /**
     * Tests the constructors when their is null array inputed to see if
     * basic size and capacity are set
     */
    @Test
    public void testNullInput(){
        //new null array list
        Object[] example= null;
        //new MyArrayList with null input
        MyArrayList basic = new MyArrayList(null);
        assertEquals("Check for size when null array is inputed",
                DEFAULT_SIZE, basic.size());
        assertEquals("Check capacity when null array is inputed",
                DEFAULT_CAPACITY, basic.getCapacity());
    }

    /**
     * Tests the constructors set correct size
     */
    @Test
    public void testDefaultSize() {
        assertEquals("Check size for default constructor",
                0, list1.size());
        assertEquals("Check size for constructor with given capacity of 10",
                0, list2.size());
        assertEquals("Check size for constructor with given capacity of 3",
                0, list3.size());
        assertEquals("Check size for constructor with given array",
                10, list4.size());
        assertEquals("Check size for constructor with given int array",
                3, list5.size());
    }

    /**
     * Tests the constructors set correct capacity
     */
    @Test
    public void testInitialCapacity() {
        assertEquals("Check default capacity",
                DEFAULT_CAPACITY, list1.getCapacity());
        assertEquals("Check given capacity",
                MY_CAPACITY, list3.getCapacity());
    }

    /**
     * Tests to see if append works fo empty array list,
     * an array list with elements but no needed
     * capacity change and an array list that needs to
     * increase capacity
     */
    @Test
    public void testAppend() {
        int[] nums = {2,4};
        list1.append(nums[0]);//append to empty list
        assertEquals("Check that append increments size",
                1, list1.size());
        assertEquals("Check that the number is appended",
                2, list1.get(list1.size() - 1));
        assertEquals("Check that capacity is now default",
                DEFAULT_CAPACITY, list1.getCapacity());
        //append to list with element and no capacity change needed
        list1.append(nums[1]);
        assertEquals("Check that capacity is unchanged",
                DEFAULT_CAPACITY, list1.getCapacity());
        assertEquals("Check that the number is appended",
                4, list1.get(list1.size() - 1));
        assertEquals("Check that size is incremented",
                2, list1.size());
        //append to list that needs capacity change
        list4.append(nums[1]);
        assertEquals("Check that capacity is changed",
                2 * DEFAULT_CAPACITY, list4.getCapacity());
        assertEquals("Check that the number is appended",
                4, list4.get(list4.size() - 1));
        assertEquals("Check that size is incremented",
                11, list4.size());
    }

    /**
     * Tests to see if prepend works for empty array list,
     * an array list with elements but no needed capacity
     * change and an array list that needs to increase capacity
     */
    @Test
    public void testPrepend(){
        int[] nums = {2,4};
        //prpend to list that is empty
        list1.prepend(nums[0]);
        assertEquals("Check that prepend increments size",
                1, list1.size());
        assertEquals("Check that the number is prepended",
                2, list1.get(0));
        //prepend to list not empty and doent need to expand
        list1.prepend(nums[1]);
        assertEquals("Check that capacity is unchanged",
                DEFAULT_CAPACITY, list1.getCapacity());
        assertEquals("Check that the number is prepended",
                4, list1.get(0));
        //prepend to list that is not empty and needs to expand
        list4.prepend(nums[1]);
        assertEquals("Check that number is prepended",
                4, list4.get(0));
        assertEquals("Check that the capacity is doubled",
                DEFAULT_CAPACITY * 2, list4.getCapacity());
    }

    /**
     * Tests to see if insert works for empty array list,
     * an array list with elements but no needed capacity
     * change and an array list that needs to increase capacity
     */
    @Test
    public void testInsert(){
        int[] nums = {1, 2, 3};
        //insert in empty list that doesnt need to be expanded
        list1.insert(0, nums[1]);
        assertEquals("Check that insert increments size",
                1, list1.size());
        assertEquals("Check that number is inserted",
                nums[1], list1.get(0));
        //insert in list that must be expanded
        list4.insert(list4.size(), nums[1]);
        assertEquals("Check that insert increments size",
                11, list4.size());
        assertEquals("Check that number is inserted",
                nums[1], list4.get(list4.size() - 1));
        assertEquals("Check to see that the capacity increased",
                DEFAULT_CAPACITY * 2, list4.getCapacity());
        // tests invalid negative input to see if exception is thrown
        try {
            list4.insert(-1, nums[1]);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
        // tests invalid out of bounds input to see if exception is thrown
        try {
            list4.insert(list4.size() + 1, nums[1]);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
    }

    /**
     * Tests to see if get works for first element and last
     * element as well as if it throws an exception
     * when index inputed is invalid
     */
    @Test
    public void testGet(){
        //test if we can get first element
        assertEquals("Check that we can get the value at index 0",
                1, list5.get(0));
        //test if we can get last element
        assertEquals("Check that we can get the value at index size - 1",
                3, list5.get(list5.size() - 1));
        // tests invalid out of bounds input to see if exception is thrown
        try {
            list5.get(list5.size());
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
        // tests invalid negative input to see if exception is thrown
        try {
            list5.get(-1);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
    }

    /**
     * Tests to see if set works and if it catches exceptions when input is
     * invalid
     */
    @Test
    public void testSet(){

        //test if we can set first element and last element in array
        list5.set(0,2);
        list5.set(list5.size()-1, 2);
        assertEquals("Check that we can set the value at index 0",
                2, list5.get(0));
        assertEquals("Check that we can get the value at index size - 1",
                2, list5.get(list5.size() - 1));
        // tests invalid out of bounds input to see if exception is thrown
        try {
            list5.set(list5.size(),2);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
        // tests invalid negative input to see if exception is thrown
        try {
            list5.set(-1,2);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
    }

    /**
     * Tests to see if remove works for first and last
     * element as well as checking if exception is thrown
     * if input is invalid
     */
    @Test
    public void testRemove(){
        //remove first element
        list5.remove(0);
        assertEquals("Check that list removes object; nothing else changes",
                2, list5.get(0));
        assertEquals("Check that size decreases by one",
                2, list5.size());
        assertEquals("Check that capacity remains the same",
                3, list5.getCapacity());
        //remove last elemement
        list5.remove(list5.size()-1);
        assertEquals("Check that list removes object; nothing else changes",
                2, list5.get(0));
        assertEquals("Check that size decreases by one",
                1, list5.size());
        // tests invalid negative input to see if exception is thrown
        try {
            list1.remove(-1);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
        // tests invalid out of bounds input to see if exception is thrown
        try {
            list5.remove(list5.size() + 1);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            //pass
        }
    }

    /**
     * Tests to see if size works when array list is empty and
     * when array list has elements
     */
    @Test
    public void testSize(){
        assertEquals("Check to see that size of null list equals 0",
                0, list1.size());
        assertEquals("Check to see that size of list with 3 elements equals 3",
                3, list5.size());
    }

    /**
     * Tests to see if check capacity works when capacity needs
     * to be doubled, original capacity is 0, and when capacity
     * needs to be more than doubled
     */
    @Test
    public void testCheckCapacity(){
        MyArrayList list0 = new MyArrayList(0);
        //check with empty list that is null
        list0.checkCapacity(2);
        assertEquals("Check to see if cap becomes 10 when capacity at 0",
                DEFAULT_CAPACITY, list0.getCapacity());
        //check with list that has required capacity
        list5.checkCapacity(MY_CAPACITY);
        assertEquals("Check to see that capacity will stay the same",
                MY_CAPACITY, list5.getCapacity());
        //check with list without required capacity
        list5.checkCapacity(MY_CAPACITY + 1);
        assertEquals("Check to see that capacity doubles",
                2 * MY_CAPACITY, list5.getCapacity());
        //check with list that needs to be more than doubled
        list3.checkCapacity(2 * MY_CAPACITY + 1);
        assertEquals("Check to see that capacity doubles",
                2 * MY_CAPACITY + 1, list3.getCapacity());
    }

    /**
     * Tests to see if trim works when array needs to be
     * trimmed and empty, needs to be trimmed and contains
     * elements and when it doesnt need to be trimmed
     */
    @Test
    public void testTrimToSize(){
        //check if trim works for empty list that needs to be trimmed
        list1.trimToSize();
        assertEquals("Check to see if empty list is trimmed",
                0, list1.getCapacity());
        assertEquals("Check to see if size remains unchanged",
                0, list1.size());
        //checks to see if arraylist doesnt trim when capacity = size
        list5.trimToSize();
        assertEquals("Check to see that array with elements is trimmed",
                3, list5.getCapacity());
        assertEquals("Check if size unchanged for arraylist with elements",
                3, list5.size());
        //trims when populated but capacity not reached
        list3.append(2);
        list3.trimToSize();
        assertEquals("Check to see that it is trimmed",
                1, list3.getCapacity());
        assertEquals("Check to see that siz remains the same",
                1, list3.size());
    }

}
