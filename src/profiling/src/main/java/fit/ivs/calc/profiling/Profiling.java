package fit.ivs.calc.profiling;

import fit.ivs.calc.mathlibrary.MathLib;

import java.io.*;
import java.util.ArrayList;

public class Profiling {

    private static final MathLib mathLib = new MathLib(); //Objekt matematické knihovny

    public static void main(String[] args) {
        ArrayList<Double> Values = new ArrayList<>();
        //Nacitani
        if(args.length == 0){
           System.out.println("Zadejte název souboru");
           return;
        }
        //System.out.println(args.length);
        if(args.length > 1){
            System.out.println("Zadáno příliš mnoho argumentů");
            return;
        }
        //System.out.println(args[0]);
        File Objective = new File(args[0]);
        if (!Objective.exists() || !Objective.canRead()) {
            System.out.println("Soubor nebyl nalezen");
            return;
        }
        //System.out.println("ahoj");
        try (BufferedReader myReader = new BufferedReader(new FileReader(Objective))) {
            String line;
            //System.out.println("ahoj1");
            while ((line = myReader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] parts = line.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        if (parts[j].length() > 0) {
                            try {
                                //System.out.println("ahoj2");
                                Values.add(Double.parseDouble(parts[j]));
                            } catch (Exception e) {
                                System.out.println("Neobsahuje číslo");
                                return;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            //System.out.println("ahoj3");
            throw new RuntimeException(e);
        }

//     for (int j = 0; j < Values.size(); j++) { //test vypisu dat
        //          System.out.println(Values.get(j));
        //    }
        if (Values.size() > 1){
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

            //System.out.println(" -- Test -- ");
            System.out.println(amplitude);
        }
    }
}
