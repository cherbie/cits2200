import CITS2200.*;
import java.util.*;

public class QueueIterator<E> implements CITS2200.Iterator<E>
{
    private Object[] list;
    private position;

    /**
     * Constructor for the iterator implementing a Linked List ADT
    **/
    public QueueIterator(LinkedList l)
    {
        list = l.toArray();
        position = -1;
    }

    /**
     * Tests if there is a next item to return
     * @return true if there is another element and false otherwise
    **/
    public boolean hasNext()
    {
        return position >= (list.length-1);
    }

    /**
     * Moves the iterator to the next element
     * @return the next element
     * @throws OutOfBounds if there is no next element
    **/
    public E next() throws OutOfBounds
    {
        if(!hasNext())
            throw new OutOfBounds("next: no next element.");
        else
        {
            position++;
            return (E) list[position];
        }
    }
}
