
.PHONY: all pack clean test doc run profile

VER = 2.2

DESTDIRHONZA = $(HOME)
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

tar:
	tar cf ../../kalkulacka-$(VER).tar.gz app/src app/pom.xml pom.xml math-library/src math-library/pom.xml Makefile

install:
	mkdir -p $(DESTDIRHONZA)/usr/bin
	install $(INSFLAGS) app/target/kalkulacka.jar $(DESTDIRHONZA)/usr/bin/
	install $(INSFLAGS) install/kalkulacka $(DESTDIRHONZA)/usr/bin/
	install $(INSFLAGS) install/kalkulacka.png $(DESTDIRHONZA)/usr/share/pixmaps/
	install $(INSFLAGS) install/kalkulacka.desktop $(DESTDIRHONZA)/usr/share/applications/

debian: tar
	rm -rf debian
	dh_make -e jan.robot7@gmail.com -n -i -p kalkulacka_$(VER) -c gpl3 -f ../../kalkulacka-$(VER).tar.gz -y
	cp dh_make/* debian
	dpkg-buildpackage -rfakeroot -uc -b
