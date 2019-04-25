cp = -cp CITS2200.jar:.

QueueCyclic : QueueCyclic.java QueueCyclicTest.java
	javac $(cp) QueueCyclic.java QueueCyclicTest.java

QueueLinked : QueueLinked.java QueueLinkedTest.java CITS2200.jar
	javac $(cp) QueueLinked.java QueueLinkedTest.java

DequeCyclic : DequeCyclic.java DequeCyclicTest.java
	javac $(cp) DequeCyclic.java DequeCyclicTest.java

clear :
	rm -f *.class
