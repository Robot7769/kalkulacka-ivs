
.PHONY: all pack clean test doc run profile

DESTDIR = $(HOME)
SHELL = /bin/bash
INSFLAGS = -m 0755

#############################################################################
all: build

build: app/src/main/java/fit/ivs/calc/app/MainJFrame.java math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java profiling/src/main/java/fit/ivs/calc/profiling/Profiling.java
	mvn clean package -Dmaven.repo.local=../repo/

test: math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java math-library/src/main
	mvn test -Dmaven.repo.local=../repo/

clean:
	mvn clean -Dmaven.repo.local=../repo/
	rm -rf ../repo/

profile:
	  make;
	  exec java -jar profiling/target/stddev.jar;

run:
	  make;
	  exec java -jar app/target/kalkulacka.jar;
