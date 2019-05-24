#####################################
Major Decisions:
    - ArrayList has a better access complexity than LinkedList
    - ArrayList prefered over array due to expansion opportunities
    - Arraylist containing linkedlist was not going to be space efficient due to sparse key - value pairs
        - HASHMAP containing linkedlists was therefore implemented
    

####################################
PROJECT STATUS:
    - | DONE | ADD VERTICES TO ArrayList LOOKUP TABLE
    - | DONE | ADD EDGE TO LINKEDLIST
    - | DONE | WRITE "public void addEdge(String, String)" method
    - | DONE | COMPLETED BFS ALGORITHM AND FOUND SHORTEST PATH.
    - | | WRITE STRONGLY CONNECTED COMPONENTS ALGORITHM

####################################
QUESTIONS:
    - [ans = YES] is a BFS garuanteed to have a shortest path outcome for each vertex?

####################################
SUGGESTIONS:
    - USE ITERATOR FOR LINKEDLIST INSTEAD OF REMOVING AND ENQUEING AGAIN
    - USE ARRAYLIST INSTEAD OF MAP FOR ITERATOR FUNCITONIONALITY?
    - USE ARRAYLIST TO ALLOW FOR BETTER TRANSPOSITION?