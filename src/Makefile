
.PHONY: all pack clean test doc run profile

DESTDIR = $(HOME)
SHELL = /bin/bash
INSFLAGS = -m 0755

#############################################################################
all: kalkulacka.jar

kalkulacka.jar: app/src/main/java/fit/ivs/calc/app/MainJFrame.java math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java
	mvn clean package -Dmaven.repo.local=../../repo/

test: math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java math-library/src/main
	mvn test -Dmaven.repo.local=../repo/

clean:
	mvn clean -Dmaven.repo.local=../../repo/

run:
	@if [ -f app/target/kalkulacka.jar ]; then \
	exec java -jar app/target/kalkulacka.jar; \
	else \
	  make; \
	  exec java -jar app/target/kalkulacka.jar; \
	fi