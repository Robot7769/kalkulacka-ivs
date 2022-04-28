# kalkulacka-ivs
Kalkulačka vytvořená pro projekt do předmětu IVS na Fakultě informačních technologií Vysokého učení technického v Brně.

<p align="center">
    <img width=30% src="https://github.com/Robot7769/kalkulacka-ivs/blob/main/screenshot.png?raw=true" alt="Světlý mód kalkulačky">
    <img width=30% src="https://github.com/Robot7769/kalkulacka-ivs/blob/main/screenshot2.png?raw=true" alt="Tmavý mód kalkulačky">
</p>



Prostředí
---------
- Ubuntu 64bit

Systémové požadavky
-------------------
- Připojení k internetu
- Operační systém Ubuntu 64 bit
- Minimálně 260 kB volného místa na disku
- Balíčky libcanberra-gtk-module a libcanberra-gtk3-module (tyto balíčky případně instalátor stáhne automaticky)
- Java JDK verze 11+

  ( TIP: přepnout verzi Javy lze skrz příkaz:
  `$ sudo update-alternatives --config java` )

Návod
-----
https://github.com/Robot7769/kalkulacka-ivs/blob/main/dokumentace.pdf

Přeložení zdrojových souborů na debian balíček
----------------------------------------------
Ve složce kalkulacka-ivs/src spustit příkaz

`make debian`

Profiling
---------

- Překlad pomocí `make profile` (výsledný stddev.jar bude ve složce src/profiling/target)
- Spuštení ve složce target pomocí `java -jar stddev.jar data.txt`

Autoři
------
Kalkulačka.jar
- Vítězslav Šafář (xsafar26)
- Jan Škrabal     (xskrab12)
- Richard Kocián  (xkocia19)
- Petr Cafourek   (xcafou01)

Licence
-------
GNU GPL verze 3

