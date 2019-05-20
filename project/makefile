PROJ_FILES = CITS2200Project.java MyCITS2200Project.java
TEST_FILES = CITS2200ProjectTester.java

project : $(PROJ_FILES) test
	javac $(PROJ_FILES);

test : $(TEST_FILES)
	javac $(TEST_FILES)

clear :
	rm -f *.class