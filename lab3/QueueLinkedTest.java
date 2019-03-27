import java.util.Random;
import java.lang.Object;
import java.lang.Math;

public class QueueLinkedTest
{
    /**
     * Randomly sets values within string[]
     **/
    private static void setValues(String[] s, int size)
    {
        Random r = new Random(19);
        for(int i = 0; i < size; i++)
        {
            s[i] = String.valueOf(Math.abs(r.nextInt())%100);
        }
    }

    private static void testQueue(int size)
    {
        //SET UP
        QueueLinked q = new QueueLinked();
        String[] values = new String[size];
        setValues(values,size);

        int count = 0; //count number of elements added and removed from the queue
        //TEST IF NEW QUEUE IS EMPTY
        assert !q.isEmpty() : "Error: new queue is not initialised to empty";

        //ENQUEUE OBJECT TO THE BLOCK QUEUE
        q.enqueue(values[0]);
        count++;
        System.out.println(values[0] + " == " + (String) q.examine());
        String s = (String) q.examine();
        assert s.equals(values[0]) : "Error: enqueue did not function";
        for(int i = 1; i < size; i++)
        {
            q.enqueue(values[i]);
            count++;
            assert values[i].equals(q.examine()) : "Error: enqueue did not function.";
            //System.out.println("success: " + i);
        }
        System.out.println("Successfull enqueueed all Object[] elements to the queue.");

        //EXAMINE FIRST ELEMENT BEING DEQUEUED
        String str = (String) q.examine();
        System.out.println("top --> (" + values[size-1] + ") " + str);
        str = (String) q.dequeue();
        count--;
        assert str.equals(values[size-1]) : "Error with first pop.";
        System.out.println("First element popped well.");

        //EXAMINE SUCCESSIVE POPS
        boolean b = false;
        do {
            System.out.println("--> " + q.dequeue());
            count--;
            b = q.isEmpty();
        } while (!b);
        System.out.println("Successive pops good.");
        System.out.println("Net enqueue's & deques: " + count);
    }

    public static void main(String[] args)
    {
        int array_size = Integer.parseInt(args[0]);
        testQueue(array_size);
        System.out.println("Successful test!");
    }
}
