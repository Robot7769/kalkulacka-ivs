package profiling;

import main.java.library.MathLib; //Matematická knihovna

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Profiling {

    private final main.java.library.MathLib MathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args) {
        ArrayList<Double> N = new ArrayList<>();
        //Nacitani            //TODO predelat aby se dalo nacitat pomoci java Profiling *.txt
            File Objective = new File("src/profiling/data.txt");
            if(!Objective.exists() || !Objective.canRead()){
                System.out.println("Soubor nebyl nalezen.");
            }
        try (BufferedReader myReader = new BufferedReader(new FileReader(Objective))){
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.length() > 0){
                   String[] parts = line.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        try {
                            N.add(Double.parseDouble(parts[j]));
                        }catch (Exception e){
                            System.out.println("Neobsahuje cislo.");
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int j = 0; j < N.size(); j++) {
            System.out.println(N.get(j));
        }

        /* dodelat prevod z nacitani do rovnice
        int n = numbers.length;
        double counter = 0.0;

        for (int i = 0; i < n; i++) {
            counter += numbers[i];
        }

        double scale = MathLib.div(counter, n);

        double root = MathLib.mul(MathLib.div(1,1-n),MathLib.pow(counter) - MathLib.mul(n, MathLib.pow(scale)));

        double amplitude = MathLib.sqrt(root);

        System.out.println(amplitude);


    */
    }
}
