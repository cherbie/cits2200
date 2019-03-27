import java.util.Random;
import java.lang.Object;
import java.lang.Math;

public class DequeCyclicTest
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
        DequeCyclic q = new DequeCyclic(size);
        String[] values = new String[size];
        setValues(values,size);
        int count = 0;

        //TEST IF NEW QUEUE IS EMPTY
        boolean b = q.isEmpty();
        assert !b: "Error: new queue is not initialised to empty";

        //ENQUEUE OBJECT TO THE RIGHT OF THE DEQUE OBJECT
        q.pushRight(values[count]); //count == 0
        System.out.println(values[0] + " == " + (String) q.peekRight());
        String s = (String) q.peekRight();
        assert s.equals(values[0]) : "Error: enqueue did not function";
        count++;
        for(count = 1; count < (size/2); count++)
        {
            System.out.println("count = " + count);
            q.pushRight(values[count]);
            s = (String) q.peekRight();
            System.out.println("right push --> "+s);
            assert values[count].equals(s) : "Error: pushRight()/peekRight() did not function.";
            //System.out.println("success: " + i);
        }
        System.out.println("Successfull enqueueed all Object[] elements to the deque.");

        for(;count<size;count++)
        {
            System.out.println("count = " + count);
            q.pushLeft(values[count]);
            s = (String) q.peekLeft();
            System.out.println("left push --> " + s);
            assert values[count].equals(s) : "Error: pushLeft()/peekLeft() did not function";
        }

        b = q.isFull();
        assert b : "Block dequed is not full";

        //EXAMINE LEFT ELEMENT BEING DEQUEUED
        s = (String) q.peekLeft();
        System.out.println("left peek --> (" + values[count-1] + ") " + s);
        s = (String) q.popLeft();
        assert s.equals(values[count]) : "Error with first pop.";
        count--;
        System.out.println("First element popped well.");

        while(count > (size/2))
        {
            System.out.println("pop left --> " + q.popLeft());
            count--;
        }

        //EXAMINE SUCCESSIVE right POPS
        b = false;
        do {
            System.out.println("pop right --> " + q.popRight());
            count--;
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
            testCycleQueue(Integer.parseInt(args[0]));
            System.out.println("Successful implementation of Cyclic Block Deque!");
        }
    }
}
