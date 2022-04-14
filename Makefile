
.PHONY: all pack clean test doc run profile

DESTDIR = $(HOME)
SHELL = /bin/bash
INSFLAGS = -m 0755

vpath %.jar target #složka ve které se vytvoří classky spustitelné .jar soubory

#############################################################################
all: kalkulacka-2.0.jar test.jar

kalkulacka-2.0.jar: src/main/java/graphicdesign/MainJFrame.java src/main/java/library/MathLib.java
	mvn clean package -Dmaven.repo.local=../repo/

clean:
	mvn clean -Dmaven.repo.local=../repo/

run: kalkulacka-2.0.jar
	exec java -jar ./target/kalkulacka-2.0.jar

