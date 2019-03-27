import CITS2200.*;

public class QueueLinked implements CITS2200.Queue
{
    //THE SUCCESSOR OF THE LAST ITEM IN THE QUEUE REFERENCES THE FIRST ITEM IN THE QUEUE
    private Link last;

    public QueueLinked()
    {
        last = new Link(null, null);
    }

    /**
     * Remove the item at the front of the queue
     * @throws Underflow
     * @return Object at the front of the queue
     **/
    public Object dequeue() throws Underflow
    {
        if(last==last.successor)
        {
            //ONE ELEMENT IN QUEUE
            Object o = last.item;
            last.item = null;
            last.successor = null;
            return o;
        }
        Object o = last.item; //to be returned
        Link first = last.successor;
        Link current = first.successor; //the first element behind the queue of first
        while(current.successor!=last)
        {
            current = current.successor; //go to the next youngest element in the queue
        }
        //current Link object is now the last
        //last.item = null; //erase memory address
        //last.successor = null;
        last = current;
        last.successor = first; //points successor to the first position.
        return o;
    }

    /**
     * Insert an intem at the back of the queue
     * @parama a Object to be inserted in the queue
     **/
    public void enqueue(Object a)
    {
        if(isEmpty())
        {
            //create first Link
            last = new Link(a, null);
            last.successor = last;
        }
        else
        {
            Link first = last.successor; //store pointer to first
            Link n = new Link(a, null); //create new queued element
            last.successor = n; //point last element to the new element
            last = n; //set last to the new element
            last.successor = first; //point the successor of last to the first element
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
            return last.item;
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
        return last.successor == null;
    }
}
