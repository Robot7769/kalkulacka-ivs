package fit.ivs.calc.profiling;

import fit.ivs.calc.mathlibrary.MathLib;

import java.io.*;
import java.util.ArrayList;

public class Profiling {

    private static final MathLib mathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args) {
        ArrayList<Double> Values = new ArrayList<>();
        //Nacitani            //TODO predelat aby se dalo nacitat pomoci java Profiling *.txt
        File Objective = new File("data.txt");
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

        double counter = 0.0;
        double counterPower = 0.0;
        double NumOfNum = Values.size();

        for (int i = 0; i < Values.size(); i++) {
            counter = mathLib.plus(counter, Values.get(i));
            counterPower = mathLib.plus(counterPower, mathLib.pow(Values.get(i)));
        }

        double scale = mathLib.div(counter, NumOfNum);

        double numerator = mathLib.minus(counterPower, mathLib.mul(NumOfNum,mathLib.pow(scale)));
        double denominator = mathLib.minus(NumOfNum,1);

        //System.out.println(mathLib.pow(counter));
        //System.out.println(mathLib.mul(Values.size(), mathLib.pow(scale)));

        double root = mathLib.div(numerator,denominator);
        double amplitude = mathLib.sqrt(root);

        System.out.println(" -- Test -- ");
        System.out.println("Result: "+ amplitude);
    }
}
