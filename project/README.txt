#####################################
Major Decisions:
    - ArrayList has a better access complexity than LinkedList
    - ArrayList prefered over array due to expansion opportunities
    - Arraylist containing linkedlist was not going to be space efficient due to sparse key - value pairs
        - HASHMAP containing linkedlists was therefore implemented
    - Dynamic Programming technique used to solve Himaltonian paths.
        - Initially "does a hamiltonian path exist?" is determined
        - then cycle through each node to find the 'parent' node in the hamiltonian path starting at the final vertex
        - O(2n * 2^n) = O(n * 2^n)
    

####################################
PROJECT STATUS:
    - | DONE | ADD VERTICES TO ArrayList LOOKUP TABLE
    - | DONE | ADD EDGE TO LINKEDLIST
    - | DONE | WRITE "public void addEdge(String, String)" method
    - | DONE | COMPLETED BFS ALGORITHM AND FOUND SHORTEST PATH.
    - | DONE | WRITE STRONGLY CONNECTED COMPONENTS ALGORITHM
    - | DONE | Write Himaltonian Path algorithm
    - | DONE | Test Himaltonian Path algorithim
    - | DONE | FIND CENTER NODES OF GRAPH
    - | DONE | TEST ALGORITHM TO FIND CENTERS
    - | | WRITE REPORT SECTION: "SHORTEST PATH"
    - | | WRITE REPORT SECTION: "HAMILTONIAN PATH"
    - | | WRITE REPORT SECTION: "STRONGLY CONNECTED COMPONENTS"
    - | | WRITE REPORT SECTION: "CENTERS & ECCENTRICITY"
    - | | REMOVE NULL POINTERS WITHIN RETURNED ARRAYS
    - | | RETURN EMPTY ARRAY INSTEAD OF NULL

####################################
QUESTIONS:
    - [ans = YES] is a BFS garuanteed to have a shortest path outcome for each vertex?

####################################
SUGGESTIONS:
    - USE ITERATOR FOR LINKEDLIST INSTEAD OF REMOVING AND ENQUEING AGAIN
    - USE ARRAYLIST INSTEAD OF MAP FOR ITERATOR FUNCITONIONALITY?
    - USE ARRAYLIST TO ALLOW FOR BETTER TRANSPOSITION?
    - NEED TO IMPLEMENT QUEUE INSTEAD TO FIND SCC. ORDERING INCORRECT.
    - CLEAN CLASSES AND LOOK FOR STORAGE EFFICIENCIES
    - INSTEAD OF DOES HAM PATHEXIST() METHOD JUST RETURN PATH OR NEW STRING[1]; //IMPROVED PERFORMANCE