cp = -cp CITS2200.jar:.

ListBlock : ListBlock.java WindowBlock.java ListBlockTest.java
	javac $(cp) ListBlock.java WindowBlock.java ListBlockTest.java

ListLinked : ListLinked.java CITS2200.jar ListLinkedTest.java
	javac $(cp) ListLinked.java ListLinkedTest.java

ListLinkedBefore : ListLinkedBefore.java ListLinkedTest.java CITS2200.jar
	javac $(cp) ListLinkedBefore.java ListLinkedTest.java

clear : 
	rm -f *.class

