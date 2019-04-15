import java.util.Random;
import CITS2200.*;

public class ListLinkedTest
{
		public static void main(String[] args)
		{
			if(args.length != 1)
			{
				System.out.println("1 integer argument required\n");
				return;
			}
			int listSize = Integer.parseInt(args[0]);
			testListLinked(listSize);
			System.out.println("List Construction Complete!\n");
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

		//prints to system.out
		private static void print(String message)
		{
				System.out.println("print: " + message);
		}

    private static void testListLinked(int size)
    {
        //SET UP
        WindowLinked window = new WindowLinked();
        ListLinkedBefore list = new ListLinkedBefore();
        String[] values = new String[size];
        setValues(values,size);
        int count = 0; //number of elements in the list

        //TEST IF NEW LIST IS EMPTY
        boolean b = list.isEmpty();
        assert !b : "Error: new list is not initialised to empty";

        //TEST BEFORE FIRST WINDOW CHANGE
        list.beforeFirst(window);
        assert list.isBeforeFirst(window) : "Window is not in the before first position.";
				print("Set to before first successful.");

        //TEST AFTER LAST WINDOW CHANGE
        list.afterLast(window);
        assert list.isAfterLast(window) : "Window is not in the after last position.";
				print("Set to after last successful.");


        list.beforeFirst(window); ///sets window to before the first element of the list

        //INSERT TWO ELEMENTS INTO TO THE LIST

        list.insertAfter(values[count], window);
        list.next(window);
        print("--insertAfter-- " + list.examine(window));
        list.previous(window);
        count++;
				print("First insertAfter() call successful.");


        list.insertAfter(values[count], window);
        list.next(window);
        print("--insertAfter-- " + list.examine(window));
        list.next(window);
        print("--insertAfter-- " + list.examine(window));
        list.beforeFirst(window);
        count++;
				print("Second insertAfter() call successful.");


        //EXAMINE TWO ELEMENTS IN THE LIST
        list.next(window); //window = 0
        String s = (String) list.examine(window);
        assert s.equals(values[0]) : "Window '0' != values[0]";
        list.next(window); //window = 1
        s = (String) list.examine(window);
        assert s.equals(values[1]) : "Window '1' != values[1]";
        print("Initial insertAfter() calls were successful. " + s);

				//PRINT TO STANDARD OUT FOR COMPARISON
				list.beforeFirst(window); //set window to the before first position
				for(int i = 0; i < count; i++)
				{
						list.next(window);
						print("--> " + list.examine(window));
				}

        //IMPLEMENT insertBefore()
        list.insertBefore(values[count], window);
				print("previous1 --> " + list.examine(window));
        list.previous(window);
        print("insertBefore examined -- " + list.examine(window));
        list.next(window);
        count++;

				//PRINT TO STANDARD OUT FOR COMPARISON
				list.beforeFirst(window); //set window to the before first position
				for(int i = 0; i < count; i++)
				{
						list.next(window);
						print("--> " + list.examine(window));
				}

        list.insertBefore(values[count], window);
				print("previous2 --> " + list.examine(window));
        list.previous(window);
        print("insertBefore examined -- " + list.examine(window));
        list.next(window);
        count++;

				//PRINT TO STANDARD OUT FOR COMPARISON
				list.beforeFirst(window); //set window to the before first position
				for(int i = 0; i < count; i++)
				{
						list.next(window);
						print("--> " + list.examine(window));
				}

        //PRINT TO STANDARD OUT FOR COMPARISON
        list.beforeFirst(window); //set window to the before first position
        for(int i = 0; i < count; i++)
        {
            list.next(window);
            print("--- " + values[i] + " --- " + list.examine(window));
        }

        //TEST replace() LIST FUNCTION
        s = (String) list.replace("Replaced", window);
        print("--Replace call-- " + list.examine(window));
        assert !s.equals("Replaced") : "replacement successful.";

        //TEST Delete
        s = (String) list.delete(window);
        print("delete performed");
        try
        {
          print("-deleted- " + s + " - now - " + list.examine(window));
        }
        catch(OutOfBounds e)
        {
          print("OutOfBoundsCatch: " + e.getMessage());
        }
        //assert !s.equals(list.examine(window)) : "Deleted element is not in window.";
        count--;
    }
}
