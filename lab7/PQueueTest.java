

public class PQueueTest
{
    private static Object[] arr;
    private static int size;
    private static PriorityQueueLinked pq;
    private static int count;

    public static void main(String[] args)
    {
        if(Integer.parseInt(args[0]) < 1)
            return;
        
        size = Integer.parseInt(args[0]);
        arr = new Object[size];
        pq = new PriorityQueueLinked<String>();

        try
        {
            setArray();
        }
        catch(Exception e)
        {
            print(e.getMessage());
        }
        testPQueue();
    }

    private static void setArray() throws Exception
    {
        if(size < 1)
            throw new Exception("size of array is less than 1.");
        for(int i = 0; i < size; i++)
        {
            arr[i] = String.valueOf((int) (Math.random()*100));
        }
    }
    private static void testPQueue()
    {
        count = 0;

        //TEST isEmpty
        if(pq.isEmpty())
            print("empty. --> success");
        assert pq.isEmpty() == true : "isEmpty is not working.";

        //TEST enqueue
        pq.enqueue(arr[count], 1);
        print("count = " + count + "\telement = " + pq.examine()); //test examine()
        count++;

        pq.enqueue(arr[count], 2);
        print("count = " + count + "\telement = " + pq.examine()); //test examine()
        count++;

        //TEST dequeue
        String element;

        element = (String) pq.dequeue();
        count--;
        print("count = " + count + "\telement = " + element + "\tnext element = " + pq.examine());

        element = (String) pq.dequeue();
        count--;
        print("count = " + count + "\telement = " + element + "\tnext element = null");

        //TEST iterator
        int p = 0;
        while(!isFull())
        {
            print("" + p%5 + " count = " + count + " \tvalue = " + arr[count]);
            pq.enqueue(arr[count++], (p++)%5);
        }

        count = 0;
        CITS2200.Iterator<String> it = pq.iterator();
        while(it.hasNext())
        {
            print(count++ + " --> " + it.next());
        }
        print("Complete");
    }

    private static boolean isEmpty()
    {
        return count == 0;
    }

    private static boolean isFull()
    {
        return count >= size;
    }

    private static void print(String s)
    {
        System.out.println(s);
    }
}