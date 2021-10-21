
public class MyArrayList<E> implements MyList<E>
{
    Object[] data;
    int size;
    static final int DEFAULT_CAPACITY = 10;
    static final int DEFAULT_SIZE = 0;
    /**
     * Constructor for MyArrayList class
     * initalize the instance variables
     *
     */
    public MyArrayList()
    {
        //initalises object array with default 
        data = new Object[DEFAULT_CAPACITY];
        //initalises size of Array list to zero
        size = DEFAULT_SIZE;
    }

    /**
     * Constructor for MyArrayListClass
     * initalizes the instance variables
     *
     * @param initalCacity inital Capacity of the Array 
     */
    public MyArrayList (int initalCapacity)
    {
        //if the input is negative throw exception
        if (initalCapacity < 0)
            throw new IllegalArgumentException("inital capacity must be greater than 0");
        //make data object with inputed capacity
        data = new Object[initalCapacity];
        //set default size to zero
        size = DEFAULT_SIZE;
    }

    /**
     * Constructor for MyArrayList class
     * initalizes instance variables 
     *
     * @param arr Array list of objects of type E
     */
    public MyArrayList(E[] arr)
    {
        //when null we construct an array list with default capacity
        if (arr == null)
        {
            data = new Object[DEFAULT_CAPACITY];
            size = DEFAULT_SIZE;
        }
        else
        {
            //initalize instance variables with input array
            data = arr;
            //all elements are valid so size = length 
            size = arr.length;
        }

    }

    /**
     * Check if list has at least as much capacity as 
     * required capacity if it doesn't double the capacity.
     * If still not enough set capacity to required capacity. 
     * If capacity id 0 reset it to 10
     *
     * @param required capacity
     */
    @Override
    public void checkCapacity(int requiredCapacity)
    {
        Object[] temp;
        int cap;
        //check to see if capacity of list is less than required
        if (getCapacity() < requiredCapacity)
        {
            //if less and equal to zero set it to default
            if (data.length == 0)
                data = new Object[DEFAULT_CAPACITY];
            else
            {
                //double capacit
                cap = 2 * data.length;
                //if still less than required set cap to required capacity
                if (cap < requiredCapacity)
                    cap = requiredCapacity;
                //makes new array list with new capacity    
                temp = new Object[cap];
                //copies array list
                for (int i = 0; i < size; i++)
                {
                    temp[i] = data[i];
                }
                //sets new array list to main array data
                data = temp;

            }

        }
    }

    /**
     * returns capacity of the array
     *
     * @return int length of the data array
     */
    @Override
    public int getCapacity()
    {
        //return data length aka capacity
        return data.length;
    }

    /**
     * inserts a new object into the array at 
     * specified index
     *
     * @param int index of where the element inserted, 
     * E element being inserted
     */
    @Override
    public void insert(int index, E element)
    {
        //if the index is less that zero or greater than size throw exception
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException ();
        //assures array has required capacity     
        checkCapacity(size + 1);
        //move every object at the index one poistion up
        for (int i = size - 1; i >= index; i--)
        {
            data[i + 1] = data[i];
        }
        //set data at index equal to  the element
        data[index] = element;
        //increase size by one
        size ++;
    }

    /**
     * Add element to the end
     *
     * @param E element that we are going to add
     */
    @Override
    public void append(E element)
    {
        //assures array has required capacity
        checkCapacity(size + 1);
        //add element to the next position without an object
        data[size] = element;
        //increase size
        size ++;
    }

    /**
     * add element to the first position
     *
     * @param E element that is to be added to list
     */

    @Override
    public void prepend(E element)
    {
        //assures array has required capacity
        checkCapacity(size + 1);
        //moves all elements one index forward
        for (int i = size - 1; i >= 0; i--)
        {
            data[i + 1] = data[i];
        }
        //set first element equal to input
        data[0] = element;
        //increase size
        size ++;
    }

    /**
     * gets the element at a soecific index
     *
     * @param int index of element to be returned
     * @return element at index
     */
    @Override
    public E get(int index)
    {
        //if index is invalid throw exception
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException ();
        //return element at index
        return (E)data[index];
    }

    /**
     * changes element a specific index
     *
     * @param int index of element to be changed,
     * E element that we are placing at index
     * @return return overwritten element
     */
    @Override
    public E set(int index, E element)
    {
        //if index is invalid throw exception
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException ();
        //save element at index    
        E ret = (E)data[index];
        //overwrite element at index with inputed element 
        data[index] = element;
        //return overwritten element
        return ret;
    }

    /**
     * removes element at index and returns said element
     *
     * @param int index of element to be removed
     * @return E that is removed
     */
    @Override
    public E remove(int index)
    {
        //if index is invalid throw exception
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException ();
        //save element that is to be overwritten    
        E ret = (E) data[index];
        //move every element after index back
        for (int i = index; i < size - 1; i ++)
        {
            data[i] = data[i + 1];
        }
        //set last element equal to null
        data[size - 1] = null;
        //decrease size by one
        size --;
        //return overwritten element
        return ret;
    }

    /**
     * return size of array
     *
     * @return in size of array
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * trims the array such that the size equals the capacity
     * and all elements are valid
     *
     */
    @Override
    public void trimToSize()
    {
        //make new array with the capacity equal to the number of valid objects
        Object[] temp = new Object[size];
        //copy objects into new array
        for (int i = 0; i < size; i ++)
        {
            temp[i] = data[i];
        }
        //set main array equal to temp array
        data = temp;
    }
}