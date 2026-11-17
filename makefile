all: compile run clean

compile :
	javac -d bin homework3/hw3/*.java
run:
	java -cp bin homework3.hw3.Main
clean:
	rm bin/homework3/hw3/*.class
