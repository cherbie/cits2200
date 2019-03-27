import CITS2200.*;

/*
  left_element  = (left+deque.length)%deque.length;
  right_element = (left+count-1)%deque.length;
  right_placement_index = (left+count)%deque.length;
*/

public class DequeCyclic implements CITS2200.Deque<Object>
{
    private Object[] deque; //block implementation of a deque
    private int count; //number of elements in the queue
    private int left; //last element in the queue

    /**
     * Constructs an empty deque of a particular size.
     * @param size int
     **/
    public DequeCyclic(int size)
    {
        deque = new Object[size];
        count = 0;
        left = 0;
    }

    /**
     * Tests if the deque is is empty
     * @return true iff the deque is empty, false otherwise
     **/
    public boolean isEmpty()
    {
        return count <= 0;
    }

    /**
     * Tests if the deque is full
     * @return true iff the deque is full, false otherwise
     **/
    public boolean isFull()
    {
        return count >= deque.length;
    }

    /**
     * Add Object c as the left-most object in the deque
     * @param c Object
     * @throws Overflow exception if the deque is full
     **/
    public void pushLeft(Object c) throws Overflow
    {
        if(!isFull())
        {
            left--; //decrement left (negative taken into consideration)
            int index = (left+deque.length)%deque.length;
            left = index;
            deque[left] = c;
            count++; //increment the number of elements in the deque
        }
        else throw new Overflow("Deque block implementation is full.");
    }

    /**
     * Add Object c as the right-most object in the deque
     * @throws Overflow exception if the deque is full
     * @param c Object
     **/
    public void pushRight(Object c) throws Overflow
    {
        if(!isFull())
        {
            int right = (left+count)%deque.length; //the cyclic first element
            deque[right] = c;
            count++;
        }
        else throw new Overflow("Deque block implementation is full.");
    }

    /**
     * Fetches the left-most Object in the deque
     * @return the left-most object in the deque
     * @throws Underflow exception if the deque is empty
     **/
    public Object peekLeft() throws Underflow
    {
        if(!isEmpty())
        {
            return deque[left];
        }
        else throw new Underflow("Deque block implementation is empty.");
    }

    /**
     * Fetches the right-most Object in the deque
     * @return the left-most object in the deque
     * @throws Underflow exception if the deque is empty
     **/
    public Object peekRight() throws Underflow
    {
        if(!isEmpty())
        {
            int right = (left+count-1)%deque.length;
            return deque[right];
        }
        else throw new Underflow("Deque block implementation is empty.");
    }

    /**
     * Fetches the left-most Object in the deque and
     * removes the fetched Object from the deque
     * @return the left-most object in the deque
     * @throws Underflow exception if the deque is empty
     **/
    public Object popLeft() throws Underflow
    {
        if(!isEmpty())
        {
            Object o = deque[left];
            left++;
            int index = (left+deque.length)%deque.length;
            left = index;
            count--;
            return o;
        }
        else throw new Underflow("Deque block implementation is empty.");
    }

    /**
     * Fetches the right-most Object in the deque and
     * removes the fetched Object from the deque
     * @return the left-most object in the deque
     * @throws Underflow exception if the deque is empty
     **/
    public Object popRight() throws Underflow
    {
        if(!isEmpty())
        {
            int right = (left+count-1)%deque.length;
            Object o = deque[right];
            count--;
            return o;
        }
        else throw new Underflow("Deque block implementation is empty.");
    }
}
