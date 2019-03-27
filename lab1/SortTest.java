/** 
 * Written by Gareth Lee, 2004.
 * Revised by Du Huynh, Feb 2005.
 * Revisied by Tim French, 2008
 */

import java.util.Random;
import CITS2200.Sort;

public class SortTest
{
   /** Prints usage and terminates program */
   public static void usage() {
      System.out.println("Usage:");
      System.out.println("e.g. For testing a given array:");
      System.out.println("  java SortTest 9 7 6 5 3");
      System.out.println("e.g. For testing a random array, specify the size:");
      System.out.println("  java SortTest 1000");
      System.exit(1);
   }

   /** Prints error message and terminates program */
   public static void pperr(String msg) {
      System.out.println("Error:");
      System.out.println(msg);
      System.exit(1);
   }


   /** Method for testing a given array of long integers for debugging.
    *  @arg a the array of longs to be sorted.
    */
   public static void testMain(long[] a)
   {
      // print contents of array a before sorting
      	for (int i = 0; i < a.length; i++)
         	System.out.print(a[i] + " ");
      	System.out.println();
		
	Sort s = new Sorter();	
	long[] a1 =(long[]) a.clone();
	long[] a2 =(long[]) a.clone();
	long[] a3 =(long[]) a.clone();
	  
      	s.insertionSort(a1);
      	s.mergeSort(a2);
	s.quickSort(a3);
      
      	// print contents of array a after sorting
	System.out.println("InsertionSort:");
      	for (int i = 0; i < a1.length; i++)
         	System.out.print(a1[i] + " ");
      	System.out.println();
	// print contents of array a after sorting
	System.out.println("MergeSort:");
      	for (int i = 0; i < a2.length; i++)
         	System.out.print(a2[i] + " ");
      	System.out.println();
      	// print contents of array a after sorting
	System.out.println("QuickSort:");
      	for (int i = 0; i < a3.length; i++)
       		System.out.print(a3[i] + " ");
      	System.out.println();
   }

   /** Method for testing an array of randomly generated
    *  long integers.
    *  @arg size the length of the array to be generated.
    */
   public static void runMain(int size)
   {
      	if (size <= 0)
          	pperr("runMain: argument must be a positive integer.");
		 
      	long a[] = new long[size];
      	Random rand = new Random();
      	for (int i = 0; i < size; i++){
		 a[i] = rand.nextLong();
	}	 
      	Sort s = new Sorter();
	s.reset();
	  
	//INSERTIONSORT
	long[] a1 =(long[]) a.clone();
      	s.insertionSort(a1);
       	for (int i = 1; i < size; i++)
         	if (a1[i - 1] > a1[i]){
            		System.out.println("Insertion Sort Error");
			return;
		}
	System.out.println("Insertionsort used "+s.getCount()+" assignments to sort "+size+ " longs");

		  
	//MERGESORT
	s.reset();
	a1 =(long[]) a.clone();
      	s.mergeSort(a1);
       	for (int i = 1; i < size; i++)
         	if (a1[i - 1] > a1[i]){
            	System.out.println("Merge Sort Error");
		return;
		}
	System.out.println("Mergesort used "+s.getCount()+" assignments to sort "+size+ " longs");
	  
	//QUICKSORT
	s.reset();
	a1 =(long[]) a.clone();
      	s.quickSort(a1);
       	for (int i = 1; i < size; i++)
         	if (a1[i - 1] > a1[i]){
            		System.out.println("Sort Error");
			return;
		}
	System.out.println("Quicksort used "+s.getCount()+" assignments to sort "+size+ " longs");
   }


   
   public static void main(String[] args)
   {
      	if (args.length ==0)
         	usage();
	else if (args.length == 1)
		 runMain(Integer.parseInt(args[0]));
      	else{
        	 /* convert arguments to longs */
         	long a[] = new long[args.length];
         	for (int i = 0; i < args.length; i++)
            		a[i] = Long.parseLong(args[i]);
		testMain(a);
     	}
   }
}
