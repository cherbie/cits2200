import java.util.Random;

public class ListBlockTest
{
    public static void main(String[] args)
    {
        int listSize = Integer.parseInt(args[0]);
        testListBlock(listSize);
        System.out.println("Test Complete: successful implementation.");
    }

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

    private static void testListBlock(int size)
    {
        //SET UP
        WindowBlock window = new WindowBlock();
        ListBlock<String> list = new ListBlock<String>(size);
        String[] values = new String[size];
        setValues(values,size);
        int count = 0; //number of elements in the list

        //TEST IF NEW LIST IS EMPTY
        boolean b = list.isEmpty();
        assert !b : "Error: new list is not initialised to empty";

        //TEST IF NEW LIST IS FULL
        b = list.isFull();
        assert !b : "Error: new list is not initialised as not full.";

        //TEST BEFORE FIRST WINDOW CHANGE
        list.beforeFirst(window);
        assert list.isBeforeFirst(window) : "Window is not in the before first position.";

        //TEST AFTER LAST WINDOW CHANGE
        list.afterLast(window);
        assert list.isAfterLast(window) : "Window is not in the after last position.";

        list.beforeFirst(window);

        //INSERT TWO ELEMENTS INTO TO THE LIST
        list.insertAfter(values[count], window);
        list.next(window);
        System.out.println("--insertAfter-- " + list.examine(window));
        list.previous(window);
        count++;
        list.insertAfter(values[count], window);
        list.next(window);
        System.out.println("--insertAfter-- " + list.examine(window));
        list.previous(window);
        count++;

        //EXAMINE TWO ELEMENTS IN THE LIST
        list.next(window); //window = 0
        String s = list.examine(window);
        assert s.equals(values[0]) : "Window '0' != values[0]";
        list.next(window); //window = 1
        s = list.examine(window);
        assert s.equals(values[1]) : "Window '1' != values[1]";
        System.out.println("Initial insertAfter() calls were successful. \n");

        //IMPLEMENT insertBefore()
        list.insertBefore(values[count], window);
        list.previous(window);
        System.out.println(list.examine(window));
        list.next(window);
        count++;
        list.insertBefore(values[count], window);
        list.previous(window);
        System.out.println(list.examine(window));
        list.next(window);
        count++;

        //PRINT TO STANDARD OUT FOR COMPARISON
        list.beforeFirst(window); //set window to the before first position
        for(int i = 0; i < count; i++)
        {
            list.next(window);
            System.out.println("--- " + values[i] + " --- " + list.examine(window));
        }

        //TEST replace() LIST FUNCTION
        s = list.replace("Replaced", window);
        System.out.println("--Replace call-- " + list.examine(window));
        assert !s.equals("Replaced") : "replacement successful.";

        //TEST Delete
        s = list.delete(window);
        System.out.println("-deleted- " + s + " - now - " + list.examine(window));
        assert !s.equals(list.examine(window)) : "Deleted element is not in window.";
        count--;
    }
}
