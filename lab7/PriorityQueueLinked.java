import CITS2200.*;

public class PriorityQueueLinked<E> implements CITS2200.PriorityQueue<E>
{
    private Link<E> front;
    /**
     * Constructor for implementation of CITS2200.PriorityQueueLinked
     * Empty priority queue created.
     */
    public PriorityQueueLinked()
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
        if( isEmpty() || p > front.priority)
        {
            front = new Link<E>(a, p, front);
        }
        else
        {
            Link<E> window = front;
            while( window.next != null && window.next.priority >= p )
            {
                window = window.next;
            }
            window.next = new Link(a, p, window.next); //insert Link at the end
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
    public Iterator<E> iterator()
    {
        return new PQueueIterator<E>();
    }

    public class PQueueIterator<A> implements CITS2200.Iterator<A>
    {
        private Link<E> current;

        public PQueueIterator()
        {
            current = PriorityQueueLinked.this.front;
        }

        /**
         * Tests if there is a next item to return
         * @return true if and only if there is a next item 
         */
        public boolean hasNext()
        {
            return current != null;
        }

        /**
         * Returns the next element and movees the iterator to the next position
         * @return the next element in the collection
         * @throws OutOfBounds if there is no next element
         */
        public A next() throws OutOfBounds
        {
            if(!this.hasNext())
                throw new OutOfBounds("next: queue is empty.");
            A temp = (A) current.element;
            current = current.next;
            return temp;
        }
    }

    public class Link<E>
    {
        public E element;
        public int priority;
        public Link<E> next;

        /**
         * Constructor for Link including priorities.
         */
        public Link(E e, int p, Link<E> n)
        {
            this.element = e;
            this.priority = p;
            this.next = n;
        }
    }
}