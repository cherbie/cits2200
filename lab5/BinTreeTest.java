import CITS2200.*;
import java.lang.Math;
import java.lang.Integer;
import java.util.Random;

public class BinTreeTest
{
    private static Object[] trees;

    public static void main(String[] args)
    {
        int num = Integer.parseInt(args[0]);
        int size = ((int) Math.pow(2.0, (double) num)*2)-1; //((2^n)*2)-1

        trees = new Object[size];
        createBinTree(num, size);
        testBinTree(num);
        print("testing complete");
    }

    public static void createBinTree(int n, int size)
    {
        int num_leaves = (int) Math.pow(2.0, (double) n);
        Random r = new Random(25);
        for(int i = num_leaves; num_leaves > 0; num_leaves--) //creating the leaves
        {
            print("writing leaves: " + num_leaves + " | " + (size-1));
            trees[--size] = new BinTree<Integer>(Integer.valueOf(r.nextInt()), new BinTree<Integer>(), new BinTree<Integer>()); 
        }
        //size++;
        while(size>0)
        {
            print("writing nodes: " + (size-1));
            int right = size*2; //right branch
            print("Right size = " + right);
            int left = size*2-1; //left branch
            print("Left size = " + left);
            trees[--size] = new BinTree<Integer>(Integer.valueOf(r.nextInt()), (BinTree<Integer>)trees[left], (BinTree<Integer>)trees[right]);
            n--;
        }
    }

    private static void testBinTree(int levels)
    {
        BinTree<Integer> tree = (BinTree<Integer>)trees[0];
        Integer i;

        //GET ROOT NODE ITEM
        i = tree.getItem();
        print("root node item = " + i + " & the array item = " + ((BinTree<Integer>)trees[0]).getItem());
        assert i.equals(((BinTree<Integer>)trees[0]).getItem()) : "get item: root node not equal";

        //GET NODE ITEM SPANNING THE ENTIRE TREE
        getLevelNodeVal(levels, tree);

        //GET ITERATOR AND PRINT ALL VALUES
        Iterator<Integer> it = tree.iterator();
        transverseTree(it);

        System.out.println(tree.toString());
    }

    private static void transverseTree(Iterator<Integer> t)
    {
        while(t.hasNext())
        {
            print("iterator --> " + t.next());
        }
        print("iterator complete.");
    }
    private static void getLevelNodeVal(int l, BinTree<Integer> b)
    {
        Integer i;
        i = b.getLeft().getItem();   
        print("level 1 left node item = " + i + " & the array item = " + ((BinTree<Integer>)trees[(int)(Math.pow(2.0, 1.0))-1]).getItem());
        assert i.equals(((BinTree<Integer>)trees[(int)(Math.pow(2.0, 1.0))-1]).getItem()) : "get item: root node not equal";

        i = b.getRight().getItem();   
        print("level 1 left node item = " + i + " & the array item = " + ((BinTree<Integer>)trees[(int)(Math.pow(2.0, 1.0))]).getItem());
        assert i.equals(((BinTree<Integer>)trees[(int)(Math.pow(2.0, 1.0))]).getItem()) : "get item: root node not equal";
    }
        
    private static void print(String s)
    {
        System.out.println("print: " + s);
    }
}
