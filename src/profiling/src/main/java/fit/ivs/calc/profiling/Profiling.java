package fit.ivs.profiling;

import fit.ivs.mathlibrary.MathLib;

import java.io.*;
import java.util.ArrayList;

public class Profiling {

    private static final MathLib mathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args) {
        System.out.println(" -- Test -- ");
        double result = mathLib.div(4, 2);
        System.out.println("Result: " + result);

        if(true) {
            return;
        }
        ArrayList<Double> Values = new ArrayList<>();
        //Nacitani            //TODO predelat aby se dalo nacitat pomoci java Profiling *.txt
        File Objective = new File("src/profiling/data.txt");
        if (!Objective.exists() || !Objective.canRead()) {
            System.out.println("Soubor nebyl nalezen.");
        }
        try (BufferedReader myReader = new BufferedReader(new FileReader(Objective))) {
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] parts = line.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        if (parts[j].length() > 0) {
                            try {
                                Values.add(Double.parseDouble(parts[j]));
                            } catch (Exception e) {
                                System.out.println("Neobsahuje cislo.");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//     for (int j = 0; j < Values.size(); j++) { //test vypisu dat
        //          System.out.println(Values.get(j));
        //    }
/*
        double counter = 0.0;
        double NumOfNum = Values.size();

        for (int i = 0; i < Values.size(); i++) {
            counter += Values.get(i);
        }


        double scale = MathLib.div(counter, NumOfNum);

        System.out.println(MathLib.pow(counter));
        System.out.println(MathLib.mul(Values.size(), MathLib.pow(scale)));

        double root = MathLib.mul(MathLib.div(1,MathLib.minus(1,Values.size())),MathLib.minus(MathLib.pow(counter) ,MathLib.mul(Values.size(), MathLib.pow(scale))));

        double amplitude = MathLib.sqrt(root);

        System.out.println("\n"+ amplitude);
    */
    }
}
