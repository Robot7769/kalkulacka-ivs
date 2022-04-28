
.PHONY: all pack clean test doc run profile debian install

VER = 2.3

DESTINATION_DIR_UBUNTU = $(HOME)
SHELL = /bin/bash
INSFLAGS = -m 0755
ZIPNAME = xsafar26_xskrab12_xkocia19_xcafou01

#############################################################################
all: build

build: app/src/main/java/fit/ivs/calc/app/MainJFrame.java math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java profiling/src/main/java/fit/ivs/calc/profiling/Profiling.java
	mvn clean package -Dmaven.repo.local=../repo/

pack: clean debian doc
	mkdir ../../$(ZIPNAME) && cd ../../$(ZIPNAME) && mkdir doc install repo
	cd ../../$(ZIPNAME)/repo && git clone https://github.com/Robot7769/kalkulacka-ivs.git
	cp -r ../../doc ../../$(ZIPNAME)/doc
	cp -r ../../install ../../$(ZIPNAME)/install
	cd ../../ && zip -r $(ZIPNAME) $(ZIPNAME)
	rm -rf ../../$(ZIPNAME)

clean:
	mvn clean -Dmaven.repo.local=../repo/
	rm -rf ../repo/
	rm -rf ../../repo/
	rm -rf ../../install/
	rm -rf ../../doc/
	rm -rf ../../$(ZIPNAME).zip
	rm -rf ../../kalkulacka*.tar.gz
	rm -rf ../kalkulacka_*.buildinfo ../kalkulacka_*.changes

test: math-library/src/main/java/fit/ivs/calc/mathlibrary/MathLib.java math-library/src/main
	mvn test -Dmaven.repo.local=../repo/

doc:
	doxygen Doxyfile

run:
	  make;
	  exec java -jar app/target/kalkulacka.jar;

profile:
	  make;
	  exec java -jar profiling/target/stddev.jar;

install:
	mkdir -p $(DESTINATION_DIR_UBUNTU)/usr/bin
	install $(INSFLAGS) app/target/kalkulacka.jar $(DESTINATION_DIR_UBUNTU)/usr/bin/
	install $(INSFLAGS) install/kalkulacka $(DESTINATION_DIR_UBUNTU)/usr/bin/
	install $(INSFLAGS) install/kalkulacka.png $(DESTINATION_DIR_UBUNTU)/usr/share/pixmaps/
	install $(INSFLAGS) install/kalkulacka.desktop $(DESTINATION_DIR_UBUNTU)/usr/share/applications/

debian:
	tar cf ../../kalkulacka-$(VER).tar.gz app/src app/pom.xml pom.xml math-library/src math-library/pom.xml Makefile
	dh_make -e jan.robot7@gmail.com -n -i -p kalkulacka_$(VER) -c gpl3 -f ../../kalkulacka-$(VER).tar.gz -y
	cp dh_make/* debian
	dpkg-buildpackage -rfakeroot -uc -b
	mkdir -p ../../install
	mv ../kalkulacka_*.deb ../../install/
	rm -rf debian
