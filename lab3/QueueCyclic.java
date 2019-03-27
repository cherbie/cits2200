import CITS2200.*;
import java.lang.Math; //use for mod arithmetic as negative messes calc up

class QueueCyclic implements CITS2200.Queue
{
    //FIELDS
    private Object[] queue; //Block implementation of queue
    private int count; //count of elements within block implementation of queue
    private int first; //index position of first element in queue
    public int len;

    public QueueCyclic(int size)
    {
        queue = new Object[size+1];
        count = 0;
        first = 0;
        len = size+1;
    }

    /**
     * Remove the item at the front of the queue
     * @throws Underflow
     * @return Object at the front of the queue
     **/
    public Object dequeue() throws Underflow
    {
        if(!isEmpty())
        {
            Object o = queue[first];
            queue[first] = null; //erase memory address
            first = (first+1)%queue.length;
            count--; //reduce number of elements in the queue
            return o;
        }
        else
        {
            throw new Underflow("Queue is empty.");
        }
    }

    /**
     * Insert an intem at the back of the queue
     * @parama a Object to be inserted in the queue
     **/
    public void enqueue(Object a) throws Overflow
    {
        if(isFull())
        {
            throw new Overflow("Block implementation is full.");
        }
        else
        {
            int last = Math.abs((first + count))%queue.length; //cyclic property of queue
            count++; //increment count of elements
            queue[last] = a;
        }
    }

    /**
     * Examine the item at the front of the queue
     * @throws Underflow
     * @return Object at the front of the queue
     **/
    public Object examine() throws Underflow
    {
        if(!isEmpty())
        {
            return queue[first];
        }
        else
        {
            throw new Underflow("Queue is empty.");
        }
    }

    /**
     * Test whether the queue is empty
     * @return true if empty, false otherwise
     **/
    public boolean isEmpty()
    {
        return count<=0;
    }

    /**
     * Test whether the queue has reached maximum capacity
     * @return true if full, false otherwise
     **/
    public boolean isFull()
    {
        return count >= (len-1);
    }
}
