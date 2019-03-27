import java.util.Random;
import java.lang.Object;
import java.lang.Math;

class QueueCyclicTest
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

    private static void testCycleQueue(int size)
    {
        //SET UP
        QueueCyclic q = new QueueCyclic(size);
        String[] values = new String[size];
        setValues(values,size);

        //TEST IF NEW QUEUE IS EMPTY
        assert !q.isEmpty() : "Error: new queue is not initialised to empty";

        //ENQUEUE OBJECT TO THE BLOCK QUEUE
        q.enqueue(values[0]);
        System.out.println(values[0] + " == " + (String) q.examine());
        String s = (String) q.examine();
        assert s.equals(values[0]) : "Error: enqueue did not function";
        for(int i = 1; i < size; i++)
        {
            q.enqueue(values[i]);
            assert values[i].equals(q.examine()) : "Error: enqueue did not function.";
            //System.out.println("success: " + i);
        }
        System.out.println("Successfull enqueueed all Object[] elements to the queue.");

        //EXAMINE FIRST ELEMENT BEING DEQUEUED
        String str = "";
        str = (String) q.examine();
        System.out.println("top --> " + str);
        assert str == q.dequeue() : "Error: Dequeue unsuccessful.";
        assert str != q.dequeue() : "Error: Successive dequeue unsuccessful.";

        //EXAMINE SUCCESSIVE POPS
        boolean b = false;
        do {
            System.out.println("--> " + q.dequeue());
            b = q.isEmpty();
        } while (!b);
    }

    public static void main(String[] args)
    {
        if(args.length < 1)
            System.out.println("One argument required.");
        else
        {
          //  testCycleQueue(Integer.parseInt());
            testCycleQueue(10);
            System.out.println("Successful implementation of Cyclic Block Queue!");
        }
    }
}
