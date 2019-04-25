import CITS2200.*;

public class PQueueLinked<E> implements CITS2200.PriorityQueueLinked<E>
{
    private Link<E> front;
    /**
     * Constructor for implementation of CITS2200.PriorityQueueLinked
     * Empty priority queue created.
     */
    public PQueueLinked()
    {
        this.front = null;
    }

    /**
     * Tests whether the queue is empty.
     * @return true if the queue is empty, otherwise returns false.
     */
    public boolean isEmpty()
    {
        return front == null;
    }

    /**
     * Insert an item at the back into the queue with a given priority
     * @param a the item to insert
     * @param priority the priority of the elements ( > 0 )
     * @throws IllegalValue if the priority is not in a valid range
     */
    public void enqueue(E a, int p) throws IllegalValue
    {
        if(p < 0)
            throw new IllegalValue("enqueue: priority value is less than 0");
        else if( isEmpty() || p > front.priority)
        {
            front = new Link(a, p, front);
        }
        else
        {
            Link<E> window = front;
            while( window.next != null || window.next.priority >= p )
            {
                window = window.next;
            }
            window.next = new Link(a, p, null); //insert Link at the end
        }
    }

    /**
     * Examine the element at the front of the queue 
     * Front -- the element with the highest priority that has been in the queue the longest.
     * @return the first iteem
     * @throws Underflow if the queue is empty
     */
    public E examine() throws Underflow
    {
        if(isEmpty())
            throw new Underflow("examine: p-queue is empty.");
        return front.element;
    }

    /**
     * Remove the item at the front of the queue
     * Front -- the element with the highest priority that has been in the queue the longest.
     * @throws Underflow if the queue is empty
     */
    public E dequeue() throws Underflow
    {
        if(isEmpty())
            throw new Underflow("dequeue: p-queue is empty.");
        E temp = (E) front.element;
        front = front.next;
        return temp;
    }

    /**
     * Return a DAT.iterator to examine all the elements in the priority queue
     * @return an iterator pointing to before the first item
     */
    public Iterator iterator()
    {

    }
}